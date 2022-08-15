package com.example.donation.model;

public class Donate {
    private int id;
    private String donatorName;
    private String bldtyp;
    private String note;
    private String location;
    private String imagePath;

    public Donate() {

    }

    public Donate(int id, String donatorName, String bldtyp, String note, String location,String imagePath) {
        this.id = id;
        this.donatorName = donatorName;
        this.bldtyp = bldtyp;
        this.note = note;
        this.location = location;
        this.imagePath =imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public String getBldtyp() {
        return bldtyp;
    }

    public void setBldtyp(String bldtyp) {
        this.bldtyp = bldtyp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
