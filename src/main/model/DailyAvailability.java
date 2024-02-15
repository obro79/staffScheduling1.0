package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DailyAvailability {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;

    public DailyAvailability(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return day + ": " + startTime + " to " + endTime;
    }
}
