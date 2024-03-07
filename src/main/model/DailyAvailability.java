package model;

import java.time.LocalTime;
import org.json.JSONArray;
import org.json.JSONObject;


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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", this.day);
        json.put("startTime", this.startTime.toString());
        json.put("endTime", this.endTime.toString());
        return json;
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
