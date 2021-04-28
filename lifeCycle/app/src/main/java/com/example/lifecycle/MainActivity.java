package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(tag, "In method OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "In the method OnStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "In method OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "In method OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "In method OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "In method OnDestroy");
    }
}