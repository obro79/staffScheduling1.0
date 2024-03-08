package persistence;

import model.Employee;
import model.Store;
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
        Employee employee = new Employee("Name","Job");

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


}
