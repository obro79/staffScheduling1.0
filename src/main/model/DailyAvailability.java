package model;

import java.time.LocalTime;

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
