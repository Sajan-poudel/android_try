package com.example.sms_call_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button call_btn, email_btn, sms_btn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call_btn = findViewById(R.id.btn_call);
        email_btn = findViewById(R.id.btn_email);
        sms_btn = findViewById(R.id.btn_sms);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Call_Activty.class);
                startActivity(intent);
            }
        });

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Email_activity.class);
                startActivity(intent);
            }
        });

        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SMS_activity.class);
                startActivity(intent);
            }
        });


    }
}