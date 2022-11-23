package com.nilscreation.photoeditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class EditActivity extends AppCompatActivity {

    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;
    Button addEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        photoEditorView = findViewById(R.id.photoEditorView);
        addEffect = findViewById(R.id.addEffect);

        photoEditorView.getSource().setImageURI(getIntent().getData());

        photoEditor = new PhotoEditor.Builder(this, photoEditorView)
                .setPinchTextScalable(true)
                .build();

        addEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new ListFragment());
                ft.commit();
            }
        });
    }

    public void takeData(int imageId) {

//        Toast.makeText(this, "imageId" + imageId, Toast.LENGTH_SHORT).show();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageId);
        photoEditor.addImage(bitmap);

    }
}