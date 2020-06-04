package com.example.newsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONParser {
    private static JSONParser parser = new JSONParser();
    private JSONParser() { }
    public static JSONParser getInstance( ) {
        return parser;
    }

    private ArrayList<News> newsList;
    ArrayList<String> categoryList;

    public void readData(Context myContext, String filename) throws JSONException, IOException {
        InputStream is = myContext.getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, "UTF-8");

        newsList = new ArrayList<>();
        JSONObject obj = new JSONObject(json);

        int records = obj.getInt("totalRecords");
        JSONArray list = obj.getJSONArray("articles");
        for(int i=0;i<records;i++) {
            obj = (JSONObject) list.get(i);
            newsList.add(new News(obj.getString("category"),
                    obj.getString("author"),
                    obj.getString("title"),
                    obj.getString("description"),
                    obj.getString("publishedAt")
            ));
        }

        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<newsList.size();i++) {
            map.put(newsList.get(i).getCategory(),1);
        }
        categoryList = new ArrayList<>(map.keySet());
    }
    public ArrayList<String> getCategories()
    {
        return categoryList;
    }
    public ArrayList<String> getTitles(String category) {
        ArrayList<String> titleList = new ArrayList<>();
        for(int i=0;i<newsList.size();i++) {
            if(newsList.get(i).getCategory().equals(category))
                titleList.add(newsList.get(i).getTitle());
        }
        return titleList;
    }

    public News getNews(String category, String title) {

        for(int i=0;i<newsList.size();i++) {
            if(newsList.get(i).getCategory().equals(category) && newsList.get(i).getTitle().equals(title))
                return newsList.get(i);
        }
        return null;
    }

}
