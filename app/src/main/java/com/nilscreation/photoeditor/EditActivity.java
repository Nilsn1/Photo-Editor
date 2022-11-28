package com.nilscreation.photoeditor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class EditActivity extends AppCompatActivity {

    StickerView stickerView;
    ImageView btnBack, mainImage, lockImg;
    TextView lockTxt;
    LinearLayout btnLock, btnRemove, btnRemoveAll;
    Boolean locked = true;

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

        mainImage.setImageURI(getIntent().getData());

        //Load ListFragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new ListFragment());
        ft.commit();

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
                    lockTxt.setTextColor(getResources().getColor(R.color.black));
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
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageId);
//        photoEditor.addImage(bitmap);

        Drawable drawable = ContextCompat.getDrawable(this, imageId);
        stickerView.addSticker(new DrawableSticker(drawable));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}