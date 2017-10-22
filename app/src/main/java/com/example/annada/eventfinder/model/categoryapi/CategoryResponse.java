package com.example.annada.eventfinder.model.categoryapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class CategoryResponse {
    @SerializedName("categories")
    @Expose
    private List<CategoryApiCategory> categories = null;

    public List<CategoryApiCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryApiCategory> categories) {
        this.categories = categories;
    }
}
