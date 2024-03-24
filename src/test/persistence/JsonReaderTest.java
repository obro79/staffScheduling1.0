package persistence;

import model.Store;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

//Tests the JsonReader Class
class JsonReaderTest {

    private Store store;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        String t = "        {\"day\": \"Monday\"";
        String s = ", \"startTime\": \"08:00\", \"endTime\": \"12:00\", \"numberOfEmployees\": 2}\n";

        // Create a sample JSON file in tempDir before each test
        Path file = tempDir.resolve("testStore.json");
        String jsonContent = "{\n" +
                             "    \"storeHours\": [\n" +
                             "        {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"18:00\"}\n" +
                             "    ],\n" +
                             "    \"allEmployeeNeeds\": [\n" +
                             t + s +
                             "    ]\n" +
                             "}";


        Files.writeString(file, jsonContent);

        store = new Store();
        store.getEmployeeList().addEmployee(new Employee("John Doe","Cashier"));
    }

    @Test
    void testReadValidJson() throws IOException {
        JsonReader reader = new JsonReader(tempDir.resolve("testStore.json").toString());
        Store store = reader.read();
        assertNotNull(store);
        assertFalse(store.getStoreHours().isEmpty());
        assertFalse(store.getAllEmployeeNeeds().isEmpty());
        assertEquals("Monday", store.getStoreHours().get(0).getDay());
        assertEquals(2, store.getAllEmployeeNeeds().get(0).getNumberOfEmployees());
    }

    @Test
    void testReadInvalidJson() {
        // Assuming you have an invalid JSON format for this test
        Path file = tempDir.resolve("invalidStore.json");
        JsonReader reader = new JsonReader(file.toString());
    }



    @Test
    void testParsingEmployeeNeedsWithNoneProvided() throws IOException {
        // Create a JSON string without the "allEmployeeNeeds" array
        String json = "{\n" +
                      "    \"storeHours\": [\n" +
                      "        {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"18:00\"}\n" +
                      "    ],\n" +
                      "    \"employeeList\": [\n" +
                      "        {\"name\": \"Jane Doe\", \"job\": \"Manager\"}\n" + // Adding an employee for completene
                      "    ]\n" + // No allEmployeeNeeds field
                      "}";

        // Write the content to a temporary JSON file
        Path fileWithoutNeeds = tempDir.resolve("testStoreWithoutNeeds.json");
        Files.writeString(fileWithoutNeeds, json);

        // Perform the parsing
        JsonReader reader = new JsonReader(fileWithoutNeeds.toString());
        Store store = reader.read();

        // Validate that employee needs were not parsed since they weren't included
        assertNotNull(store);
        assertTrue(store.getAllEmployeeNeeds().isEmpty(), "Store's employee needs should be empty");
    }

    @Test
    void testParseStoreWithoutStoreHours() throws IOException {
        // Create JSON content without "storeHours"
        String s = "        {\"day\": \"Monday\", \"startTime\":";
        String json = "{\n" +
                      "    \"allEmployeeNeeds\": [\n" +
                      s + " \"09:00\", \"endTime\": \"17:00\", \"numberOfEmployees\": 3}\n" +
                      "    ]\n" + // No storeHours array
                      "}";

        // Write the JSON string to a temporary file
        Path fileWithoutStoreHours = tempDir.resolve("testStoreWithoutStoreHours.json");
        Files.writeString(fileWithoutStoreHours, json);

        // Parse the JSON
        JsonReader reader = new JsonReader(fileWithoutStoreHours.toString());
        Store store = reader.read();

        // Assert that the store does not have any store hours added
        assertNotNull(store);
        assertTrue(store.getStoreHours().isEmpty(), "Store's storeHours should be empty");
    }

    @Test
    public void testParseEmployeeWithNoAvailability() throws IOException {
        String jsonContent = "{ \"name\": \"John Doe\", \"job\": \"Cashier\" }"; // No weeklyAvailability

        JSONObject employeeObject = new JSONObject(jsonContent);
        JsonReader jsonReader = new JsonReader("");
        Employee employee = jsonReader.parseEmployee(employeeObject);

        assertNotNull(employee);
        assertEquals("John Doe", employee.getName());
        assertEquals("Cashier", employee.getJob());
        assertTrue(employee.getWeeklyAvailability().isEmpty(), "Expected empty weekly availability");
    }

