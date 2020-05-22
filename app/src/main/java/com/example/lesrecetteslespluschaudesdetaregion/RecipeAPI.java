package com.example.lesrecetteslespluschaudesdetaregion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecipeAPI {
    @GET("/recipes/search")
    Call<RestRecipesResponse> getRecipesResponse(@Query("apiKey") String apiKey,
                                                 @Query("number") Integer number,
                                                 @Query("cuisine") String cuisine);
}
