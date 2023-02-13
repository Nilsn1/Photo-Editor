package com.nilscreation.sunglasseseditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nilscreation.sunglasseseditor.Adapter.glassesAdapter;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    LinearLayout btnSunglasses, btnFrames, btnCool, btnGirls, btnPremium;
    ArrayList<effects> arrayeffects = new ArrayList<>();
    private ArrayList<Bitmap> pngImages = new ArrayList<>();
    AdView mAdView;

    private InterstitialAd mInterstitialAd;

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

        mInterstitialAd();

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView.setLayoutManager(new LinearLayoutManager((context), LinearLayoutManager.HORIZONTAL, false));

        ImageLoader imageLoader = new ImageLoader();
        pngImages = imageLoader.loadImages(getContext(), "sunglasses");
        setadapter();

        btnSunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "sunglasses");
                setadapter();
            }
        });

        btnFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "frames");
                setadapter();
            }
        });

        btnCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "cool");
                setadapter();
            }
        });

        btnGirls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "girls");
                setadapter();
            }
        });

        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SHOW INTERSTITIAL AD
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();

                            mInterstitialAd = null;
                            mInterstitialAd();
                        }
                    });

                } else {
//                    Toast.makeText(EditActivity.this, "ad not ready", Toast.LENGTH_SHORT).show();
                    mInterstitialAd();
                }

                ImageLoader imageLoader = new ImageLoader();
                pngImages = imageLoader.loadImages(getContext(), "premuim");
                setadapter();
            }
        });

        return view;
    }

    private void setadapter() {
//        RecyclerviewEffectAdapter adapter = new RecyclerviewEffectAdapter(context, arrayeffects, getActivity());
//        recyclerView.setAdapter(adapter);

        glassesAdapter adapter = new glassesAdapter(getContext(), pngImages, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void mInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getContext(), "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
//                Toast.makeText(EditActivity.this, "loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
    }
}