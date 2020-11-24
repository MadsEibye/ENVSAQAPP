package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.UFormat;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.envsaqapp.R;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.TilesOverlay;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class MapPickActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MapView mapView;
    private MapController mapController;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private Integer item8ID;
    private static double userX;
    private static double userY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_pick);
        mapView = findViewById(R.id.PickMapView);
        mDrawerLayout = findViewById(R.id.PickDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setNavigationViewListener();
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);
        GeoPoint geoPoint = new GeoPoint(userX,userY);
        LoadMap(geoPoint);

    }

    private void LoadMap(GeoPoint gPt) {
        //ParseAndShowLayerWMS();
        //ShowSelectedLayer();
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //final ITileSource tileSource = TileSourceFactory.MAPNIK;
        final ITileSource tileSource = new XYTileSource("Mapnik", 1, 20, 256, ".png",
                new String[]{
                        "http://tile.openstreetmap.org/",
                });

        final ITileSource dotsOverlay = new XYTileSource("OSMPublicTransport", 1, 20, 256, ".png",
                new String[]{
                        "http://openptmap.org/tiles/",
                });
        mapView.setTileSource(tileSource);
        MapTileProviderBasic provider = new MapTileProviderBasic(getApplicationContext());
        provider.setTileSource(dotsOverlay);
        TilesOverlay tilesOverlay = new TilesOverlay(provider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        mapView.getOverlays().add(tilesOverlay);
        //mapView.getOverlays().add(dotsOverlay);
        mapController = (MapController) mapView.getController();
        mapView.setMinZoomLevel(8.0);
        mapView.setMultiTouchControls(true);
        mapController.setZoom(13);
        //mapController.setZoom(4);
        GeoPoint geopointcenter = new GeoPoint(55.64152, 12.08035);
        mapController.setCenter(geopointcenter);
        //addMarkerUserLocation(gPt);
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.PickNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void PlotDots(){

            /*
            GeoPoint Skagen = new GeoPoint();
            GeoPoint Hjørring = new GeoPoint();
            GeoPoint Frederikshavn = new GeoPoint();
            GeoPoint Brønderslev = new GeoPoint();
            GeoPoint Byrum_Læsø = new GeoPoint();
            GeoPoint Aalborg = new GeoPoint();
            GeoPoint Klitmøller = new GeoPoint();
            GeoPoint Thisted = new GeoPoint();
            GeoPoint Aars = new GeoPoint();
            GeoPoint Nykøbing_Mors = new GeoPoint();
            GeoPoint Hobro = new GeoPoint();
            GeoPoint Hadsund = new GeoPoint();
            GeoPoint Anholt = new GeoPoint();
            GeoPoint Grenaa = new GeoPoint();
            GeoPoint Randers = new GeoPoint();
            GeoPoint Viborg = new GeoPoint();
            GeoPoint Tange = new GeoPoint();
            GeoPoint Ebeltoft = new GeoPoint();
            GeoPoint Aarhus = new GeoPoint();
            GeoPoint Skive = new GeoPoint();
            GeoPoint Struer = new GeoPoint();
            GeoPoint Holstebro = new GeoPoint();
            GeoPoint Ulborg = new GeoPoint();
            GeoPoint Ringkøbing = new GeoPoint();
            GeoPoint Silkeborg = new GeoPoint();
            GeoPoint Ballen = new GeoPoint();
            GeoPoint Horsens = new GeoPoint();
            GeoPoint Vejle = new GeoPoint();
            GeoPoint Grindsted = new GeoPoint();
            GeoPoint Varde = new GeoPoint();
            GeoPoint Esbjerg = new GeoPoint();
            GeoPoint Skærbæk = new GeoPoint();
            GeoPoint Tønder = new GeoPoint();
            GeoPoint Aabenraa = new GeoPoint();
            GeoPoint Haderslev = new GeoPoint();
            GeoPoint Vejen = new GeoPoint();
            GeoPoint Kolding = new GeoPoint();
            GeoPoint Middelfart = new GeoPoint();
            GeoPoint Glamsbjerg = new GeoPoint();
            GeoPoint Nyborg = new GeoPoint();
            GeoPoint Svendborg = new GeoPoint();
            GeoPoint Faaborg = new GeoPoint();
            GeoPoint Keldsnor = new GeoPoint();
            GeoPoint Nakskov = new GeoPoint();
            GeoPoint Rødby = new GeoPoint();
            GeoPoint Maribo = new GeoPoint();
            GeoPoint Næstved = new GeoPoint();
            GeoPoint Slagelse = new GeoPoint();
            GeoPoint Korsør = new GeoPoint();
            GeoPoint Jyderup = new GeoPoint();
            GeoPoint Kalundborg = new GeoPoint();
            GeoPoint Frederikssund = new GeoPoint();
            GeoPoint Helsingør = new GeoPoint();
            GeoPoint Rønne = new GeoPoint();
            GeoPoint Aakirkeby = new GeoPoint();
            GeoPoint Nexø = new GeoPoint();
            GeoPoint Nordborg = new GeoPoint();*/
            GeoPoint Vordingborg = new GeoPoint(55.008925,11.909363);
            GeoPoint Køge = new GeoPoint(55.45802, 12.18214);
            GeoPoint Roskilde = new GeoPoint(55.64152, 12.08035);
            GeoPoint København = new GeoPoint(55.6760968,12.5683371);
            GeoPoint Århus = new GeoPoint(56.1572, 10.2107);
            GeoPoint Odense = new GeoPoint(55.39594, 10.38831);
            GeoPoint Nykøbing_falster = new GeoPoint(54.7666636, 11.8833298);
            GeoPoint Herning = new GeoPoint(56.13932, 8.97378);
            GeoPoint Hillerød = new GeoPoint(55.92791, 12.30081);



        Marker VordingborgMarker = new Marker(mapView);
        VordingborgMarker.setPosition(Vordingborg);
        VordingborgMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        VordingborgMarker.setIcon(getResources().getDrawable(R.drawable.ic_icon_blue_dot));
        VordingborgMarker.setTitle("Vordingborg");
        VordingborgMarker.showInfoWindow();
        mapView.getOverlays().add(VordingborgMarker);

        Marker KøgeMarker = new Marker(mapView);
        KøgeMarker.setPosition(Køge);
        KøgeMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        KøgeMarker.setIcon(getResources().getDrawable(R.drawable.ic_icon_blue_dot));
        KøgeMarker.setTitle("Køge");
        KøgeMarker.showInfoWindow();
        mapView.getOverlays().add(KøgeMarker);

        Marker RoskildeMarker = new Marker(mapView);
        RoskildeMarker.setPosition(Roskilde);
        RoskildeMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        RoskildeMarker.setIcon(getResources().getDrawable(R.drawable.ic_icon_blue_dot));
        RoskildeMarker.setTitle("Køge");
        RoskildeMarker.showInfoWindow();
        RoskildeMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(MapPickActivity.this,"Roskilde",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mapView.getOverlays().add(RoskildeMarker);
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ForHerItem1:
                item1ID = item.getItemId();
                ChangeActivity(item1ID);
                return true;
            case R.id.KortItem2:
                item2ID = item.getItemId();
                ChangeActivity(item2ID);
                return true;

            case R.id.UdsigtItem3:
                item3ID = item.getItemId();
                ChangeActivity(item3ID);
                return true;/*
            case R.id.GroenItem4:
                item4ID = item.getItemId();
                ChangeActivity(item4ID);
                return true;*/
            case R.id.NotiItem5:
                item5ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item5ID);
                return true;
            case R.id.SkalaItem6:
                item6ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item6ID);
                return true;
            case R.id.infoItem7:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item7ID = item.getItemId();
                ChangeActivity(item7ID);
                return true;
            case R.id.KortItem8:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item8ID = item.getItemId();
                ChangeActivity(item8ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){

        }

        return super.onOptionsItemSelected(item);
    }

    public void ChangeActivity(Integer ID){
        if (ID == item1ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, ForureningHer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        }
        else if (ID == item2ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, MainActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item3ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, NavigationUdsigt.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item4ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, Notifikationer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, Forureningskala.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item7ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, Info.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item8ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MapPickActivity.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
    }
}