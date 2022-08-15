package com.example.donation.model;

public class Blood {
    private int id;
    private String type;
    private String imagePath;


    public Blood() {
    }

    public Blood(int id, String type,  String imagePath) {
        this.id = id;
        this.type = type;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

