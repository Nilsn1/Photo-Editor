
package com.nilscreation.sunglasseseditor.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nilscreation.sunglasseseditor.EditActivity;
import com.nilscreation.sunglasseseditor.R;

import java.util.ArrayList;

public class glassesAdapter extends RecyclerView.Adapter<glassesAdapter.ViewHolder> {

    Context context;
    ArrayList<Bitmap> pngImageslist;
    FragmentActivity activity;

    public glassesAdapter(Context context, ArrayList<Bitmap> pngImageslist,FragmentActivity activity) {
        this.context = context;
        this.pngImageslist = pngImageslist;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageBitmap(pngImageslist.get(position));
        Bitmap bitmap = pngImageslist.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditActivity editActivity = (EditActivity) activity;
                editActivity.takeData(bitmap);

//                Toast.makeText(holder.imageView.getContext(), "text" + effect.getImage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pngImageslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgEffect);
        }
    }
}
