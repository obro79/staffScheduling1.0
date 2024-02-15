package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class EmployeeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream inContent;
    private Employee employee;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    void setInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testEmployeeCreationAndConfirmation() {
        // Simulate user input for name, job, and confirmation
        String input = "John Doe\nSoftware Developer\nYes\n";
        setInput(input);

        // Create the Employee object
        employee = new Employee();

        // Test the output
        String printedContent = outContent.toString();
        assertTrue(printedContent.contains("Enter employee's name: "));
        assertTrue(printedContent.contains("Enter employee's job: "));
        assertTrue(printedContent.contains("Please Confirm (Yes/No): "));
        assertTrue(printedContent.contains("Employee's name: John Doe"));
        assertTrue(printedContent.contains("Employee's job: Software Developer"));

        assertEquals("John Doe", employee.getName());
        assertEquals("Software Developer", employee.getJob());
        assertNotNull(employee.getWeeklyAvailability());
    }


}

