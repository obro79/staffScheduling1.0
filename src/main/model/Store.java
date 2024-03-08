package model;

import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;




// stores a list of EmployeeNeeds as well as the hours of the store
public class Store {
    private static Store instance;
    protected List<DailyAvailability> storeHours;
    private List<EmployeeNeeds> allEmployeeNeeds;
    private EmployeeList employeeList;

    //EFFECTS: Creates a new instance of OperationalNeeds
    public Store() {
        this.storeHours = new ArrayList<>();
        this.allEmployeeNeeds = new ArrayList<>();
        this.employeeList = model.EmployeeList.getInstance();
    }

    //EFFECTS: Ensures that there is only ever one operationalNeeds
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



}
