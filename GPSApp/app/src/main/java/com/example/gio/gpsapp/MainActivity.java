package com.example.gio.gpsapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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

    List<KnownLocation> knownLocations = new ArrayList<KnownLocation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        knownLocations.add(new KnownLocation(100,20, Location1.class));         //REALLY IMPORTANT, the class is what activity will open
        knownLocations.add(new KnownLocation(5,5, Location15.class));
        knownLocations.add(new KnownLocation(100,25, Location2.class));
        knownLocations.add(new KnownLocation(15,15, Location25.class));
        knownLocations.add(new KnownLocation(100,30, Location3.class));
        knownLocations.add(new KnownLocation(25,25, Location35.class));
        knownLocations.add(new KnownLocation(100,35, Location4.class));
        knownLocations.add(new KnownLocation(35,35, Location45.class));
        knownLocations.add(new KnownLocation(100,40, Location5.class));
        knownLocations.add(new KnownLocation(45,45, Location55.class));
        knownLocations.add(new KnownLocation(100,45, Location6.class));
        knownLocations.add(new KnownLocation(55,55, Location65.class));



        LocationManager locationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);    //the object that gives you the location

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION } ,
                    REQUEST_PERMISSION_ACCESS_FINE_LOCATION );

        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, this);    //500millisecond refresh rate
    }

    boolean key_unlocked = false;   //since items need to be unlocked in a certain order, all are false booleans

    boolean firstLocVisited = false;
    boolean secondLocVisited = false;
    boolean thirdLocVisited = false;
    boolean fourthLocVisited = false;
    boolean fifthLocVisited = false;
    boolean sixthLocVisited = false;

    @Override
    public void onLocationChanged(Location location) {



        TextView text = findViewById(R.id.messageArea);
        text.setText("" + location.getLongitude() + " " + location.getLatitude());


        for(int i=0;i<knownLocations.size();i++){
            KnownLocation loc=knownLocations.get(i);
            if(loc.isCloseTo(location.getLongitude(),location.getLatitude(),100)){


                      //this shows whatever activity you specified
                //1
                if(i == 0 && !firstLocVisited) {
                    loc.show(this,getBaseContext());

                    firstLocVisited = true;

                }
                //1.5
                else if(i == 1 && firstLocVisited) {
                    loc.show(this,getBaseContext());


                }
                //2
                else if(i == 2 && firstLocVisited && !secondLocVisited) {
                    loc.show(this,getBaseContext());

                    secondLocVisited = true;
                }
                //2.5
                else if(i == 3 && firstLocVisited && secondLocVisited ) {
                    loc.show(this,getBaseContext());

                }
                //3
                else if(i == 4 && firstLocVisited && secondLocVisited && !thirdLocVisited) {
                    loc.show(this,getBaseContext());
                    thirdLocVisited = true;
                }
                //3.5
                else if(i == 5 && firstLocVisited && secondLocVisited && thirdLocVisited) {
                    loc.show(this,getBaseContext());
                }

                //4
                else if(i == 6 && firstLocVisited && secondLocVisited && thirdLocVisited && !fourthLocVisited) {
                    loc.show(this,getBaseContext());
                    fourthLocVisited = true;
                }
                //4.5
                else if(i == 7 && firstLocVisited && secondLocVisited && thirdLocVisited && fourthLocVisited) {
                    loc.show(this,getBaseContext());
                }


                /*
                //5
                else if(i == 8 && firstLocVisited && secondLocVisited && thirdLocVisited && fourthLocVisited &&!fifthLocVisited) {
                    loc.show(this,getBaseContext());
                    fifthLocVisited = true;
                }
                //5.5
                else if(i == 9 && firstLocVisited && secondLocVisited && thirdLocVisited && fourthLocVisited && fifthLocVisited) {
                    loc.show(this,getBaseContext());
                }

                /*
                //6
                else if(i == 10 && firstLocVisited && secondLocVisited && thirdLocVisited && fourthLocVisited && fifthLocVisited && !sixthLocVisited) {
                    loc.show(this, getBaseContext());
                    sixthLocVisited = true;
                }
                //6.5
                else if(i == 11 && firstLocVisited && secondLocVisited && thirdLocVisited && fourthLocVisited &&fifthLocVisited && sixthLocVisited) {
                    loc.show(this, getBaseContext());
                }

                /*






                /*else if(i == 4 && firstLocVisited && !secondLocVisited) {
                    secondLocVisited = true;
                    knownLocations.set(0,new KnownLocation(12, 1, Location25.class));
                }
                else if(i == 5 && firstLocVisited && !secondLocVisited) {
                    secondLocVisited = true;
                    knownLocations.set(0,new KnownLocation(12, 1, Location25.class));
                }
                else if(i == 6 && firstLocVisited && !secondLocVisited) {
                    secondLocVisited = true;
                    knownLocations.set(0,new KnownLocation(12, 1, Location25.class));
                }*/

                //maybe I can have a bool isVisited in the KnownLocation class, and if that is true/false I do stuff
            }
            /*else if(firstLocVisited == true && secondLocVisited == false && thirdLocVisited == false){      //after exiting the radius of
                Intent myIntent = new Intent(getBaseContext(), Location4.class);    //should switch to Location1.5, the main menu after finding 1
                this.startActivity(myIntent);
            }*/

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