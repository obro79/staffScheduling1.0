package persistence;

import org.json.JSONObject;
import org.json.*;
import model.Store;
import model.DailyAvailability;
import java.io.File;
import model.EmployeeNeeds;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {


    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of store to file
    public void write(Store store) {
        JSONObject json = new JSONObject();

        // Convert storeHours to JSON
        JSONArray storeHoursArray = new JSONArray();
        for (DailyAvailability da : store.getStoreHours()) {
            storeHoursArray.put(da.toJson()); // Assuming DailyAvailability has a toJson method
        }
        json.put("storeHours", storeHoursArray);

        // Convert allEmployeeNeeds to JSON
        JSONArray needsArray = new JSONArray();
        for (EmployeeNeeds en : store.getAllEmployeeNeeds()) {
            needsArray.put(en.toJson()); // Assuming EmployeeNeeds has a toJson method
        }
        json.put("allEmployeeNeeds", needsArray);

        // Assuming EmployeeList to JSON is similar

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    public JSONObject toJson(Store s) {

        JSONObject json = new JSONObject();

        json.put("storeHours", s.getStoreHours());

        return json;

    }
}
