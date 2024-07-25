package model.scheduling;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import model.DailyAvailability;
import model.Employee;
import model.TimeRange;
import model.Shift;
import model.TimeRange;
import model.enums.Day;
import ui.Store;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

import static java.lang.System.loadLibrary;


public class Scheduler {

    private Map<Shift, Employee> assignments;
    private Map<Day, List<Shift>> tempShifts;
    private Store store;
    private int numEmployees;
    private static int segmentsPerDay = 96;
    private static int numDays = 7;

    public Scheduler(Store store) {
        assignments =  new HashMap<>();
        this.store = store;
    }

    public void makeSchedule() {
        Loader.loadNativeLibraries();
        MPSolver solver = MPSolver.createSolver("solver");
        initilizeVariables(solver);
        initializeContraints();
        setObjective();
        solver.solve();
        makeAssignments();
    }


    public void initilizeVariables(MPSolver solver) {
        numEmployees = store.getEmployees().size();
        MPVariable[][] x = new MPVariable[numEmployees][tempShifts.size()];
        for (int i = 0; i < numEmployees; i++) {
            for (int j = 0; j < tempShifts.size(); j++) {
                x[i][j] = solver.makeIntVar(0, 1, "x_" + i + "_" + j);
            }
        }
    }

    public void setObjective() {
        //TODO
    }


    public void initializeContraints() {
        shiftsFilledConstraint();
        employeeAvailableConstraint();
        maxHoursPerDayConstraint();
        maxHoursPerWeekConstraint();
        maxDaysPerWeekConstraint();
        maxShiftsPerDay();
        minShiftLength();
        preferLongerShifts();
    }

    public void shiftsFilledConstraint() {
        //TODO
    }

    public void employeeAvailableConstraint() {
        //TODO
    }

    public void maxHoursPerDayConstraint() {
        //TODo
    }

    public void maxHoursPerWeekConstraint() {
        //TODO
    }

    public void maxDaysPerWeekConstraint() {
        //TODO
    }

    public void maxShiftsPerDay() {
        //TODO
    }

    public void minShiftLength() {
        //TODO
    }

    public void makeAssignments() {
        //TODO
    }

    public void preferLongerShifts() {
        //TODO
    }



}

