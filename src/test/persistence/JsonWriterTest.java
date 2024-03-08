package persistence;

import model.Employee;
import model.Store;
import model.DailyAvailability;
import model.EmployeeNeeds;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest {


    @TempDir
    Path tempDir; // JUnit will create and clean up a temporary directory

    Path file;

    @BeforeEach
    void setUp() {
        // Define the path for the JSON file to be written
        file = tempDir.resolve("testStore.json");
    }

    @Test
    void testWriteValidStore() throws IOException {
        Store store = new Store();
        Employee employee = new Employee("Name", "Job");

        store.getEmployeeList().addEmployee(employee);

        JsonWriter writer = new JsonWriter(file.toString());
        writer.open(); // Make sure the file is open for writing
        writer.write(store); // Write the store object to file
        writer.close(); // Close the writer

        assertTrue(Files.exists(file));
        // Optionally, you can read the file's content here and assert its JSON structure
        String content = Files.readString(file);
        assertFalse(content.isEmpty());
        // Further assertions can be made on the content if needed
    }

    @Test
    public void testWriteEmptyStore() throws IOException {
        String testFileName = "testStoreEmpty.json";
        Store store = new Store(); // Assuming Store has a public constructor
        JsonWriter writer = new JsonWriter(testFileName);

        writer.write(store);
        writer.close();

        // Now, read the file and assert its contents
        String content = new String(Files.readAllBytes(Paths.get(testFileName)), StandardCharsets.UTF_8);
        assertNotNull(content);
        assertTrue(content.contains("storeHours")); // Checks if the structure is present
        assertTrue(content.contains("allEmployeeNeeds"));
        assertTrue(content.contains("employees")); // Ensure it's handling an empty list correctly
    }

    @Test
    public void testWriteStoreWithEmployees() throws IOException {
        String testFileName = "testStoreWithEmployees.json";
        Store store = new Store();
        store.getEmployeeList().addEmployee(new Employee("Owen", "Chef"));
        JsonWriter writer = new JsonWriter(testFileName);

        writer.write(store);
        writer.close();

        // Now, read the file and assert its contents
        String content = new String(Files.readAllBytes(Paths.get(testFileName)), StandardCharsets.UTF_8);
        assertNotNull(content);
        assertTrue(content.contains("storeHours"));
        assertTrue(content.contains("allEmployeeNeeds"));
        assertTrue(content.contains("employees"));
        assertTrue(content.contains("Owen")); // Replace with actual employee name expected
    }

    @Test
    public void testWriteWithInvalidPath() throws FileNotFoundException {
        try {
            String invalidFileName = "/invalid/path/testStore.json";
            Store store = new Store(); // Assuming Store has a public constructor
            JsonWriter writer = new JsonWriter(invalidFileName);

            writer.write(store); // This should throw FileNotFoundException

        } catch (FileNotFoundException e) {
            //goodf

        }
    }

    @Test
    public void testPutStoreHoursArray() throws Exception {
        Store store = new Store();
        // Add sample store hours directly inside the test
        store.getStoreHours().add(new DailyAvailability("Monday", LocalTime.of(9, 0), LocalTime.of(17, 0)));
        store.getStoreHours().add(new DailyAvailability("Tuesday", LocalTime.of(10, 0), LocalTime.of(18, 0)));

        JSONObject json = new JSONObject();
        JsonWriter writer = new JsonWriter(""); // Path is irrelevant for this test
        writer.putStoreHoursArray(store, json);

        JSONArray storeHoursArray = json.getJSONArray("storeHours");
        assertEquals(2, storeHoursArray.length());

        // Verify the first added store hour
        JSONObject firstDay = storeHoursArray.getJSONObject(0);
        assertEquals("Monday", firstDay.getString("day"));
        assertEquals("09:00", firstDay.getString("startTime"));
        assertEquals("17:00", firstDay.getString("endTime"));

        // Verify the second added store hour
        JSONObject secondDay = storeHoursArray.getJSONObject(1);
        assertEquals("Tuesday", secondDay.getString("day"));
        assertEquals("10:00", secondDay.getString("startTime"));
        assertEquals("18:00", secondDay.getString("endTime"));
    }

    @Test
    public void testPutAllEmployeeNeedsArray() throws Exception {
        Store store = new Store();
        // Add sample employee needs
        EmployeeNeeds sampleNeed1 = new EmployeeNeeds("Wednesday", LocalTime.of(9, 0)
                , LocalTime.of(12, 0), 3);
        EmployeeNeeds sampleNeed2 = new EmployeeNeeds("Thursday", LocalTime.of(13, 0)
                , LocalTime.of(17, 0), 2);
        store.getAllEmployeeNeeds().add(sampleNeed1);
        store.getAllEmployeeNeeds().add(sampleNeed2);

        JSONObject json = new JSONObject();
        JsonWriter writer = new JsonWriter(""); // Path is irrelevant for this test
        writer.putAllEmployeeNeedsArray(store, json);

        JSONArray needsArray = json.getJSONArray("allEmployeeNeeds");
        assertEquals(2, needsArray.length());

        // Verify the first added employee need
        JSONObject firstNeed = needsArray.getJSONObject(0);
        assertEquals("Wednesday", firstNeed.getString("day"));
        assertEquals("09:00", firstNeed.getString("startTime"));
        assertEquals("12:00", firstNeed.getString("endTime"));
        assertEquals(3, firstNeed.getInt("numberOfEmployees"));

        // Verify the second added employee need
        JSONObject secondNeed = needsArray.getJSONObject(1);
        assertEquals("Thursday", secondNeed.getString("day"));
        assertEquals("13:00", secondNeed.getString("startTime"));
        assertEquals("17:00", secondNeed.getString("endTime"));
        assertEquals(2, secondNeed.getInt("numberOfEmployees"));


    }
}
