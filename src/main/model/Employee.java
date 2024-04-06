package model;


import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.format.DateTimeFormatter;


import model.DailyAvailability;
import persistence.Writeable;


//Represents an employee at the store with a name, job and availability.

public class Employee implements Writeable {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;

    //EFFECTS: creates a new instance of Employee with name and job and empty availability
    public Employee(String name, String job) {
        this.weeklyAvailability = new ArrayList<>();
        this.job = job;
        this.name = name;
        String eventString = "New Employee added with name " + name + " and job " + job;
        EventLog.getInstance().logEvent(new Event(eventString));
    }


    //MODIFIES: this
    //EFFECTS: sets employees name to given name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: sets employees job to given job
    public void setJob(String job) {
        this.job = job;
    }

    //EFFECTS: returns an employee turned into a json object
    public JSONObject toJson() {
        JSONObject employee = new JSONObject();
        employee.put("name", this.name);
        employee.put("job", this.job);


        // Convert the list of DailyAvailability to JSON
        JSONArray availabilityArray = new JSONArray();
        for (DailyAvailability availability : this.weeklyAvailability) {
            JSONObject availabilityJson = new JSONObject();
            availabilityJson.put("day", availability.getDay());
            availabilityJson.put("startTime", availability.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
            availabilityJson.put("endTime", availability.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME));

            // If DailyAvailability contains other fields, convert them to JSON as well

            availabilityArray.put(availabilityJson);
        }
        employee.put("weeklyAvailability", availabilityArray);

        return employee;
    }

    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

    public ArrayList<DailyAvailability> getWeeklyAvailability() {
        return this.weeklyAvailability;
    }
}

