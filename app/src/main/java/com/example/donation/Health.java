package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Health extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.health);

//        ImageView imageView =findViewById(R.id.qbackground);
//        Glide.with(this)
//                .load(R.drawable.qbackground)
//                .into(imageView);
//
//        ImageView Health =findViewById(R.id.health);
//        Glide.with(this)
//                .load(R.drawable.health)
//                .override(60,60)
//                .into(imageView);

        Button yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y = new Intent(getApplicationContext(), Weight.class);
                startActivity(y);
                finish();
            }
        });

        Button no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(), Sorry.class);
                startActivity(n);
                finish();
            }
        });
    }
}