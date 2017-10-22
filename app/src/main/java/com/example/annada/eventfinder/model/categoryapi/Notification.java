package com.example.annada.eventfinder.model.categoryapi;

import com.example.annada.eventfinder.model.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class Notification {
    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("type")
    @Expose
    private String type;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
