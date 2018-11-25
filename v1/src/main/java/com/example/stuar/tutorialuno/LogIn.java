package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LogIn extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void onSignUpLinkBtnClick(View v){
        if (v.getId() == R.id.signUpLinkBtn) {

            Intent i = new Intent(LogIn.this, SignUp.class);
            //link user to sign up form
            startActivity(i);
        }
    }

    public void onLoginBtnClick(View v){
        if (v.getId() == R.id.logInBtn) {



            Intent i = new Intent(LogIn.this, UserHome.class);
            //link user to sign up form

            startActivity(i);
        }
    }
}
