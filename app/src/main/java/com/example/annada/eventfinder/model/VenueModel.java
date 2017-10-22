package com.example.annada.eventfinder.model;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public class VenueModel {
    private String venueName;
    private String venueRating;
    private String venueIcon;
    private final static String venueIconSize = "64";

    public VenueModel(String venueName, String venueRating, List<Category> venueCategories) {
        this.venueName = venueName;
        this.venueRating = venueRating;
        this.venueIcon = buildIconUri(venueCategories);
    }

    private String buildIconUri(List<Category> venueCategories) {
        return venueCategories.get(0).getIcon().getPrefix() + venueIconSize + venueCategories.get(0).getIcon().getSuffix();
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueRating() {
        return venueRating;
    }


    public String getVenueIcon() {
        return venueIcon;
    }

    @Nullable
    public int color;

}
