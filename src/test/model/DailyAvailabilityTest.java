package model;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DailyAvailabilityTest {

    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private DailyAvailability da1;


    @BeforeEach
    public void runBefore() {

        startTime = LocalTime.of(10, 0); // 10:00 AM
        endTime = LocalTime.of(12, 0);   // 12:00 PM
        day = "Monday";
        da1 = new DailyAvailability(day, startTime, endTime);
    }

    @Test
    public void toStringTest() {

        assertEquals(da1.toString(), "Monday: 10:00 to 12:00");
    }

    @Test

    public void getDayTest() {
        assertEquals(da1.getDay(), "Monday");
    }

    @Test

    public void getStartTimeTest() {

        assertEquals(da1.getStartTime(), LocalTime.of(10,00));
    }

    @Test

    public void getEndTimeTest() {
        assertEquals(da1.getEndTime(),LocalTime.of(12,00));
    }


}


