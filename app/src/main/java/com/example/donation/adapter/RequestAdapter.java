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
import com.example.donation.model.Request;
import com.example.donation.ui.account.AccountFragment;
import com.example.donation.ui.account.AccountFragmentRequest;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Request> requests;
    private Context context;

    public RequestAdapter(List<Request> requests, Context context) {
        this.requests = requests;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list_item,parent,false);

        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {

        Request rqst = requests.get(position);
        holder.rq=rqst;
        holder.name.setText(rqst.getPatientName());
        holder.bldtyp.setText(rqst.getBldtyp());
        holder.date.setText(rqst.getBldtyp());
        Glide.with(context)
                .load(rqst.getImagePath().isEmpty() ? R.drawable.default_image :rqst.getImagePath())
                .override(100,100)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.name.getText(), Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, new AccountFragmentRequest(holder.rq));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        CircleImageView image;
        TextView name, bldtyp, date;
        Request rq;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.request_list_item_profile_image);
            name = itemView.findViewById(R.id.request_list_item_name);
            bldtyp = itemView.findViewById(R.id.request_list_item_bldtyp);
            date = itemView.findViewById(R.id.request_list_item_date);
            this.itemView = itemView;
        }
    }
}
