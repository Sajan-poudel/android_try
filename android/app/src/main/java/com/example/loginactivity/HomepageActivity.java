package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity {

    String email;
    TextView emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        emailTxt = findViewById(R.id.emailTxt);
        email = intent.getStringExtra("get_email").toString();
        emailTxt.setText("Hi! " + email);
        Log.i("email :", email);
    }
}