package model;

import java.time.LocalTime;
import java.time.Duration;
import org.json.JSONObject;


public class TimeRange {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    public boolean overlaps(TimeRange other) {
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }

    public boolean isWithin(TimeRange other) {
        return !startTime.isBefore(other.getStartTime()) && !endTime.isAfter(other.getEndTime());
    }


    @Override
    public String toString() {
        return startTime + " to " + endTime;
    }

}
