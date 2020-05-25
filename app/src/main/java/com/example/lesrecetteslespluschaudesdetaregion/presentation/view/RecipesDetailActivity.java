package com.example.lesrecetteslespluschaudesdetaregion.presentation.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesrecetteslespluschaudesdetaregion.R;
import com.example.lesrecetteslespluschaudesdetaregion.Singletons;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.Recipes;


public class RecipesDetailActivity extends AppCompatActivity {

    private TextView textDetails;

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
}
