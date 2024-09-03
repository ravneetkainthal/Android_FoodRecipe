package com.project.foodrecipe;

import android.content.Context;

import com.project.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.project.foodrecipe.Listeners.RecipeDetailsListener;
import com.project.foodrecipe.Models.RandomRecipeApiResponse;
import com.project.foodrecipe.Models.RecipeDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//HANDLE API
public class RequestManager {

    Context context;
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //constructor
    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        //instance
        CallRandomRecipes callRandomRecipes= retrofit.create(CallRandomRecipes.class);

        //call object
        Call<RandomRecipeApiResponse> call=  callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError((response.message()));
                    return;
                }

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }


    public  void getRecipeDetails(RecipeDetailsListener listener, int id){

        CallRecipeDetails callRecipeDetails= retrofit.create(CallRecipeDetails.class);

        //call object
        Call<RecipeDetailResponse> call= callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));

        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {

                listener.didError(t.getMessage());

            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")   List<String> tags
                );

    }

    //add api for info
    private  interface  CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailResponse> callRecipeDetails(
               //pass is and api key
               @Path("id") int id,
               @Query("apiKey") String apiKey
        );
    }
}
