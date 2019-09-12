package com.example.newsapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.model.Article;
import com.example.newsapp.repository.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Article>> categoryArticle;
    private LiveData<List<Article>> searchArticles;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }




    public LiveData<List<Article>> getCategoryArticle(String type){
        repository.fetchCategoryArticle(type);
        categoryArticle = repository.getCategoryArticles();
        return categoryArticle;
    }

    public LiveData<List<Article>> getSearchArticles(String query) {
        repository.fetchSearchArticle(query);
        searchArticles = repository.getSearchArticles();
        return searchArticles;
    }
}
