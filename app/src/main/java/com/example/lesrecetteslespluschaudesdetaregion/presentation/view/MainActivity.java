package com.example.lesrecetteslespluschaudesdetaregion.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesrecetteslespluschaudesdetaregion.R;
import com.example.lesrecetteslespluschaudesdetaregion.Singletons;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.controller.MainController;
import com.example.lesrecetteslespluschaudesdetaregion.presentation.model.Recipes;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController mainController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainController = new MainController(
                this,
                Singletons.getGson(),
            Singletons.getSharedPreference(getApplicationContext())
        );
        mainController.onStart();
    }

    public void showList(List<Recipes> recipesList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(recipesList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipes recipes) {
                mainController.onItemClick(recipes);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Recipes recipes){
        Intent myIntent = new Intent(MainActivity.this, RecipesDetailActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}