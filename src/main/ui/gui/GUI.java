package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




//Makes the graphical user interface

public class GUI implements ActionListener {

    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    JComboBox<String> dayDropdown = ComboBox.createDropdown(daysOfWeek);
    String[] times = { "00:00", "00:30","01:00","01:30","02:00","02:30","03:00","03:30", "04:00","04:30", "05:00",
            "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00",
            "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
            "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30",
            "23:00", "23:30"};

    JComboBox<String> hourTimes = ComboBox.createDropdown(times);


    public GUI() {
        // Main frame setup
        JFrame frame = createStandardFrame("Schedule Manager");

        // Panel
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        // Buttons
        JButton loadStoreButton = createButton("Load Store", createActionListener("Load Store"));
        JButton saveStoreButton = createButton("Save", createActionListener("Save"));
        JButton employeeSettingsButton =
                createButton("Employee Settings", createActionListener("Employee Settings"));
        JButton scheduleSettingsButton =
                createButton("Schedule Settings", createActionListener("Schedule Settings"));
        JButton viewAllStoreAttributesButton = createButton("View All Store Attributes",
                createActionListener("View All Store Attributes"));

        // Adding buttons to panel
        panel.add(loadStoreButton);
        panel.add(scheduleSettingsButton);
        panel.add(employeeSettingsButton);
        panel.add(viewAllStoreAttributesButton);
        panel.add(saveStoreButton);

        // Display main frame
        displayFrame(frame);
    }

    private void employeeSettingsWindow() {
        JFrame frame = createStandardFrame("Employee Settings");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        JButton addEmployeeButton = createButton("Add Employee", createActionListener("Add Employee"));
        JButton updateEmployeeAvailabilityButton = createButton("Update Employee Availability", createActionListener("Update Employee Availability"));
        JButton getEmployeeListButton = createButton("View Employee List", createActionListener("View Employee List"));
        JButton saveStoreButton = createButton("Save", createActionListener("Save"));

        panel.add(addEmployeeButton);
        panel.add(updateEmployeeAvailabilityButton);
        panel.add(getEmployeeListButton);
        panel.add(saveStoreButton);

        JButton backToEmployeeSettings = createButton("Back", createActionListener("Return to Main"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);
    }

    private void storeSettingsWindow() {
        JFrame frame = createStandardFrame("Schedule Settings");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        JButton updateStoreHoursButton = createButton("Update Store Hours", createActionListener("Update Store Hours"));
        JButton getStoreHoursButton = createButton("Get Store Hours", createActionListener("Get Store Hours"));
        JButton updateSchedulingNeedsButton = createButton("Update Scheduling Needs", createActionListener("Update Scheduling Needs"));
        JButton saveStoreButton = createButton("Save", createActionListener("Save"));

        panel.add(updateStoreHoursButton);
        panel.add(getStoreHoursButton);
        panel.add(updateSchedulingNeedsButton);
        panel.add(saveStoreButton);
        JButton backToEmployeeSettings = createButton("Back", createActionListener("Return to Main"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);
    }

    private void updateStoreHoursWindow() {
        JFrame frame = createStandardFrame("Store Hours");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        panel.add(new JLabel("Day:"));
        panel.add(dayDropdown);

        JPanel openTimePanel = createTimePanel("Open Time:");
        JPanel closeTimePanel = createTimePanel("Close Time:");
        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");


        panel.add(openTimePanel);
        panel.add(closeTimePanel);
        panel.add(closedCheckbox);

        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);
    }

    private void addEmployeeWindow() {

        JFrame frame = createStandardFrame("Add Employee");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        panel.add(new JLabel("Name:"));
        JTextField nameTextField = createTextField(10);
        panel.add(nameTextField);

        panel.add(new JLabel("Job:"));
        JTextField jobTextField = createTextField(10);
        panel.add(jobTextField);

        JButton updateEmployeeAvailabilityButton =
                createButton("Update Employee Availability",
                        createActionListener("Update Employee Availability"));


        panel.add(updateEmployeeAvailabilityButton);

        JButton backToEmployeeSettings = createButton("Back", createActionListener("Employee Settings"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);

    }

    private void updateEmployeeAvailabilityWindow() {

        JFrame frame = createStandardFrame("Update Employee Availability");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        panel.add(new JLabel("Day:"));
        panel.add(dayDropdown);

        JPanel openTimePanel = createTimePanel("Start Time:");
        JPanel closeTimePanel = createTimePanel("End Time:");
        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");

        panel.add(openTimePanel);
        panel.add(closeTimePanel);
        panel.add(closedCheckbox);

        JButton backToEmployeeSettings = createButton("Back", createActionListener("Employee Settings"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);

    }

    private void employeeInfoWindow() {

        JFrame frame = createStandardFrame("Employee Info");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        JLabel label = new JLabel("Hello, World!");

        JButton backToEmployeeSettings = createButton("Back", createActionListener("Employee Settings"));
        panel.add(label);
        panel.add(backToEmployeeSettings);
        displayFrame(frame);

    }

    private void updateSchedulingNeedsWindow() {

        JFrame frame = createStandardFrame("Update Schedule Needs");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);

        panel.add(new JLabel("Day:"));
        panel.add(dayDropdown);

        JPanel openTimePanel = createTimePanel("Starting Time:");
        JPanel closeTimePanel = createTimePanel("End Time:");
        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");

        panel.add(openTimePanel);
        panel.add(closeTimePanel);
        panel.add(closedCheckbox);
        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
        panel.add(backToEmployeeSettings);

        displayFrame(frame);

    }


    private void viewStoreHoursWindow() {
        JFrame frame = createStandardFrame("Employee Info");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);


        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
        panel.add(backToEmployeeSettings);
        displayFrame(frame);

    }

    private void viewAllStoreAttributesWindow() {
        JFrame frame = createStandardFrame("Employee Info");
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0);


        JButton backToEmployeeSettings = createButton("Back", createActionListener("Schedule Settings"));
        panel.add(backToEmployeeSettings);
        displayFrame(frame);
    }

