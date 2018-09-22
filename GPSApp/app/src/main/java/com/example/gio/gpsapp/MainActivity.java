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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {


    final int  REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1; //CONSTANT, won't be changed

    List<KnownLocation> knownLocations = new ArrayList<KnownLocation>();    //definitely fill this arrayList out with stuff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        knownLocations.add(new KnownLocation(100,20, Location1.class));         //REALLY IMPORTANT, the class is what activity will open
        knownLocations.add(new KnownLocation(100,25, Location2.class));
        knownLocations.add(new KnownLocation(100,30, Location3.class));



        LocationManager locationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);    //the object that gives you the location

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION } ,
                    REQUEST_PERMISSION_ACCESS_FINE_LOCATION );

        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, this);    //500millisecond refresh rate
    }

    boolean key_unlocked = false;   //since items need to be unlocked in a certain order, all are false booleans

    @Override
    public void onLocationChanged(Location location) {



        TextView text = findViewById(R.id.messageArea);
        text.setText("" + location.getLongitude() + " " + location.getLatitude());


        for(int i=0;i<knownLocations.size();i++){
            KnownLocation loc=knownLocations.get(i);
            if(loc.isCloseTo(location.getLongitude(),location.getLatitude(),100)){
                loc.show(this,getBaseContext());        //this shows whatever activity you specified
            }

        //create a for loop to check to see if you're close to a location; is that really necessary?



        }
        //if location is close to KnownLocation1, then show Activity1
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


// 1:42 is how to pop up activities