package com.example.donation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.donation.model.Blood;
import com.example.donation.R;
import com.example.donation.model.Blood;


import java.util.List;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.BloodViewHolder> {

    private List<Blood> bloodTyp;
    private Context context;

    public BloodAdapter(List<Blood> bloodTyp, Context context) {
        this.bloodTyp = bloodTyp;
        this.context = context;
    }


    @NonNull
    @Override
    public BloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodtype_list_item, parent, false);
        return new BloodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodViewHolder holder, int position) {

        Blood bld = bloodTyp.get(position);
        holder.name.setText(bld.getType());
        Glide.with(context)
                .load(bld.getImagePath().isEmpty() ? R.drawable.default_image : bld.getImagePath())
                .override(100, 100)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.name.getText(), Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }



    @Override
    public int getItemCount() {
        return bloodTyp.size();
    }

    public static class BloodViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView image;
        TextView name;

        public BloodViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.categories_list_item_image);

            this.itemView = itemView;
        }
    }

}
