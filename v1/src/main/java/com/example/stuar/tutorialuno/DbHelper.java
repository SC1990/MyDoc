package com.example.stuar.tutorialuno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "myDoc.db";

    //patients table
    private static final String PATIENT_TABLE_NAME = "patient";
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_FULLNAME = "fullName";
    private static final String COLUMN_EMAIL = "patient_email";
    private static final String COLUMN_PASSWORD = "password";

    //doctors table
    private static final String DOCTOR_TABLE_NAME = "doctor";
    private static final String COLUMN_DOCTOR_ID = "doc_id";
    private static final String COLUMN_DOC_FULLNAME = "doc_fullName";
    private static final String COLUMN_DOC_EMAIL = "doc_email";
    private static final String COLUMN_DOC_PASSWORD = "doc_password";

    SQLiteDatabase db;

    //create patients table
    private static final String CREATE_PATIENTS_TABLE = "create table patient (patient_id integer primary key not null , " +
            "fullName text not null , patient_email text , password text not null);";

    //create doctors table
    private static final String CREATE_DOCTORS_TABLE = "create table doctor (doc_id integer primary key not null , " +
            "doc_fullName text not null , doc_email text , doc_password text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate() " + db);
        this.db = db;
        db.execSQL(CREATE_PATIENTS_TABLE);
        db.execSQL(CREATE_DOCTORS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PATIENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DOCTOR_TABLE_NAME);

        this.onCreate(db);
    }

    public Patient getPatient(int pId) {
        db = getReadableDatabase();

        String query = "select patient_id, fullName, " +
                "from patient where patient_id = '" + pId + "'";

        Cursor cursor = db.rawQuery(query, null);
        Patient patient = new Patient();

        while (cursor.moveToNext()) {
            patient.setFullName(cursor.getString(1));

        }
        cursor.close();
        db.close();

        return patient;

    }

    public Doctor getDoctor(int dId) {
        db = getReadableDatabase();

        String query = "select doc_id, doc_fullName, " +
                "from doctor where doc_id = '" + dId + "'";

        Cursor cursor = db.rawQuery(query, null);
        Doctor doctor = new Doctor();

        while (cursor.moveToNext()) {
            doctor.setFullName(cursor.getString(1));

        }
        cursor.close();
        db.close();

        return doctor;

    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> retailers = new ArrayList();

        db = this.getReadableDatabase();
        String query = "select doc_fullName, doc_email from " + DOCTOR_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            Doctor doctor = new Doctor();
            doctor.setFullName(cursor.getString(0));
            doctor.setEmail(cursor.getString(1));

            retailers.add(doctor);

        }

        cursor.close();
        db.close();


        return retailers;
    }

    public int insertPatient(Patient patient) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from patient";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_PATIENT_ID, count);
        values.put(COLUMN_FULLNAME, patient.getFullName());
        values.put(COLUMN_EMAIL, patient.getEmail());

        values.put(COLUMN_PASSWORD, patient.getPassword());

        db.insert(PATIENT_TABLE_NAME, null, values);
        db.close();

        return count;


    }

    public int insertDoctor(Doctor doctor) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from doctor";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_DOCTOR_ID, count);
        values.put(COLUMN_DOC_FULLNAME, doctor.getFullName());
        values.put(COLUMN_DOC_EMAIL, doctor.getEmail());


        values.put(COLUMN_DOC_PASSWORD, doctor.getPassword());

        db.insert(DOCTOR_TABLE_NAME, null, values);
        db.close();

        return count;

    }

    /*
     * @reference https://www.example-code.com/android/crypt2_blowfish.asp
     */
    public String encryptPassword(String password, String key) {

        try {

            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");


            // create a cipher based upon Blowfish
            Cipher cipher = Cipher.getInstance("Blowfish");

            // initialise cipher with secret key
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);


            return new String(cipher.doFinal(password.getBytes()));

        } catch (Exception exception) {
            return null;
        }


    }

    public String searchPatientPassword(String username) {
        db = this.getReadableDatabase();
        String query = "select fullName, password from " + PATIENT_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname;
        String pword = "not found";

        if (cursor.moveToFirst()) {

            do {
                uname = cursor.getString(0);


                if (uname.equals(username)) {
                    pword = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();

        return pword;

    }

    public String searchDocPassword(String username) {
        db = this.getReadableDatabase();
        String query = "select doc_fullName, doc_password from " + DOCTOR_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname;
        String pword = "not found";

        if (cursor.moveToFirst()) {

            do {
                uname = cursor.getString(0);


                if (uname.equals(username)) {
                    pword = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();

        return pword;

    }

    public boolean checkIfPatientExists(String username) {
        db = this.getReadableDatabase();
        String query = "select * from patient where fullName = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }


    }

    public boolean checkIfDoctorExists(String username) {
        db = this.getReadableDatabase();
        String query = "select * from doctor where doc_fullName = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }


    }

    public String searchPassword(String username) {
        db = this.getReadableDatabase();
        String query = "select fullName, password from " + PATIENT_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname;
        String pword = "not found";

        if (cursor.moveToFirst()) {

            do {
                uname = cursor.getString(0);


                if (uname.equals(username)) {
                    pword = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();

        return pword;

    }
}