package com.example.sms_call_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SMS_activity extends AppCompatActivity {

    TextView phone_txt, message_txt;
    Button send_sms_btn;
    String phoneNum, messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s_activity);
        phone_txt = findViewById(R.id.phone_num);
        message_txt = findViewById(R.id.message);
        send_sms_btn = findViewById(R.id.send_sms);

        send_sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = phone_txt.getText().toString();
                messages = message_txt.getText().toString();
                Log.i(phoneNum, messages);
                if(phoneNum.equals("") || phoneNum.length() < 10){
                    Toast.makeText(SMS_activity.this, "enter valid phone number", Toast.LENGTH_SHORT).show();
                }else if(messages.equals("")){
                    Toast.makeText(SMS_activity.this, "Atleast type some message", Toast.LENGTH_SHORT).show();
                }else{
                    Uri smsUri  = Uri.parse("smsto:" + phoneNum);
                    Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
                    intent.setType("vnd.android-dir/mms-sms");
                    intent.putExtra("sms_body", messages);
                    try {
                        startActivity(intent);
                        Toast.makeText(SMS_activity.this, "SMS sent", Toast.LENGTH_SHORT).show();
                    }catch (android.content.ActivityNotFoundException ex){
                        Toast.makeText(SMS_activity.this, "failed !!", Toast.LENGTH_SHORT).show();
                        Log.i("failed", ex.toString());
                    }

                }
            }
        });
    }
}