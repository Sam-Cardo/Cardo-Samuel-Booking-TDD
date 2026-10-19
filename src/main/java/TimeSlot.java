package main.java;
import java.time.*;

public final class TimeSlot 
{
    // variables
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a new TimeSlot with the specified start and end times.
     * The start time must be before the end time.
     * @param start 
     * @param end
     */

    public TimeSlot(LocalDateTime start, LocalDateTime end) { // A1
        if (start == null || end == null) { // Check if start or end is null
            throw new IllegalArgumentException("Start and end cannot be null");
        } 
        if (!start.isBefore(end)) { // Check if start is not before end
            throw new IllegalArgumentException("Start must be before end");
        }

        this.start = start; 
        this.end = end;

    }

    /**
     * 
     * @return the start time of the TimeSlot.
     */

    public LocalDateTime getStart() { // Getter for start
        return start;
    }

    /**
     * 
     * @return the end time of the TimeSlot.
     */

    public LocalDateTime getEnd() { // Getter for end
        return end;
    }

    /**
     * Calculates the duration of the TimeSlot.
     * @return the duration of the TimeSlot as a Duration object.
     */
    public Duration duration() {    // A2
        return Duration.between(start, end); // Duration.between returns a Duration object representing the time between start and end
    }

    /**
     * 
     * @param other
     * @return true if this TimeSlot overlaps with the other TimeSlot, false otherwise.
     */

    public boolean overlaps(TimeSlot other) { // A3
        return this.start.isBefore(other.end) && other.start.isBefore(this.end); //
    }


    /**
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     * Indicates whether some other object is "equal to" this one.
     */

    public boolean equals(Object obj) { // A5
        if (this == obj) { // Check if the same object
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // Check if obj is null or not the same class
            return false;
        }

        TimeSlot that = (TimeSlot) obj; // Cast obj to TimeSlot

        return start.equals(that.start) && end.equals(that.end); // Check if start and end are equal

    }

    /**
     * Returns the hash code value for this TimeSlot.
     *
     * @return the hash code value
     */
    public int hashCode() {
        return start.hashCode() * 31 + end.hashCode();
    }

    /**
     * Returns a string representation of this TimeSlot.
     *
     * @return a string representation of this TimeSlot
     */

    public String toString() {
        return "TimeSlot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2027, 1, 11, 10, 0);
        LocalDateTime end = LocalDateTime.of(2027, 1, 11, 12, 0);
        TimeSlot slot = new TimeSlot(start, end);

        System.out.println("Start: " + slot.getStart());
        System.out.println("End: " + slot.getEnd());
        System.out.println("Duration: " + slot.duration().toHours() + " hours");
    }


}