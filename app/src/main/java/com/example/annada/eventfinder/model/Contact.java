package com.example.annada.eventfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public class Contact {
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("formattedPhone")
    @Expose
    private String formattedPhone;
    @SerializedName("twitter")
    @Expose
    private String twitter;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
