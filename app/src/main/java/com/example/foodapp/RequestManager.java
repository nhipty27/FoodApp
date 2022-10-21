package com.example.foodapp;
//class quan ly viec call API

import android.content.Context;

import com.example.foodapp.Listeners.InstructionsListener;
import com.example.foodapp.Listeners.RandomRecipeResponseListener;
import com.example.foodapp.Listeners.RecipeDetailsListener;
import com.example.foodapp.Listeners.SimilarRecipesListener;
import com.example.foodapp.Models.InstructionsResponse;
import com.example.foodapp.Models.RandomrecipeApiResponse;
import com.example.foodapp.Models.RecipeDetailsResponse;
import com.example.foodapp.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getrandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomrecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10", tags);
        call.enqueue(new Callback<RandomrecipeApiResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RandomrecipeApiResponse> call, Response<RandomrecipeApiResponse> response) {
                if( !response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(retrofit2.Call<RandomrecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallrecipeDetail callrecipeDetail = retrofit.create(CallrecipeDetail.class);
        Call<RecipeDetailsResponse> call = callrecipeDetail.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(retrofit2.Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id){
        CallSimilarRecipe callSimilarRecipe = retrofit.create(CallSimilarRecipe.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipe.callSimilarRecipe(id,"4", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(retrofit2.Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getInstructionRecipes(InstructionsListener listener, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructionsRecipe(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        retrofit2.Call<RandomrecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );
    }

    private interface CallrecipeDetail{
        @GET("recipes/{id}/information")
        retrofit2.Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipe{
        @GET("recipes/{id}/similar")
        retrofit2.Call<List<SimilarRecipeResponse>> callSimilarRecipe(
            @Path("id") int id,
            @Query("number") String number,
            @Query("apiKey") String apiKey
        );
    }

    private interface CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        retrofit2.Call<List<InstructionsResponse>> callInstructionsRecipe(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
