package com.example.joel.hey;

public class SignedClient {
    private int marketer_id;
    private String name;
    private String email;
    private String location;
    private String phone_number;
    private String street_name;
    private String house_number;
    private String gps_coordinates;

    public SignedClient(int marketer_id,
                        String name,
                        String email,
                        String location,
                        String phone_number,
                        String street_name,
                        String house_number,
                        String gps_coordinates) {
        this.marketer_id = marketer_id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone_number = phone_number;
        this.street_name = street_name;
        this.house_number = house_number;
        this.gps_coordinates = gps_coordinates;
    }


}
