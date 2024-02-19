package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperationalNeedsTest {

    private static OperationalNeeds instance;
    protected List<DailyAvailability> storeHours;
    private ArrayList<EmployeeNeeds> allEmployeeNeeds;

    @BeforeEach

    public void runBefore() {

    }

    @Test
    void testSingletonInstance() {

        OperationalNeeds firstInstance = OperationalNeeds.getInstance();
        OperationalNeeds secondInstance = OperationalNeeds.getInstance();
        assertSame(firstInstance, secondInstance, "getInstance should return the same instance of OperationalNeeds");
    }

    @Test
    void testStoreHoursList() {

        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        assertNotNull(operationalNeeds.getStoreHours(), "getStoreHours should return a non-null List");
        assertTrue(operationalNeeds.getStoreHours().isEmpty(), "getStoreHours should return an empty List by default");
    }

    @Test
    void testAllEmployeeNeedsList() {

        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        assertNotNull(operationalNeeds.getAllEmployeeNeeds(), "getAllEmployeeNeeds should return a non-null List");
        assertTrue(operationalNeeds.getAllEmployeeNeeds().isEmpty(), "getAllEmployeeNeeds should return an empty List by default");
    }



}

