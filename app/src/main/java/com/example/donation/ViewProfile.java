package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.donation.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private List<User> userList;
    CircleImageView image;
    TextView name;
    TextView username;
    TextView location;
    ImageView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        userList = new ArrayList<>();

        image = findViewById(R.id.View_profile_image);
        name = findViewById(R.id.View_profile_name);
        location = findViewById(R.id.View_profile_account_location);
        ImageButton btn = findViewById(R.id.activity_view_profile_edit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ViewProfile.this, profile.class);
                startActivity(in);
                finish();
            }
        });

        firebaseFirestore.collection("users").whereEqualTo("id", firebaseAuth.getCurrentUser().getUid())
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot snapshot : task.getResult()){

                                User user = snapshot.toObject(User.class);
                               String nm = user.getFname()+" "+user.getLname();
                               String lctn = user.getAddress();

                                Glide.with(ViewProfile.this)
                                        .load(user.getImagePath().isEmpty() ? R.drawable.default_image :user.getImagePath())
                                        .override(100,100)
                                        .into(image);

                               name.setText(nm);
                               location.setText(lctn);

//                                msg.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//
//                                    }
//                                });

                            }
                        }
                    }
                });


    }
}