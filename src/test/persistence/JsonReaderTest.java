package persistence;

import model.Store;
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
        // Create a sample JSON file in tempDir before each test
        Path file = tempDir.resolve("testStore.json");
        String jsonContent = "{\n" +
                             "    \"storeHours\": [\n" +
                             "        {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"18:00\"}\n" +
                             "    ],\n" +
                             "    \"allEmployeeNeeds\": [\n" +
                             "        {\"day\": \"Monday\", \"startTime\": \"08:00\", \"endTime\": \"12:00\", \"numberOfEmployees\": 2}\n" +
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


}

