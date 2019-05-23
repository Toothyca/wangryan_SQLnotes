package com.example.mycontactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2019.db";
    public static final String TABLE_NAME = "Contact2019_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";

    public ArrayList<String> nameList = new ArrayList<String>();
    public ArrayList<String> usernameList = new ArrayList<String>();
    public ArrayList<Integer> socialSecurityList = new ArrayList<Integer>();

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMANY KEY AUTOINCREMENT" +
                    COLUMN_NAME_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //remove later
        Log.d("MyContactApp", "DatabaseHelper: constructed the DatabaseHelper");
    }

    public void addName(String name)
    {
        nameList.add(name);
    }

    public void addUsername(String username)
    {
        usernameList.add(username);
    }

    public void addSocialSecurity(Integer socialsecurity)
    {
        socialSecurityList.add(socialsecurity);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
