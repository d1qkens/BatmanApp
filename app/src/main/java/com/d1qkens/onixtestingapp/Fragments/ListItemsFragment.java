package com.d1qkens.onixtestingapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d1qkens.onixtestingapp.Utils.CardAdapter;
import com.d1qkens.onixtestingapp.R;

import java.util.ArrayList;


public class ListItemsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_list_items, container, false);

        RecyclerView mRecyclerView = (RecyclerView) V.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> cars = getActivity().getIntent().getStringArrayListExtra(RegistrationFragment.IMAGES);
        ArrayList<String> titles = getActivity().getIntent().getStringArrayListExtra(RegistrationFragment.TITLES);
        RecyclerView.Adapter mAdapter = new CardAdapter(cars, titles, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return V;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
