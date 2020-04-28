package com.xyz;

public class MyTestObj {

    private String test;

    public String getTest() {
        return this.test;
    }

    public void setTest(final String testParam) {
        this.test = testParam;
    }

    @Override
    public String toString() {
        return "MyTestObj [test=" + this.test + "]";
    }

}
