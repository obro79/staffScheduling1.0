import model.Shift;
import model.TimeRange;
import model.enums.Day;
import model.eventlog.Event;
import model.eventlog.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftTest {

    private Shift shift;
    private EventLog eventLog;
    private TimeRange timeRange;

    @BeforeEach
    public void setUp() {
        eventLog = EventLog.getInstance();
        eventLog.clear();  // Ensure the EventLog is empty before each test
        timeRange = new TimeRange(LocalTime.of(9, 00), LocalTime.of(17, 00));
        shift = new Shift(Day.MONDAY, timeRange, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(Day.MONDAY, shift.getDay());
        assertEquals(timeRange, shift.getTimeRange());
        assertEquals(5, shift.getNumberOfEmployees());

//        List<Event> events = eventLog.getEvents();
//        assertEquals(1, events.size());
//        assertEquals("Shift was added for MONDAY 09:00 - 17:00 with 5 employees", events.get(0).getDescription());
    }

    @Test
    public void testToString() {
        assertEquals("MONDAY: 09:00 to 17:00, 5 employees needed", shift.toString());
    }

    @Test
    public void testGetDay() {
        assertEquals(Day.MONDAY, shift.getDay());
    }

}
