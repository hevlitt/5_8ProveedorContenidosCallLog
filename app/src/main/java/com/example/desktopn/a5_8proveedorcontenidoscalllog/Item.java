package com.example.desktopn.a5_8proveedorcontenidoscalllog;

public class Item {

    String name,number,date,duration,type;

    public Item(String name, String number, String date, String duration, String type) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.duration = duration;
        this.type = type;
    }

    public String getName() { return name; }

    public String getNumber() { return number; }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

}
