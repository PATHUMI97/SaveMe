package com.example.donation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.donation.R;
import com.example.donation.model.Donate;
import com.example.donation.model.Request;
import com.example.donation.ui.account.AccountFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonateAdapter extends RecyclerView.Adapter<DonateAdapter.DonateViewHolder> {

    private List<Donate> donates;
    private Context context;

    public DonateAdapter(List<Donate> donates, Context context) {
        this.donates = donates;
        this.context = context;
    }

    @NonNull
    @Override
    public DonateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_list_item,parent,false);

        return new DonateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonateViewHolder holder, int position) {

        Donate donate = donates.get(position);
        holder.dnt=donate;
        holder.name.setText(donate.getDonatorName());
        holder.bldtyp.setText(donate.getBldtyp());
        holder.location.setText(donate.getLocation());
        Glide.with(context)
                .load(donate.getImagePath().isEmpty() ? R.drawable.default_image :donate.getImagePath())
                .override(100,100)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.name.getText(), Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, new AccountFragment(holder.dnt));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return donates.size();
    }

    public static class DonateViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        CircleImageView image;
        TextView name, bldtyp, location;
        Donate dnt;
        public DonateViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.fragment_donor_profile_image);
            name = itemView.findViewById(R.id.fragment_donor_name);
            bldtyp = itemView.findViewById(R.id.fragment_donor_bldtyp);
            location = itemView.findViewById(R.id.fragment_donor_location);
            this.itemView = itemView;
        }
    }
}
