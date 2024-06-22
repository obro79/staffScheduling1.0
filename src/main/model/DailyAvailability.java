package model;

import org.json.JSONObject;

public class DailyAvailability {
    private Day day;
    private TimeRange timeRange;
    private boolean available;

    public DailyAvailability(Day day, TimeRange timeRange) {
        this.day = day;
        this.timeRange = timeRange;
        this.available = true;
    }

    public DailyAvailability(Day day, boolean available) {
        this.day = day;
        this.timeRange = null;
        this.available = available;
    }

    @Override
    public String toString() {
        if (available) {
            return day + ": " + timeRange;
        } else {
            return day + ": Not Available";
        }
    }

    public Day getDay() {
        return day;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public boolean isAvailable() {
        return available;
    }

}
