package com.example.gio.gpsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class KnownLocation {

    double longitude;
    double latitude;
    Class activity;

    KnownLocation(double lon, double lat, Class a){
        longitude=lon;
        latitude=lat;
        activity =a;
    }


    public void show(AppCompatActivity act, Context c){
        Intent intent1 = new Intent("CloseTop");    //closes any new open activity before creating a new one
        act.sendBroadcast(intent1);                         //technically, just sends a message only to activities that will be listening; so only extend activities you want to close

        Intent i = new Intent(c, activity); //opens the new activity
        act.startActivity(i);
    }
    public boolean isCloseTo(double lon, double lat, double radius){  //if that point is within this radius, return true

        if(distanceFrom(longitude, latitude, lon,lat) < radius) return true;
        else return false;
    }

    public double distanceFrom(double lon1, double lat1, double lon2, double lat2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                *  Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                *  Math.cos(deg2rad(lat2))
                *  Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515* 1000.0;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
