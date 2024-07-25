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



    @Test
    void testOverlaps2() {
        TimeRange range1 = new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0));
        TimeRange range2 = new TimeRange(LocalTime.of(11, 0), LocalTime.of(14, 0));
        TimeRange range3 = new TimeRange(LocalTime.of(13, 0), LocalTime.of(15, 0));

        assertTrue(range1.overlaps(range2), "Expected range1 to overlap with range2");
        assertTrue(range2.overlaps(range1), "Expected range2 to overlap with range1");
        assertFalse(range1.overlaps(range3), "Expected range1 to not overlap with range3");
    }



    @Test
    void testIsWithin10() {
        TimeRange range1 = new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0));
        TimeRange range2 = new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0));
        TimeRange range3 = new TimeRange(LocalTime.of(13, 0), LocalTime.of(17, 0));
        TimeRange range4 = new TimeRange(LocalTime.of(8, 0), LocalTime.of(18, 0));
        TimeRange range5 = new TimeRange(LocalTime.of(10, 0), LocalTime.of(16, 0));
        TimeRange range6 = new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0));

        assertTrue(range2.isWithin(range1), "Expected range2 to be within range1");
        assertTrue(range3.isWithin(range1), "Expected range3 to be within range1");
        assertTrue(range5.isWithin(range1), "Expected range5 to be within range1");
        assertFalse(range1.isWithin(range2), "Expected range1 to not be within range2");
        assertTrue(range1.isWithin(range4), "Expected range1 to not be within range4");
        assertTrue(range1.isWithin(range6), "Expected range1 to not be within range4");
    }

}
