package com.nilscreation.sunglasseseditor;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {
    private ArrayList<Bitmap> pngImages = new ArrayList<>();

    public ArrayList<Bitmap> loadImages(Context context, String Category) {
        // Load the ZIP file from assets
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("cool.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a temporary file to extract the contents of the ZIP file
        File tempFile = new File(context.getCacheDir(), "cool.zip");
        try {
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lljtu = null;
        // Extract the contents of the ZIP file
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(tempFile);
            if (zipFile.isEncrypted()) {
                char[] chars = "lgjqej_qqcq".toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    int newCode = (int) c + 2;
                    chars[i] = (char) newCode;
                    lljtu = String.valueOf(chars);
                }
                zipFile.setPassword(lljtu.toCharArray());
            }
            List<FileHeader> fileHeaders = zipFile.getFileHeaders();
            for (FileHeader fileHeader : fileHeaders) {
                if (fileHeader.getFileName().startsWith(Category) && fileHeader.getFileName().endsWith(".png")) {
                    InputStream imageStream = zipFile.getInputStream(fileHeader);
                    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                    pngImages.add(bitmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.getInputStream(zipFile.getFileHeader("cool.zip")).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pngImages;
    }

    public static String shiftCharacters(String str, int shift) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int newCode = (int) c + shift;
            chars[i] = (char) newCode;
        }
        return new String(chars);
    }
}
