package test.java;

// Imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.java.TimeSlot;
import java.time.LocalDateTime;



class TimeSlotTest {
    private static TimeSlot slot(int startHour, int endHour) {
        return new TimeSlot(LocalDateTime.of(2027, 1, 11, startHour, 0),
                            LocalDateTime.of(2027, 1, 11, endHour, 0));
    }

    @ParameterizedTest(name = "{0}-{1} vs {2}-{3} overlaps: {4}")
    @CsvSource({
        "10, 11, 11, 12, false",   // touching
        "10, 12, 11, 13, true",    // partly overlapping
        "10, 13, 11, 12, true",    // one completely inside the other
        "10, 11, 12, 13, false",   // non-overlapping
    })
    
    void overlapCases(int aStart, int aEnd, int bStart, int bEnd, boolean expected) {
        assertEquals(expected, slot(aStart, aEnd).overlaps(slot(bStart, bEnd)));
        assertEquals(expected, slot(bStart, bEnd).overlaps(slot(aStart, aEnd)));  
    }
}
