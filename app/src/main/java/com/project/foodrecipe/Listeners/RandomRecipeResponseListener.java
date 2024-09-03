package com.project.foodrecipe.Listeners;

import com.project.foodrecipe.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {


    void didFetch(RandomRecipeApiResponse response, String message);//fetching api response
    void didError(String message);//any error

}
