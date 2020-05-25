package com.example.lesrecetteslespluschaudesdetaregion.presentation.model;

public class Recipes {
    private String title;
    private String imageUrls;
    private Integer id;
    private Integer readyInMinutes;
    private Integer servings;

    // Methods


    public String getTitle() {
        return title;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPreparationTime() {
        return readyInMinutes;
    }

    public Integer getServings() {
        return servings;
    }
}