package persistence;

import model.Employee;
import org.json.JSONObject;
import org.json.*;
import model.Store;
import model.DailyAvailability;
import java.io.File;
import model.EmployeeNeeds;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//this persistance package is heaviliy inspired by the example given to us

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
        this.writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of store to file
    // EFFECTS: writes JSON representation of store to file
    // MODIFIES: this
// EFFECTS: writes JSON representation of store to file //TODO make helpers and gget this shorter
    public void write(Store store) throws FileNotFoundException {
        try {

            this.open();
            JSONObject json = new JSONObject();

            // Convert storeHours to JSON
            JSONArray storeHoursArray = new JSONArray();
            for (DailyAvailability da : Store.getInstance().getStoreHours()) {
                storeHoursArray.put(da.toJson());
            }
            json.put("storeHours", storeHoursArray);

            // Convert allEmployeeNeeds to JSON
            JSONArray needsArray = new JSONArray();
            for (EmployeeNeeds en : store.getInstance().getAllEmployeeNeeds()) {
                needsArray.put(en.toJson());
            }
            json.put("allEmployeeNeeds", needsArray);

            // Convert employees to JSON
            JSONArray employeesArray = new JSONArray();
            for (Employee employee : store.getEmployeeList().getEmployeeList()) {
                employeesArray.put(employee.toJson());
            }
            json.put("employees", employeesArray);

            saveToFile(json.toString(TAB));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening the file");
        } finally {
            if (this.writer != null) {
                this.close();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        this.writer.close();
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
