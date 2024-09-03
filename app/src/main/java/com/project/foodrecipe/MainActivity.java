package com.project.foodrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.foodrecipe.Adapters.RandomRecipeAdapter;
import com.project.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.project.foodrecipe.Listeners.RecipeClickListener;
import com.project.foodrecipe.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //objects
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    //spinner object
    Spinner spinner;

    List<String> tags= new ArrayList<>();//adding item to this when user select any item from spinner



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intialize dialog
        dialog=new ProgressDialog(this);


        dialog.setTitle("Loading...");

        spinner=findViewById(R.id.spinner_tags);
        //array adapter attach to spinner
        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );

        //set dropdown layout
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        //attach spinner
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager= new RequestManager(this);

        //removing as we don't need as calling api from spinner
        manager.getRandomRecipes(randomRecipeResponseListener,tags);
        //dialog.show();


    }

    private final RandomRecipeResponseListener randomRecipeResponseListener= new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            //initialize recycler view
            recyclerView=findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            //create adapter
            randomRecipeAdapter=new RandomRecipeAdapter(MainActivity.this, response.recipes, recipeClickListener);
            //add this to recyclerView
            recyclerView.setAdapter(randomRecipeAdapter);


        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();

        }
    };

    //separate on create method for dropdown
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

           tags.clear();
            tags.add(parent.getSelectedView().toString());
            manager.getRandomRecipes(randomRecipeResponseListener,tags);
           dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

//passing listener
    private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {

            //will show id of recipe
           // Toast.makeText(MainActivity.this,id, Toast.LENGTH_SHORT).show();

            //START NEW ACTIVITY TO GET DETAILS
            startActivity(new Intent(MainActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id)); //pass id to new activity

        }
    };

}