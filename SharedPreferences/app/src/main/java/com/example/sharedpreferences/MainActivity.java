package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView nameTxt;
    TextView emailTxt;
    Button saveBtn, retrieveBtn, clearBtn;

    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.EmailTxt);
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        saveBtn = findViewById(R.id.save);
        retrieveBtn = findViewById(R.id.retrieve);
        clearBtn = findViewById(R.id.clear);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve(v);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });
    }

    public void save(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, nameTxt.getText().toString());
        editor.putString(email, emailTxt.getText().toString());
        editor.commit();
    }

    public void retrieve(View view){
        if(sharedPreferences.contains(name)){
            nameTxt.setText(sharedPreferences.getString(name, ""));
        }
        if(sharedPreferences.contains(email)){
            emailTxt.setText(sharedPreferences.getString(email, ""));
        }
    }

    public void clear(View view){
        nameTxt.setText("");
        emailTxt.setText("");
    }
}