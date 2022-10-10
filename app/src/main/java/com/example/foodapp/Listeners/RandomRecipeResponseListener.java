package com.example.foodapp.Listeners;

import com.example.foodapp.Models.RandomrecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomrecipeApiResponse response, String message); //fetch api
    void didError(String message);
}
