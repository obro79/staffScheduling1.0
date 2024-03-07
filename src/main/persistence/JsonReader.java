package persistence;

import model.DailyAvailability;

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

public class JsonReader {
    // Represents a reader that reads workroom from JSON data stored in file
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Store read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStore(jsonObject);
    }


    private Store parseStore(JSONObject jsonObject) {
        Store store = Store.getInstance();

        // Parse storeHours
        JSONArray storeHoursArray = jsonObject.getJSONArray("storeHours");
        for (int i = 0; i < storeHoursArray.length(); i++) {
            JSONObject hourObject = storeHoursArray.getJSONObject(i);
            DailyAvailability availability = new DailyAvailability(
                    hourObject.getString("day"),
                    LocalTime.parse(hourObject.getString("startTime")),
                    LocalTime.parse(hourObject.getString("endTime"))
            );
            store.getStoreHours().add(availability);
        }

        // Parse allEmployeeNeeds
        JSONArray needsArray = jsonObject.getJSONArray("allEmployeeNeeds");
        for (int i = 0; i < needsArray.length(); i++) {
            JSONObject needObject = needsArray.getJSONObject(i);
            EmployeeNeeds needs = new EmployeeNeeds(
                    needObject.getString("day"),
                    LocalTime.parse(needObject.getString("startTime")),
                    LocalTime.parse(needObject.getString("endTime")),
                    needObject.getInt("numberOfEmployees")
            );
            store.getAllEmployeeNeeds().add(needs);
        }

        return store;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


}
