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

public class TitleFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView titleListView;
    ArrayList<String> titlesList;
    ArrayAdapter<String> adapter;


    public TitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        titleListView = view.findViewById(R.id.title_list);
        Bundle bundle = getArguments();
        String cat = bundle.getString("category");
        titlesList = JSONParser.getInstance().getTitles(getArguments().getString("category"));
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1,titlesList);
        titleListView.setAdapter(adapter);
        titleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.displayNews(getArguments().getString("category"),titlesList.get(position));
    }
}
