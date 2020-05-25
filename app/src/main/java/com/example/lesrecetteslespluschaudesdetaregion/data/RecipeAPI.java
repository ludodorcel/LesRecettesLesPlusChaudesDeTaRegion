package com.example.lesrecetteslespluschaudesdetaregion.data;

import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.RestRecipesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecipeAPI {
    @GET("/recipes/search")
    Call<RestRecipesResponse> getRecipesResponse(@Query("apiKey") String apiKey,
                                                 @Query("number") Integer number,
                                                 @Query("cuisine") String cuisine);
}
