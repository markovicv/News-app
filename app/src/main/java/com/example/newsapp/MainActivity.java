package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.listeners.OnNewsItemClickListener;
import com.example.newsapp.model.Article;
import com.example.newsapp.utils.Konstants;
import com.example.newsapp.viewModel.MainViewModel;


import java.util.List;

public class MainActivity extends AppCompatActivity implements  OnNewsItemClickListener {

    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private NewsAdapter newsAdapter;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initViewModel();
        populateActivity(0,null);
    }

    private void init() {
        recyclerView = findViewById(R.id.mainRecyclerViewId);
        linearLayout = findViewById(R.id.linearLayId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void populateActivity(int i,String query) {
        if(i==Konstants.SEARCH_ID){
            mainViewModel.getSearchArticles(query).observe(MainActivity.this, new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    setRecyclerAdapter(articles);
                }
            });
        }
        else {

            mainViewModel.getCategoryArticle(Konstants.CATEGORIES[i]).observe(this, new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    setRecyclerAdapter(articles);
                }
            });
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.searchId);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>2)
                   populateActivity(Konstants.SEARCH_ID,query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sportsId:
                populateActivity(Konstants.SPORT_ID,null);
                return true;
            case R.id.businessId:
                populateActivity(Konstants.BUSINESS_ID,null);
                return true;
            case R.id.technologyId:
                populateActivity(Konstants.TECHNOLOGY_ID,null);
                return true;
            case R.id.healthId:
                populateActivity(Konstants.HEALTH_ID,null);
                return true;
            case R.id.generalId:
                populateActivity(Konstants.GENERAL_ID,null);
                return true;
            case R.id.blueId:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.blueB));
                return true;
            case R.id.blackId:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.blackB));
                return true;
            case R.id.redId:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.redB));
                return true;
            case R.id.yellowId:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.yellowB));
                return true;
            case R.id.whiteId:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.whiteB));
                return  true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void setRecyclerAdapter(List<Article> articles) {
        if (articles != null) {
            newsAdapter = new NewsAdapter(MainActivity.this);
            newsAdapter.setArticles(articles);
            newsAdapter.setOnNewsItemClickListener(this);
            recyclerView.setAdapter(newsAdapter);


        }

    }



    @Override
    public void onNewsClick(Article article) {
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra(Konstants.URL_KEY,article.getUrl());
        startActivity(intent);


    }
}
