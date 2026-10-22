package main.java;

public class Booking {

    // Variables for Booking class
    private String userID;
    private String roomID;
    private TimeSlot timeSlot;

    /**
     * Creates a new Booking with the specified userID, roomID, and timeSlot.
     * @param userID the ID of the user making the booking
     * @param roomID the ID of the room being booked
     * @param timeSlot the TimeSlot for which the booking is made
     */

    public Booking(String userID, String roomID, TimeSlot timeSlot) {
        this.userID = userID;
        this.roomID = roomID;
        this.timeSlot = timeSlot;
    }

    /**
     * getter for the userID of the booking
     * @return userID of the booking
     */

    public String getUserID() {
        return userID;
    }

    /**
     * getter for the roomID of the booking
     * @return roomID of the booking
     */

    public String getRoomID() {
        return roomID;
    }

    /**
     * getter for the timeSlot of the booking
     * @return timeSlot of the booking
     */

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Checks if this booking is equal to another booking.
     * Two bookings are considered equal if they have the 
     * same userID, roomID, and timeSlot.
     * @param other the other booking to compare with
     * @return true if the bookings are equal, false otherwise
     */

    public boolean equals(Booking other) {
        if (other == null) {
            return false;
        }
        return this.userID.equals(other.userID) &&
               this.roomID.equals(other.roomID) &&
               this.timeSlot.equals(other.timeSlot);
    }

    /**
     * Generates a hash code for this booking.
     * The hash code is based on the userID, roomID, and timeSlot.
     * @return the hash code of the booking
     */

    public int hashCode() {
        int result = userID.hashCode();
        result = 31 * result + roomID.hashCode();
        result = 31 * result + timeSlot.hashCode();
        return result;
    }

}
