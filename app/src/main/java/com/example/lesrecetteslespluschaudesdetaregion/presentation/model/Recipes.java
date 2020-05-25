package com.example.lesrecetteslespluschaudesdetaregion.presentation.model;

public class Recipes {
    private String title;
    private Integer id;
    private Integer readyInMinutes;
    private Integer servings;

    // Methods


    public String getTitle() {
        return title;
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