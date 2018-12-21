package com.example.stuar.tutorialuno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

    private DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

    }


    //when user clicks sign up button after filling in details
    public void onGoBtnClick(View v){
        if(v.getId() == R.id.completeSignUpBtn){

            //fetch what user enters into text fields
            EditText fullName = (EditText)findViewById(R.id.chooseFullName);
            EditText email = (EditText)findViewById(R.id.email);
            EditText pass = (EditText)findViewById(R.id.pWord);
            EditText cPass = (EditText)findViewById(R.id.pWordConfirmation);

            //convert to strings
            String fullNameStr = fullName.getText().toString();
            String emailStr = email.getText().toString();
            String passStr = pass.getText().toString();
            String cPassStr = cPass.getText().toString();


            //if password does not contain a number
            if(!passStr.matches(".*\\d+.*")){
                Toast toast = Toast.makeText(SignUp.this, "Password must contain a number", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                //password and password confirmation must be identical
                if(!passStr.equals(cPassStr)){
                    Toast passwordMsg = Toast.makeText(SignUp.this, "Passwords don't match", Toast.LENGTH_SHORT);
                    passwordMsg.show();
                }
                //check availability of username
                else if(dbHelper.checkIfPatientExists(fullNameStr)){
                    Toast toast = Toast.makeText(SignUp.this, "Username not available", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    //create new customer
                    //insert details in db
                    Patient newPatient = new Patient();
                    newPatient.setFullName(fullNameStr);
                    newPatient.setEmail(emailStr);
                    newPatient.setPassword(passStr);




                        //insert this new user into the user table in the database
                        dbHelper.insertPatient(newPatient);

                        Toast toast = Toast.makeText(SignUp.this, "Your username : " + fullNameStr, Toast.LENGTH_LONG);
                        toast.show();

                        Intent i = new Intent(SignUp.this, LogIn.class);
                        //return to home screen after user successfully inserted
                        startActivity(i);
                    }


                }
            }




        }
    }

