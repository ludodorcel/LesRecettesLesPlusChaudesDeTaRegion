package com.example.lesrecetteslespluschaudesdetaregion.presentation.controller;

import android.content.SharedPreferences;

import com.example.lesrecetteslespluschaudesdetaregion.Constants;
import com.example.lesrecetteslespluschaudesdetaregion.Singletons;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.Recipes;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.RestRecipesResponse;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Recipes> recipesList = getDataFromCache();

        if(recipesList != null){
            view.showList(recipesList);
        } else{
            makeApiCall();
        }
    }

    private List<Recipes> getDataFromCache() {
        String jsonRecipes = sharedPreferences.getString(Constants.KEY_RECIPES_LIST, null);

        if(jsonRecipes == null){
            return null;
        } else{
            Type listType = new TypeToken<List<Recipes>>(){}.getType();
            return gson.fromJson(jsonRecipes, listType);
        }

    }

    public void onItemClick(Recipes recipes){

    }

    private void makeApiCall(){
        Call<RestRecipesResponse> call = Singletons.getRecipeAPI().getRecipesResponse(
                Constants.API_KEY,
                10,
                "french");

        call.enqueue(new Callback<RestRecipesResponse>() {
            @Override
            public void onResponse(Call<RestRecipesResponse> call, Response<RestRecipesResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Recipes> recipesList = (List<Recipes>) response.body().getResults();

                    saveList(recipesList);
                    view.showList(recipesList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestRecipesResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Recipes> recipesList) {
        String jsonString = gson.toJson(recipesList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_RECIPES_LIST, jsonString)
                .apply();
    }
}
