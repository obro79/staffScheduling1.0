package ui.gui;

import model.*;
import model.scheduling.Schedule;
import ui.LogPrinter;
import ui.StoreApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.*;
import java.awt.event.WindowEvent;
import model.EventLog;
import model.Event;

//Creates and handles the Graphical user interface
public class GUI implements LogPrinter {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private StoreApp storeApp;
    private int currentDayIndex;
    private JTextArea employeeInfoTextArea;
    private JTextArea scheduleInfoTextArea;
    private JTextArea scheduleAssignments;

    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday"};
    private static final String[] TIMES = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
            "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
            "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
            "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
            "21:30", "22:00", "22:30", "23:00", "23:30"};

    //EFFECTS: creates a new instance of GUI
    public GUI(StoreApp storeApp) {
        this.storeApp = storeApp;
        this.currentDayIndex = 0;
        this.employeeInfoTextArea = new JTextArea();
        this.scheduleInfoTextArea = new JTextArea();
        this.scheduleAssignments = new JTextArea();
    }

    //EFFECTS: creates the only JFrame and only cardlayout and sets all its attributes
    public void initializeUI() {
        this.frame = new JFrame("Schedule Manager");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(600, 400));

        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);


        // Initialize panels
        JPanel mainPanel = mainWindow();
        this.cardPanel.add(mainPanel, "Main");

        initializePanels();

        this.frame.add(cardPanel, BorderLayout.CENTER);
        this.frame.pack(); // Adjust window size based on components
        this.frame.setLocationRelativeTo(null); // Center window on screen
        this.cardLayout.show(cardPanel, "Main");
        this.frame.setVisible(true);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        });
    }

    public void printLog(EventLog el) {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
    }


    //EFFECTS: initializes all the panels for the store and add it to cardPanel
    //MODIFIES: this
    public void initializePanels() {
        // Main window panel
        JPanel mainPanel = mainWindow();
        this.cardPanel.add(mainPanel, "Main");

        JPanel employeeSettingsWindow = employeeSettingsWindow();
        this.cardPanel.add(employeeSettingsWindow, "Employee Settings");

        // Store settings window panel
        JPanel storeSettingsPanel = storeSettingsPanel();
        cardPanel.add(storeSettingsPanel, "Schedule Settings");

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

        // Scheduling needs update window panel
        JPanel viewScheduleAttributes = viewScheduleAttributes();
        cardPanel.add(viewScheduleAttributes, "View All Schedule Stuff");

        JPanel viewScheduleWindow = viewScheduleWindow();
        cardPanel.add(viewScheduleWindow, "Schedule Window");
    }

    //EFFECTS: returns and creates the main window with all its buttons
    public JPanel mainWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        panel.add(createButtonPanel("Load Store", e -> loadStore()));
        panel.add(createButtonPanel("Save Store", e -> saveStore()));
        panel.add(createButtonPanel("Employee Settings",
                e -> switchToCard("Employee Settings")));
        panel.add(createButtonPanel("Schedule Settings",
                e -> switchToCard("Schedule Settings")));

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    //EFFECTS: returns and creates the employeeSettingsWindow window with all its buttons
    public JPanel employeeSettingsWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // Create each button and add it to a JPanel with FlowLayout to center it
        panel.add(createButtonPanel("Add Employee", e -> switchToCard("Add Employee")));
        panel.add(createButtonPanel("Update Employee Availability",
                e -> switchToCard("Update Employee Availability")));
        panel.add(createButtonPanel("View Employee List", e -> switchToCard("Employee Info")));
        panel.add(createButtonPanel("Back to Main", e -> switchToCard("Main")));
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    //EFFECTS: returns and creates a panel containing a button
    public JPanel createButtonPanel(String buttonText, ActionListener actionListener) {
        JButton button = createButton(buttonText, actionListener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Set both the maximum and preferred size to control the button size
        button.setMaximumSize(new Dimension(300, button.getMinimumSize().height));
        button.setPreferredSize(new Dimension(250, button.getMinimumSize().height));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue()); // Add space before the button
        buttonPanel.add(button);                    // Add the button
        buttonPanel.add(Box.createHorizontalGlue()); // Add space after the button

        return buttonPanel;
    }

    //EFFECTS: returns and creates a JButton with text and a listener
    public JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    //EFFECTS: returns and creates the update Store Hours Panel panel with all its buttons
    //MODIFEIS: this
    public JPanel updateStoreHoursPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // Initialize components

        JComboBox<String> dayDropdown = createComponent(JComboBox.class, DAYS_OF_WEEK, 0);
        JComboBox<String> startTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JComboBox<String> endTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JPanel dayPanel = createLabeledComboBoxPanel(new JLabel("Day: "),dayDropdown);
        JPanel openTimePanel = createLabeledComboBoxPanel(new JLabel("Open Time: "),startTimeDropdown);
        JPanel closeTimePanel = createLabeledComboBoxPanel(new JLabel("Open Time: "),endTimeDropdown);

        JCheckBox sameForWeekCheckbox = createComponent(JCheckBox.class, "Same for the rest of the week?",
                0);

        panel.add(dayPanel);
        panel.add(openTimePanel);
        panel.add(closeTimePanel);
        panel.add(sameForWeekCheckbox);

        // Save and Back buttons
        panel.add(createButtonPanel("Save Hours", e -> {
            saveStoreHours(dayDropdown,startTimeDropdown,endTimeDropdown,sameForWeekCheckbox);
        }));
        panel.add(createButtonPanel("Back", e -> switchToCard("Main")));
        panel.add(Box.createVerticalGlue());

        // Adding components directly since GridLayout handles layout

        return panel;
    }

    //EFFECTS: returns and creates panel with a combobox and its label
    public JPanel createLabeledComboBoxPanel(JLabel label, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }

    //EFFECTS: returns and creates panel with a Text Field and its label
    public JPanel createLabeledTextField(JLabel label, JTextField text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        panel.add(label);
        panel.add(text);
        return panel;
    }

    //EFFECTS: returns and creates a panel for to update the schedule needs
    public JPanel updateSchedulingNeedsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // Initializing components
        JComboBox<String> dayDropdown = createComponent(JComboBox.class, DAYS_OF_WEEK, 0);
        JComboBox<String> startTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JComboBox<String> endTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JTextField numEmployeesField = new JTextField(10);


        JPanel dayPanel = createLabeledComboBoxPanel(new JLabel("Day: "),dayDropdown);
        JPanel openTimePanel = createLabeledComboBoxPanel(new JLabel("Start Time: "),startTimeDropdown);
        JPanel closeTimePanel = createLabeledComboBoxPanel(new JLabel("End Time: "),endTimeDropdown);
        JPanel check = createLabeledTextField(new JLabel("Number of Employees Needed:"),numEmployeesField);
        JCheckBox sameForWeekCheckbox = createComponent(JCheckBox.class, "More Slots for Today?", 0);


        // Save and Back buttons
        JButton saveButton = createButton("Save", e -> {
            saveSchedulingNeeds(dayDropdown,startTimeDropdown,endTimeDropdown,numEmployeesField,sameForWeekCheckbox);
        });
        JButton backButton = createButton("Back", e -> switchToCard("Main"));

        addPanels(panel,dayPanel,openTimePanel,closeTimePanel,check);
        panel.add(sameForWeekCheckbox);
        panel.add(saveButton);
        panel.add(backButton);

        return panel;
    }

    //EFFECTS: returns and creates a panel to add an employee
    public JPanel addEmployeePanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());

        JTextField nameField = new JTextField(20);
        JPanel names = createLabeledTextField(new JLabel("Name:"),nameField);

        JTextField jobField = new JTextField(20);
        JPanel jobs = createLabeledTextField(new JLabel("Job:"),jobField);


        // Save button with action to add an employee
        JButton saveButton = createButton("Save Employee", e -> {
            saveEmployee(nameField,jobField);
        });

        // Back button to return to the main menu
        JButton backButton = createButton("Back", e -> switchToCard("Main"));

        // Adding components to panel
        panel.add(names);
        panel.add(jobs);
        panel.add(saveButton);
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    //EFFECTS: returns and creates a panel to represent all the stuff you can do with the store
    public JPanel storeSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // Buttons for different store settings
        panel.add(createButtonPanel("Update Store Hours",
                e -> switchToCard("Update Store Hours")));
        panel.add(createButtonPanel("Update Scheduling Needs",
                e -> switchToCard("Update Scheduling Needs")));
        panel.add(createButtonPanel("View All Schedule Stuff",
                e -> switchToCard("View All Schedule Stuff")));
        panel.add(createButtonPanel("Create Schedule",
                e -> switchToCard("Schedule Window")));
        // Back button to return to the main menu
        panel.add(createButtonPanel("Back", e -> switchToCard("Main")));

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    //EFFECTS: returns and creates a panel to display all the schedule
    public JPanel viewScheduleAttributes() {
        JPanel panel = new JPanel(new BorderLayout());
        scheduleInfoTextArea.setEditable(false);

        // Initialize the text area with initial data
        scheduleInfoTextArea.setText(getStoreInfo());

        JScrollPane scrollPane = new JScrollPane(scheduleInfoTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); // For better alignment of the back button
        JButton backButton = createButton("Back", e -> switchToCard("Main"));
        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        return panel;
    }

    //EFFECTS: returns and creates a panel to display all the employee info
    public JPanel employeeInfoWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        employeeInfoTextArea.setEditable(false);

        // Initialize the text area with initial data
        employeeInfoTextArea.setText(getEmployeeListInfo());

        JScrollPane scrollPane = new JScrollPane(employeeInfoTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); // For better alignment of the back button
        JButton backButton = createButton("Back", e -> switchToCard("Main"));
        JButton sortButton = createButton("Sort Alphabetically",
                e -> makeAlphabetical());
        JButton sortNoAvailability = createButton("Employees Without Availability",
                e -> getEmployeesWithNoAvailabilityInfo());

        bottomPanel.add(backButton);
        bottomPanel.add(sortButton);
        bottomPanel.add(sortNoAvailability);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        return panel;
    }

    //TODO initialize panel in that method "Schedule Window"
    //EFFECTS: returns and creates a panel to display all the employee info
    public JPanel viewScheduleWindow() {
        String theString = new Schedule(this.storeApp).solve();

        JPanel panel = new JPanel(new BorderLayout());
        scheduleAssignments.setEditable(false);

        // Initialize the text area with initial data
        scheduleAssignments.setText(theString);

        JScrollPane scrollPane = new JScrollPane(scheduleAssignments);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); // For better alignment of the back button
        JButton backButton = createButton("Back", e -> switchToCard("Main"));


        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        return panel;
    }

    //EFFECTS: sorts the employees base on alphabeticalness
    public void makeAlphabetical() {
        this.storeApp.getThisStore().employeeSortLog();
        List<Employee> employees = this.storeApp.getThisStore().getEmployeeList().getEmployeeList();
        // Sort the employees list by name
        Collections.sort(employees, Comparator.comparing(Employee::getName));

        StringBuilder finalString = new StringBuilder();

        for (Employee emp : employees) {
            finalString.append("\n");
            finalString.append("Name: ").append(emp.getName());
            finalString.append("\n");
            finalString.append("Job: ").append(emp.getJob());
            for (DailyAvailability d : emp.getWeeklyAvailability()) {
                finalString.append("\n");
                finalString.append(" ");
                finalString.append(d.toString());
            }
        }

        employeeInfoTextArea.setText(finalString.toString());
    }

    //EFFECTS: displays the employees that dont have availability filled out
    public void getEmployeesWithNoAvailabilityInfo() {
        this.storeApp.getThisStore().employeeFilterLog();
        List<Employee> employees = this.storeApp.getThisStore().getEmployeeList().getEmployeeList();

        List<Employee> employeesWithNoAvailability = employees.stream()
                .filter(emp -> emp.getWeeklyAvailability() == null || emp.getWeeklyAvailability().isEmpty())
                .collect(Collectors.toList());

        StringBuilder finalString = new StringBuilder();

        for (Employee emp : employeesWithNoAvailability) {
            finalString.append("\n");
            finalString.append("Name: ").append(emp.getName());
            finalString.append("\n");
            finalString.append("Job: ").append(emp.getJob());

        }
        employeeInfoTextArea.setText(finalString.toString());

    }

    //EFFECTS: Method to update the text in JTextArea for employees
    //MODIFIES: this
    public void updateEmployeeInfo() {
        employeeInfoTextArea.setText(getEmployeeListInfo());
    }

    //EFFECTS: Method to update the text in JTextArea for store
    //MODIFIES: this
    public void updateStoreInfo() {
        scheduleInfoTextArea.setText(getStoreInfo());
    }

    //EFFECTS: updates the text that is displayed in the view schedule window
    public void updateScheduleText() {
        String theString = new Schedule(this.storeApp).solve();
        scheduleAssignments.setText(theString);
    }

    //EFFECTS: returns and creates a panel to update the employees availability
    public JPanel updateEmployeeAvailabilityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JTextField nameField = new JTextField(20);
        JComboBox<String> dayDropdown = createComponent(JComboBox.class, DAYS_OF_WEEK, 0);
        JComboBox<String> startTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JComboBox<String> endTimeDropdown = createComponent(JComboBox.class, TIMES, 0);
        JCheckBox sameForWeek = createComponent(JCheckBox.class, "Same for the rest of the week?",
                0);

        JPanel namePanel = createLabeledTextField(new JLabel("Employee Name:"), nameField);
        JPanel dayPanel = createLabeledComboBoxPanel(new JLabel("Day: "),dayDropdown);
        JPanel openTimePanel = createLabeledComboBoxPanel(new JLabel("Start Time: "),startTimeDropdown);
        JPanel closeTimePanel = createLabeledComboBoxPanel(new JLabel("End Time: "),endTimeDropdown);

        // Next and Back buttons
        JButton nextButton = createButton("Next", e -> {
            saveEmployeeAvailability(dayDropdown,startTimeDropdown,endTimeDropdown,sameForWeek,nameField);
        });
        JButton backButton = createButton("Back", e -> switchToCard("Main"));

        addPanels(panel,namePanel,dayPanel,openTimePanel,closeTimePanel);
        panel.add(sameForWeek);
        panel.add(nextButton);
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());


        return panel;
    }

    //EFFECTS: adds panels to a panel
    public void addPanels(JPanel main,JPanel b, JPanel c, JPanel d,JPanel e) {
        main.add(b);
        main.add(c);
        main.add(d);
        main.add(e);
    }

    //EFFECTS: switches  to the card with the given card name
    public void switchToCard(String cardName) {
        updateEmployeeInfo();
        updateStoreInfo();
        updateScheduleText();
        cardLayout.show(cardPanel, cardName);
    }

    //EFFECTS: returns a string to display on the view employee info panel
    public String getEmployeeListInfo() {
        List<Employee> e = this.storeApp.getThisStore().getEmployeeList().getEmployeeList();
        String finalString = "";

        for (Employee emp : e) {
            finalString += "\n";
            finalString += "Name: " + emp.getName();
            finalString += "\n";
            finalString += "Job: " + emp.getJob();
            for (DailyAvailability d : emp.getWeeklyAvailability()) {
                finalString += "\n";
                finalString += " ";
                finalString += d.toString();
            }
        }

        return finalString;
    }

    //EFFECTS: returns a string to display on the view store info panel
    public String getStoreInfo() {

        String finalString = "";
        List<DailyAvailability> storeHours = this.storeApp.getThisStore().getStoreHours();
        List<EmployeeNeeds> needs = this.storeApp.getThisStore().getAllEmployeeNeeds();

        for (DailyAvailability d : storeHours) {
            finalString += "\n";
            finalString += " ";
            finalString += d.toString();
        }

        for (EmployeeNeeds en : needs) {
            finalString += "\n";
            finalString += " ";
            finalString += en.toString();
        }

        return finalString;
    }

    //EFFECTS creates either a combobox or a checkbox
    public <T> T createComponent(Class<T> componentClass, Object itemsOrText, int columns) {
        if (componentClass == JComboBox.class) {
            JComboBox<String> comboBox = new JComboBox<>((String[]) itemsOrText);
            if (columns > 0) {
                comboBox.setPreferredSize(new Dimension(columns, comboBox.getPreferredSize().height));
            }
            return componentClass.cast(comboBox);
        } else if (componentClass == JCheckBox.class) {
            return componentClass.cast(new JCheckBox((String) itemsOrText));
        }
        return null;
    }

    //SAVE METHODS

    //EFFECTS: saves an employee
    //MODIFEIS: this
    public void saveEmployee(JTextField name,JTextField job) {
        String employeeName = name.getText();
        String employeejob = job.getText();

        Employee newEmployee = new Employee(employeeName, employeejob);
        this.storeApp.getThisStore().getEmployeeList().getInstance().addEmployee(newEmployee);
        try {
            this.storeApp.saveAllStoreAttributes();
        } catch (FileNotFoundException e) {
            //
        }
    }

    //EFFECTS: saves an employees availability
    //MODIFIES: this
    public void saveEmployeeAvailability(JComboBox<String> day, JComboBox<String> open, JComboBox<String> close,
                                         JCheckBox restWeek, JTextField name) {

        String dayOfW = (String) day.getSelectedItem();
        String startTime = (String) open.getSelectedItem();
        String endTime = (String) close.getSelectedItem();
        boolean selected = restWeek.isSelected();
        String employeeName = name.getText();

        LocalTime openTime = LocalTime.parse(startTime);
        LocalTime closeTime = LocalTime.parse(endTime);
        EmployeeList employeeList = this.storeApp.getThisStore().getEmployeeList();

        for (Employee e : employeeList.getEmployeeList()) {
            if (e.getName().equalsIgnoreCase(employeeName)) {
                if (selected) {
                    for (int i = currentDayIndex; i < DAYS_OF_WEEK.length; i++) {

                        e.getWeeklyAvailability().add(new DailyAvailability(DAYS_OF_WEEK[i], openTime, closeTime));
                    }
                } else {
                    e.getWeeklyAvailability().add(new DailyAvailability(dayOfW, openTime, closeTime));
                }

                try {
                    this.storeApp.saveAllStoreAttributes();
                } catch (FileNotFoundException ex) {
                    //
                }
            }
        }
    }

    //EFFECTS: saves store hours
    public void saveStoreHours(JComboBox<String> day, JComboBox<String> open, JComboBox<String> close,
                               JCheckBox restWeek) {

        String dayOfWeek = (String) day.getSelectedItem();
        String startTime = (String) open.getSelectedItem();
        String endTime = (String) close.getSelectedItem();
        boolean selected = restWeek.isSelected();

        LocalTime openTime = LocalTime.parse(startTime);
        LocalTime closeTime = LocalTime.parse(endTime);
        Store storeHours = this.storeApp.getThisStore();

        int index = java.util.Arrays.asList(DAYS_OF_WEEK).indexOf(dayOfWeek);

        if (selected) {
            for (int i = index; i <= DAYS_OF_WEEK.length - 1; i++) {
                storeHours.addStoreHours(new DailyAvailability(DAYS_OF_WEEK[i], openTime, closeTime));
            }
        }
        storeHours.addStoreHours(new DailyAvailability(dayOfWeek, openTime, closeTime));
        try {
            this.storeApp.saveAllStoreAttributes();
        } catch (FileNotFoundException e) {
            //
        }
    }

    //EFFECTS: saves scheduling needs
    public void saveSchedulingNeeds(JComboBox<String> day, JComboBox<String> open, JComboBox<String> close,
                                    JTextField employeeNumber,JCheckBox isClosed) {
        List<EmployeeNeeds> employeeNeeds = this.storeApp.getThisStore().getAllEmployeeNeeds();

        String dayOfWeek = (String) day.getSelectedItem();
        String startTime = (String) open.getSelectedItem();

        String endTime = (String) close.getSelectedItem();
        int number = Integer.parseInt(employeeNumber.getText());
        boolean more = isClosed.isSelected();

        LocalTime opening = LocalTime.parse(startTime);
        LocalTime closing = LocalTime.parse(endTime);

        if (more) {
            for (int i = currentDayIndex; i < DAYS_OF_WEEK.length; i++) {
                employeeNeeds.add(new EmployeeNeeds(DAYS_OF_WEEK[i], opening, closing, number));
            }
            this.currentDayIndex = 0;
        }
        employeeNeeds.add(new EmployeeNeeds(dayOfWeek, opening, closing, number));
        this.currentDayIndex++;
        try {
            this.storeApp.saveAllStoreAttributes();
        } catch (FileNotFoundException e) {
            //
        }
    }

    //EFFECTS: saves entire store
    public void saveStore() {
        JPanel panel = new JPanel(new BorderLayout()); // Create a panel with BorderLayout

        JLabel picture;
        try {
            BufferedImage myPic = ImageIO.read(this.getClass().getResource("Store.png"));
            picture = new JLabel(new ImageIcon(myPic));
            panel.add(picture, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            picture = new JLabel("Store saved successfully, but image could not be loaded.");
            panel.add(picture, BorderLayout.CENTER);
        }

        JLabel label = new JLabel("Store saved successfully!"); // Create a label with text
        panel.add(label, BorderLayout.SOUTH);

        try {
            storeApp.saveAllStoreAttributes();
            JOptionPane.showMessageDialog(null, panel, "Store Saved",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving the store: " + e.getMessage());
        }
    }

    //EFFECTS: loads entire store from Json file
    //MODIFIES: store
    public void loadStore() {
        try {
            storeApp.loadAllStoreAttributes();
            JOptionPane.showMessageDialog(frame, "Store loaded successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading the store: " + e.getMessage());
        }
    }

}



