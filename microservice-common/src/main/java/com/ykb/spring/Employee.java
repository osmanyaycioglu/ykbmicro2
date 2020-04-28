package com.ykb.spring;


public class Employee {

    private String name;
    private String surname;
    private String department;
    private String organization;

    public String getName() {
        return this.name;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surnameParam) {
        this.surname = surnameParam;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(final String departmentParam) {
        this.department = departmentParam;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(final String organizationParam) {
        this.organization = organizationParam;
    }


}
