package model;

import java.time.LocalTime;


//This class is the daily availability that each employee has

public class DailyAvailability {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;


    //EFFECTS: creates a new instance of DailyAvailability with day, startTime, endTime
    public DailyAvailability(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //EFFECTS: Turns the DailyAvailability to a String
    public String toString() {
        return day + ": " + startTime + " to " + endTime;
    }

    public String getDay() {
        return this.day;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }
}
