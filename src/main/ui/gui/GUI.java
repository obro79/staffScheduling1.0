package ui.gui;

import ui.StoreApp;
import model.*;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.ArrayList;



public class GUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private StoreApp storeApp;

    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday"};
    private static final String[]  TIMES = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
            "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
            "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
            "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
            "21:30", "22:00", "22:30", "23:00", "23:30"};

    public GUI(StoreApp storeApp) {
        this.storeApp = storeApp;
    }

    public void initializeUI() {
        this.frame = new JFrame("Schedule Manager");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(600, 400));

        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout); // cardPanel must be initialized before adding panels to it

        // Initialize panels
        JPanel mainPanel = mainWindow(); // Ensure this method does not return null
        this.cardPanel.add(mainPanel, "Main");

        initializePanels();

        this.frame.add(cardPanel, BorderLayout.CENTER);
        this.frame.pack(); // Adjust window size based on components
        this.frame.setLocationRelativeTo(null); // Center window on screen
        this.cardLayout.show(cardPanel, "Main");
        this.frame.setVisible(true);
    }

    private void initializePanels() {
        // Main window panel
        JPanel mainPanel = mainWindow();
        this.cardPanel.add(mainPanel, "Main");

        JPanel employeeSettingsWindow = employeeSettingsWindow();
        this.cardPanel.add(employeeSettingsWindow, "Employee Settings");

        // Store settings window panel
        JPanel storeSettingsPanel = storeSettingsPanel();
        cardPanel.add(storeSettingsPanel, "Store Settings");

        // Add employee window panel
        JPanel addEmployeePanel = addEmployeePanel();
        cardPanel.add(addEmployeePanel, "Add Employee");

        JPanel updateEmployeeAvailabilityPanel = updateEmployeeAvailabilityPanel();
        cardPanel.add(updateEmployeeAvailabilityPanel, "Update Employee Availability");

        JPanel employeeInfoWindow = employeeInfoWindow();
        cardPanel.add(employeeInfoWindow, "Employee Info");

        // Store hours update window panel
        JPanel updateStoreHoursPanel = updateStoreHoursPanel();
        cardPanel.add(updateStoreHoursPanel, "Update Store Hours");

        // Scheduling needs update window panel
        JPanel updateSchedulingNeedsPanel = updateSchedulingNeedsPanel();
        cardPanel.add(updateSchedulingNeedsPanel, "Update Scheduling Needs");

        // View all store attributes window panel
//        JPanel viewAllStoreAttributesPanel = viewAllStoreAttributes();
//        cardPanel.add(viewAllStoreAttributesPanel, "View All Store Attributes");
    }

    private JPanel mainWindow() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Each button in its own row
        gbc.fill = GridBagConstraints.HORIZONTAL;      // Make button fill the horizontal space
        gbc.insets = new Insets(3, 10, 3, 10);


        JButton loadStoreButton = createButton("Load Store", e -> loadStore());
        JButton saveStoreButton = createButton("Save Store", e -> saveStore());
        JButton employeeSettingsButton = createButton("Employee Settings", e -> switchToCard("Employee Settings"));
        JButton scheduleSettingsButton = createButton("Schedule Settings", e -> switchToCard("Schedule Settings"));
        JButton viewAllStoreAttributesButton = createButton("View All Store Attributes", e -> viewAllStoreAttributes());

        panel.add(loadStoreButton, gbc);
        panel.add(saveStoreButton, gbc);
        panel.add(employeeSettingsButton, gbc);
        panel.add(scheduleSettingsButton, gbc);
        panel.add(viewAllStoreAttributesButton, gbc);


         // Add the grid panel to the centeredPanel with constraints

        return panel;
    }

    private JPanel employeeSettingsWindow() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Each button in its own row
        gbc.fill = GridBagConstraints.HORIZONTAL;      // Make button fill the horizontal space
        gbc.insets = new Insets(3, 10, 3, 10);        // Set some padding

        JButton addEmployeeButton = createButton("Add Employee", e -> switchToCard("Add Employee"));
        JButton updateEmployeeAvailabilityButton = createButton("Update Employee Availability", e -> switchToCard("Update Employee Availability"));
        JButton viewEmployeeListButton = createButton("View Employee List", e -> switchToCard("Employee Info"));
        JButton backButton = createButton("Back to Main", e -> switchToCard("Main"));

        panel.add(addEmployeeButton, gbc);
        panel.add(updateEmployeeAvailabilityButton, gbc);
        panel.add(viewEmployeeListButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }



    private JPanel employeeInfoWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea employeeInfoTextArea = new JTextArea();
        employeeInfoTextArea.setEditable(false); // Make the text area read-only
        JScrollPane scrollPane = new JScrollPane(employeeInfoTextArea); // Enable scrolling
        panel.add(scrollPane, BorderLayout.CENTER);

        // Populate the text area with employee information
        StringBuilder infoBuilder = new StringBuilder();
        List<Employee> employeeList = storeApp.getThisStore().getEmployeeList().getEmployeeList(); // Assuming this method exists
        for (Employee employee : employeeList) {
            infoBuilder.append("Name: ").append(employee.getName())
                    .append(", Job: ").append(employee.getJob())
                    .append(", Availability: ").append(employee.getWeeklyAvailability().toString()) // Format as needed
                    .append("\n");
        }
        employeeInfoTextArea.setText(infoBuilder.toString());

        // Back button to return to the main menu or previous screen
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> switchToCard("Main"));
        JPanel backButtonPanel = new JPanel(); // Use a panel to better control the layout of the back button
        backButtonPanel.add(backButton);
        panel.add(backButtonPanel, BorderLayout.PAGE_END);

        return panel;
    }



    public JPanel viewAllStoreAttributes() {
        return null;
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void switchToCard(String cardName) {
        System.out.println("Switching to card: " + cardName); // Debug print statement
        cardLayout.show(cardPanel, cardName);
    }


    private JPanel addEmployeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margins between components
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0

        JLabel nameLabel = new JLabel("Name:");
        JLabel jobLabel = new JLabel("Job Title:");

        JTextField nameField = new JTextField(20); // Suggest a column count to size the field
        JTextField jobField = new JTextField(20); // Suggest a column count to size the field

        JButton saveButton = createButton("Save Employee", e -> {
            String name = nameField.getText().trim();
            String job = jobField.getText().trim();
            // Implement adding the employee to the store's employee list
            // For example: storeApp.addEmployee(new Employee(name, job));
            JOptionPane.showMessageDialog(frame, "Employee added successfully.");
            switchToCard("Main");
        });

        panel.add(nameLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(nameField, gbc);

        gbc.gridx = 0; // Reset to Column 0
        gbc.gridy++; // Move to next row
        panel.add(jobLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(jobField, gbc);

        gbc.gridwidth = 2; // Span across two columns
        gbc.gridy++; // Move to next row
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(saveButton, gbc);

        return panel;
    }


    public JPanel updateEmployeeAvailabilityPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margins between components
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0

        JLabel nameLabel = new JLabel("Employee Name:");
        JLabel dayLabel = new JLabel("Day:");
        JLabel startTimeLabel = new JLabel("Start Time:");
        JLabel endTimeLabel = new JLabel("End Time:");

        JTextField nameField = new JTextField(20); // Suggest a column count to size the field
        JComboBox<String> dayDropdown = new JComboBox<>(DAYS_OF_WEEK);
        JComboBox<String> startTimeDropdown = new JComboBox<>(TIMES);
        JComboBox<String> endTimeDropdown = new JComboBox<>(TIMES);
        JCheckBox sameForWeek = new JCheckBox("Apply to entire week");

        JButton nextButton = createButton("Next", e -> {
            // ... existing button functionality ...
        });

        panel.add(nameLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(nameField, gbc);

        gbc.gridx = 0; // Reset to Column 0
        gbc.gridy++; // Move to next row
        panel.add(dayLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(dayDropdown, gbc);

        gbc.gridx = 0; // Reset to Column 0
        gbc.gridy++; // Move to next row
        panel.add(startTimeLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(startTimeDropdown, gbc);

        gbc.gridx = 0; // Reset to Column 0
        gbc.gridy++; // Move to next row
        panel.add(endTimeLabel, gbc);

        gbc.gridx = 1; // Column 1
        panel.add(endTimeDropdown, gbc);

        gbc.gridx = 0; // Column 0
        gbc.gridy++; // Move to next row
        gbc.gridwidth = 2; // Span across two columns
        panel.add(sameForWeek, gbc);

        gbc.gridy++; // Move to next row
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(nextButton, gbc);

        return panel;
    }


    private void saveStore() {
        try {
            storeApp.saveAllStoreAttributes();
            JOptionPane.showMessageDialog(frame, "Store saved successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error saving the store: " + e.getMessage());
        }
    }

    private void loadStore() {
        try {
            storeApp.loadAllStoreAttributes();
            JOptionPane.showMessageDialog(frame, "Store loaded successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading the store: " + e.getMessage());
        }
    }

    private JPanel storeSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 5, 5));

        JButton updateStoreHoursButton = createButton("Update Store Hours",
                e -> switchToCard("Update Store Hours"));
        JButton updateSchedulingNeedsButton = createButton("Update Scheduling Needs",
                e -> switchToCard("Update Scheduling Needs"));

        panel.add(updateStoreHoursButton);
        panel.add(updateSchedulingNeedsButton);

        return panel;
    }

    private JPanel updateStoreHoursPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        JComboBox<String> dayDropdown = new JComboBox<>(DAYS_OF_WEEK);
        JComboBox<String> openTimeDropdown = new JComboBox<>(TIMES);
        JComboBox<String> closeTimeDropdown = new JComboBox<>(TIMES);

        JButton saveButton = createButton("Save Hours", e -> {
            String day = (String) dayDropdown.getSelectedItem();
            String openTime = (String) openTimeDropdown.getSelectedItem();
            String closeTime = (String) closeTimeDropdown.getSelectedItem();
            // Implement saving the store hours for the selected day
            // Example: storeApp.updateStoreHours(day, openTime, closeTime);
            JOptionPane.showMessageDialog(panel, "Hours updated for " + day);
        });

        panel.add(new JLabel("Day:"));
        panel.add(dayDropdown);
        panel.add(new JLabel("Open Time:"));
        panel.add(openTimeDropdown);
        panel.add(new JLabel("Close Time:"));
        panel.add(closeTimeDropdown);
        panel.add(saveButton);

        return panel;
    }

    private JPanel updateSchedulingNeedsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        JComboBox<String> dayDropdown = new JComboBox<>(DAYS_OF_WEEK);
        JTextField numEmployeesField = new JTextField();

        JButton saveButton = createButton("Save Needs", e -> {
            String day = (String) dayDropdown.getSelectedItem();
            int numEmployees;
            try {
                numEmployees = Integer.parseInt(numEmployeesField.getText().trim());
                // Implement saving the scheduling needs
                // Example: storeApp.updateSchedulingNeeds(day, numEmployees);
                JOptionPane.showMessageDialog(panel, "Scheduling needs updated for " + day);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid number of employees.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("Day:"));
        panel.add(dayDropdown);
        panel.add(new JLabel("Number of Employees Needed:"));
        panel.add(numEmployeesField);
        panel.add(saveButton);

        return panel;
    }
}


