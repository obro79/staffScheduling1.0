

import model.*;
import model.enums.Day;
import model.enums.Job;
import model.scheduling.Scheduler;
import model.scheduling.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Store;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {
    private Store store;
    private Scheduler scheduler;

    @BeforeEach
    void setUp() {
        // Initialize the store and scheduler before each test
        store = new Store();

        // Set store hours
        store.addStoreHours(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        store.addStoreHours(Day.TUESDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0)));
    }

    @Test
    void testSimpleScheduling() {
        // Add employees
        Employee emp1 = new Employee("Alice", Job.MANAGER);
        emp1.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0))));
        store.addEmployee(emp1);

        Employee emp2 = new Employee("Bob", Job.COOK);
        emp2.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0))));
        store.addEmployee(emp2);

        // Create shifts
        Shift shift1 = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1);
        Shift shift2 = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)), 1);
        store.addShift(shift1);
        store.addShift(shift2);

        // Create the scheduler and generate the schedule
        scheduler = new Scheduler(store);
        Schedule schedule = scheduler.createSchedule();

        // Verify the schedule
        List<Employee> shift1Employees = schedule.getEmployeesForShift(shift1);
        assertEquals(1, shift1Employees.size());
        assertEquals("Alice", shift1Employees.get(0).getName());

        List<Employee> shift2Employees = schedule.getEmployeesForShift(shift2);
        assertEquals(1, shift2Employees.size());
        assertEquals("Bob", shift2Employees.get(0).getName());
    }

    @Test
    void testMultipleDaysScheduling() {
        // Add employees
        Employee emp1 = new Employee("Alice", Job.MANAGER);
        emp1.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0))));
        emp1.addDailyAvailability(new DailyAvailability(Day.TUESDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0))));
        store.addEmployee(emp1);

        Employee emp2 = new Employee("Bob", Job.COOK);
        emp2.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0))));
        emp2.addDailyAvailability(new DailyAvailability(Day.TUESDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0))));
        store.addEmployee(emp2);

        // Create shifts
        Shift shift1 = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1);
        Shift shift2 = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)), 1);
        Shift shift3 = new Shift(Day.TUESDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1);
        Shift shift4 = new Shift(Day.TUESDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)), 1);
        store.addShift(shift1);
        store.addShift(shift2);
        store.addShift(shift3);
        store.addShift(shift4);

        // Create the scheduler and generate the schedule
        scheduler = new Scheduler(store);
        Schedule schedule = scheduler.createSchedule();

        // Verify the schedule
        List<Employee> shift1Employees = schedule.getEmployeesForShift(shift1);
        assertEquals(1, shift1Employees.size());
        assertEquals("Alice", shift1Employees.get(0).getName());

        List<Employee> shift2Employees = schedule.getEmployeesForShift(shift2);
        assertEquals(1, shift2Employees.size());
        assertEquals("Bob", shift2Employees.get(0).getName());

        List<Employee> shift3Employees = schedule.getEmployeesForShift(shift3);
        assertEquals(1, shift3Employees.size());
        assertEquals("Alice", shift3Employees.get(0).getName());

        List<Employee> shift4Employees = schedule.getEmployeesForShift(shift4);
        assertEquals(1, shift4Employees.size());
        assertEquals("Bob", shift4Employees.get(0).getName());
    }

    @Test
    void testComplexScheduling() {
        // Add employees with varied availabilities
        for (int i = 1; i <= 30; i++) {
            Employee emp = new Employee("Employee" + i, Job.values()[i % Job.values().length]);
            emp.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0))));
            emp.addDailyAvailability(new DailyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0))));
            emp.addDailyAvailability(new DailyAvailability(Day.TUESDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0))));
            emp.addDailyAvailability(new DailyAvailability(Day.TUESDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0))));
            store.addEmployee(emp);
        }

        // Create shifts with different requirements
        for (int i = 0; i < 10; i++) {
            Shift shiftMorningMon = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), i % 3 + 1);
            Shift shiftAfternoonMon = new Shift(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)), i % 3 + 1);
            Shift shiftMorningTue = new Shift(Day.TUESDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), i % 3 + 1);
            Shift shiftAfternoonTue = new Shift(Day.TUESDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(17, 0)), i % 3 + 1);
            store.addShift(shiftMorningMon);
            store.addShift(shiftAfternoonMon);
            store.addShift(shiftMorningTue);
            store.addShift(shiftAfternoonTue);
        }

        // Create the scheduler and generate the schedule
        scheduler = new Scheduler(store);
        Schedule schedule = scheduler.createSchedule();

        // Verify the schedule
        for (Day day : Day.values()) {
            List<Shift> shifts = store.getShifts(day);
            for (Shift shift : shifts) {
                List<Employee> assignedEmployees = schedule.getEmployeesForShift(shift);
                assertEquals(shift.getNumberOfEmployees(), assignedEmployees.size());
            }
        }
    }
}
