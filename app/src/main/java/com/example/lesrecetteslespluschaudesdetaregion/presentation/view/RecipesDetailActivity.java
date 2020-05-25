package com.example.lesrecetteslespluschaudesdetaregion.presentation.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesrecetteslespluschaudesdetaregion.R;
import com.example.lesrecetteslespluschaudesdetaregion.Singletons;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.controller.MainController;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.Recipes;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.Step;

import java.util.List;


public class RecipesDetailActivity extends AppCompatActivity {

    private TextView textDetails;
    private List<Step> steps;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailrecipes);

        textDetails = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String recipeJson = intent.getStringExtra("recipeKey");
        Recipes recipes = Singletons.getGson().fromJson(recipeJson, Recipes.class);
        showDetail(recipes);
    }

    private void showDetail(Recipes recipes) {
        textDetails.setText(recipes.getTitle());
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
