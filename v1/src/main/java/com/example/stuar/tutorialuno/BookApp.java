package com.example.stuar.tutorialuno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class BookApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_app);
    }

    public void onBookBtnClick(View v){
        if (v.getId() == R.id.bookBtn) {

            Context context = getApplicationContext();
            CharSequence text = "Appointment Booked";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent i = new Intent(BookApp.this, UserHome.class);
            //link user to sign up form
            startActivity(i);
        }
    }
}
