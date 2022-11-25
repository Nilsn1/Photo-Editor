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

        addGlasses();

        RecyclerviewEffectAdapter adapter = new RecyclerviewEffectAdapter(context, arrayeffects, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void addGlasses() {
        arrayeffects.add(new effects(R.drawable.zglass1));
        arrayeffects.add(new effects(R.drawable.zglass2));
        arrayeffects.add(new effects(R.drawable.zglass3));
        arrayeffects.add(new effects(R.drawable.zglass4));
        arrayeffects.add(new effects(R.drawable.zglass5));
        arrayeffects.add(new effects(R.drawable.zglass6));
        arrayeffects.add(new effects(R.drawable.zglass7));
        arrayeffects.add(new effects(R.drawable.zglass8));
        arrayeffects.add(new effects(R.drawable.zglass9));
        arrayeffects.add(new effects(R.drawable.zglass10));
        arrayeffects.add(new effects(R.drawable.zglass11));
        arrayeffects.add(new effects(R.drawable.zglass12));
        arrayeffects.add(new effects(R.drawable.zglass13));
        arrayeffects.add(new effects(R.drawable.zglass14));
        arrayeffects.add(new effects(R.drawable.zglass15));
        arrayeffects.add(new effects(R.drawable.zglass16));
        arrayeffects.add(new effects(R.drawable.zglass17));
        arrayeffects.add(new effects(R.drawable.zglass18));
        arrayeffects.add(new effects(R.drawable.zglass19));
        arrayeffects.add(new effects(R.drawable.zglass20));
        arrayeffects.add(new effects(R.drawable.zglass21));
        arrayeffects.add(new effects(R.drawable.zglass22));
        arrayeffects.add(new effects(R.drawable.zglass23));
        arrayeffects.add(new effects(R.drawable.zglass24));
        arrayeffects.add(new effects(R.drawable.zglass25));
        arrayeffects.add(new effects(R.drawable.zglass26));
        arrayeffects.add(new effects(R.drawable.zglass27));
        arrayeffects.add(new effects(R.drawable.zglass28));
        arrayeffects.add(new effects(R.drawable.zglass29));
        arrayeffects.add(new effects(R.drawable.zglass30));
        arrayeffects.add(new effects(R.drawable.zglass31));
        arrayeffects.add(new effects(R.drawable.zglass32));
        arrayeffects.add(new effects(R.drawable.zglass33));
        arrayeffects.add(new effects(R.drawable.zglass34));
        arrayeffects.add(new effects(R.drawable.zglass35));
        arrayeffects.add(new effects(R.drawable.zglass36));
        arrayeffects.add(new effects(R.drawable.zglass37));
        arrayeffects.add(new effects(R.drawable.zglass38));
        arrayeffects.add(new effects(R.drawable.zglass39));
        arrayeffects.add(new effects(R.drawable.zglass40));
        arrayeffects.add(new effects(R.drawable.zglass41));
        arrayeffects.add(new effects(R.drawable.zglass42));
        arrayeffects.add(new effects(R.drawable.zglass43));
        arrayeffects.add(new effects(R.drawable.zglass44));
        arrayeffects.add(new effects(R.drawable.zglass45));
        arrayeffects.add(new effects(R.drawable.zglass46));
        arrayeffects.add(new effects(R.drawable.zglass47));
        arrayeffects.add(new effects(R.drawable.zglass48));
        arrayeffects.add(new effects(R.drawable.zglass49));
        arrayeffects.add(new effects(R.drawable.zglass50));
    }
}