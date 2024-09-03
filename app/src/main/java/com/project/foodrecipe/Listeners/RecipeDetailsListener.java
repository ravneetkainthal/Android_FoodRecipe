package com.project.foodrecipe.Listeners;

import com.project.foodrecipe.Models.RecipeDetailResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailResponse response, String message);

    void didError(String message);
}
