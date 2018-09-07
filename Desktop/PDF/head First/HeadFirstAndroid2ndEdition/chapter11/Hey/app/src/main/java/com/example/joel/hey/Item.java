package com.example.joel.hey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {


    @SerializedName("name")
    @Expose
    private String login;

    @SerializedName("location")
    @Expose
    private String htmlUrl;

    @SerializedName("id")
    @Expose
    private int clientId;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Item(String login, String htmlUrl, int clientId, String created_at) {

        this.login = login;
        this.htmlUrl = htmlUrl;
        this.clientId = clientId;
        this.created_at = created_at;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    //    @SerializedName("email")
//    @Expose
    //  private String avatarUrl;

//    @SerializedName("created_at")
//    @Expose
//    private int RegisteredTime;


//    public String getAvatarUrl(){
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(String avatarUrl){
//        this.avatarUrl = avatarUrl;
//    }


}
