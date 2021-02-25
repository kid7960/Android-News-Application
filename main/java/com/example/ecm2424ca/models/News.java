package com.example.ecm2424ca.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResult")
    @Expose
    private int totalResult ;

    @SerializedName("articles")
    @Expose
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

}
