package com.example.lesrecetteslespluschaudesdetaregion.presentation.model;

import java.util.List;

public class RestRecipesResponse {
    private Integer offset; // nb results skipped
    private Integer number; // nb results returned
    private Integer preparationTime; // in minutes
    private Integer servings; // nb of servings
    private String imageUrls; // URL of the image
    private List<Recipes> results;

    public Integer getOffset() {
        return offset;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public Integer getServings() {
        return servings;
    }

    public List<Recipes> getResults() {
        return results;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public RestRecipesResponse() {
    }
}
