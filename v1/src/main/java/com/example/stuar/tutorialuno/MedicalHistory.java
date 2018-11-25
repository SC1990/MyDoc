package com.example.stuar.tutorialuno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MedicalHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_history);
    }

    public void onSaveBtnClick(View v){
        if (v.getId() == R.id.saveBtn) {

            Context context = getApplicationContext();
            CharSequence text = "Changes saved";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent i = new Intent(MedicalHistory.this, UserHome.class);
            //link user to sign up form
            startActivity(i);
        }
    }
}
