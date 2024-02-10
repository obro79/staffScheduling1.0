package model;

import java.time.LocalTime;

public class Availability extends WeeklyAvailability {

    private String day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Availability(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return day + ": " + startTime + " to " + endTime;
    }

}


