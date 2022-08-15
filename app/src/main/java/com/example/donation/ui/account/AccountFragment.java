package com.example.donation.ui.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.donation.Congrats;
import com.example.donation.Home;
import com.example.donation.R;
import com.example.donation.model.Donate;
import com.example.donation.model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private Donate dnt;
//    private Request rq;
    private List<Donate> donates;
//    private List<Request> requests;
    private TextView username,typ;
    private CircleImageView image;
    private ImageButton email;

    public AccountFragment(Donate dnt) {
        // Required empty public constructor
        this.dnt = dnt;
    }

//    public AccountFragment(Request rq) {
//        // Required empty public constructor
//        this.rq = rq;
//    }

    public AccountFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        donates = new ArrayList<>();
//        requests = new ArrayList<>();

        Home activity = (Home) getActivity();
        activity.showBottomNavigationView(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.account_name);
        typ = view.findViewById(R.id.account_type);
        username.setText(dnt.getDonatorName());
        typ.setText("I wish to Donate");
        image = view.findViewById(R.id.account_profile_image);
        Glide.with(view.getContext())
                .load(dnt.getImagePath().isEmpty() ? R.drawable.default_image :dnt.getImagePath())
                .override(100,100)
                .into(image);




    }
}