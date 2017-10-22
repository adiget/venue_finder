package com.example.annada.eventfinder.model.categoryapi;


import com.example.annada.eventfinder.model.Meta;
import com.example.annada.eventfinder.model.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class CategoryListResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;
    @SerializedName("response")
    @Expose
    private CategoryResponse response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public CategoryResponse getResponse() {
        return response;
    }

    public void setResponse(CategoryResponse response) {
        this.response = response;
    }
}
