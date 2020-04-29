package com.ykb.spring;


public class Department {

    private String name;
    private int    employeeCount;
    private String emailHost;

    public String getName() {
        return this.name;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(final int employeeCountParam) {
        this.employeeCount = employeeCountParam;
    }

    public String getEmailHost() {
        return this.emailHost;
    }

    public void setEmailHost(final String emailHostParam) {
        this.emailHost = emailHostParam;
    }

    @Override
    public String toString() {
        return "Department [name="
               + this.name
               + ", employeeCount="
               + this.employeeCount
               + ", emailHost="
               + this.emailHost
               + "]";
    }


}
