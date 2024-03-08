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

import static org.junit.jupiter.api.Assertions.*;


class JsonReaderTest {

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
    void testEmployeeParsing() throws IOException {
        Path file = tempDir.resolve("testStoreWithEmployees.json");

        String s = "            {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"12:00\"}\n";
        String t = "        {\"name\": \"John Doe\", \"job\": \"Cashier\", \"weeklyAvailability\": [\n";
        String j = "        {\"day\": \"Monday\", \"startTime\":";
        String k = " \"08:00\", \"endTime\": \"12:00\", \"numberOfEmployees\": 2}\n";
        String l = "        {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"18:00\"}\n";
        String jsonContentWithEmployees = "{\n" +
                                          "    \"storeHours\": [\n" +
                                          l +
                                          "    ],\n" +
                                          "    \"allEmployeeNeeds\": [\n" +
                                          j + k +
                                          "    ],\n" +
                                          "    \"employeeList\": [\n" +
                                          t +
                                          s +
                                          "        ]}\n" +
                                          "    ]\n" +
                                          "}";


        Files.writeString(file, jsonContentWithEmployees);

        JsonReader reader = new JsonReader(file.toString());
        Store store = reader.read();
        assertNotNull(store);
        assertFalse(store.getEmployeeList().getEmployeeList().isEmpty());
        Employee firstEmployee = store.getEmployeeList().getEmployeeList().get(0);
        assertEquals("John Doe", firstEmployee.getName());
        assertEquals("Cashier", firstEmployee.getJob());
        assertFalse(firstEmployee.getWeeklyAvailability().isEmpty());
        assertEquals("Monday", firstEmployee.getWeeklyAvailability().get(0).getDay());
    }


}

