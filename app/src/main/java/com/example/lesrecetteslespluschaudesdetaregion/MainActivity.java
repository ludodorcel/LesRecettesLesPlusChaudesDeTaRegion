package com.example.lesrecetteslespluschaudesdetaregion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://api.spoonacular.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeApiCall();
    }

    private void showList(List<Recipes> recipesList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        // define an adapter
        mAdapter = new ListAdapter(recipesList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        String apiKey = "c150fb2f36ec4c33b035b3aebfd887f8";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

        Call<RestRecipesResponse> call = recipeAPI.getRecipesResponse(
                "c150fb2f36ec4c33b035b3aebfd887f8",
                10,
                "french");

        call.enqueue(new Callback<RestRecipesResponse>() {
            @Override
            public void onResponse(Call<RestRecipesResponse> call, Response<RestRecipesResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Recipes> recipesList = (List<Recipes>) response.body().getResults();
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    showList(recipesList);
                    //changesList.forEach(change -> System.out.println(change.subject));
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RestRecipesResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}