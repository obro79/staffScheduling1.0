package model;

import java.time.LocalTime;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;


//This class represents the daily availability that each employee has.

public class DailyAvailability implements Writeable {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;


    //EFFECTS: creates a new instance of DailyAvailability with day, startTime, endTime
    public DailyAvailability(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //EFFECTS: returns the DailyAvailability as a String
    public String toString() {
        return day + ": " + startTime + " to " + endTime;
    }

    //EFFECTS: Turns this into a Json object
    public JSONObject toJson() {
        JSONObject dailyAvailability = new JSONObject();
        dailyAvailability.put("day", this.day);
        dailyAvailability.put("startTime", this.startTime.toString());
        dailyAvailability.put("endTime", this.endTime.toString());
        return dailyAvailability;
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
