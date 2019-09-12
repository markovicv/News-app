package com.example.newsapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.utils.Konstants;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        url = getIntent().getStringExtra(Konstants.URL_KEY);
        init();
    }
    private void init(){
        webView = findViewById(R.id.webViewId);

        webView = findViewById(R.id.webViewId);
        progressBar = findViewById(R.id.detailProgresBarId);
        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
