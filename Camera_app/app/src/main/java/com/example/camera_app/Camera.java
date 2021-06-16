package com.example.camera_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.security.Timestamp;
import java.text.SimpleDateFormat;

public class Camera extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    Button btnsave;
    ImageView imagedisplay;

    private File photofile, file;
    private int TAKENPHOTO = 0;

    public String photoFileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnsave = findViewById(R.id.Btn_save);
        imagedisplay = findViewById(R.id.image_view);

        File photostorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        photofile = new File(photostorage, (System.currentTimeMillis()) + ".jpg");
//        File photoStorage = new File(String.valueOf(getExternalFilesDirs(Environment.DIRECTORY_PICTURES)), "Camera");
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if(getFromPref(this, ALLOW_KEY)){
                showSettingsAlert();
            }else if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                    showAlert();
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                }
            }
        }else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photofile));
            startActivity(intent);
        }

    }

    public static void saveTopref(Context context, String key, Boolean allowed){
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key){
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    private void showSettingsAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(Camera.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Please give camera access to the app");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OPEN settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DON'T ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(Camera.this == null){
                    return;
                }
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:"+Camera.this.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                Camera.this.startActivity(i);
            }
        });
        alertDialog.show();
    }

    private void showAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(Camera.this).create();
        alertDialog.setTitle("Alert!!!");
        alertDialog.setMessage("Please give camera access to the app");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OPEN settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ActivityCompat.requestPermissions(Camera.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DON't ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST: {
                for (int i =0, len = permissions.length; i< len; i++){
                    String permission = permissions[i];

                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if(showRationale){
                            showAlert();
                        }else if(!showRationale){
                            saveTopref(Camera.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }
}