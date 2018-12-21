package com.example.stuar.tutorialuno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    ImageView image;


    public static String PREFS_NAME = "SharedPrefsFile";

    //global variables
    public static String SHARED_PREF_USERNAME = "username";
    public static String SHARED_PREF_PASSWORD = "password";

    private DbHelper dbHelper = new DbHelper(this);

    private Doctor doctor;
    private Doctor doctor1;
    private Doctor doctor2;
    private Doctor doctor3;
    private Doctor doctor4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void onStart() {
        super.onStart();
        //read username and password from SharedPreferences file
        getUser();

    }

    //fill database with data
    public void onTestBtnClick(View view){
        if (view.getId() == R.id.fillDbBtn) {

            //create patient
            Patient patient = new Patient();
            patient.setFullName("John Smith");
            patient.setEmail("john@gmail.com");
            patient.setPassword("john");

            //insert patient
            patient.setId(dbHelper.insertPatient(patient));

            //create and insert doctors

            this.doctor = new Doctor(1, "doc", "doc", "doc@gmail.com");
            doctor.setId(dbHelper.insertDoctor(doctor));

            this.doctor1 = new Doctor(2, "doc1", "doc1", "doc1@gmail.com");
            doctor1.setId(dbHelper.insertDoctor(doctor1));

            this.doctor2 = new Doctor(3, "doc2", "doc2", "doc2@gmail.com");
            doctor2.setId(dbHelper.insertDoctor(doctor2));

            this.doctor3 = new Doctor(4, "doc3", "doc3", "doc3@gmail.com");
            doctor3.setId(dbHelper.insertDoctor(doctor3));

            Toast toast = Toast.makeText(LogIn.this, "Data successfully inserted", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    //if user clicked on 'remember me' checkbox on previous log in
    public void getUser() {
        //create shared preferences object
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //get username if it exists - if not, return null
        String username = pref.getString(SHARED_PREF_USERNAME, null);
        //get password if it exists - if not, return null
        String password = pref.getString(SHARED_PREF_PASSWORD, null);

        //if username and password exists in shared preferences file
        if (username != null || password != null) {

            linkToWelcomeScreen(username);
        }


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

            EditText uName = (EditText) findViewById(R.id.userName);

            String username = uName.getText().toString().trim();
            EditText pw = (EditText) findViewById(R.id.password);

            //password user typed in at log in screen
            String password = pw.getText().toString().trim();

            //encrypt entered password using username and password entered as a key
            password = dbHelper.encryptPassword(password, username + password);

            //fetch password associated with this username, if it exists
            String passwordToSearch = dbHelper.searchPassword(username);

            //encrypt this password if found using username and password entered as a key
            dbHelper.encryptPassword(passwordToSearch, username + password);

            CheckBox cb = (CheckBox) findViewById(R.id.rememberMeCheckBox);

            //if passwords match
            if (password.equals(passwordToSearch)) {

                if (cb.isChecked()) {

                    rememberUser(username, password);

                    linkToWelcomeScreen(username);
                } else {
                    linkToWelcomeScreen(username);
                }
            }
            else if (!password.equals(passwordToSearch)) {
                Toast passwordMsg = Toast.makeText(LogIn.this, "Username and password don't match", Toast.LENGTH_SHORT);
                passwordMsg.show();
            }
        }




    }

    public void rememberUser(String username, String password) {
        //save username and password in SharedPreferences file
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(SHARED_PREF_USERNAME, username)
                .putString(SHARED_PREF_PASSWORD, password)
                .apply();
    }

    public void linkToWelcomeScreen(String username) {
        Intent i = new Intent(LogIn.this, UserHome.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        finish();
        System.exit(0);
    }


}


