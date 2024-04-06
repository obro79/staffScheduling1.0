package model;

import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;



// Stores all attributes that belong to a store, storeHours, allEmployeeNeeds, employeeList.
public class Store {
    private static Store instance;
    protected List<DailyAvailability> storeHours;
    private List<EmployeeNeeds> allEmployeeNeeds;
    private EmployeeList employeeList;

    //EFFECTS: Creates a new instance of Store
    public Store() {
        this.storeHours = new ArrayList<>();
        this.allEmployeeNeeds = new ArrayList<>();
        this.employeeList = model.EmployeeList.getInstance();
    }

    //EFFECTS: Ensures that there is only ever one Store
    public static synchronized Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public List<DailyAvailability> getStoreHours() {
        return storeHours;
    }

    //EFFECTS: adds the hours to the store
    public void addStoreHours(DailyAvailability d) {
        storeHours.add(d);
        String day = d.getDay();
        String startTime = d.getStartTime().toString();
        String endTime = d.getEndTime().toString();
        String logString = "Store Hours were updated. " + day + " " + startTime + "-" + endTime;
        EventLog.getInstance().logEvent(new Event(logString));
    }

    public List<EmployeeNeeds> getAllEmployeeNeeds() {
        return this.allEmployeeNeeds;
    }

    public EmployeeList getEmployeeList() {
        return this.employeeList;

    }

    public Store getStore() {
        return this;
    }

    //EFFECTS: logs the employee sort function
    //MODIFIES: log
    public void employeeSortLog() {
        EventLog.getInstance().logEvent(new Event("Employees were sorted alphabetically"));
    }

    //EFFECTS: logs the employee filter function
    //MODIFIES: log
    public void employeeFilterLog() {
        EventLog.getInstance().logEvent(new Event("Employees without availability were filtered"));
    }



}
