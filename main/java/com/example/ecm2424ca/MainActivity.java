package com.example.ecm2424ca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecm2424ca.api.ApiClient;
import com.example.ecm2424ca.api.ApiInterface;
import com.example.ecm2424ca.models.Article;
import com.example.ecm2424ca.models.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Article> articles = new ArrayList<>();
    final String API_KEY = "948bc6a9f980472dba5fa0f0f085aabe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCountry();
        retrieveJson(country,API_KEY);

        /*
        Swipe to refresh the news page
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(country,API_KEY);
            }
        });
    }

    public void retrieveJson(String country, String apiKey) {
        swipeRefreshLayout.setRefreshing(true);
        Call<News> call = ApiClient.getInstance().getApi().getNews(country,apiKey);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new com.example.ecm2424ca.Adapter(MainActivity.this,
                            articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}