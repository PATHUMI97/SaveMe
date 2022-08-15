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
import com.example.donation.R;
import com.example.donation.model.Campaign;
import com.example.donation.model.Donate;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder> {

    private List<Campaign> campaigns;
    private Context context;

    public CampaignAdapter(List<Campaign> campaignList, Context context) {
        this.campaigns = campaignList;
        this.context = context;
    }

    @NonNull
    @Override
    public CampaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.campaign_item,parent,false);

        return new CampaignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampaignViewHolder holder, int position) {

        Campaign camp = campaigns.get(position);
        holder.cmp = camp;
        holder.description.setText(camp.getDiscription());
        Glide.with(context)
                .load(camp.getImagePath().isEmpty() ? R.drawable.default_image :camp.getImagePath())
                .override(100,100)
                .into(holder.image);



    }

    @Override
    public int getItemCount() {
        return campaigns.size();
    }

    public static class CampaignViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        ImageView image;
        TextView description;
        Campaign cmp;
        public CampaignViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.campaign_item_image);
            description = itemView.findViewById(R.id.campaign_item_description);

            this.itemView = itemView;
        }
    }
}
