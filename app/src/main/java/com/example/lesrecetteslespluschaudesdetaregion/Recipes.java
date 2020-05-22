package com.example.lesrecetteslespluschaudesdetaregion;

public class Recipes {
    private String title;
    private String url_Image;
    private Integer id;
    private Integer readyInMinutes;
    private Integer servings;

    // Methods


    public String getTitle() {
        return title;
    }

    public String getUrl_Image() {
        return url_Image;
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