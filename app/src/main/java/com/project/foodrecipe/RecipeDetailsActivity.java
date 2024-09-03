package com.project.foodrecipe;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.foodrecipe.Adapters.IngredientsAdapter;
import com.project.foodrecipe.Listeners.RecipeDetailsListener;
import com.project.foodrecipe.Models.RecipeDetailResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    //pass below from activityrecipedetail.xml
    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageview_meal_image;
    RecyclerView recycler_meal_ingredients;
    RequestManager manager;
    ProgressDialog dialog;

    IngredientsAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        //capture id here from intent
        id=Integer.parseInt(getIntent().getStringExtra("id"));

        //call api
        manager= new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading Details");
        dialog.show();


    }
    //intialize all views

    private void findViews() {
        textView_meal_name= findViewById(R.id.textView_meal_name);
        textView_meal_source= findViewById(R.id.textView_meal_source);
        textView_meal_summary= findViewById(R.id.textView_meal_summary);
        imageview_meal_image= findViewById(R.id.imageview_meal_image);
        recycler_meal_ingredients= findViewById(R.id.recycler_meal_ingredients);

    }

    private final RecipeDetailsListener recipeDetailsListener =new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {

            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageview_meal_image);

            //adapter
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));

            ingredientAdapter= new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);

            recycler_meal_ingredients.setAdapter(ingredientAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
}