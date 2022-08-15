package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donation.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_home);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

//        updateUI(firebaseAuth.getCurrentUser());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            }
        }, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(MainActivity.this, CheckEligible.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 10000);

    }

//    private void updateUI(FirebaseUser currentUser) {
//
//        if (currentUser != null){
//            Menu menu = navigationView.getMenu();
//
//            View headerView = navigationView.getHeaderView(0);
//            ImageView imageViewProfile = headerView.findViewById(R.id.profilepic);
//            TextView textViewName = headerView.findViewById(R.id.name);
//            TextView textViewUserName = headerView.findViewById(R.id.username);
//            TextView textViewBlood = headerView.findViewById(R.id.blood);
//
//            firebaseFirestore.collection("users").whereEqualTo("id", currentUser.getUid()).get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot snapshot : task.getResult()) {
//                                    User user = snapshot.toObject(User.class);
//
//                                    textViewName.setText(user.getName());
////                                    textViewEmail.setText(user.getEmail());
//
//
//
//                                }
//                            }
//                        }
//                    });
//        }
//    }
}