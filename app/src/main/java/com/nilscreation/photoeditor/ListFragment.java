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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    ArrayList<effects> arrayeffects = new ArrayList<>();
    LinearLayout btnSunglasses, btnFrames, btnCool, btnGirls, btnHats, btnPremium;
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
        btnHats = view.findViewById(R.id.btnHat);
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

        btnGirls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGirlsglasses();
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

        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
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
        arrayeffects.add(new effects(R.drawable.zsunglasses16));
        arrayeffects.add(new effects(R.drawable.zsunglasses17));
        arrayeffects.add(new effects(R.drawable.zsunglasses18));
        arrayeffects.add(new effects(R.drawable.zsunglasses19));
        arrayeffects.add(new effects(R.drawable.zsunglasses20));
        arrayeffects.add(new effects(R.drawable.zsunglasses21));
        arrayeffects.add(new effects(R.drawable.zsunglasses22));
        arrayeffects.add(new effects(R.drawable.zsunglasses23));
        arrayeffects.add(new effects(R.drawable.zsunglasses24));
        arrayeffects.add(new effects(R.drawable.zsunglasses25));
        arrayeffects.add(new effects(R.drawable.zsunglasses26));
        arrayeffects.add(new effects(R.drawable.zsunglasses27));
        arrayeffects.add(new effects(R.drawable.zsunglasses28));
        arrayeffects.add(new effects(R.drawable.zsunglasses29));
        arrayeffects.add(new effects(R.drawable.zsunglasses30));
        arrayeffects.add(new effects(R.drawable.zsunglasses31));
        arrayeffects.add(new effects(R.drawable.zsunglasses32));
        arrayeffects.add(new effects(R.drawable.zsunglasses33));
        arrayeffects.add(new effects(R.drawable.zsunglasses34));
        arrayeffects.add(new effects(R.drawable.zsunglasses35));
        arrayeffects.add(new effects(R.drawable.zsunglasses36));
        arrayeffects.add(new effects(R.drawable.zsunglasses37));
        arrayeffects.add(new effects(R.drawable.zsunglasses38));
        arrayeffects.add(new effects(R.drawable.zsunglasses39));
        arrayeffects.add(new effects(R.drawable.zsunglasses40));
        arrayeffects.add(new effects(R.drawable.zsunglasses41));
        arrayeffects.add(new effects(R.drawable.zsunglasses42));
        arrayeffects.add(new effects(R.drawable.zsunglasses43));
        arrayeffects.add(new effects(R.drawable.zsunglasses44));
        arrayeffects.add(new effects(R.drawable.zsunglasses45));
        arrayeffects.add(new effects(R.drawable.zsunglasses46));
        arrayeffects.add(new effects(R.drawable.zsunglasses47));
        arrayeffects.add(new effects(R.drawable.zsunglasses48));
        arrayeffects.add(new effects(R.drawable.zsunglasses49));
        arrayeffects.add(new effects(R.drawable.zsunglasses50));
        arrayeffects.add(new effects(R.drawable.zsunglasses51));
        arrayeffects.add(new effects(R.drawable.zsunglasses52));
        arrayeffects.add(new effects(R.drawable.zsunglasses53));
        arrayeffects.add(new effects(R.drawable.zsunglasses54));
        arrayeffects.add(new effects(R.drawable.zsunglasses55));
        arrayeffects.add(new effects(R.drawable.zsunglasses56));
        arrayeffects.add(new effects(R.drawable.zsunglasses57));
        arrayeffects.add(new effects(R.drawable.zsunglasses58));
        arrayeffects.add(new effects(R.drawable.zsunglasses59));
        arrayeffects.add(new effects(R.drawable.zsunglasses60));
        arrayeffects.add(new effects(R.drawable.zsunglasses61));
        arrayeffects.add(new effects(R.drawable.zsunglasses62));
        arrayeffects.add(new effects(R.drawable.zsunglasses63));
        arrayeffects.add(new effects(R.drawable.zsunglasses64));
        arrayeffects.add(new effects(R.drawable.zsunglasses65));
        arrayeffects.add(new effects(R.drawable.zsunglasses66));
        arrayeffects.add(new effects(R.drawable.zsunglasses67));
        arrayeffects.add(new effects(R.drawable.zsunglasses68));
        arrayeffects.add(new effects(R.drawable.zsunglasses69));
        arrayeffects.add(new effects(R.drawable.zsunglasses70));
        arrayeffects.add(new effects(R.drawable.zsunglasses71));
        arrayeffects.add(new effects(R.drawable.zsunglasses72));
        arrayeffects.add(new effects(R.drawable.zsunglasses73));
        arrayeffects.add(new effects(R.drawable.zsunglasses74));
        arrayeffects.add(new effects(R.drawable.zsunglasses75));
        arrayeffects.add(new effects(R.drawable.zsunglasses76));
        arrayeffects.add(new effects(R.drawable.zsunglasses77));
        arrayeffects.add(new effects(R.drawable.zsunglasses78));
        arrayeffects.add(new effects(R.drawable.zsunglasses79));
        arrayeffects.add(new effects(R.drawable.zsunglasses80));
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
        arrayeffects.add(new effects(R.drawable.zframes16));
        arrayeffects.add(new effects(R.drawable.zframes17));
        arrayeffects.add(new effects(R.drawable.zframes18));
        arrayeffects.add(new effects(R.drawable.zframes19));
        arrayeffects.add(new effects(R.drawable.zframes20));
        arrayeffects.add(new effects(R.drawable.zframes21));
        arrayeffects.add(new effects(R.drawable.zframes22));
        arrayeffects.add(new effects(R.drawable.zframes23));
        arrayeffects.add(new effects(R.drawable.zframes24));
        arrayeffects.add(new effects(R.drawable.zframes25));
        arrayeffects.add(new effects(R.drawable.zframes26));
        arrayeffects.add(new effects(R.drawable.zframes27));
        arrayeffects.add(new effects(R.drawable.zframes28));
        arrayeffects.add(new effects(R.drawable.zframes29));
        arrayeffects.add(new effects(R.drawable.zframes30));
        arrayeffects.add(new effects(R.drawable.zframes31));
        arrayeffects.add(new effects(R.drawable.zframes32));
        arrayeffects.add(new effects(R.drawable.zframes33));
        arrayeffects.add(new effects(R.drawable.zframes34));
        arrayeffects.add(new effects(R.drawable.zframes35));
        arrayeffects.add(new effects(R.drawable.zframes36));
        arrayeffects.add(new effects(R.drawable.zframes37));
    }

    private void addCool() {
        arrayeffects.removeAll(arrayeffects);
//        arrayeffects.add(new effects(R.drawable.zfunny1));
//        arrayeffects.add(new effects(R.drawable.zfunny2));
//        arrayeffects.add(new effects(R.drawable.zfunny3));
//        arrayeffects.add(new effects(R.drawable.zfunny4));
//        arrayeffects.add(new effects(R.drawable.zfunny5));
//        arrayeffects.add(new effects(R.drawable.zfunny6));
//        arrayeffects.add(new effects(R.drawable.zfunny7));
//        arrayeffects.add(new effects(R.drawable.zfunny8));
//        arrayeffects.add(new effects(R.drawable.zfunny9));
//        arrayeffects.add(new effects(R.drawable.zfunny10));
//        arrayeffects.add(new effects(R.drawable.zfunny11));
//        arrayeffects.add(new effects(R.drawable.zfunny12));
//        arrayeffects.add(new effects(R.drawable.zfunny13));
//        arrayeffects.add(new effects(R.drawable.zfunny14));
//        arrayeffects.add(new effects(R.drawable.zfunny15));
//        arrayeffects.add(new effects(R.drawable.zfunny16));
//        arrayeffects.add(new effects(R.drawable.zfunny17));
//        arrayeffects.add(new effects(R.drawable.zfunny18));
//        arrayeffects.add(new effects(R.drawable.zfunny19));
//        arrayeffects.add(new effects(R.drawable.zfunny20));
//        arrayeffects.add(new effects(R.drawable.zfunny21));
//        arrayeffects.add(new effects(R.drawable.zfunny22));
//        arrayeffects.add(new effects(R.drawable.zfunny23));
//        arrayeffects.add(new effects(R.drawable.zfunny24));
//        arrayeffects.add(new effects(R.drawable.zfunny25));
//        arrayeffects.add(new effects(R.drawable.zfunny26));
//        arrayeffects.add(new effects(R.drawable.zfunny27));
//        arrayeffects.add(new effects(R.drawable.zfunny28));
//        arrayeffects.add(new effects(R.drawable.zfunny29));
//        arrayeffects.add(new effects(R.drawable.zfunny30));
//        arrayeffects.add(new effects(R.drawable.zfunny31));

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
        arrayeffects.add(new effects(R.drawable.zpremium1));
        arrayeffects.add(new effects(R.drawable.zpremium2));
        arrayeffects.add(new effects(R.drawable.zpremium3));
        arrayeffects.add(new effects(R.drawable.zpremium4));
        arrayeffects.add(new effects(R.drawable.zpremium5));
        arrayeffects.add(new effects(R.drawable.zpremium6));
        arrayeffects.add(new effects(R.drawable.zpremium7));
        arrayeffects.add(new effects(R.drawable.zpremium8));
        arrayeffects.add(new effects(R.drawable.zpremium9));
        arrayeffects.add(new effects(R.drawable.zpremium10));
        arrayeffects.add(new effects(R.drawable.zpremium11));
        arrayeffects.add(new effects(R.drawable.zpremium12));
        arrayeffects.add(new effects(R.drawable.zpremium13));
        arrayeffects.add(new effects(R.drawable.zpremium14));
        arrayeffects.add(new effects(R.drawable.zpremium15));
        arrayeffects.add(new effects(R.drawable.zpremium16));
        arrayeffects.add(new effects(R.drawable.zpremium17));
        arrayeffects.add(new effects(R.drawable.zpremium18));
    }

    private void addGirlsglasses() {
        arrayeffects.removeAll(arrayeffects);
        arrayeffects.add(new effects(R.drawable.zgirls3));
        arrayeffects.add(new effects(R.drawable.zgirls4));
        arrayeffects.add(new effects(R.drawable.zgirls5));
        arrayeffects.add(new effects(R.drawable.zgirls6));
        arrayeffects.add(new effects(R.drawable.zgirls7));
        arrayeffects.add(new effects(R.drawable.zgirls8));
        arrayeffects.add(new effects(R.drawable.zgirls9));
        arrayeffects.add(new effects(R.drawable.zgirls10));
        arrayeffects.add(new effects(R.drawable.zgirls11));
        arrayeffects.add(new effects(R.drawable.zgirls12));
        arrayeffects.add(new effects(R.drawable.zgirls13));
        arrayeffects.add(new effects(R.drawable.zgirls14));
        arrayeffects.add(new effects(R.drawable.zgirls15));
        arrayeffects.add(new effects(R.drawable.zgirls16));
        arrayeffects.add(new effects(R.drawable.zgirls17));
        arrayeffects.add(new effects(R.drawable.zgirls18));
        arrayeffects.add(new effects(R.drawable.zgirls19));
        arrayeffects.add(new effects(R.drawable.zgirls20));
        arrayeffects.add(new effects(R.drawable.zgirls1));
        arrayeffects.add(new effects(R.drawable.zgirls2));
        arrayeffects.add(new effects(R.drawable.zgirls21));
        arrayeffects.add(new effects(R.drawable.zgirls22));
        arrayeffects.add(new effects(R.drawable.zgirls23));
        arrayeffects.add(new effects(R.drawable.zgirls24));
        arrayeffects.add(new effects(R.drawable.zgirls25));
        arrayeffects.add(new effects(R.drawable.zgirls26));
        arrayeffects.add(new effects(R.drawable.zgirls27));
        arrayeffects.add(new effects(R.drawable.zgirls28));

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
        arrayeffects.add(new effects(R.drawable.zcap16));
        arrayeffects.add(new effects(R.drawable.zcap17));
        arrayeffects.add(new effects(R.drawable.zcap18));
        arrayeffects.add(new effects(R.drawable.zcap19));
        arrayeffects.add(new effects(R.drawable.zcap20));
    }
}