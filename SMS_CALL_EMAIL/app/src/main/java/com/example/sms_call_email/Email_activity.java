package com.example.sms_call_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Email_activity extends AppCompatActivity {

    TextView email_id, subject, message;
    Button send_email;

    String email, subject_msg, messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email_id = findViewById(R.id.email_id);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);

        send_email = findViewById(R.id.send_email);

        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_id.getText().toString();
                subject_msg = subject.getText().toString();
                messages = message.getText().toString();

                if(email.equals("")){
                    Toast.makeText(Email_activity.this, "Please enter email id", Toast.LENGTH_SHORT).show();

                }else if(subject_msg.equals("")){
                    Toast.makeText(Email_activity.this, "Please enter subject", Toast.LENGTH_SHORT).show();

                }else if(messages.equals("")){
                    Toast.makeText(Email_activity.this, "Please enter messages", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject_msg);
                    intent.putExtra(Intent.EXTRA_TEXT, messages);
                    startActivity(intent);
                }
            }
        });
    }
}