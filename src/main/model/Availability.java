package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class Availability {

    private int idealWeeklyHours;
    private int maxWeeklyHours;
    private int monStart;
    private int monEnd;
    private int tueStart;
    private int tueEnd;
    private int wedStart;
    private int wedEnd;
    private int thurStart;
    private int thurEnd;
    private int friStart;
    private int friEnd;
    private int satStart;
    private int satEnd;
    private int sunStart;
    private int sunEnd;
    private LinkedList<Integer> weekAvailibility;

    public Availability(int idealWeeklyHours, int maxWeeklyHours, int monStart, int monEnd, int tueStart, int tueEnd,
                      int wedStart, int wedEnd, int thurStart, int thurEnd, int friStart, int friEnd, int satStart,
                      int satEnd, int sunStart, int sunEnd) {

        this.idealWeeklyHours = idealWeeklyHours;
        this.maxWeeklyHours = maxWeeklyHours;
        this.monStart = monStart;
        this.monEnd = monEnd;
        this.tueStart = tueStart;
        this.tueEnd = tueEnd;
        this.wedStart = wedStart;
        this.wedEnd = wedEnd;
        this.thurStart = thurStart;
        this.thurEnd = thurEnd;
        this.friStart = friStart;
        this.friEnd = friEnd;
        this.satStart = satStart;
        this.satEnd = satEnd;
        this.sunStart = sunStart;
        this.sunEnd = sunEnd;
    }

    public int getIdealWeeklyHours() {
        return this.idealWeeklyHours;
    }

    public int getMaxWeeklyHours() {
        return this.maxWeeklyHours;
    }

    public int getMonStart() {
        return this.monStart;
    }

    public int getMonEnd() {
        return this.monEnd;
    }

    public int getTueStart() {
        return this.tueStart;
    }

    public int getTueEnd() {
        return this.tueEnd;
    }

    public int getWedStart() {
        return this.wedStart;
    }

    public int getWedEnd() {
        return this.wedEnd;
    }

    public int getThurStart() {
        return this.thurStart;
    }

    public int getThurEnd() {
        return this.thurEnd;
    }

    public int getFriStart() {
        return this.friStart;
    }

    public int getFriEnd() {
        return this.friEnd;
    }

    public int getSatStart() {
        return this.satStart;
    }

    public int getSatEnd() {
        return this.satEnd;
    }

    public int getSunStart() {
        return this.sunStart;
    }

    public int getSunEnd() {
        return this.sunEnd;
    }

}
