package com.example.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(200);
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setMessage("its Downloading");
                progressDialog.setProgress(0);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            while(progressDialog.getProgress() <= progressDialog.getMax()){
                                Thread.sleep(50);
                                handle.sendMessage(handle.obtainMessage());
                                if(progressDialog.getProgress() == progressDialog.getMax()){
                                    progressDialog.dismiss();
                                }
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @SuppressLint("HandlerLeak")
            final Handler handle = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(1);
                }
            };
        });
    }
}