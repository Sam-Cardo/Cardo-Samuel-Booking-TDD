package main.java;

// Step 2 of TDD Green
public class Room {

    // Variables for Room class
    private String id;
    private String name;

    /** 
     * Creates a new Room with the specified id and name.
     * @param id the unique identifier for the room
     * @param name the name of the room
     */
    public Room(String id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Getter for the id of the room
     * @return id of the room
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the name of the room
     * @return name of the room
     */
    
    public String getName() {
        return name;
    }

    /**
     * Converts the Room object to a string.
     * @return a string from the Room object
     */ 

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    
}
