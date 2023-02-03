package com.nilscreation.photoeditor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nilscreation.photoeditor.Adapter.glassesAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardView btnGallery, btnCamera, btnMore;
    ImageSlider imageSlider;
    AdView mAdView;
    ImageView settings, imggallery;
    private static final String PASSWORD = "nilsglasses";

    RecyclerView recyclerView;


    private ArrayList<Bitmap> pngImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSlider = findViewById(R.id.image_slider);
        btnGallery = findViewById(R.id.btnGallery);
        btnCamera = findViewById(R.id.btnCamera);
        btnMore = findViewById(R.id.btnMore);
        settings = findViewById(R.id.settings);
        imggallery = findViewById(R.id.imggallery);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager((this), LinearLayoutManager.HORIZONTAL, false));


        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.sunglasses, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.sunglasses2, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.sunglasses, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.sunglasses2, ScaleTypes.CENTER_INSIDE));

        ImageLoader imageLoader = new ImageLoader();
        pngImages = imageLoader.loadImages(MainActivity.this);

        glassesAdapter adapter = new glassesAdapter(MainActivity.this, pngImages);
        recyclerView.setAdapter(adapter);

//        // Load the ZIP file from assets
//        AssetManager assetManager = getAssets();
//        InputStream inputStream = null;
//        try {
//            inputStream = assetManager.open("cool.zip");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Create a temporary file to extract the contents of the ZIP file
//        File tempFile = new File(getCacheDir(), "cool.zip");
//        try {
//            FileOutputStream outputStream = new FileOutputStream(tempFile);
//            byte[] buffer = new byte[1024];
//            int read;
//            while ((read = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, read);
//            }
//            outputStream.flush();
//            outputStream.close();
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Extract the contents of the ZIP file
//        ZipFile zipFile = null;
//        try {
//            zipFile = new ZipFile(tempFile);
//            if (zipFile.isEncrypted()) {
//                zipFile.setPassword(PASSWORD.toCharArray());
//            }
//            List<FileHeader> fileHeaders = zipFile.getFileHeaders();
//            for (FileHeader fileHeader : fileHeaders) {
//                if (fileHeader.getFileName().endsWith(".png")) {
//                    InputStream imageStream = zipFile.getInputStream(fileHeader);
//                    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
//                    pngImages.add(bitmap);
//
//                    glassesAdapter adapter = new glassesAdapter(MainActivity.this, pngImages);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (zipFile != null) {
//                try {
//                    zipFile.getInputStream(zipFile.getFileHeader("cool.zip")).close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Saving state of our app using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        // When user reopens the app after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Basic Image picker
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 100);

                //dhaval2404 Image picker Library
                ImagePicker.with(MainActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .galleryOnly()
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .start(100);

            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .crop()
                        .cameraOnly()
                        .start(100);
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/developer?id=Nils+Creation";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (data.getData() != null) {
                Uri filepath = data.getData();
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.setData(filepath);
                startActivity(intent);
            }
        }
    }
}
