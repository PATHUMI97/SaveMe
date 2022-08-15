package com.example.donation.model;

public class User {
    private String id;
    private String username;
    private String email;
    private String fname;
    private String lname;
    private String nic;
    private String mobile;
    private String gender;
    private String dob;
    private String address;
    private String bldtyp;
    private String userTyp;
    private String imagePath;


    public User() {

    }

    public User(String id, String username,  String email) {

        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User( String fname, String lname,String nic,String mobile,String gender,String dob,String address,String bldtyp,String userTyp, String imagePath) {

        this.fname = fname;
        this.lname = lname;
        this.nic = nic;
        this.mobile = mobile;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.bldtyp = bldtyp;
        this.userTyp = userTyp;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserTyp() {
        return userTyp;
    }

    public void setUserTyp(String userTyp) {
        this.userTyp = userTyp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBldtyp() {
        return bldtyp;
    }

    public void setBldtyp(String bldtyp) {
        this.bldtyp = bldtyp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
