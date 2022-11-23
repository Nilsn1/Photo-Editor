package com.nilscreation.photoeditor;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    ArrayList<effects> arrayeffects = new ArrayList<>();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager((context), LinearLayoutManager.HORIZONTAL, false));

        arrayeffects.add(new effects(R.drawable.hat1));
        arrayeffects.add(new effects(R.drawable.hat2));
        arrayeffects.add(new effects(R.drawable.hat3));
        arrayeffects.add(new effects(R.drawable.hat4));
        arrayeffects.add(new effects(R.drawable.hat5));
        arrayeffects.add(new effects(R.drawable.hat6));
        arrayeffects.add(new effects(R.drawable.hat7));
        arrayeffects.add(new effects(R.drawable.hat8));
        arrayeffects.add(new effects(R.drawable.hat9));
        arrayeffects.add(new effects(R.drawable.hat10));

        RecyclerviewEffectAdapter adapter = new RecyclerviewEffectAdapter(context, arrayeffects, getActivity());

        recyclerView.setAdapter(adapter);

        return view;
    }

}