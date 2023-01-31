package com.nilscreation.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.LocalFileHeader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageLoader {
    private static final int BUFFER_SIZE = 1024;
    private static final String ZIP_FILE = "cool.zip";
    private static final String ZIP_PASSWORD = "nilsglasses";
    private ArrayList<Bitmap> mBitmaps = new ArrayList<>();
    private Context mContext;

    public ImageLoader(Context context) {
        mContext = context;
    }

    public ArrayList<Bitmap> loadImages() {
        try {
            InputStream is = mContext.getAssets().open(ZIP_FILE);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
            zis.setPassword(ZIP_PASSWORD.toCharArray());
            LocalFileHeader entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    Bitmap bitmap = BitmapFactory.decodeStream(zis);
                    mBitmaps.add(bitmap);
                    Toast.makeText(mContext, " " + bitmap, Toast.LENGTH_SHORT).show();
                }
                zis.close();
            }
            zis.close();
        } catch (Exception e) {
        }
        return mBitmaps;
    }
}
