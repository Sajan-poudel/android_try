package com.example.menuoptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        registerForContextMenu(btn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mail:
                Toast.makeText(this, "User want to communicate via mail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat:
                Toast.makeText(this, "User want to communicate via chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.call:
                Toast.makeText(this, "User want to communicate via call", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("context menu");
        menu.add(0, v.getId(), 0, "football");
        menu.add(0, v.getId(), 2, "Cricket");
        menu.add(0, v.getId(), 3, "volleyball");
        menu.add(0, v.getId(), 1, "hockey");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "user like " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}