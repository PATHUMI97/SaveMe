package com.example.donation.model;

public class Request {
    private int id;
    private String patientName;
    private String bldtyp;
    private String date;
    private String unit;
    private String note;
    private String location;
    private String imagePath;

    public Request() {

    }

    public Request(int id, String patientName, String bldtyp, String date, String unit, String note, String location,String imagePath) {
        this.id = id;
        this.patientName = patientName;
        this.bldtyp = bldtyp;
        this.date = date;
        this.unit = unit;
        this.note = note;
        this.location = location;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBldtyp() {
        return bldtyp;
    }

    public void setBldtyp(String bldtyp) {
        this.bldtyp = bldtyp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
