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
        
        // R1
        if (rooms.findById(roomId) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        // R2
        if(!slot.getStart().isAfter(LocalDateTime.now(clock))) {
            throw new IllegalArgumentException("Booking must be in the Future and not the past");
        }

        //R3
        if (Duration.between(slot.getStart(), slot.getEnd()).compareTo(MAX_SLOT) > 0) {
            throw new IllegalArgumentException("Booking must not be longer than three hours");
        }

        //R4
        for (Booking booking : bookings.findByRoom(roomId)) {
            if (slot.getStart().isBefore(booking.getTimeSlot().getEnd()) &&
                slot.getEnd().isAfter(booking.getTimeSlot().getStart())) {
                throw new IllegalStateException("Room is already booked");
            }
        }

        // R5
        for (Booking booking : bookings.findByUser(userId)) {
            if (slot.getStart().isBefore(booking.getTimeSlot().getEnd()) &&
                slot.getEnd().isAfter(booking.getTimeSlot().getStart())) {
                throw new IllegalStateException("User already has a booking");
            }
        }


        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
    }


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

    /** A user's bookings, earliest first. */
    public List<Booking> bookingsFor(String userId) {
        throw new UnsupportedOperationException("TODO: implement using TDD");
    } 
    
}
