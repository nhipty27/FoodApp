package com.example.foodapp.Listeners;

import com.example.foodapp.Models.InstructionsResponse;
import com.example.foodapp.Models.RandomrecipeApiResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message); //fetch api
    void didError(String message);
}
