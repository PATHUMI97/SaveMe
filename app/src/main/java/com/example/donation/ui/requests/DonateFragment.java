package com.example.donation.ui.requests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.donation.R;
import com.example.donation.model.Donate;
import com.example.donation.model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class DonateFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private List<Donate> donateList;




    public DonateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        donateList = new ArrayList<>();

        insertSample();
    }

    private void insertSample() {

        CollectionReference donate = firebaseFirestore.collection("donate");
        List<Donate> donateLst = new ArrayList<>();

        donateLst.add(new Donate(16,"sample donator","O+","I wish to donate platelets","Kalutara",""));

        for(Donate d : donateLst){
            donate.add(d);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        Button donate = view.findViewById(R.id.donate_btn_post_request);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSample();
                Toast.makeText(view.getContext(),"Successfully Created Your Blood donate post", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}