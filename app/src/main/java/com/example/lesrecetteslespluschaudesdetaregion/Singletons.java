package com.example.lesrecetteslespluschaudesdetaregion;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lesrecetteslespluschaudesdetaregion.data.RecipeAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static RecipeAPI recipeAPIInstance;
    private static SharedPreferences sharedPreferenceInstance;

    public static Gson getGson(){
        if (gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static RecipeAPI getRecipeAPI(){
        if (recipeAPIInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            recipeAPIInstance = retrofit.create(RecipeAPI.class);
        }
        return recipeAPIInstance;
    }

    public static SharedPreferences getSharedPreference(Context context){
        if (sharedPreferenceInstance == null){
            sharedPreferenceInstance = context.getSharedPreferences(Constants.KEY_RECIPES_LIST, Context.MODE_PRIVATE);
        }
        return sharedPreferenceInstance;
    }
}
