
package com.nilscreation.photoeditor.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nilscreation.photoeditor.EditActivity;
import com.nilscreation.photoeditor.R;
import com.nilscreation.photoeditor.effects;

import java.util.ArrayList;

public class glassesAdapter extends RecyclerView.Adapter<glassesAdapter.ViewHolder> {

    Context context;
    ArrayList<Bitmap> pngImageslist;

    public glassesAdapter(Context context, ArrayList<Bitmap> pngImageslist) {
        this.context = context;
        this.pngImageslist = pngImageslist;
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
