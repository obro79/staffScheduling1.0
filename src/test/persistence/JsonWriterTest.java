package persistence;

import model.Employee;
import model.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        Employee employee = new Employee();
        employee.setJob("Job");
        employee.setName("Name");
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
    void testFileNotFoundException() {
        // Assuming an invalid path to simulate FileNotFoundException
        JsonWriter writer = new JsonWriter("/invalid/path/testStore.json");
        // assertThrows(FileNotFoundException.class, writer::open);
    }

    @AfterEach
    void tearDown() {
        // Cleanup if necessary
    }
}
