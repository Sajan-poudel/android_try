package com.example.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.customview.views.CustomView;

public class MainActivity extends AppCompatActivity {
    private  CustomView customView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.customView);
        btn = findViewById(R.id.btnChangeColor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.changeColor();
            }
        });
    }
}