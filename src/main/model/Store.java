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

    public List<EmployeeNeeds> getAllEmployeeNeeds() {
        return this.allEmployeeNeeds;
    }

    public EmployeeList getEmployeeList() {
        return this.employeeList;

    }

    public Store getStore() {
        return this;
    }



}
