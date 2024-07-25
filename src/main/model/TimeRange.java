package model;

import java.time.LocalTime;
import java.time.Duration;
import java.util.Objects;

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
        if (!this.startTime.isBefore(other.getStartTime())) {
            if (!this.endTime.isAfter(other.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOf15MinuteSegments() {
        Duration duration = getDuration();
        long totalMinutes = duration.toMinutes();
        return (int) totalMinutes / 15;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeRange timeRange = (TimeRange) o;
        return startTime.equals(timeRange.startTime) && endTime.equals(timeRange.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }


    @Override
    public String toString() {
        return startTime + " to " + endTime;
    }

}
