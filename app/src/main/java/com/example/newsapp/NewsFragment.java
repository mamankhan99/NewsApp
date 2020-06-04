package com.example.newsapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsFragment extends Fragment {


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        News news = JSONParser.getInstance().getNews(getArguments().getString("category"),getArguments().getString("title"));

        TextView textView = view.findViewById(R.id.news_title);
        textView.setText(news.getTitle()+"\n");

        textView = view.findViewById(R.id.news_author);
        textView.setText("Author: "+news.getAuthor()+"\n");

        textView = view.findViewById(R.id.news_description);
        textView.setText("News: "+news.getDescription()+"\n");

        textView = view.findViewById(R.id.news_category);
        textView.setText(news.getCategory()+"\n");

        textView = view.findViewById(R.id.news_publishedAt);
        textView.setText("Published At: "+news.getPublishedAt()+"\n");

        return view;

    }

}
