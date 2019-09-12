package com.example.newsapp.db;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.WebSite;
import com.example.newsapp.services.ApiInterface;
import com.example.newsapp.services.RetrofitClient;
import com.example.newsapp.utils.Konstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {

    private static MutableLiveData<List<Article>> categoryArticles = new MutableLiveData<>();
    private static MutableLiveData<List<Article>> searchedArticles = new MutableLiveData<>();


    public static void fetchCategoryArticles(String typeOfNews){
        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<WebSite> call = apiInterface.getCategoryArticle(typeOfNews,Konstants.API_KEY);
        call.enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                if(response.isSuccessful() && response.body()!=null){
                    if(categoryArticles.getValue()!=null && categoryArticles.getValue().size()!=0)
                        categoryArticles.getValue().clear();
                    List<Article> list = response.body().getArticles();
                    categoryArticles.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<WebSite> call, Throwable t) {

            }
        });
    }
    public static void fetchSearchArticles(String query){
        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<WebSite> call = apiInterface.getSearchArticle(query,Konstants.PUBLISHED_AT,Konstants.API_KEY);
        call.enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                if(response.isSuccessful() && response.body()!=null){
                    if(searchedArticles.getValue()!=null && searchedArticles.getValue().size()!=0)
                        searchedArticles.getValue().clear();
                    List<Article>list = response.body().getArticles();
                    searchedArticles.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<WebSite> call, Throwable t) {

            }
        });
    }


    public static MutableLiveData<List<Article>> getCategoryArticles() {
        return categoryArticles;
    }

    public static MutableLiveData<List<Article>> getSearchedArticles() {
        return searchedArticles;
    }
}
