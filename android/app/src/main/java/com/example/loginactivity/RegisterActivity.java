package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText nameTxt, lastTxt, emailTxt, passwordTxt;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameTxt = findViewById(R.id.nameEditTxt);
        lastTxt = findViewById(R.id.lastEditTxt);
        emailTxt = findViewById(R.id.emailEditTxt);
        passwordTxt = findViewById(R.id.passwordEditTxt);
        registerBtn = findViewById(R.id.submitBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String last_name = lastTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                if(name.equals("")){
                    Toast.makeText(RegisterActivity.this, "Name required", Toast.LENGTH_SHORT).show();
                }else if(last_name.equals("")){
                    Toast.makeText(RegisterActivity.this, "last_Name required", Toast.LENGTH_SHORT).show();
                }else if(email.equals("")){
                    Toast.makeText(RegisterActivity.this, "Email required", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(RegisterActivity.this, "Password required", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}