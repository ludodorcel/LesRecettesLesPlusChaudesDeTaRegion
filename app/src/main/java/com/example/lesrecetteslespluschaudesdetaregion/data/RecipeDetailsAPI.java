package com.example.lesrecetteslespluschaudesdetaregion.data;

import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.RestRecipesDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecipeDetailsAPI {
    @GET("/analyzedInstructions")
    Call<RestRecipesDetailsResponse> getRecipesDetailsResponse(@Query("apiKey") String apiKey);
}