    private ActionListener createActionListener(String command) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (command) {
                    case "Load Store": loadStore();
                        break;
                    case "Save": saveStore();
                        break;
                    case "Employee Settings": employeeSettingsWindow();
                        break;
                    case "Schedule Settings": storeSettingsWindow();
                        break;
                    case "View All Store Attributes": viewAllStoreAttributesWindow();
                        break;
                    case "Add Employee": addEmployeeWindow();
                        break;
                    case "Update Employee Availability": updateEmployeeAvailabilityWindow();
                        break;
                    case "View Employee List": employeeInfoWindow();
                        break;
                    case "Update Store Hours": updateStoreHoursWindow();
                        break;
                    case "View Store Hours": viewStoreHoursWindow();
                        break;
                    case "Update Scheduling Needs": updateSchedulingNeedsWindow();
                        break;
                    case "Return to Main": new GUI();
                        break;
                    default: System.out.println("Command not recognized: " + command);
                }
            }
        };
    }

    private JPanel createStandardPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        return panel;
    }

    private JFrame createStandardFrame(String title) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.add(createStandardPanel(), BorderLayout.CENTER);
        frame.pack();
        return frame;
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Load Store":
                loadStore();
                break;
            case "Save":
                saveStore();
                break;
            case "Employee Settings":
                employeeSettingsWindow();
                break;

        }
    }

    private JPanel createTimePanel(String label) {
        JPanel timePanel = new JPanel(new GridLayout(1, 2));
        timePanel.add(new JLabel(label));
        timePanel.add(new JComboBox<>(times));
        return timePanel;
    }

    private void displayFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    private void loadStore() {
        //
    }

    private void saveStore() {
        //
    }


    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);

        return textField;
    }













}
