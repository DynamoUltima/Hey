package com.example.joel.hey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientResponse {


    @SerializedName("clients")
    @Expose
    public ClientDetails Details;

    public ClientDetails getDetails() {
        return Details;
    }

    public void setDetails(ClientDetails details) {
        Details = details;
    }

}
