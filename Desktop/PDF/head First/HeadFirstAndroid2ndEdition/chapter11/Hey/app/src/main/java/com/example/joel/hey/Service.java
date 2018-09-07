package com.example.joel.hey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("client")
    Call<ItemResponse> getItems(
            @Query("marketer_id") int id
    );


    @GET("client/details")
    Call<ClientResponse> getClientDetails(
            @Query("id") int clientId
    );
}