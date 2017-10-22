package com.example.annada.eventfinder.model.categoryapi;

import android.support.annotation.Nullable;

import com.example.annada.eventfinder.model.Category;
import com.example.annada.eventfinder.model.Icon;

import java.util.List;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class CategoryModel {
    public String categoryId;
    private String categoryName;
    private String categoryIcon;
    private final static String venueIconSize = "64";
    private static double mLatitude;
    private static double mLongitude;

    public CategoryModel(String categoryId, String categoryName, Icon icon) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = buildIconUri(icon);
    }

    private String buildIconUri(Icon icon) {
        return icon.getPrefix() + venueIconSize + icon.getSuffix();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public static double getLatitude(){
        return mLatitude;
    }

    public static void setLatitude(double latitude){
        mLatitude = latitude;
    }

    public static double getLongitude(){
        return mLongitude;
    }

    public static void setLongitude(double longitude){
        mLongitude = longitude;
    }

    @Nullable
    public int color;
}
