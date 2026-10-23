package main.java;

import java.time.*;
import java.util.List;

public class BookingService {
    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
    }

    /** Book a room for a user. Returns the new booking. */

    public Booking book(String userId, String roomId, TimeSlot slot) {

        new RoomMustExist(rooms).check(userId, roomId, slot);
        new BookingInFuture(clock).check(userId, roomId, slot);
        new bookingLongerThanThreeHours(MAX_SLOT).check(userId, roomId, slot);
        new overlappingRooms(bookings).check(userId, roomId, slot);
        new overlappingUsersRooms(bookings).check(userId, roomId, slot);

        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
        
    }

    interface BookingRule {
        void check(String userId, String roomId, TimeSlot slot);   // throws if the rule is broken
    }

    // R1
    class RoomMustExist implements BookingRule {
        private final IRoomRepository rooms;

        RoomMustExist(IRoomRepository rooms) { 
            this.rooms = rooms; 
        }

        public void check(String userId, String roomId, TimeSlot slot) {
            if (rooms.findById(roomId) == null) {
                throw new IllegalArgumentException("no such room: " + roomId);
            }
        }
    }

    // R2
    class BookingInFuture implements BookingRule {

        private final Clock clock;

            BookingInFuture(Clock clock) {
                this.clock = clock;
            }

        public void check(String userId, String roomId, TimeSlot slot) {
            if(!slot.getStart().isAfter(LocalDateTime.now(clock))) {
                throw new IllegalArgumentException("Booking must be in the Future and not the past");
            }
        }

    }

    //R3
    class bookingLongerThanThreeHours implements BookingRule {

        private final Duration maxSlot;

        bookingLongerThanThreeHours(Duration maxSlot) {
            this.maxSlot = maxSlot;
        }

        public void check(String userId, String roomId, TimeSlot slot) {
            if (Duration.between(slot.getStart(), slot.getEnd()).compareTo(maxSlot) > 0) {
                throw new IllegalArgumentException("Booking must not be longer than three hours");
            }
        }

    }

    //R4
    class overlappingRooms implements BookingRule {

        private final IBookingRepository bookings;


        overlappingRooms(IBookingRepository bookings) {
            this.bookings = bookings;
        }

         public void check(String userId, String roomId, TimeSlot slot) {
            for (Booking booking : bookings.findByRoom(roomId)) {
                if (slot.getStart().isBefore(booking.getTimeSlot().getEnd()) &&
                    slot.getEnd().isAfter(booking.getTimeSlot().getStart())) {
                    throw new IllegalStateException("Room is already booked");
                }
            }
         }
    }

    //R5
    class overlappingUsersRooms implements BookingRule {

        private final IBookingRepository bookings;

        overlappingUsersRooms(IBookingRepository bookings) {
            this.bookings = bookings;
        }
        public void check(String userId, String roomId, TimeSlot slot) {
            for (Booking booking : bookings.findByUser(userId)) {
                if (slot.getStart().isBefore(booking.getTimeSlot().getEnd()) &&
                    slot.getEnd().isAfter(booking.getTimeSlot().getStart())) {
                    throw new IllegalStateException("User already has a booking");
                }
            }
        }
    }


    //R6
    /** Cancel a user's booking. */
    public void cancel(String userId, String roomId, TimeSlot slot) {
        for (Booking booking : bookings.findByUser(userId)) {
            if (booking.getRoomId().equals(roomId) &&
                booking.getTimeSlot().equals(slot)) {
                bookings.delete(booking);
                return;
            }
        }
        
        throw new IllegalArgumentException("Booking doesn't exist");
    }

    //R7
    /** A user's bookings, earliest first. */
    public List<Booking> bookingsFor(String userId) {
        List<Booking> userBookings = new java.util.ArrayList<>(bookings.findByUser(userId));
        userBookings.sort(java.util.Comparator.comparing(booking -> booking.getTimeSlot().getStart()));
        return userBookings;
    } 

        // D1
    class holdThreeBookingsNotEnded implements BookingRule {

        private final IBookingRepository bookings;

        holdThreeBookingsNotEnded(IBookingRepository bookings) {
            this.bookings = bookings;
        }


        public void check(String userId, String roomId, TimeSlot slot) {
            int count = 0;

            for(Booking booking : bookings.findByUser(userId)) {
                if(booking.getTimeSlot().getEnd().isAfter(LocalDateTime.now())) {
                    count ++;
                }
            }
            if (count >= 3) {
                throw new IllegalStateException("Too many Rooms Booked!");
            }
       }
   }

    //D2
    class bookingforteendaysafterclockdate implements BookingRule {
        public void check(String userId, String roomId, TimeSlot slot) {
            if(slot.getStart().isAfter(LocalDateTime.now().plusDays(14))) {
                throw new IllegalStateException("A booking may not start more than 14 days after the clock's current date");
            }
        } 
    }	


}
