package com.example.gio.gpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Location15 extends ClosableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location15);
    }
    public void onNextClicked(View view) {
        Intent i = new Intent(getBaseContext(), clue2.class);
        startActivity(i);
    }
}
