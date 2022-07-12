package com.example.database.classes;

public class Activity_class {

    String name;
    String address;
    String statuse;

    public Activity_class(String name, String address, String statuse) {
        this.name = name;
        this.address = address;
        this.statuse = statuse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatuse() {
        return statuse;
    }

    public void setStatuse(String statuse) {
        this.statuse = statuse;
    }
}
