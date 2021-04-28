package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, registerBtn;
    EditText emailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        emailTxt = findViewById(R.id.editTextTextEmailAddress);
        passwordTxt = findViewById(R.id.editTextTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = emailTxt.getText().toString();
                password = passwordTxt.getText().toString();

                if(email.equals("")){
                    Toast.makeText(MainActivity.this, "Email required", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(MainActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                }else{
                    Intent firstPageIntent = new Intent(MainActivity.this, HomepageActivity.class);
                    firstPageIntent.putExtra("get_email", email);
                    startActivity(firstPageIntent);
                    finish();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}