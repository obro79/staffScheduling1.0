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

//Takes all the Json Data and writes it a Json File.
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
    public void write(Store store) throws FileNotFoundException {
        try {

            this.open();
            JSONObject json = new JSONObject();

            this.putStoreHoursArray(store,json);
            this.putAllEmployeeNeedsArray(store,json);
            this.putEmployeeListArray(store, json);

            saveToFile(json.toString(TAB));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening the file");
        } finally {
            if (this.writer != null) {
                this.close();
            }
        }
    }

    //EFFECTS: turns storehours field to a jsonArray and puts it into Json
    public void putStoreHoursArray(Store store, JSONObject json) {
        JSONArray storeHoursArray = new JSONArray(); //public void putStoreHoursArray()
        for (DailyAvailability da : store.getStoreHours()) {
            storeHoursArray.put(da.toJson());
        }
        json.put("storeHours", storeHoursArray);

    }

    //EFFECTS: turns EmployeeNeeds field to a jsonArray and puts it into Json
    public void putAllEmployeeNeedsArray(Store store, JSONObject json) {
        JSONArray needsArray = new JSONArray();
        for (EmployeeNeeds en : store.getAllEmployeeNeeds()) {
            needsArray.put(en.toJson());
        }
        json.put("allEmployeeNeeds", needsArray);
    }

    //EFFECTS: turns EmployeeList field to a jsonArray and puts it into Json
    public void putEmployeeListArray(Store store, JSONObject json) {
        JSONArray employeesArray = new JSONArray();
        for (Employee employee : store.getEmployeeList().getEmployeeList()) {
            employeesArray.put(employee.toJson());
        }
        json.put("employees", employeesArray);
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

}
