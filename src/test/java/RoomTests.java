package test.java;

// Imports
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import main.java.Room;



// Step 1 of TDD Red
public class RoomTests {

    
    @Test 
    public void testRoomID() {

        Room room = new Room("R1", "Enum Room");
        assertEquals("R1", room.getId());

    }

    @Test 
    public void testRoomName() {

        Room room = new Room("R1", "Video Room");
        assertEquals("Video Room", room.getName());

    }

    
}
