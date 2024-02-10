package model;

import java.util.*;

public class Employee {

    private String name;
    private String job;

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

}
