package com.nilscreation.sunglasseseditor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.nilscreation.sunglasseseditor.StickerView.DrawableSticker;
import com.nilscreation.sunglasseseditor.StickerView.Sticker;
import com.nilscreation.sunglasseseditor.StickerView.StickerView;
import com.nilscreation.sunglasseseditor.StickerView.TextSticker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EditActivity extends AppCompatActivity {

    StickerView stickerView;
    ImageView btnBack, mainImage, lockImg, save;
    TextView lockTxt;
    LinearLayout mainLayout;
    Boolean locked = true;
    String[] permission = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private InterstitialAd mInterstitialAd;

    networkChangListener networkChangListener = new networkChangListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        stickerView = findViewById(R.id.sticker_view);
        btnBack = findViewById(R.id.btnBack);
        mainImage = findViewById(R.id.mainImage);
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
//                    Toast.makeText(EditActivity.this, "ad not ready", Toast.LENGTH_SHORT).show();
                    mInterstitialAd();
                }
            }
        });

    }

    public void takeData(Bitmap imageId) {

//        Drawable drawable = ContextCompat.getDrawable(this, imageId);
        Drawable drawable = new BitmapDrawable(getResources(), imageId);
        stickerView.addSticker(new DrawableSticker(drawable));
    }

    private void mInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(EditActivity.this, "ca-app-pub-9137303962163689/9521808783", adRequest, new InterstitialAdLoadCallback() {
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

                saveImageToGallery(bitmap);
            }
        } else {
            Toast.makeText(EditActivity.this, "Save cancel", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToGallery(Bitmap imageBitmap) {
        String savedImagePath;
        String imageFileName = "Photo_" + System.currentTimeMillis() + ".jpg";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/PhotoEditor");
            Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            try (OutputStream os = getContentResolver().openOutputStream(imageUri)) {
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                stickerView.setLocked(false);
            } catch (IOException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
            savedImagePath = imageUri.toString();
        } else {
            File storageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "PhotoEditor");
            if (!storageDir.exists()) {
                storageDir.mkdirs();
            }
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                stickerView.setLocked(false);
            } catch (IOException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        galleryAddPic(savedImagePath);
//        EditActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangListener);
        super.onStop();
    }

    private boolean isConnected(EditActivity Activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) Activity.getSystemService(EditActivity.this.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }

    public class networkChangListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Internet connection
            if (!isConnected(EditActivity.this)) {

                Dialog dialog = new Dialog(EditActivity.this);
                dialog.setContentView(R.layout.internet_dialog);
                dialog.setCancelable(false);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                Button btnOk = dialog.findViewById(R.id.btn_retry);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        onReceive(EditActivity.this, intent);
//                        loadFragment(new MainFragment());
                    }
                });
                dialog.show();
            }

        }
    }
}