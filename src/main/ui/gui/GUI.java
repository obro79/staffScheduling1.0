package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



//Makes the graphical user interface

public class GUI implements ActionListener {

//TODO fix methodlength
    public GUI() {

        //Buttons
        JButton loadStore = new JButton("Load Store");
        loadStore.addActionListener(this);

        JButton saveStore = new JButton("Save");
        saveStore.addActionListener(this);

        JButton employeeSettings = new JButton("Employee Settings");
        employeeSettings.addActionListener(this);

        JButton scheduleSettings = new JButton("Schedule Settings");
        scheduleSettings.addActionListener(this);

        JButton viewAllStoreAttributes = new JButton("View All Store Attributes");
        viewAllStoreAttributes.addActionListener(this);


        JLabel label = new JLabel();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));

        //Adding buttons to panel
        panel.add(loadStore);
        panel.add(scheduleSettings);
        panel.add(employeeSettings);
        panel.add(viewAllStoreAttributes);
        panel.add(saveStore);

        //Adding listeners to the buttons
        loadStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //TODO  loadStore();
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });

        saveStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when button is clicked //TODO
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });

        employeeSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeSettingsWindow();
            }
        });

        scheduleSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleSettingsWindow();
            }
        });

        viewAllStoreAttributes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when button is clicked //TODO
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Schedule Manager");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void employeeSettingsWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Employee Settings");
        frame.pack();
        frame.setVisible(true);

        JButton saveStore = new JButton("Save");
        saveStore.addActionListener(this);

        JButton addEmployee = new JButton("Add Employee");
        addEmployee.addActionListener(this);

        JButton updateEmployeeAvailability = new JButton("Schedule Settings");
        updateEmployeeAvailability.addActionListener(this);

        JButton getEmployeeList = new JButton("View All Store Attributes");
        getEmployeeList.addActionListener(this);

        panel.add(addEmployee);
        panel.add(updateEmployeeAvailability);
        panel.add(getEmployeeList);
        panel.add(saveStore);

    }

    public void scheduleSettingsWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Schedule Settings");
        frame.pack();
        frame.setVisible(true);

        JButton updateStoreHours = new JButton("Update Store Hours");
        updateStoreHours.addActionListener(this);

        JButton getStoreHours = new JButton("Get Store Hours");
        getStoreHours.addActionListener(this);

        JButton updateSchedulingNeeds = new JButton("Update Scheduling Needs");
        updateSchedulingNeeds.addActionListener(this);

        JButton saveStore = new JButton("Save");
        saveStore.addActionListener(this);

        panel.add(updateStoreHours);
        panel.add(getStoreHours);
        panel.add(updateSchedulingNeeds);
        panel.add(saveStore);


        saveStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //TODO save();
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });

        updateStoreHours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeHoursWindow();
            }
        });

    }


    public void storeHoursWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Schedule Settings");
        frame.pack();
        frame.setVisible(true);


        JLabel dayLabel = new JLabel("Day:");
        JTextField dayTextField = new JTextField();

        JLabel openTimeLabel = new JLabel("Open Time:");
        JTextField openTimeTextField = new JTextField();

        JLabel closeTimeLabel = new JLabel("Close Time:");
        JTextField closeTimeTextField = new JTextField();

        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");

        panel.add(dayLabel);
        panel.add(dayTextField);

        panel.add(openTimeLabel);
        panel.add(openTimeTextField);

        panel.add(closeTimeLabel);
        panel.add(closeTimeTextField);

        panel.add(closedCheckbox);
    }

    public void updateEmployeeAvailabilityWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Employee Availability");
        frame.pack();
        frame.setVisible(true);


        JLabel dayLabel = new JLabel("Day:");
        JTextField dayTextField = new JTextField();

        JLabel openTimeLabel = new JLabel("Start Time:");
        JTextField openTimeTextField = new JTextField();

        JLabel closeTimeLabel = new JLabel("End Time:");
        JTextField closeTimeTextField = new JTextField();

        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");

        panel.add(dayLabel);
        panel.add(dayTextField);

        panel.add(openTimeLabel);
        panel.add(openTimeTextField);

        panel.add(closeTimeLabel);
        panel.add(closeTimeTextField);

        panel.add(closedCheckbox);
    }

    public void updateEmployeeNeedsWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Employee Availability");
        frame.pack();
        frame.setVisible(true);


        JLabel dayLabel = new JLabel("Day:");
        JTextField dayTextField = new JTextField();

        JLabel openTimeLabel = new JLabel("Start Time:");
        JTextField openTimeTextField = new JTextField();

        JLabel closeTimeLabel = new JLabel("End Time:");
        JTextField closeTimeTextField = new JTextField();

        JCheckBox moreSlots = new JCheckBox("More Slots For Today?");
        JCheckBox closedCheckbox = new JCheckBox("Same for the rest of the week?");

        panel.add(dayLabel);
        panel.add(dayTextField);

        panel.add(openTimeLabel);
        panel.add(openTimeTextField);

        panel.add(closeTimeLabel);
        panel.add(closeTimeTextField);

        panel.add(closedCheckbox);
        panel.add(moreSlots);
    }




    public void addEmployeeWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("New Employee");
        frame.pack();
        frame.setVisible(true);


        JLabel dayLabel = new JLabel("Name:");
        JTextField dayTextField = new JTextField();

        JLabel openTimeLabel = new JLabel("Job");
        JTextField openTimeTextField = new JTextField();


        JCheckBox closedCheckbox = new JCheckBox("Update Availability?");

        panel.add(dayLabel);
        panel.add(dayTextField);

        panel.add(openTimeLabel);
        panel.add(openTimeTextField);


        panel.add(closedCheckbox);
    }

    public void employeesWindow() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("New Employee");
        frame.pack();
        frame.setVisible(true);

    }




}
