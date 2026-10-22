package main.java;

public class Booking {

    // Variables for Booking class
    private String userId;
    private String roomId;
    private TimeSlot timeSlot;

    /**
     * Creates a new Booking with the specified userId, roomId, and timeSlot.
     * @param userId the Id of the user making the booking
     * @param roomId the Id of the room being booked
     * @param timeSlot the TimeSlot for which the booking is made
     */

    public Booking(String userId, String roomId, TimeSlot timeSlot) {
        this.userId = userId;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
    }

    /**
     * getter for the userId of the booking
     * @return userId of the booking
     */

    public String getUserId() {
        return userId;
    }

    /**
     * getter for the roomId of the booking
     * @return roomId of the booking
     */

    public String getRoomId() {
        return roomId;
    }

    /**
     * getter for the timeSlot of the booking
     * @return timeSlot of the booking
     */

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Checks if this booking is equal to anobject booking.
     * Two bookings are consIdered equal if they have the 
     * same userId, roomId, and timeSlot.
     * @param object the object booking to compare with
     * @return true if the bookings are equal, false objectwise
     */

    @Override 
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (!(obj instanceof Booking)) {
                return false;
            }

            Booking other = (Booking) obj;

            return (this.userId.equals(other.getUserId()) &&
                    this.roomId.equals(other.getRoomId()) &&
                    this.timeSlot.equals(other.getTimeSlot()));
        }

    /**
     * Generates a hash code for this booking.
     * The hash code is based on the userId, roomId, and timeSlot.
     * @return the hash code of the booking
     */

    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + roomId.hashCode();
        result = 31 * result + timeSlot.hashCode();
        return result;
    }

}
