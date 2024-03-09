package persistence;

import model.DailyAvailability;

import model.Employee;
import model.EmployeeNeeds;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;
import model.Store;
import java.time.LocalTime;

//this persistance package is heaviliy inspired by the example given to us

//Takes all the data in the Json file and turns it into data that is used to set Store fields.
public class JsonReader {
    // Represents a reader that reads workroom from JSON data stored in file
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Store from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Store read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStore(jsonObject);
    }



    //EFFECTS: turns all the Json data into store fields then sets a store with those fields and returns it
    public Store parseStore(JSONObject jsonObject) {
        Store store = new Store();

        // Parse storeHours
        if (jsonObject.optJSONArray("storeHours") != null) {
            JSONArray storeHoursArray = jsonObject.optJSONArray("storeHours");
            for (int i = 0; i < storeHoursArray.length(); i++) {
                JSONObject hourObject = storeHoursArray.optJSONObject(i);
                DailyAvailability availability = new DailyAvailability(
                        hourObject.getString("day"),
                        LocalTime.parse(hourObject.optString("startTime")),
                        LocalTime.parse(hourObject.optString("endTime"))
                );
                store.getStoreHours().add(availability);
            }
        }
        // Parse allEmployeeNeeds
        this.parseEmployeeNeeds(jsonObject,store);
        // Parse employeeList
        this.parseEmployeeList(jsonObject,store);
        return store;
    }

    //MODIFIES: store
    //EFFECTS: turns allEmployeeNeeds that is stored in json into allEmployeeNeeds and uses it to set Store
    public void parseEmployeeNeeds(JSONObject jsonObject, Store store) {
        if (jsonObject.optJSONArray("allEmployeeNeeds") != null) {
            JSONArray needsArray = jsonObject.optJSONArray("allEmployeeNeeds");
            for (int i = 0; i < needsArray.length(); i++) {
                JSONObject needObject = needsArray.optJSONObject(i);
                EmployeeNeeds needs = new EmployeeNeeds(
                        needObject.getString("day"),
                        LocalTime.parse(needObject.optString("startTime")),
                        LocalTime.parse(needObject.optString("endTime")),
                        needObject.optInt("numberOfEmployees")
                );
                store.getAllEmployeeNeeds().add(needs);
            }
        }
    }

    //MODIFIES: store
    //EFFECTS: turns employeelist that is stored in json into allEmployeeNeeds and uses it to set Store
    public void parseEmployeeList(JSONObject jsonObject, Store store) {
        if (jsonObject.optJSONArray("employees") != null) {
            JSONArray employeeListArray = jsonObject.optJSONArray("employees");
            for (int i = 0; i < employeeListArray.length(); i++) {
                JSONObject employeeObject = employeeListArray.optJSONObject(i);
                Employee employee = parseEmployee(employeeObject);
                store.getEmployeeList().addEmployee(employee);
            }
        }
    }


    //Effects: takes all the json data and turns it into an employee and returns is
    public Employee parseEmployee(JSONObject jsonObject) {

        String name = jsonObject.optString("name");
        String job = jsonObject.optString("job");
        Employee employee = new Employee(name, job);

        // Parse availability for the employee if it's part of the JSON object
        if (jsonObject.optJSONArray("weeklyAvailability") != null) {
            JSONArray availabilityArray = jsonObject.optJSONArray("weeklyAvailability");
            for (int i = 0; i < availabilityArray.length(); i++) {
                JSONObject availabilityObject = availabilityArray.optJSONObject(i);
                DailyAvailability availability = new DailyAvailability(
                        availabilityObject.getString("day"),
                        LocalTime.parse(availabilityObject.optString("startTime")),
                        LocalTime.parse(availabilityObject.optString("endTime"))
                );
                employee.getWeeklyAvailability().add(availability);
            }
        }

        return employee;
    }

    // throwing exception because not all store fields have a value so need to guard against that.

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }





}
