package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.donation.model.User;
import com.example.donation.ui.aboutus.AboutusFragment;
import com.example.donation.ui.account.AccountFragment;
import com.example.donation.ui.chat.ChatFragment;
import com.example.donation.ui.compaigns.CampaignFragment;
import com.example.donation.ui.home.HomeFragment;
import com.example.donation.ui.profile.ProfileFragment;
import com.example.donation.ui.requests.CreatePostFragment;
import com.example.donation.ui.requests.DonateFragment;
import com.example.donation.ui.requests.DonorsFragment;
import com.example.donation.ui.requests.RequestFragment;
import com.example.donation.ui.tips.TipsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        NavigationBarView.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private List<User> user;
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        drawerLayout = findViewById(R.id.drawer_home);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        updateUI(firebaseAuth.getCurrentUser());

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        loadFragment(new HomeFragment());

    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {

//            menu.findItem(R.id.side_nav_profile).setVisible(true);
//            menu.findItem(R.id.side_nav_orders).setVisible(true);
//            menu.findItem(R.id.side_nav_wishlist).setVisible(true);
//            menu.findItem(R.id.side_nav_message).setVisible(true);
//            menu.findItem(R.id.side_nav_logout).setVisible(true);
//            menu.findItem(R.id.side_nav_login).setVisible(false);


            View headerView = navigationView.getHeaderView(0);
            ImageView imageViewProfile = headerView.findViewById(R.id.profilepic);
            TextView textViewName = headerView.findViewById(R.id.name);
            TextView textViewUserName = headerView.findViewById(R.id.username);
            TextView textViewutyp = headerView.findViewById(R.id.header_utyp);
            TextView bld = headerView.findViewById(R.id.blood);



            firebaseFirestore.collection("users").whereEqualTo("id", currentUser.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                    User user = snapshot.toObject(User.class);

                                    textViewName.setText(user.getFname()+" "+user.getLname());
                                    textViewutyp.setText(user.getUserTyp());
                                    textViewUserName.setText(user.getUsername());
                                    bld.setText(user.getBldtyp());

                                    Glide.with(Home.this)
                                            .load(user.getImagePath().isEmpty() ? R.drawable.default_image :user.getImagePath())
                                            .override(100,100)
                                            .into(imageViewProfile);

                                    Menu menu = navigationView.getMenu();
//            Intent i = getIntent();
//            String utyp = i.getStringExtra("user_type");
//            System.out.println(utyp+"...................................................................................");
                                    if(user.getUserTyp().equals("I wish to donate")){
                                        menu.findItem(R.id.drw_request).setVisible(true);
                                    }else if(user.getUserTyp().equals("I'm seeking for blood")){
                                        menu.findItem(R.id.drw_donat).setVisible(true);
                                    }

                                }
                            }
                        }
                    });


        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.drw_home:
            case R.id.bot_home:
                loadFragment(new HomeFragment());
                break;
            case R.id.drw_tips:
                loadFragment(new TipsFragment());
                break;
            case R.id.drw_request:
                loadFragment(new RequestFragment());
                break;
            case R.id.drw_donat:
                loadFragment(new DonorsFragment());
                break;
            case R.id.campaigns:
                loadFragment(new CampaignFragment());
                break;
            case R.id.drw_profile:
            case R.id.bot_profile:
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent i = new Intent(Home.this, ViewProfile.class);
                    i.putExtra("user_id",firebaseAuth.getCurrentUser().getUid());
                    startActivity(i);
                }
                break;
            case R.id.drw_history:
                break;
            case R.id.drw_logout:
                firebaseAuth.signOut();
                Intent login = new Intent(getApplicationContext(),Signup.class);
                startActivity(login);
                finish();
                break;
            case R.id.drw_about:
                loadFragment(new AboutusFragment());
                break;
            case R.id.drw_rate:
                break;

            case R.id.addpost:
                if (firebaseAuth.getCurrentUser() != null) {
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                    firebaseFirestore.collection("users").whereEqualTo("id", currentUser.getUid()).get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                            User user = snapshot.toObject(User.class);

                                            if(user.getUserTyp().equals("I wish to donate")){
                                                loadFragment(new DonateFragment());
                                            }else if(user.getUserTyp().equals("I'm seeking for blood")){
                                                loadFragment(new CreatePostFragment());
                                            }

                                        }
                                    }
                                }
                            });
                }


                break;

        }
        return true;
    }
    public void loadFragment(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

    public void showBottomNavigationView(boolean show){
        if (show){
            bottomNavigationView.setVisibility(View.VISIBLE);
        }else{
            bottomNavigationView.setVisibility(View.GONE);
        }
    }
}