//    @Test
//    public void testParseEmployeeWithInvalidAvailability() {
//        String jsonContent = "{ \"name\": \"Jane Doe\", \"job\": \"Manager\", \"weeklyAvailability\"" +
//                             ": [{ \"day\": \"Monday\", \"startTime\": \"18:00\", \"endTime\": \"08:00\" }] }";
//        JSONObject employeeObject = new JSONObject(jsonContent);
//
//        JsonReader jsonReader = new JsonReader("");
//
//
//
//        // Assuming your method should throw an IllegalArgumentException for invalid availability
//        assertThrows(IllegalArgumentException.class, () -> jsonReader.parseEmployee(employeeObject));
//    }

    @Test
    public void testParseStoreWithMultipleEmployees1() throws IOException {
        String jsonContent = "{ \"employees\": [ {\"name\": \"John Doe\", \"job\": \"Cashier\"}, {\"name\": \"" +
                             "Jane Doe\", \"job\": \"Manager\"} ] }";
        JSONObject jsonObject = new JSONObject(jsonContent);
        Store store = new Store();
        JsonReader jsonReader = new JsonReader("");

        jsonReader.parseEmployeeList(jsonObject, store);

        assertEquals(3, store.getEmployeeList().getEmployeeList().size());
    }

    @Test
    public void testParseStoreWithEmptyEmployeeList() throws IOException {
        String jsonContent = "{ \"employees\": [] }";
        JSONObject jsonObject = new JSONObject(jsonContent);
        Store store = new Store();
        JsonReader jsonReader = new JsonReader("");

        jsonReader.parseEmployeeList(jsonObject, store);

        assertFalse(store.getEmployeeList().getEmployeeList().isEmpty());
    }

    @Test
    public void testParseStoreWithMultipleEmployees() throws IOException {
        String jsonContent = "{ \"employees\": [ {\"name\": \"John Doe\", \"job\": \"Cashier\"}, {\"name\": \"" +
                             "Jane Doe\", \"job\": \"Manager\"} ] }";
        JSONObject jsonObject = new JSONObject(jsonContent);
        Store store = new Store();
        JsonReader jsonReader = new JsonReader("");

        jsonReader.parseEmployeeList(jsonObject, store);

        assertEquals(3, store.getEmployeeList().getEmployeeList().size());
    }



    @Test
    public void testParseEmployeeWithAvailability() throws IOException {
        // Prepare JSON data for an employee with weekly availability
        String jsonContent =
                "{\n" +
                "  \"name\": \"Alice Johnson\",\n" +
                "  \"job\": \"Supervisor\",\n" +
                "  \"weeklyAvailability\": [\n" +
                "    {\n" +
                "      \"day\": \"Monday\",\n" +
                "      \"startTime\": \"09:00\",\n" +
                "      \"endTime\": \"17:00\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"day\": \"Wednesday\",\n" +
                "      \"startTime\": \"10:00\",\n" +
                "      \"endTime\": \"18:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        JSONObject employeeObject = new JSONObject(jsonContent);
        JsonReader jsonReader = new JsonReader(""); // Mocked JsonReader
        Employee employee = jsonReader.parseEmployee(employeeObject);

        assertNotNull(employee);
        assertEquals("Alice Johnson", employee.getName());
        assertEquals("Supervisor", employee.getJob());
        List<DailyAvailability> availabilities = employee.getWeeklyAvailability();
        assertEquals(2, availabilities.size());
        assertEquals(LocalTime.of(9, 0), availabilities.get(0).getStartTime());
        assertEquals(LocalTime.of(17, 0), availabilities.get(0).getEndTime());
        assertEquals("Monday", availabilities.get(0).getDay());
        assertEquals(LocalTime.of(10, 0), availabilities.get(1).getStartTime());
        assertEquals(LocalTime.of(18, 0), availabilities.get(1).getEndTime());
        assertEquals("Wednesday", availabilities.get(1).getDay());
    }

    @Test
    public void testParseStoreWithMultipleEmployeesAndAvailability() throws IOException {
        // Prepare JSON data for a store with multiple employees, including their availability
        String jsonContent =
                "{\n" +
                "  \"employees\": [\n" +
                "    {\n" +
                "      \"name\": \"Alice Johnson\",\n" +
                "      \"job\": \"Supervisor\",\n" +
                "      \"weeklyAvailability\": [\n" +
                "        {\n" +
                "          \"day\": \"Monday\",\n" +
                "          \"startTime\": \"09:00\",\n" +
                "          \"endTime\": \"17:00\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Bob Smith\",\n" +
                "      \"job\": \"Sales\",\n" +
                "      \"weeklyAvailability\": [\n" +
                "        {\n" +
                "          \"day\": \"Tuesday\",\n" +
                "          \"startTime\": \"10:00\",\n" +
                "          \"endTime\": \"18:00\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        JSONObject jsonObject = new JSONObject(jsonContent);
        JsonReader jsonReader = new JsonReader(""); // Mocked JsonReader
        Store store = jsonReader.parseStore(jsonObject);

        assertNotNull(store);
        assertEquals(3, store.getEmployeeList().getEmployeeList().size());
        Employee firstEmployee = store.getEmployeeList().getEmployeeList().get(0);
        assertEquals("John Doe", firstEmployee.getName());
        assertEquals(0, firstEmployee.getWeeklyAvailability().size());
        Employee secondEmployee = store.getEmployeeList().getEmployeeList().get(1);
        assertEquals("Alice Johnson", secondEmployee.getName());
        assertEquals(1, secondEmployee.getWeeklyAvailability().size());
    }
}








