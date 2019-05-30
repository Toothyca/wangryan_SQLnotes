package com.example.mycontactapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editUsername;
    EditText editSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editUsername = findViewById(R.id.editText_username);
        editSocial = findViewById(R.id.editText_ssn);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated DatabaseHelper");
    }

    public void addData(View view)
    {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editUsername.getText().toString(), editSocial.getText().toString());

        if (isInserted == true)
        {
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG);
        }
        else
        {
            Toast.makeText(MainActivity.this, "FAILED - contact not inserted", Toast.LENGTH_LONG);
        }
    }

    public void viewData(View view)
    {
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "Main Activity: displaying data");

        if(res.getCount() == 0)
        {
            showMessage("Error", "No data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            //moves from row to row for different contacts, individual info is in different columns
            //Append res columns to buffer - look at StringBuffer and Cursor API
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Username: " + res.getString(2) + "\n");
            buffer.append("SSN: " + res.getString(3) + "\n\n");


        }
        showMessage("Data", buffer.toString());
    }

    public void searchData(View view)
    {
        Log.d("MyContactApp", "Main Activity: displaying some data");
        Cursor res = myDb.getAllData();

        String name = editName.getText().toString();
        String username = editUsername.getText().toString();
        String social = editSocial.getText().toString();

        boolean display = false;

        if(res.getCount() == 0)
        {
            showMessage("Error", "No data found in database");
            return;
        }

        if(name.equals("") && username.equals("") && social.equals(""))
        {
            showMessage("Error", "No parameters entered");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext())
        {
            String searchName = res.getString(1);
            String searchUsername = res.getString(2);
            String searchSocial = res.getString(3);

            if (name.equals(""))
            {
                searchName = "";
            }

            if (username.equals(""))
            {
                searchUsername = "";
            }

            if (social.equals(""))
            {
                searchSocial = "";
            }

            if(name.equals(searchName) && username.equals(searchUsername) && social.equals(searchSocial))
            {
                Log.d("MyContactApp", "It worked");
                buffer.append("ID: " + res.getString(0) + "\n");
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Username: " + res.getString(2) + "\n");
                buffer.append("SSN: " + res.getString(3) + "\n\n");
                display = true;
            }
        }
        if (!display)
        {
            showMessage("Error", "No contacts with entered parameters");
            return;
        }

        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message)
    {
        Log.d("MyContactApp", "MainActivity: showing message");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}