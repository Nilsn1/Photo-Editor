package com.nilscreation.photoeditor;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    ArrayList<effects> arrayeffects = new ArrayList<>();
    LinearLayout btnSunglasses, btnFrames, btnCool, btnGirls, btnHats;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        btnSunglasses = view.findViewById(R.id.btnSunglasses);
        btnFrames = view.findViewById(R.id.btnFrames);
        btnCool = view.findViewById(R.id.btnCool);
        btnHats = view.findViewById(R.id.btnHat);
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager((context), LinearLayoutManager.HORIZONTAL, false));

        addSunglasses();
        setadapter();

        btnSunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSunglasses();
                setadapter();
            }
        });

        btnFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFrames();
                setadapter();
            }
        });

        btnCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCool();
                setadapter();
            }
        });

        btnHats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHats();
                setadapter();
            }
        });

        return view;
    }

    private void setadapter() {
        RecyclerviewEffectAdapter adapter = new RecyclerviewEffectAdapter(context, arrayeffects, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void addSunglasses() {
        arrayeffects.removeAll(arrayeffects);
        arrayeffects.add(new effects(R.drawable.zsunglasses1));
        arrayeffects.add(new effects(R.drawable.zsunglasses2));
        arrayeffects.add(new effects(R.drawable.zsunglasses3));
        arrayeffects.add(new effects(R.drawable.zsunglasses4));
        arrayeffects.add(new effects(R.drawable.zsunglasses5));
        arrayeffects.add(new effects(R.drawable.zsunglasses6));
        arrayeffects.add(new effects(R.drawable.zsunglasses7));
        arrayeffects.add(new effects(R.drawable.zsunglasses8));
        arrayeffects.add(new effects(R.drawable.zsunglasses9));
        arrayeffects.add(new effects(R.drawable.zsunglasses10));
        arrayeffects.add(new effects(R.drawable.zsunglasses11));
        arrayeffects.add(new effects(R.drawable.zsunglasses12));
        arrayeffects.add(new effects(R.drawable.zsunglasses13));
        arrayeffects.add(new effects(R.drawable.zsunglasses14));
        arrayeffects.add(new effects(R.drawable.zsunglasses15));
    }

    private void addFrames() {
        arrayeffects.removeAll(arrayeffects);
        arrayeffects.add(new effects(R.drawable.zframes1));
        arrayeffects.add(new effects(R.drawable.zframes2));
        arrayeffects.add(new effects(R.drawable.zframes3));
        arrayeffects.add(new effects(R.drawable.zframes4));
        arrayeffects.add(new effects(R.drawable.zframes5));
        arrayeffects.add(new effects(R.drawable.zframes6));
        arrayeffects.add(new effects(R.drawable.zframes7));
        arrayeffects.add(new effects(R.drawable.zframes8));
        arrayeffects.add(new effects(R.drawable.zframes9));
        arrayeffects.add(new effects(R.drawable.zframes10));
        arrayeffects.add(new effects(R.drawable.zframes11));
        arrayeffects.add(new effects(R.drawable.zframes12));
        arrayeffects.add(new effects(R.drawable.zframes13));
        arrayeffects.add(new effects(R.drawable.zframes14));
        arrayeffects.add(new effects(R.drawable.zframes15));
    }

    private void addCool() {
        arrayeffects.removeAll(arrayeffects);
        arrayeffects.add(new effects(R.drawable.zcool1));
        arrayeffects.add(new effects(R.drawable.zcool2));
        arrayeffects.add(new effects(R.drawable.zcool3));
        arrayeffects.add(new effects(R.drawable.zcool4));
        arrayeffects.add(new effects(R.drawable.zcool5));
        arrayeffects.add(new effects(R.drawable.zcool6));
        arrayeffects.add(new effects(R.drawable.zcool7));
        arrayeffects.add(new effects(R.drawable.zcool8));
        arrayeffects.add(new effects(R.drawable.zcool9));
        arrayeffects.add(new effects(R.drawable.zcool10));
        arrayeffects.add(new effects(R.drawable.zcool11));
        arrayeffects.add(new effects(R.drawable.zcool12));
        arrayeffects.add(new effects(R.drawable.zcool13));
        arrayeffects.add(new effects(R.drawable.zcool14));
        arrayeffects.add(new effects(R.drawable.zcool15));
        arrayeffects.add(new effects(R.drawable.zcool16));
        arrayeffects.add(new effects(R.drawable.zcool17));
        arrayeffects.add(new effects(R.drawable.zcool18));
        arrayeffects.add(new effects(R.drawable.zcool19));
        arrayeffects.add(new effects(R.drawable.zcool20));
    }

    private void addGirlsglasses() {

    }

    private void addHats() {
        arrayeffects.removeAll(arrayeffects);
        arrayeffects.add(new effects(R.drawable.zcap1));
        arrayeffects.add(new effects(R.drawable.zcap2));
        arrayeffects.add(new effects(R.drawable.zcap3));
        arrayeffects.add(new effects(R.drawable.zcap4));
        arrayeffects.add(new effects(R.drawable.zcap5));
        arrayeffects.add(new effects(R.drawable.zcap6));
        arrayeffects.add(new effects(R.drawable.zcap7));
        arrayeffects.add(new effects(R.drawable.zcap8));
        arrayeffects.add(new effects(R.drawable.zcap9));
        arrayeffects.add(new effects(R.drawable.zcap10));
        arrayeffects.add(new effects(R.drawable.zcap11));
        arrayeffects.add(new effects(R.drawable.zcap12));
        arrayeffects.add(new effects(R.drawable.zcap13));
        arrayeffects.add(new effects(R.drawable.zcap14));
        arrayeffects.add(new effects(R.drawable.zcap15));
    }
}