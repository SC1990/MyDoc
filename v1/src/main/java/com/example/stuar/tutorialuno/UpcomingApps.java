package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class UpcomingApps extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_apps);

        ImageView imgview =  findViewById(R.id.homeImg2);
        imgview.bringToFront();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UpcomingApps.this, UserHome.class);
                startActivity(myIntent);
            }
        });
    }
}