//Makes the graphical user interface
//TODO store hours isnt looping through days of the week like it should
//TODO maybe i should convert the lists to sets to not allow duplicates

//public class GUI implements ActionListener {
//
//    private JFrame frame;
//    private CardLayout cardLayout;
//    private JPanel cardPanel;
//
//    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//    JComboBox<String> dayDropdown = ComboBox.createDropdown(daysOfWeek);
//    String[] times = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
//    "05:00",
//            "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
//            "11:00",
//            "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
//            "17:00",
//            "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30",
//            "23:00", "23:30"};
//
//    private int currentDayIndex = 0;
//    JComboBox<String> hourTimes = ComboBox.createDropdown(times);
//
//    private StoreApp storeApp;
//
//    public GUI(StoreApp storeApp) {
//        this.storeApp = storeApp;
//        initializeUI();
//    }
//
//    public void initializeUI() {
//        frame = new JFrame("Schedule Manager");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400); // Set your desired size
//
//        cardLayout = new CardLayout();
//        cardPanel = new JPanel(cardLayout);
//
//        // Initialize and add panels to the cardPanel
//        initializePanels();
//
//        frame.add(cardPanel); // Add the cardPanel to the frame
//
//        switchToCard("Main"); // Switch to the main panel at startup
//
//        frame.setVisible(true);
//    }
//
//
//    public JPanel mainWindow() {
//        JPanel panel = new JPanel();
//
//        // Buttons
//        JButton loadStoreButton = createButton("Load Store", createActionListener("Load Store"));
//        JButton saveStoreButton = createButton("Save", createActionListener("Save"));
//        JButton employeeSettingsButton =
//                createButton("Employee Settings", createActionListener("Employee Settings"));
//        JButton scheduleSettingsButton =
//                createButton("Schedule Settings", createActionListener("Schedule Settings"));
//        JButton viewAllStoreAttributesButton = createButton("View All Store Attributes",
//                createActionListener("View All Store Attributes"));
//
//        // Adding buttons to panel
//        panel.add(loadStoreButton);
//        panel.add(scheduleSettingsButton);
//        panel.add(employeeSettingsButton);
//        panel.add(viewAllStoreAttributesButton);
//        panel.add(saveStoreButton);
//
//        // Display main frame
//        displayFrame(frame);
//
//        return panel;
//    }
//
//    public void initializePanels() {
//
//        JPanel mainWindow = mainWindow();
//        cardPanel.add(mainWindow, "Main");
//
//        JPanel employeeSettingsPanel = employeeSettingsWindow();
//        cardPanel.add(employeeSettingsPanel, "Employee Settings");
//
//        JPanel storeSettingsWindow = storeSettingsWindow();
//        cardPanel.add(storeSettingsWindow, "Schedule Settings");
//
//        JPanel updateStoreHoursWindow = updateStoreHoursWindow();
//        cardPanel.add(updateStoreHoursWindow, "Store Hours");
//
//        JPanel addEmployeeWindow = addEmployeeWindow();
//        cardPanel.add(addEmployeeWindow, "Add Employee");
//
//        JPanel updateEmployeeAvailabilityWindow = updateEmployeeAvailabilityWindow();
//        cardPanel.add(updateEmployeeAvailabilityWindow, "Update Employee Availability");
//
//        JPanel employeeInfoWindow = employeeInfoWindow();
//        cardPanel.add(employeeInfoWindow, "Employee Info");
//
//        JPanel updateSchedulingNeedsWindow = updateSchedulingNeedsWindow();
//        cardPanel.add(updateSchedulingNeedsWindow, "Update Schedule Needs");
//
//        JPanel viewStoreHoursWindow = viewStoreHoursWindow();
//        cardPanel.add(viewStoreHoursWindow, "Store Hours");
//
////        JPanel viewAllStoreAttributesWindow = viewAllStoreAttributesWindow();
////        cardPanel.add(viewAllStoreAttributesWindow, "Employee Info");
//
//    }
//
//    private JPanel employeeSettingsWindow() {
//
//        JPanel panel = new JPanel();
//
//        JButton addEmployeeButton = createButton("Add Employee", createActionListener("Add Employee"));
//        JButton updateEmployeeAvailabilityButton = createButton("Update Employee Availability", createActionListener
//        ("Update Employee Availability"));
//        JButton getEmployeeListButton = createButton("View Employee List", createActionListener
//        ("View Employee List"));
//        JButton saveStoreButton = createButton("Save", createActionListener("Save"));
//
//        panel.add(addEmployeeButton);
//        panel.add(updateEmployeeAvailabilityButton);
//        panel.add(getEmployeeListButton);
//        panel.add(saveStoreButton);
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Return to Main"));
//        panel.add(backToEmployeeSettings);
//
//        return panel;
//    }
//
//    private JPanel storeSettingsWindow() {
//
//        JPanel panel = new JPanel();
//
//        JButton updateStoreHoursButton = createButton("Update Store Hours", createActionListener
//        ("Update Store Hours"));
//        JButton getStoreHoursButton = createButton("Get Store Hours", createActionListener("Get Store Hours"));
//        JButton updateSchedulingNeedsButton = createButton("Update Scheduling Needs", createActionListener
//        ("Update Scheduling Needs"));
//        JButton saveStoreButton = createButton("Save", createActionListener("Save"));
//
//        panel.add(updateStoreHoursButton);
//        panel.add(getStoreHoursButton);
//        panel.add(updateSchedulingNeedsButton);
//        panel.add(saveStoreButton);
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Return to Main"));
//        panel.add(backToEmployeeSettings);
//
//
//        return panel;
//    }
//
//    public JPanel updateStoreHoursWindow() {
//        JPanel panel = new JPanel();
//        JLabel dayLabel = new JLabel("Day: " + daysOfWeek[currentDayIndex]);
//
//        // Assume these panels each contain a JComboBox<String> for time selection
//        JPanel openTimePanel = createTimePanel("Open Time:");
//        JPanel closeTimePanel = createTimePanel("Close Time:");
//
//        // Extracting JComboBoxes directly if they're the only component in the panel
//        JComboBox<String> openTimeComboBox = (JComboBox<String>) openTimePanel.getComponent(1);
//        JComboBox<String> closeTimeComboBox = (JComboBox<String>) closeTimePanel.getComponent(1);
//
//        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");
//        panel.add(dayLabel);
//        panel.add(openTimePanel);
//        panel.add(closeTimePanel);
//        panel.add(closedCheckbox);
//
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(e -> {
//            String openTime = (String) openTimeComboBox.getSelectedItem();
//            String closeTime = (String) closeTimeComboBox.getSelectedItem();
//            boolean isClosed = closedCheckbox.isSelected();
//            saveStoreHours(daysOfWeek[currentDayIndex], openTime, closeTime, isClosed);
//        });
//        panel.add(saveButton);
//
//        JButton backButton = createButton("Back", createActionListener("Schedule Settings"));
//        panel.add(backButton);
//
//        return panel;
//    }
//
//    public JPanel addEmployeeWindow() {
//
//        JPanel panel = new JPanel();
//
//        panel.add(new JLabel("Name:"));
//        JTextField nameTextField = createTextField(20);
//        panel.add(nameTextField);
//
//        panel.add(new JLabel("Job:"));
//        JTextField jobTextField = createTextField(20);
//        panel.add(jobTextField);
//
//        JButton saveEmployeeButton = new JButton("Save");
//        saveEmployeeButton.addActionListener(e -> saveEmployee(nameTextField.getText(), jobTextField.getText()));
//        panel.add(saveEmployeeButton);
//
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Employee Settings"));
//        panel.add(backToEmployeeSettings);
//        panel.add(saveEmployeeButton);
//
//        return panel;
//
//    }
//
//    public JPanel updateEmployeeAvailabilityWindow() {
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Employee's Name:"));
//        JTextField name = new JTextField();
//        panel.add(name);
//        JLabel dayLabel = new JLabel("Day: " + daysOfWeek[currentDayIndex]);
//        panel.add(dayLabel);
//
//        // Assuming createTimePanel creates a JPanel with a JComboBox<String> for time selection
//        JPanel openTimePanel = createTimePanel("Start Time:");
//        JPanel closeTimePanel = createTimePanel("End Time:");
//        JComboBox<String> openTimeComboBox = (JComboBox<String>) openTimePanel.getComponent(1);
//        JComboBox<String> closeTimeComboBox = (JComboBox<String>) closeTimePanel.getComponent(1);
//
//        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");
//        panel.add(openTimePanel);
//        panel.add(closeTimePanel);
//        panel.add(closedCheckbox);
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Employee Settings"));
//        panel.add(backToEmployeeSettings);
//
//        JButton nextButton = createButton("Next", e -> {
//            String openTime = (String) openTimeComboBox.getSelectedItem();
//            String closeTime = (String) closeTimeComboBox.getSelectedItem();
//            boolean isClosed = closedCheckbox.isSelected();
//            String empName = name.getText();
//
//            // Save current employee availability
//            saveEmployeeAvailability(daysOfWeek[currentDayIndex], openTime, closeTime, isClosed, empName);
//
//            if (closedCheckbox.isSelected() || currentDayIndex >= daysOfWeek.length - 1) {
//                JOptionPane.showMessageDialog(frame, "Employee availability updated for all days.");
//                currentDayIndex = 0; // Reset for next use
//            } else {
//                currentDayIndex += 1; // Move to the next day
//                updateEmployeeAvailabilityWindow(); // Refresh the window for the next day
//            }
//        });
//        panel.add(nextButton);
//
//        return panel;
//    }
//
//    public JPanel employeeInfoWindow() {
//        JPanel contentPanel = new JPanel(new BorderLayout()); // Use BorderLayout for better arrangement
//
//
//        JTextArea employeeInfoTextArea = new JTextArea(15, 30); // Adjust size as needed
//        employeeInfoTextArea.setEditable(false); // Make it read-only
//        employeeInfoTextArea.setLineWrap(true);
//        employeeInfoTextArea.setWrapStyleWord(true);
//
//        // Assuming storeApp.getThisStore().getEmployeeList() returns a list of Employee objects
//        StringBuilder employeeInfoBuilder = new StringBuilder("Employee List:\n");
//        List<Employee> employeeList = storeApp.getThisStore().getEmployeeList().getEmployeeList();
//        for (Employee employee : employeeList) {
//            employeeInfoBuilder.append(employee.getName()).append(",");
//                     // Customize based on your Employee class structure
//        }
//
//        for (Employee e : employeeList) {
//            employeeInfoBuilder.append("\n").append("Name: ").append(e.getName()).append("\n")
//                    .append("Job").append(e.getJob()).append("\n").append(e.getWeeklyAvailability());
//        }
//
//        employeeInfoTextArea.setText(employeeInfoBuilder.toString());
//
//        JScrollPane scrollPane = new JScrollPane(employeeInfoTextArea);
//        contentPanel.add(scrollPane, BorderLayout.CENTER);
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
//        contentPanel.add(backToEmployeeSettings, BorderLayout.PAGE_END);
//
//
//        return contentPanel;
//    }
//
//    public JPanel updateSchedulingNeedsWindow() {
//
//        JPanel panel = new JPanel();
//
//        JLabel dayLabel = new JLabel("Day: " + daysOfWeek[currentDayIndex]);
//        panel.add(dayLabel);
//
//        JLabel numberOfEmployees = new JLabel("Number of Employees Needed");
//        JTextField numberOfEmp = new JTextField();
//        JPanel openTimePanel = createTimePanel("Starting Time:");
//        JPanel closeTimePanel = createTimePanel("End Time:");
//        JComboBox<String> openTimeComboBox = (JComboBox<String>) openTimePanel.getComponent(1);
//        JComboBox<String> closeTimeComboBox = (JComboBox<String>) closeTimePanel.getComponent(1);
//
//        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");
//
//        panel.add(openTimePanel);
//        panel.add(closeTimePanel);
//        panel.add(numberOfEmployees);
//        panel.add(numberOfEmp);
//        panel.add(closedCheckbox);
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
//        panel.add(backToEmployeeSettings);
//
//        JButton nextButton = createButton("Next", e -> {
//            String openTime = (String) openTimeComboBox.getSelectedItem();
//            String closeTime = (String) closeTimeComboBox.getSelectedItem();
//            String numberOfEmployeesNeeded = numberOfEmp.getText();
//            boolean isClosed = closedCheckbox.isSelected();
//
//            // Save current scheduling needs
//            saveSchedulingNeeds(daysOfWeek[currentDayIndex], openTime, closeTime, isClosed, numberOfEmployeesNeeded);
//
//            if (closedCheckbox.isSelected() || currentDayIndex >= daysOfWeek.length - 1) {
//                JOptionPane.showMessageDialog(frame, "Scheduling needs updated for all days.");
//                currentDayIndex = 0; // Reset for the next use
//
//            } else {
//                currentDayIndex += 1; // Move to the next day
//                updateSchedulingNeedsWindow(); // Refresh the window for the next day
//            }
//        });
//
//        panel.add(nextButton);
//
//        return panel;
//
//    }
//
//    public JPanel viewStoreHoursWindow() {
//
//        JPanel contentPanel = new JPanel(new BorderLayout());
//
//        JTextArea employeeInfoTextArea = new JTextArea(15, 30);
//        employeeInfoTextArea.setEditable(false); // Make it read-only
//        employeeInfoTextArea.setLineWrap(true);
//        employeeInfoTextArea.setWrapStyleWord(true);
//
//        StringBuilder employeeInfoBuilder = new StringBuilder("Store Hours:\n");
//        List<DailyAvailability> storeHours = storeApp.getThisStore().getStoreHours();
//        for (DailyAvailability da : storeHours) {
//            employeeInfoBuilder.append(da);
//
//        }
//
//        employeeInfoTextArea.setText(employeeInfoBuilder.toString());
//
//        JScrollPane scrollPane = new JScrollPane(employeeInfoTextArea);
//        contentPanel.add(scrollPane, BorderLayout.CENTER);
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
//        contentPanel.add(backToEmployeeSettings, BorderLayout.PAGE_END);
//
//
//        return contentPanel;
//
//    }
//
//    public JPanel viewAllStoreAttributesWindow() {
//        JPanel panel = new JPanel();
//
//        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
//        panel.add(backToEmployeeSettings);
//
//        return panel;
//    }
//
//    public ActionListener createActionListener(String command) {
//        return new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                switch (command) {
//                    case "Load Store":
//                        loadStore();
//                        break;
//                    case "Save":
//                        saveStore();
//                        break;
//                    case "Employee Settings":
//                        switchToCard("Employee Settings");
//                        break;
//                    case "Schedule Settings":
//                        switchToCard("Schedule Settings");
//                        break;
//                    case "View All Store Attributes":
//                        switchToCard("View All Store Attributes");
//                        break;
//                    case "Add Employee": switchToCard("Add Employee");
//                        break;
//                    case "Update Employee Availability": switchToCard("Update Employee Availability");
//                        break;
//                    case "View Employee List": switchToCard("View Employee List");
//                        break;
//                    case "Update Store Hours": switchToCard("Update Store Hours");
//                        break;
//                    case "View Store Hours": switchToCard("View Store Hours");
//                        break;
//                    case "Update Scheduling Needs": switchToCard("Update Scheduling Needs");
//                        break;
//                    case "Return to Main": switchToCard("Schedule Manager");
//                        break;
//                    default:
//                        System.out.println("Command not recognized: " + command);
//                }
//            }
//        };
//    }
//
//    public void saveStoreHours(String day, String open, String close, boolean restWeek) {
//        LocalTime openTime = LocalTime.parse(open);
//        LocalTime closeTime = LocalTime.parse(close);
//        List<DailyAvailability> storeHours = this.storeApp.getThisStore().getStoreHours();
//        if (restWeek) {
//            for (int i = currentDayIndex; i <= daysOfWeek.length - 1; i++) {
//                storeHours.add(new DailyAvailability(daysOfWeek[i], openTime, closeTime));
//            }
//        }
//        storeHours.add(new DailyAvailability(day, openTime, closeTime));
//        try {
//            this.storeApp.saveAllStoreAttributes();
//        } catch (FileNotFoundException e) {
//            //
//        }
//    }
//
//    public void saveEmployee(String name, String job) {
//
//        Employee newEmployee = new Employee(name, job);
//        EmployeeList.getInstance().addEmployee(newEmployee);
//        try {
//            this.storeApp.saveAllStoreAttributes();
//        } catch (FileNotFoundException e) {
//            //
//        }
//    }
//
//    public void saveEmployeeAvailability(String day, String open, String close, boolean restWeek, String name) {
//        LocalTime openTime = LocalTime.parse(open);
//        LocalTime closeTime = LocalTime.parse(close);
//        EmployeeList employeeList = this.storeApp.getThisStore().getEmployeeList();
//
//        boolean employeeExists = false;
//        for (Employee e : employeeList.getEmployeeList()) {
//            if (e.getName().equalsIgnoreCase(name)) {
//                employeeExists = true;
//                // Process the logic for existing employee
//                if (restWeek) {
//                    for (int i = currentDayIndex; i < daysOfWeek.length; i++) {
//
//                        e.getWeeklyAvailability().add(new DailyAvailability(daysOfWeek[i], openTime, closeTime));
//                    }
//                } else {
//
//                    e.getWeeklyAvailability().add(new DailyAvailability(day, openTime, closeTime));
//                }
//
//                try {
//                    this.storeApp.saveAllStoreAttributes();
//                } catch (FileNotFoundException ex) {
//                  //
//                }
//            }
//        }
//
//        if (!employeeExists) {
//            JOptionPane.showMessageDialog(null, "Employee doesn't exist: " + name);
//            updateEmployeeAvailabilityWindow();
//        }
//    }
//
//    public void saveSchedulingNeeds(String day, String open, String close, boolean isClosed, String employeeNumber) {
//        List<EmployeeNeeds> employeeNeeds = this.storeApp.getThisStore().getAllEmployeeNeeds();
//        LocalTime openTime = LocalTime.parse(open);
//        LocalTime closeTime = LocalTime.parse(close);
//        int numberOfEmployees = Integer.parseInt(employeeNumber);
//
//        if (isClosed) {
//            for (int i = currentDayIndex; i < daysOfWeek.length; i++) {
//                employeeNeeds.add(new EmployeeNeeds(daysOfWeek[i], openTime, closeTime, numberOfEmployees));
//            }
//            this.currentDayIndex = 0;
//        }
//        employeeNeeds.add(new EmployeeNeeds(daysOfWeek[currentDayIndex], openTime, closeTime, numberOfEmployees));
//        this.currentDayIndex++;
//        try {
//            this.storeApp.saveAllStoreAttributes();
//        } catch (FileNotFoundException e) {
//            //
//        }
//    }
//
//    private JPanel createStandardPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
//        panel.setLayout(new GridLayout(0, 1));
//        return panel;
//    }
//
//    private JFrame createStandardFrame(String title) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle(title);
//        frame.add(createStandardPanel(), BorderLayout.CENTER);
//        frame.pack();
//        return frame;
//    }
//
//    private JButton createButton(String text, ActionListener listener) {
//        JButton button = new JButton(text);
//        button.addActionListener(listener);
//        return button;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String command = e.getActionCommand();
//        switch (command) {
//            case "Load Store":
//                loadStore();
//                break;
//            case "Save":
//                saveStore();
//                break;
//            case "Employee Settings":
//                employeeSettingsWindow();
//                break;
//
//        }
//    }
//
//    private JPanel createTimePanel(String label) {
//        JPanel timePanel = new JPanel(new GridLayout(1, 2));
//        timePanel.add(new JLabel(label));
//        timePanel.add(new JComboBox<>(times));
//        return timePanel;
//    }
//
//    private void displayFrame(JFrame frame) {
//        frame.pack();
//        frame.setLocationRelativeTo(null); // Center on screen
//        frame.setVisible(true);
//    }
//
//    private void loadStore() {
//        this.storeApp.loadAllStoreAttributes();;
//    }
//
//    private void saveStore() {
//        try {
//            this.storeApp.saveAllStoreAttributes();
//        } catch (FileNotFoundException e) {
//            //
//        }
//    }
//
//    private JTextField createTextField(int columns) {
//        JTextField textField = new JTextField(columns);
//        return textField;
//    }
//
//    public void switchToCard(String cardName) {
//        cardLayout.show(cardPanel, cardName);
//    }
//

