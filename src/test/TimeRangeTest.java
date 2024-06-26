import model.TimeRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimeRangeTest {

    private TimeRange timeRange;

    @BeforeEach
    public void setUp() {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        timeRange = new TimeRange(startTime, endTime);
    }

    @Test
    public void testConstructor() {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        TimeRange timeRange = new TimeRange(startTime, endTime);

        assertEquals(startTime, timeRange.getStartTime());
        assertEquals(endTime, timeRange.getEndTime());
    }

    @Test
    public void testConstructorThrowsException() {
        LocalTime startTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(9, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            new TimeRange(startTime, endTime);
        });
    }

    @Test
    public void testGetStartTime() {
        assertEquals(LocalTime.of(9, 0), timeRange.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        assertEquals(LocalTime.of(17, 0), timeRange.getEndTime());
    }

    @Test
    public void testGetDuration() {
        Duration expectedDuration = Duration.ofHours(8);
        assertEquals(expectedDuration, timeRange.getDuration());
    }

    @Test
    public void testOverlaps() {
        TimeRange otherTimeRange = new TimeRange(LocalTime.of(16, 0), LocalTime.of(18, 0));
        assertTrue(timeRange.overlaps(otherTimeRange));
    }

    @Test
    public void testNoOverlap() {
        TimeRange otherTimeRange = new TimeRange(LocalTime.of(18, 0), LocalTime.of(20, 0));
        assertFalse(timeRange.overlaps(otherTimeRange));
    }

    @Test
    public void testToString() {
        assertEquals("09:00 to 17:00", timeRange.toString());
    }
}
