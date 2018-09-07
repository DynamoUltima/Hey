package com.example.joel.hey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUser {
    /**
     * ResponseUser class has all the subcategories of the User(JsonObject)
     */
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("street_name")
    @Expose
    private String street_name;
    @SerializedName("gps_coordinates")
    @Expose
    private String gps_coordinates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getGps_coordinates() {
        return gps_coordinates;
    }

    public void setGps_coordinates(String gps_coordinates) {
        this.gps_coordinates = gps_coordinates;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
