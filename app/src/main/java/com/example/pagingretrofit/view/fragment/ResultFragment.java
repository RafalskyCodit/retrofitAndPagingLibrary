package com.example.pagingretrofit.view.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pagingretrofit.R;
import com.example.pagingretrofit.adapter.ImageListAdapter;
import com.example.pagingretrofit.utils.FragmentUtils;
import com.example.pagingretrofit.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private RecyclerView resultList;
    private FloatingActionButton searchButton;
    private ImageListAdapter adapter;
    private MainViewModel model;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListeners();
        observe();
    }

    private void setListeners() {
        searchButton.setOnClickListener(view -> FragmentUtils.replaceFragment(getFragmentManager(), new SearchFragment()));
    }

    private void observe() {
        model.getLivePagedList().observe(this, pagedList -> {
            Toast.makeText(getActivity(), pagedList.toString(), Toast.LENGTH_SHORT).show();
            adapter.submitList(pagedList);
        });
    }

    private void init(View view) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        adapter = new ImageListAdapter();

        resultList = view.findViewById(R.id.result_list);
        resultList.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultList.setAdapter(adapter);

        searchButton = view.findViewById(R.id.search_fab);
    }
}
