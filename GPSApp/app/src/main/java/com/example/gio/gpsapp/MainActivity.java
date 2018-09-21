package com.example.gio.gpsapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {


    final int  REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1; //CONSTANT, won't be changed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);    //the object that gives you the location

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION } ,
                    REQUEST_PERMISSION_ACCESS_FINE_LOCATION );

        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, this);    //500millisecond refresh rate
    }


    @Override
    public void onLocationChanged(Location location) {

        TextView text = findViewById(R.id.messageArea);
        text.setText("" + location.getLongitude() + " " + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
