package com.nilscreation.photoeditor;

import static android.os.Environment.DIRECTORY_PICTURES;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nilscreation.photoeditor.StickerView.DrawableSticker;
import com.nilscreation.photoeditor.StickerView.Sticker;
import com.nilscreation.photoeditor.StickerView.StickerView;
import com.nilscreation.photoeditor.StickerView.TextSticker;
import com.nilscreation.photoeditor.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EditActivity extends AppCompatActivity {

    StickerView stickerView;
    ImageView btnBack, mainImage, lockImg, save;
    TextView lockTxt;
    LinearLayout btnLock, btnRemove, btnRemoveAll, btnSave, mainLayout;
    Boolean locked = true;
    String[] permission = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private InterstitialAd mInterstitialAd;

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
        btnSave = findViewById(R.id.btnSave);
        mainLayout = findViewById(R.id.mainLayout);
        save = findViewById(R.id.save);

        mainImage.setImageURI(getIntent().getData());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd();

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
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerView.setLocked(!stickerView.isLocked());
                if (locked) {
                    locked = false;
                    lockTxt.setTextColor(getResources().getColor(R.color.primary));
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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //REQUEST PERMISSIONS
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permission, 80);
                }

                stickerView.setLocked(true);

            }
        });

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stickerView.isSelected()) {
                    stickerView.setSelected(false);
                } else {
                    stickerView.setSelected(true);
                }
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //REQUEST PERMISSIONS
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permission, 80);
                }

                stickerView.setLocked(true);

                //SHOW INTERSTITIAL AD
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(EditActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();

                            mInterstitialAd = null;
                            mInterstitialAd();
                        }
                    });

                } else {
                    Toast.makeText(EditActivity.this, "ad not ready", Toast.LENGTH_SHORT).show();
                    mInterstitialAd();
                }
            }
        });

    }

    public void takeData(int imageId) {

        Drawable drawable = ContextCompat.getDrawable(this, imageId);
        stickerView.addSticker(new DrawableSticker(drawable));
    }

    private void mInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(EditActivity.this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Toast.makeText(EditActivity.this, "loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 80) {
            if (grantResults[0] == getPackageManager().PERMISSION_GRANTED) {

//                stickerView.setDrawingCacheEnabled(true);
//                Bitmap bitmap = stickerView.getDrawingCache();

                // Create a bitmap of the layout
                Bitmap bitmap = Bitmap.createBitmap(stickerView.getWidth(), stickerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                stickerView.draw(canvas);

                OutputStream outStream = null;
                File filepath = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
                File dir = new File(filepath.getAbsolutePath() + "/Glasses Photo Editor/");
                dir.mkdir();

                File file = new File(dir, "Photo_" + System.currentTimeMillis() + ".jpg");

                try {
                    outStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();

                    EditActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                    Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
                    stickerView.setLocked(false);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(EditActivity.this, "Save cancel", Toast.LENGTH_SHORT).show();
        }
    }
}