package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);
    }

    public void onBookAppBtnClick(View v){
        if (v.getId() == R.id.bookAppBtn) {


            Intent i = new Intent(UserHome.this, BookApp.class);
            //link user to sign up form
            startActivity(i);
        }
    }

    public void onMHBtnClick(View v){
        if (v.getId() == R.id.medHistoryBtn) {


            Intent i = new Intent(UserHome.this, MedicalHistory.class);
            //link user to sign up form
            startActivity(i);
        }
    }

    public void onRecordBtnClick(View v){
        if (v.getId() == R.id.recordBtn) {

            Intent i = new Intent(UserHome.this, RecordVideo.class);
            //link user to sign up form
            startActivity(i);
        }
    }

    public void onSearchBtnClick(View v){
        if (v.getId() == R.id.searchBtn) {


            Intent i = new Intent(UserHome.this, DocSearch.class);
            //link user to sign up form
            startActivity(i);
        }
    }
}
