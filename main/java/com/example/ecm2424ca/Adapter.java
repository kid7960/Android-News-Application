package com.example.ecm2424ca;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ecm2424ca.models.Article;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Article> articles;

    public Adapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article article = articles.get(position);

        holder.tvTitle.setText(article.getTitle());
        holder.tvSource.setText(article.getSource().getName());
        holder.tvDate.setText(dateTime(article.getPublishedAt()));

        String url = article.getUrl();
        String imageUrl = article.getUrlToImage();

        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.tvTitle.setText(article.getTitle());
        holder.tvSource.setText(article.getSource().getName());
        holder.tvDate.setText("\u2022"+dateTime(article.getPublishedAt()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedNews.class);
                intent.putExtra("title",article.getTitle());
                intent.putExtra("source",article.getSource().getName());
                intent.putExtra("desc",article.getDescription());
                intent.putExtra("date",dateTime(article.getPublishedAt()));
                intent.putExtra("imageURL",article.getUrlToImage());
                intent.putExtra("url",article.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    /*
    Formatting the date of news using PrettyTime
     */
    public String dateTime(String a) {
        String time = null;
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry())); //Getting local time
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                    ("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);     //Setting new format
            Date date = simpleDateFormat.parse(a);
            time = prettyTime.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

    /*
    Getting current country location
     */
    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
