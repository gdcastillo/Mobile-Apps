package com.example.gio.gpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Location1 extends ClosableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location1);
    }
    public void onNextClicked(View view) {
        Intent i = new Intent(getBaseContext(), Location15.class);
        startActivity(i);
    }
}
