package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONParser.getInstance().readData(this,"news.json");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayCategories();
    }
    public void displayCategories()
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        CategoryFragment categoryFragment = new CategoryFragment();
        transaction.add(R.id.main_fragment,categoryFragment);
        transaction.commit();
    }

    public void displayTitles(String category)
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        TitleFragment titleFragment = new TitleFragment();

        Bundle bundle = new Bundle();
        bundle.putString("category",category);
        titleFragment.setArguments(bundle);

        transaction.replace(R.id.main_fragment,titleFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void displayNews(String category,String title)
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category",category);
        bundle.putString("title",title);
        newsFragment.setArguments(bundle);

        transaction.replace(R.id.main_fragment,newsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
