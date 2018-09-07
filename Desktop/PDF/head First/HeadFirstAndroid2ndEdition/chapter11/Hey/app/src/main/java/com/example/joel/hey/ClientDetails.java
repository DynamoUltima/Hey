package com.example.joel.hey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientDetails {

    @SerializedName("id")
    @Expose
    private int clientId;

    @SerializedName("marketer_id")
    @Expose
    private int marketerId;

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
    @SerializedName("house_number")
    @Expose
    private String house_number;
    @SerializedName("gps_coordinates")
    @Expose
    private String gps_coordinates;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public ClientDetails(int clientId,
                         int marketerId,
                         String name,
                         String email,
                         String phone_number,
                         String location,
                         String street_name,
                         String house_number,
                         String gps_coordinates,
                         String created_at) {

        this.clientId = clientId;
        this.marketerId = marketerId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.location = location;
        this.street_name = street_name;
        this.house_number = house_number;
        this.gps_coordinates = gps_coordinates;
        this.created_at = created_at;
    }

    @SerializedName("created_at")
    @Expose
    private String created_at;


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(int marketerId) {
        this.marketerId = marketerId;
    }

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

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getGps_coordinates() {
        return gps_coordinates;
    }

    public void setGps_coordinates(String gps_coordinates) {
        this.gps_coordinates = gps_coordinates;
    }


}
