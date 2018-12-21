package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;



public class UserHome extends AppCompatActivity {

    Locale locale;
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

            Intent i = new Intent(UserHome.this, CaptureImage.class);
            //link user to sign up form
            startActivity(i);
        }
    }

    public void onAudioBtnClick(View v){
        if (v.getId() == R.id.audioRecordBtn) {

            Intent i = new Intent(UserHome.this, CaptureAudio.class);
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

    public void onLangBtnClick(View v) {
        if (v.getId() == R.id.langBtn) {
            changeLang("fr");
        }
    }

    public void changeLang(String lang) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {

            locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration conf = new Configuration(config);
            conf.locale = locale;
            getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
