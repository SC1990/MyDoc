package com.example.stuar.tutorialuno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Activity {

    private EditText name;
    private Button button;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        button = (Button)findViewById(R.id.completeSignUpBtn);
        //name = (EditText)findViewById(R.id.name);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addName();


            }
        });
    }


    public void addName(){
      String uName = name.getText().toString().trim();
      SaveData saveData = new SaveData(uName);

      databaseReference.push().setValue(saveData);
    }

}
