package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Congrats extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.congrats);

        firebaseAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
            }
        }, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);

                    Intent i = new Intent(Congrats.this, Getstarted.class);
                    startActivity(i);
                    finish();


            }
        }, 5000);
    }
}