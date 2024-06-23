import model.Employee;
import model.enums.Day;
import model.enums.Job;
import model.eventlog.Event;
import model.eventlog.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.DailyAvailability;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private Employee employee;
    private EventLog eventLog;

    @BeforeEach
    public void setUp() {
        eventLog = EventLog.getInstance();
        eventLog.clear();  // Ensure the EventLog is empty before each test
        employee = new Employee("John Doe", Job.MANAGER);
    }

    @Test
    public void testConstructor() {
        assertEquals("John Doe", employee.getName());
        assertEquals(Job.MANAGER, employee.getJob());
        assertTrue(employee.getWeeklyAvailability().isEmpty());
        assertTrue(employee.getShifts().isEmpty());

//        List<Event> events = eventLog.getEvents();
//        assertEquals(1, events.size());
//        assertEquals("New Employee added with name John Doe and job CASHIER", events.get(0).getDescription());
    }

    @Test
    public void testSetName() {
        employee.setName("Jane Doe");
        assertEquals("Jane Doe", employee.getName());
    }

    @Test
    public void testSetJob() {
        employee.setJob(Job.MANAGER);
        assertEquals(Job.MANAGER, employee.getJob());
    }

    @Test
    public void testGetShifts() {
        assertTrue(employee.getShifts().isEmpty());
    }

    @Test
    public void testGetWeeklyAvailability() {
        Map<Day, List<DailyAvailability>> availability = employee.getWeeklyAvailability();
        assertTrue(availability.isEmpty());
    }
}
