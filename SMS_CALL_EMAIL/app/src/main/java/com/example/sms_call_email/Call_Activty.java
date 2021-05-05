package com.example.sms_call_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Call_Activty extends AppCompatActivity {

    Button call_btn;
    TextView phone_txt;
    String phone_num;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__activty);
        call_btn = findViewById(R.id.dial_btn);
        phone_txt = findViewById(R.id.phone_txt);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_num = phone_txt.getText().toString();

                if(phone_num.equals("") || phone_num.length() < 10){
                    Toast.makeText(Call_Activty.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                }
                else{
                    phone_num = String.format("tel: %s", phone_num);
                    intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phone_num));
                    startActivity(intent);
                }
            }
        });
    }
}