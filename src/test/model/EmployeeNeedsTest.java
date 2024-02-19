package model;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeNeedsTest {

    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private DailyAvailability da1;
    private int numberOfEmployees;
    private EmployeeNeeds empNeeds1;


    @BeforeEach
    public void runBefore() {
        startTime = LocalTime.of(12,00);
        endTime = LocalTime.of(13,00);

        empNeeds1 = new EmployeeNeeds("Monday", startTime, endTime,10 );

    }

    @Test
    public void toStringTest() {

        assertEquals(empNeeds1.toString(), "Monday: 12:00 to 13:00, 10 employees needed");
    }

    @Test

    public void getDayTest() {
        assertEquals(empNeeds1.getDay(), "Monday");
    }

    @Test

    public void getStartTimeTest() {

        assertEquals(empNeeds1.getStartTime(), LocalTime.of(12,00));
    }

    @Test

    public void getEndTimeTest() {
        assertEquals(empNeeds1.getEndTime(),LocalTime.of(13,00));
    }

    @Test

    public void getNumberOfEmployeesTest() {
        assertEquals(empNeeds1.getNumberOfEmployees(), 10);
    }


}


