package com.example.newsapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {
    public CategoryFragment() {
        // Required empty public constructor
    }
    ListView categoryListView;
    ArrayList<String> categoryList;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categoryListView = view.findViewById(R.id.category_list);
        categoryList = JSONParser.getInstance().getCategories();
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1,categoryList);
        categoryListView.setAdapter(adapter);
        categoryListView.setOnItemClickListener(this);
        //categoryListView.setOnItemClickListener();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.displayTitles(categoryList.get(position));
    }
}
