package com.example.envsaqapp.JavaClasses;

import org.osmdroid.util.GeoPoint;

public class GeoPointXY extends GeoPoint {



    public double getX_utm() {
        return x_utm;
    }

    public double getY_utm() {
        return y_utm;
    }


    public String getName() {
        return name;
    }

    private String name;
    private double x_utm;
    private double y_utm;

    public GeoPointXY(double aLatitude, double aLongitude,double x, double y, String name) {
        super(aLatitude, aLongitude);
        this.x_utm = x;
        this.y_utm = y;
        this.name = name;
    }


}
