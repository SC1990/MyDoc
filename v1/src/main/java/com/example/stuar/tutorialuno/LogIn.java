package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogIn extends AppCompatActivity {



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
}
