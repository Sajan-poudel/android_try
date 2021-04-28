package com.example.instancestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    TextView textDisplay;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        textDisplay = (TextView) findViewById(R.id.textDisplay);
        submitBtn = findViewById(R.id.submit);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display(v);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("NAME_KEY", textDisplay.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String nameStr = savedInstanceState.getString("NAME_KEY").toString();
        textDisplay.setText(nameStr);
    }

    public void display (View v){
        textDisplay.setText(name.getText().toString());
    }
}