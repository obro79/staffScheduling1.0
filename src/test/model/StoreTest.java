package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    private static Store instance;
    protected List<DailyAvailability> storeHours;
    private ArrayList<EmployeeNeeds> allEmployeeNeeds;

    @BeforeEach

    public void runBefore() {

    }

    @Test
    void testSingletonInstance() {

        Store firstInstance = Store.getInstance();
        Store secondInstance = Store.getInstance();
        assertSame(firstInstance, secondInstance, "getInstance should return the same instance of OperationalNeeds");
    }

    @Test
    void testStoreHoursList() {

        Store store = Store.getInstance();
        assertNotNull(store.getStoreHours(), "getStoreHours should return a non-null List");
        assertTrue(store.getStoreHours().isEmpty(), "getStoreHours should return an empty List by default");
    }

    @Test
    void testAllEmployeeNeedsList() {

        Store store = Store.getInstance();
        assertNotNull(store.getAllEmployeeNeeds(), "getAllEmployeeNeeds should return a non-null List");
        assertTrue(store.getAllEmployeeNeeds().isEmpty(), "getAllEmployeeNeeds should return an empty List by default");
    }



}

