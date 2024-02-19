
package model;

import model.EmployeeNeeds;
import ui.UiHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OperationalNeeds {
    private static OperationalNeeds instance;
    protected List<DailyAvailability> storeHours;
    private ArrayList<EmployeeNeeds> allEmployeeNeeds;

    public OperationalNeeds() {
        this.storeHours = new ArrayList<>();
        this.allEmployeeNeeds = new ArrayList<>();
    }

    public static synchronized OperationalNeeds getInstance() {
        if (instance == null) {
            instance = new OperationalNeeds();
        }
        return instance;
    }

    public List<DailyAvailability> getStoreHours() {
        return storeHours;
    }


    public ArrayList<EmployeeNeeds> getAllEmployeeNeeds() {
        return this.allEmployeeNeeds;
    }
}
