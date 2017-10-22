package com.example.annada.eventfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public class Geometry {
    @SerializedName("bounds")
    @Expose
    private Bounds bounds;

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}
