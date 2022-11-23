
package com.nilscreation.photoeditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewEffectAdapter extends RecyclerView.Adapter<RecyclerviewEffectAdapter.ViewHolder> {

    Context context;
    ArrayList<effects> effectslist;

    FragmentActivity activity;

    RecyclerviewEffectAdapter(Context context, ArrayList<effects> effects, FragmentActivity activity) {
        this.context = context;
        this.effectslist = effects;
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

        holder.imageView.setImageResource(effectslist.get(position).image);

        effects effect = effectslist.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditActivity editActivity = (EditActivity) activity;
                editActivity.takeData(effect.getImage());

//                Toast.makeText(holder.imageView.getContext(), "text" + effect.getImage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return effectslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgEffect);
        }
    }
}
