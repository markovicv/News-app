package com.example.newsapp.services;

import com.example.newsapp.model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("top-headlines?country=us")
    Call<WebSite>getCategoryArticle(
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
    @GET("everything")
    Call<WebSite>getSearchArticle(
            @Query("q") String searchRes,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

}
