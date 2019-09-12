package com.example.newsapp.repository;

import androidx.lifecycle.LiveData;

import com.example.newsapp.db.NetworkCalls;
import com.example.newsapp.model.Article;
import com.example.newsapp.utils.Konstants;

import java.util.List;

public class Repository {


    private LiveData<List<Article>> categoryArticles;
    private LiveData<List<Article>> searchArticles;

    public Repository(){
        NetworkCalls.fetchSearchArticles(Konstants.CATEGORIES[0]);
    }


    public void fetchCategoryArticle(String type){
        NetworkCalls.fetchCategoryArticles(type);

    }
    public void fetchSearchArticle(String query){
        NetworkCalls.fetchSearchArticles(query);
    }

    public LiveData<List<Article>> getCategoryArticles() {
        categoryArticles = NetworkCalls.getCategoryArticles();
        return categoryArticles;
    }



    public LiveData<List<Article>> getSearchArticles() {
        searchArticles = NetworkCalls.getSearchedArticles();
        return searchArticles;
    }
}
