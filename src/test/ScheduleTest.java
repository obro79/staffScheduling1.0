import model.*;
import model.enums.Day;
import model.enums.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Store;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {
    private Schedule schedule;
    private Store store;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
        store = new Store();

        // Add store hours
        store.addStoreHours(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0)));
    }

    @Test
    void testSuccessfulAssignment() {
        Employee emp = new Employee("Alice", Job.MANAGER);
        emp.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp);

        Shift shift = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1);
        store.addShift(shift);

        schedule.addShift(shift);
        schedule.assignEmployeeToShift(emp, shift);

        List<Employee> shiftEmployees = schedule.getEmployeesForShift(shift);
        assertEquals(1, shiftEmployees.size());
        assertEquals("Alice", shiftEmployees.get(0).getName());
    }

    @Test
    void testUnsuccessfulAssignmentDueToUnavailability() {
        Employee emp = new Employee("Bob", Job.COOK);
        emp.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)));
        store.addEmployee(emp);

        Shift shift = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1);
        store.addShift(shift);

        schedule.addShift(shift);

        assertThrows(IllegalArgumentException.class, () -> {
            schedule.assignEmployeeToShift(emp, shift);
        });
    }

    @Test
    void testUnsuccessfulAssignmentDueToNonExistentShift() {
        Employee emp = new Employee("Charlie", Job.COOK);
        emp.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp);

        Shift nonExistentShift = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(14, 0), LocalTime.of(17, 0)), 1);

        assertThrows(IllegalArgumentException.class, () -> {
            schedule.assignEmployeeToShift(emp, nonExistentShift);
        });
    }

    @Test
    void testGetEmployeesForShift() {
        Employee emp1 = new Employee("Alice", Job.MANAGER);
        emp1.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp1);

        Employee emp2 = new Employee("Bob", Job.COOK);
        emp2.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp2);

        Shift shift = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 2);
        store.addShift(shift);

        schedule.addShift(shift);
        schedule.assignEmployeeToShift(emp1, shift);
        schedule.assignEmployeeToShift(emp2, shift);

        List<Employee> shiftEmployees = schedule.getEmployeesForShift(shift);
        assertEquals(2, shiftEmployees.size());
        assertTrue(shiftEmployees.stream().anyMatch(emp -> emp.getName().equals("Alice")));
        assertTrue(shiftEmployees.stream().anyMatch(emp -> emp.getName().equals("Bob")));
    }

    @Test
    void testAssignMultipleEmployeesToShift() {
        Employee emp1 = new Employee("Alice", Job.MANAGER);
        emp1.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp1);

        Employee emp2 = new Employee("Bob", Job.COOK);
        emp2.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp2);

        Employee emp3 = new Employee("Charlie", Job.COOK);
        emp3.addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        store.addEmployee(emp3);

        Shift shift = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 3);
        store.addShift(shift);

        schedule.addShift(shift);
        schedule.assignEmployeeToShift(emp1, shift);
        schedule.assignEmployeeToShift(emp2, shift);
        schedule.assignEmployeeToShift(emp3, shift);

        List<Employee> shiftEmployees = schedule.getEmployeesForShift(shift);
        assertEquals(3, shiftEmployees.size());
        assertTrue(shiftEmployees.stream().anyMatch(emp -> emp.getName().equals("Alice")));
        assertTrue(shiftEmployees.stream().anyMatch(emp -> emp.getName().equals("Bob")));
        assertTrue(shiftEmployees.stream().anyMatch(emp -> emp.getName().equals("Charlie")));
    }
}
