package com.nilscreation.photoeditor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nilscreation.photoeditor.StickerView.DrawableSticker;
import com.nilscreation.photoeditor.StickerView.Sticker;
import com.nilscreation.photoeditor.StickerView.StickerView;
import com.nilscreation.photoeditor.StickerView.TextSticker;

public class EditActivity extends AppCompatActivity {

    StickerView stickerView;
    ImageView btnBack, mainImage, lockImg;
    TextView lockTxt;
    LinearLayout btnLock, btnRemove, btnRemoveAll;
    Boolean locked = true;
    SwitchCompat switchMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        stickerView = findViewById(R.id.sticker_view);
        btnBack = findViewById(R.id.btnBack);
        mainImage = findViewById(R.id.mainImage);
        lockImg = findViewById(R.id.lockImg);
        lockTxt = findViewById(R.id.lockTxt);
        btnLock = findViewById(R.id.btnLock);
        btnRemove = findViewById(R.id.btnRemove);
        btnRemoveAll = findViewById(R.id.btnRemoveaAll);
        switchMode = findViewById(R.id.mode);

        mainImage.setImageURI(getIntent().getData());

        //Load ListFragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new ListFragment());
        ft.commit();

        // Saving state of our app using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        // When user reopens the app after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchMode.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            switchMode.setChecked(false);
        }

        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchMode.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    // it will set isDarkModeOn
                    // boolean to false
                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();
                }
            }
        });

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerView.setLocked(!stickerView.isLocked());
                if (locked) {
                    locked = false;
                    lockTxt.setTextColor(getResources().getColor(R.color.red));
                    lockImg.setImageResource(R.drawable.lock_red);
                } else {
                    locked = true;
                    lockTxt.setTextColor(getResources().getColor(R.color.text_color));
                    lockImg.setImageResource(R.drawable.lock);

                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerView.removeCurrentSticker();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stickerView.removeAllStickers();

            }
        });

        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
                    stickerView.replace(sticker);
                    stickerView.invalidate();
                }
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(TAG, "onDoubleTapped: double tap will be with two click");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void takeData(int imageId) {

        Drawable drawable = ContextCompat.getDrawable(this, imageId);
        stickerView.addSticker(new DrawableSticker(drawable));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}