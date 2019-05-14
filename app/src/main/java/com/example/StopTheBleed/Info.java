package com.example.my_app;

public class Info {
    private String location;
    private String date;
    private String time;
    private String fees;
    private String instructor;

    public Info(String location, String date, String time, String fees, String instructor) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.fees = fees;
        this.instructor = instructor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
