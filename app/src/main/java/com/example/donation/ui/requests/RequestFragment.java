package com.example.donation.ui.requests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.donation.Home;
import com.example.donation.R;
import com.example.donation.adapter.RequestAdapter;
import com.example.donation.model.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RequestFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private List<Request> requestList;

    private EditText editPatientName, editUnit, editLocation, editNote, editDate;
    private RadioGroup bloodTypegroup;
    private RadioButton selctedbldtyp;


    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        requestList = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Home home = (Home) getActivity();
        home.showBottomNavigationView(true);

        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.request_request_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RequestAdapter adapter = new RequestAdapter(requestList, view.getContext());
        recyclerView.setAdapter(adapter);

        firebaseFirestore.collection("request").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()){
                   requestList.clear();
                   for (QueryDocumentSnapshot snapshot : task.getResult()){
                       Request request = snapshot.toObject(Request.class);
                       requestList.add(request);
                   }
                   adapter.notifyDataSetChanged();
               }
            }
        });
    }
}