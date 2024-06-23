
import model.DailyAvailability;
import model.enums.Day;
import model.TimeRange;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class DailyAvailabilityTest {

    @Test
    public void testConstructorWithTimeRange() {
        Day day = Day.MONDAY;
        TimeRange timeRange = new TimeRange(LocalTime.of(9,00), LocalTime.of(17,00));
        DailyAvailability availability = new DailyAvailability(day, timeRange);

        assertEquals(day, availability.getDay());
        assertEquals(timeRange, availability.getTimeRange());
        assertTrue(availability.isAvailable());
        assertEquals("MONDAY: 09:00 to 17:00", availability.toString());
    }

    @Test
    public void testConstructorWithAvailability() {
        Day day = Day.TUESDAY;
        boolean available = false;
        DailyAvailability availability = new DailyAvailability(day, available);

        assertEquals(day, availability.getDay());
        assertNull(availability.getTimeRange());
        assertFalse(availability.isAvailable());
        assertEquals("TUESDAY: Not Available", availability.toString());
    }

    @Test
    public void testToStringWhenAvailable() {
        Day day = Day.WEDNESDAY;
        TimeRange timeRange = new TimeRange(LocalTime.of(8,00), LocalTime.of(12,00));
        DailyAvailability availability = new DailyAvailability(day, timeRange);

        assertEquals("WEDNESDAY: 08:00 to 12:00", availability.toString());
    }

    @Test
    public void testToStringWhenNotAvailable() {
        Day day = Day.THURSDAY;
        DailyAvailability availability = new DailyAvailability(day, false);

        assertEquals("THURSDAY: Not Available", availability.toString());
    }
}
