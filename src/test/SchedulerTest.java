import model.DailyAvailability;
import model.Employee;
import model.Shift;
import model.TimeRange;
import model.enums.Day;
import model.enums.Job;
import model.scheduling.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Store;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SchedulerTest {

    private Store store;
    private Scheduler scheduler;

    @BeforeEach
    public void setUp() {
        store = new Store();
        this.scheduler = new Scheduler(store);

        // Add more employees with their qualifications
        store.addEmployee(new Employee("Alice", Job.MANAGER));
        store.addEmployee(new Employee("Bob", Job.COOK));
        store.addEmployee(new Employee("Charlie", Job.SERVER));
        store.addEmployee(new Employee("David", Job.MANAGER));
        store.addEmployee(new Employee("Eve", Job.COOK));
        store.addEmployee(new Employee("Frank", Job.SERVER));
        store.addEmployee(new Employee("Grace", Job.MANAGER));
        store.addEmployee(new Employee("Hank", Job.COOK));
        store.addEmployee(new Employee("Ivy", Job.SERVER));

        // Set weekly availability for employees
        store.getEmployees().get(0).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        store.getEmployees().get(1).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(10, 0), LocalTime.of(14, 0)));
        store.getEmployees().get(2).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(20, 0)));
        store.getEmployees().get(3).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0)));
        store.getEmployees().get(4).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(14, 0), LocalTime.of(18, 0)));
        store.getEmployees().get(5).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(15, 0), LocalTime.of(19, 0)));
        store.getEmployees().get(6).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        store.getEmployees().get(7).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(10, 0), LocalTime.of(14, 0)));
        store.getEmployees().get(8).addWeeklyAvailability(Day.MONDAY, new TimeRange(LocalTime.of(12, 0), LocalTime.of(21, 0)));

        // Add shifts to be covered
        store.addShift(new Shift(Day.MONDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)), 1));
        store.addShift(new Shift(Day.MONDAY, new TimeRange(LocalTime.of(10, 0), LocalTime.of(14, 0)), 1));
        store.addShift(new Shift(Day.MONDAY, new TimeRange(LocalTime.of(13, 0), LocalTime.of(17, 0)), 1));
        store.addShift(new Shift(Day.MONDAY, new TimeRange(LocalTime.of(15, 0), LocalTime.of(20, 0)), 1));
    }



    @Test
    public void testMakeSchedule1() {
    }

//    @Test
//    public void testMakeSchedule() {
//        // Print initial state of store shifts and employee availability
//        System.out.println("Initial Store Shifts:");
//        for (Day day : Day.values()) {
//            List<Shift> shifts = store.getShifts(day);
//            if (shifts != null) {
//                for (Shift shift : shifts) {
//                    System.out.println(shift);
//                }
//            }
//        }
//
//        System.out.println("Employee Availability:");
//        for (Employee employee : store.getEmployees()) {
//            System.out.println(employee.getName() + " (" + employee.getJob() + "): " + employee.getWeeklyAvailability());
//        }
//
//        scheduler.makeSchedule(store);
//
//        Schedule schedule = scheduler.getSchedule();
//        Map<Shift, List<Employee>> assignments = schedule.getShiftAssignments();
//
//        // Print assignments for debugging
//        System.out.println("Assignments:");
//        for (Shift shift : assignments.keySet()) {
//            List<Employee> assignedEmployees = assignments.get(shift);
//            System.out.println("Shift: " + shift + " Assigned Employees: " + assignedEmployees);
//        }
//
//        // Check if assignments are not empty
//        assertFalse(assignments.isEmpty(), "Assignments should not be empty");
//
//        // Check specific assignments if needed
//        for (Shift shift : store.getShifts().values().stream().flatMap(List::stream).collect(Collectors.toList())) {
//            List<Employee> assignedEmployees = assignments.get(shift);
//            assertNotNull(assignedEmployees, "Assigned employees should not be null");
//            assertFalse(assignedEmployees.isEmpty(), "Assigned employees list should not be empty");
//            System.out.println("Shift: " + shift + " Assigned Employees: " + assignedEmployees);
//        }
//    }

    @Test
    public void testEnsureConstraintsAreRespected() {

    }

    @Test
    public void testMinimizeShiftSplits() {

    }

    @Test
    void testEnsureAssignmentsAreValid() {
    }

}
