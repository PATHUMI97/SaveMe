package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CheckEligible extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.check_eligible);

        ImageView imageView =findViewById(R.id.elligibiity);
        Glide.with(this)
                .load(R.drawable.eligible)
                .into(imageView);

        ImageButton next = findViewById(R.id.btnnext);
        Button skip = findViewById(R.id.skip);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next = new Intent(CheckEligible.this,Health.class);
                startActivity(next);
                finish();
            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gs = new Intent(CheckEligible.this, Getstarted.class);
                startActivity(gs);
                finish();
            }
        });

    }
}