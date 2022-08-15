package com.example.donation.model;

public class Campaign {
    private int id;
    private String imagePath;
    private String discription;

    public Campaign() {

    }

    public Campaign(int id, String imagePath, String discription) {
        this.id = id;
        this.imagePath = imagePath;
        this.discription = discription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
