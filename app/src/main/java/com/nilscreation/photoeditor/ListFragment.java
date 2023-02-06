package com.nilscreation.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nilscreation.photoeditor.Adapter.glassesAdapter;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    LinearLayout btnSunglasses, btnFrames, btnCool, btnGirls, btnPremium;
    ArrayList<effects> arrayeffects = new ArrayList<>();
    private ArrayList<Bitmap> pngImages = new ArrayList<>();
    AdView mAdView;

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
        btnGirls = view.findViewById(R.id.btnGirls);
        btnPremium = view.findViewById(R.id.btnPremium);
        recyclerView = view.findViewById(R.id.recyclerview);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView.setLayoutManager(new LinearLayoutManager((context), LinearLayoutManager.HORIZONTAL, false));

        ImageLoader imageLoader = new ImageLoader();
        pngImages = imageLoader.loadImages(getContext(), "A");
        setadapter();

        btnSunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "A");
                setadapter();
            }
        });

        btnFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "A");
                setadapter();
            }
        });

        btnCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "A");
                setadapter();
            }
        });

        btnGirls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "A");
                setadapter();
            }
        });

        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "A");
                setadapter();
            }
        });

        return view;
    }

    private void setadapter() {
//        RecyclerviewEffectAdapter adapter = new RecyclerviewEffectAdapter(context, arrayeffects, getActivity());
//        recyclerView.setAdapter(adapter);

        glassesAdapter adapter = new glassesAdapter(getContext(), pngImages,getActivity());
        recyclerView.setAdapter(adapter);
    }
}