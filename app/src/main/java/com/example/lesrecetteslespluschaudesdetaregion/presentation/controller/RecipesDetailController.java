package com.example.lesrecetteslespluschaudesdetaregion.presentation.controller;

import android.content.SharedPreferences;

import com.example.lesrecetteslespluschaudesdetaregion.Constants;
import com.example.lesrecetteslespluschaudesdetaregion.Singletons;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.RecipesDetails;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.RestRecipesDetailsResponse;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.view.RecipesDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesDetailController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private RecipesDetailActivity view;


    public RecipesDetailController(RecipesDetailActivity detailActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = detailActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<RecipesDetails> recipesDetailsList = getDataFromCache();

        if(recipesDetailsList != null){
            //view.showDetail(recipesDetailsList);
        } else{
            makeApiCall();
        }
    }

    private List<RecipesDetails> getDataFromCache() {
        String jsonRecipesDetails = sharedPreferences.getString(Constants.KEY_RECIPES_DETAILS_LIST, null);

        if(jsonRecipesDetails == null){
            return null;
        } else{
            Type listType = new TypeToken<List<RecipesDetails>>(){}.getType();
            return gson.fromJson(jsonRecipesDetails, listType);
        }

    }

    private void makeApiCall(){
        Call<RestRecipesDetailsResponse> call = Singletons.getRecipeDetailsAPI().getRecipesDetailsResponse(
                Constants.API_KEY);

        call.enqueue(new Callback<RestRecipesDetailsResponse>() {
            @Override
            public void onResponse(Call<RestRecipesDetailsResponse> call, Response<RestRecipesDetailsResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<RecipesDetails> recipesDetailsList = (List<RecipesDetails>) response.body().getSteps();

                    saveList(recipesDetailsList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestRecipesDetailsResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<RecipesDetails> recipesDetails) {
        String jsonString = gson.toJson(recipesDetails);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_RECIPES_LIST, jsonString)
                .apply();
    }
}
