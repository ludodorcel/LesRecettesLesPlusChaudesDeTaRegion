package com.example.lesrecetteslespluschaudesdetaregion.presentation.model;

import java.util.List;

public class RestRecipesDetailsResponse {
    public String name;
    public List<RecipesDetails> steps;


    public String getName() {
        return name;
    }

    public List<RecipesDetails> getSteps() {
        return steps;
    }

    public RestRecipesDetailsResponse() {
    }
}

