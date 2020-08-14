package com.example.envsaqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MainActivity extends AppCompatActivity {
    public ArcGISMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MapView map = findViewById(R.id.map);
        //mMap = new ArcGISMap();
        //map.setMap(mMap);
    }
}