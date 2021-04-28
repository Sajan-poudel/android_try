package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button football_btn, cricket_btn;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        football_btn = findViewById(R.id.Football_btn);
        cricket_btn = findViewById(R.id.Cricket_btn);
        display = findViewById(R.id.display);

        football_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change("football");
            }
        });

        cricket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change("cricket");
            }
        });
    }

    private void change(String item){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("favorite sport")
                .setMessage("Are you sure you like " + item)
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        display.setText("your best sport is : " + item.toUpperCase());
                        Toast.makeText(MainActivity.this, "Selected yes", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "selected no", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}