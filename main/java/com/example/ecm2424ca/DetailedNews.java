package com.example.ecm2424ca;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedNews extends AppCompatActivity {

    TextView tvTitle, tvSource, tvDate, tvDesc;
    WebView webView;        //display web content
    ImageView imageView;
    ProgressBar loader;     //loading bar for web view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        tvTitle = findViewById(R.id.tvTitle);
        tvSource = findViewById(R.id.tvSource);
        tvDate = findViewById(R.id.tvDate);
        tvDesc = findViewById(R.id.tvDesc);

        webView = findViewById(R.id.webView);

        imageView = findViewById(R.id.imageView);

        loader = findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //back button on action bar

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String source = intent.getStringExtra("source");
        String date = intent.getStringExtra("date");
        String desc = intent.getStringExtra("desc");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");

        tvTitle.setText(title);
        tvSource.setText(source);
        tvDate.setText(date);
        tvDesc.setText(desc);

        Picasso.with(DetailedNews.this).load(imageUrl).into(imageView);

        webView.getSettings().setDomStorageEnabled(true);   //enable web storage
        webView.getSettings().setJavaScriptEnabled(true);   //set web view to enable javaScript
        webView.getSettings().setLoadsImagesAutomatically(true);    //set web view to load images
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);  //set scrollbar style
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        /*
        if web view is loaded progressbar disappears
         */
        if (webView.isShown()) {
            loader.setVisibility(View.INVISIBLE);
        }

    }
}