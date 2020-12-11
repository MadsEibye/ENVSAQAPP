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
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.envsaqapp.JavaClasses.GeoPointXY;
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

import java.lang.reflect.Array;
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
        Log.d("USERLOCATION","LP:" + userX + ", " + userY);
        GeoPoint geoPoint = new GeoPoint(userX,userY);
        PlotDots();
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

        mapView.setTileSource(tileSource);
        MapTileProviderBasic provider = new MapTileProviderBasic(getApplicationContext());
        TilesOverlay tilesOverlay = new TilesOverlay(provider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        //mapView.getOverlays().add(tilesOverlay);
        //mapView.getOverlays().add(dotsOverlay);
        mapController = (MapController) mapView.getController();
        mapView.setMinZoomLevel(8.0);
        mapView.setMultiTouchControls(true);
        mapController.setZoom(11);
        //mapController.setZoom(4);
        GeoPoint geopointcenter = new GeoPoint(userX, userY);
        mapController.setCenter(geopointcenter);
        //addMarkerUserLocation(gPt);
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.PickNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    ArrayList<GeoPointXY> geoPoints = new ArrayList<GeoPointXY>(){};


    public void PlotDots(){

            //region GeoPoints
            GeoPointXY Skagen = new GeoPointXY(57.720920,10.583880, 594344.01465938, 6398742.60765198,"Skagen");
            GeoPointXY Hjørring = new GeoPointXY(57.463450,9.981270, 558865.89677431, 6369401.90974297,"Hjørring");
            GeoPointXY Frederikshavn = new GeoPointXY(57.440840,10.538370, 592340.25019866, 6367504.82878019,"Frederikshavn");
            GeoPointXY Brønderslev = new GeoPointXY(57.269880,9.940530, 556719.91634111, 6347819.99063959,"Brønderslev");
            GeoPointXY Byrum_Læsø = new GeoPointXY(57.254840, 10.999390, 620617.12527334, 6347524.46852685,"Byrum_Læsø");
            GeoPointXY Aalborg = new GeoPointXY(57.047218, 9.920100, 555822.33459646, 6323018.14983396,"Aalborg");
            GeoPointXY Klitmøller = new GeoPointXY(57.038970, 8.506190, 470033.60483901, 6321832.24251931,"Klitmøller");
            GeoPointXY Thisted = new GeoPointXY(56.954430, 8.689600, 481120.85504201, 6312356.1660442,"Thisted");
            GeoPointXY Aars = new GeoPointXY(57.383990,10.116240, 567107.50186352, 6360681.84925287,"Aars");
            GeoPointXY Nykøbing_Mors = new GeoPointXY(56.797880, 8.857410, 491291.05919325, 6294896.29940943,"Nykøbing Mors");
            GeoPointXY Hobro = new GeoPointXY(56.64306, 9.79029, 548466.51, 6277933.36,"Hobro");
            GeoPointXY Hadsund = new GeoPointXY(56.71482, 10.11682, 568360.8222549, 6286198.7246963,"Hadsund");
            GeoPointXY Anholt = new GeoPointXY(56.6999972, 11.5666644, 657151.01984717, 6286934.5889844,"Anholt");
            GeoPointXY Grenaa = new GeoPointXY(56.41578, 10.87825, 615873.56339404, 6253938.7983276,"Grenaa");
            GeoPointXY Randers = new GeoPointXY(56.4666648, 10.0499998, 564693.49768959, 6258514.2372812,"Randers");
            GeoPointXY Viborg = new GeoPointXY(56.45319, 9.40201, 524778.2201636, 6256592.7177725,"Viborg");
            GeoPointXY Tange = new GeoPointXY(55.14186, 10.76098, 612243.4585144, 6111993.24973299,"Tange");
            GeoPointXY Ebeltoft = new GeoPointXY(56.19442, 10.6821, 604375.92877806, 6228991.6036092,"Ebeltoft");
            GeoPointXY Skive = new GeoPointXY(56.56381, 9.04025, 502473.63649333, 6268833.7192097,"Skive");
            GeoPointXY Struer = new GeoPointXY(56.49122, 8.58376, 474370.34300584, 6260830.8552239,"Struer");
            GeoPointXY Holstebro = new GeoPointXY(56.359909, 8.615390, 476236.18, 6246204.06,"Holstebro");
            GeoPointXY Ulborg = new GeoPointXY(56.289227, 8.431417, 692173.74, 6192486.31,"Ulborg");
            GeoPointXY Ringkøbing = new GeoPointXY(56.090729, 8.244300, 453599.75712822, 6216386.75699406,"Ringkøbing");
            GeoPointXY Silkeborg = new GeoPointXY(56.183189, 9.551720, 534121.68731936, 6225075.4327736,"Silkeborg");
            GeoPointXY Ballen = new GeoPointXY(55.040740, 10.488260, 595101.65549129, 6100337.30350452,"Ballen");
            GeoPointXY Horsens = new GeoPointXY(55.860451, 9.849620, 553178.99914117, 6190874.68861151,"Horsens");
            GeoPointXY Vejle = new GeoPointXY(55.709251, 9.535910, 533673.84631955,6173850.89147649,"Vejle");
            GeoPointXY Grindsted = new GeoPointXY(55.755241, 8.931280, 495687.03700229, 6178841.25604994,"Grindsted");
            GeoPointXY Varde = new GeoPointXY(55.620400, 8.479490, 467219.62151539, 6163955.33585825,"Varde");
            GeoPointXY Esbjerg = new GeoPointXY(55.467270, 8.449080, 465169.38298311, 6146928.71646363,"Esbjerg");
            GeoPointXY Skærbæk = new GeoPointXY(55.160150,8.766620, 485130.56007874, 6112637.84387901,"Skærbæk");
            GeoPointXY Tønder = new GeoPointXY(54.932570,8.867100, 491484.33352311, 6087296.00301394,"Tønder");
            GeoPointXY Aabenraa = new GeoPointXY(55.043368,9.424000, 527093.31046995, 6099699.54811992,"Aabenraa");
            GeoPointXY Haderslev = new GeoPointXY(55.253950,9.489140, 531091.50280286, 6123160.367682,"Haderslev");
            GeoPointXY Vejen = new GeoPointXY(55.482480,9.139760, 508832.63991511, 6148492.32749349,"Vejen");
            GeoPointXY Kolding = new GeoPointXY(55.490750,9.472110, 529830.34424322, 6149505.07666036,"Kolding");
            GeoPointXY Middelfart = new GeoPointXY(55.506920,9.728400, 546004.95699968, 6151444.35659968,"Middelfart");
            GeoPointXY Glamsbjerg = new GeoPointXY(55.278050, 10.105750, 570241.6932972, 6126290.3847184,"Glamsbjerg");
            GeoPointXY Nyborg = new GeoPointXY(55.310380, 10.791690, 613718.83299319, 6130793.27154933,"Nyborg");
            GeoPointXY Svendborg = new GeoPointXY(55.059750, 10.606870, 602631.69069211, 6102620.25476429,"Svendborg");
            GeoPointXY Faaborg = new GeoPointXY(55.096788, 10.241801, 579242.71653662, 6106266.28946664,"Faaborg");
            GeoPointXY Keldsnor = new GeoPointXY(54.730915, 10.721515, 610852.9882133, 6066208.42304367,"Keldsnor");
            GeoPointXY Nakskov = new GeoPointXY(54.834720, 11.158450, 638628.84226944, 6078534.55475363,"Nakskov");
            GeoPointXY Rødby = new GeoPointXY(54.695700, 11.385980, 653765.83081112, 6063543.55510826,"Rødby");
            GeoPointXY Maribo = new GeoPointXY(54.776070, 11.501760, 660906.86354153, 6072743.65211178,"Maribo");
            GeoPointXY Næstved = new GeoPointXY(55.229850, 11.760600, 675556.88027264, 6123844.64289889,"Næstved");
            GeoPointXY Slagelse = new GeoPointXY(55.402110, 11.351620, 648906.69940963, 6142055.31436313,"Slagelse");
            GeoPointXY Korsør = new GeoPointXY(55.330090, 11.140240, 635770.63558405, 6133610.46239217,"Korsør");
            GeoPointXY Jyderup = new GeoPointXY(55.659950, 11.397980, 650851.28498546, 6170841.17180967,"Jyderup");
            GeoPointXY Kalundborg = new GeoPointXY(55.682610, 11.101100, 631329.15233838, 6172391.5117991,"Kalundborg");
            GeoPointXY Frederikssund = new GeoPointXY(55.839581, 12.069140, 316145.04928843, 6192369.75419746,"Frederikssund");
            GeoPointXY Helsingør = new GeoPointXY(56.05, 12.5, 344300.39615291, 6214462.9665116,"Helsingør");
            GeoPointXY Rønne = new GeoPointXY(55.10091, 14.70664, 481281.34582041, 6106059.9820493,"Rønne");
            GeoPointXY Aakirkeby = new GeoPointXY(55.06841, 14.90970, 494233.66635144, 6102407.64073468,"Aakirkeby");
            GeoPointXY Nexø = new GeoPointXY(55.06067, 15.13226, 508447.92544174, 6101550.62240584,"Nexø");
            GeoPointXY Nordborg = new GeoPointXY(55.05732, 9.7408, 547319.8560519, 6101420.7310558,"Nordborg");
            GeoPointXY Vordingborg = new GeoPointXY(55.008925,11.909363,123,132, "Vordingborg");
            GeoPointXY Køge = new GeoPointXY(55.45802, 12.18214,701193.67,6150385.45,"Køge");
            GeoPointXY Roskilde = new GeoPointXY(55.64152, 12.08035,724413.52,6175831.62,"Roskilde");
            GeoPointXY København = new GeoPointXY(55.676319,12.569300,724413.52,6175831.62, "København");
            GeoPointXY Århus = new GeoPointXY(56.1572, 10.2107,575199.81,6224235.68,"Århus");
            GeoPointXY Odense = new GeoPointXY(55.39594, 10.38831,588801.35 ,6140617.03,"Odense");
            GeoPointXY Nykøbing_falster = new GeoPointXY(54.7666636, 11.8833298,685086.69 ,6072755.69,"Nykøbing Falster");
            GeoPointXY Herning = new GeoPointXY(56.13932, 8.97378,498145.84,6221321.43,"Herning");
            GeoPointXY Hillerød = new GeoPointXY(55.92791, 12.30081,706653.36,6203141.54,"Hillerød");

        geoPoints.add(København);
        geoPoints.add(Vordingborg);
        geoPoints.add(Køge);
        geoPoints.add(Roskilde);
        geoPoints.add(Århus);
        geoPoints.add(Odense);
        geoPoints.add(Nykøbing_falster);
        geoPoints.add(Herning);
        geoPoints.add(Skagen);
        geoPoints.add(Hjørring);
        geoPoints.add(Frederikshavn);
        geoPoints.add(Brønderslev);
        geoPoints.add(Byrum_Læsø);
        geoPoints.add(Aalborg);
        geoPoints.add(Klitmøller);
        geoPoints.add(Thisted);
        geoPoints.add(Aars);
        geoPoints.add(Nykøbing_Mors);
        geoPoints.add(Hobro);
        geoPoints.add(Hadsund);
        geoPoints.add(Anholt);
        geoPoints.add(Grenaa);
        geoPoints.add(Randers);
        geoPoints.add(Viborg);
        geoPoints.add(Tange);
        geoPoints.add(Ebeltoft);
        geoPoints.add(Skive);
        geoPoints.add(Struer);
        geoPoints.add(Holstebro);
        geoPoints.add(Ulborg);
        geoPoints.add(Ringkøbing);
        geoPoints.add(Silkeborg);
        geoPoints.add(Ballen);
        geoPoints.add(Horsens);
        geoPoints.add(Vejle);
        geoPoints.add(Grindsted);
        geoPoints.add(Varde);
        geoPoints.add(Esbjerg);
        geoPoints.add(Skærbæk);
        geoPoints.add(Tønder);
        geoPoints.add(Aabenraa);
        geoPoints.add(Haderslev);
        geoPoints.add(Vejen);
        geoPoints.add(Kolding);
        geoPoints.add(Middelfart);
        geoPoints.add(Glamsbjerg);
        geoPoints.add(Nyborg);
        geoPoints.add(Svendborg);
        geoPoints.add(Faaborg);
        geoPoints.add(Keldsnor);
        geoPoints.add(Nakskov);
        geoPoints.add(Rødby);
        geoPoints.add(Maribo);
        geoPoints.add(Næstved);
        geoPoints.add(Slagelse);
        geoPoints.add(Korsør);
        geoPoints.add(Jyderup);
        geoPoints.add(Kalundborg);
        geoPoints.add(Frederikssund);
        geoPoints.add(Helsingør);
        geoPoints.add(Rønne);
        geoPoints.add(Aakirkeby);
        geoPoints.add(Nexø);
        geoPoints.add(Nordborg);

        //endregion GeoPoints

        for (GeoPointXY o:geoPoints
             ) {
            Marker marker = new Marker(mapView);
            marker.setPosition(o);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setIcon(getResources().getDrawable(R.drawable.ic_icon_place_blackgray_36dp));
            marker.setTitle(o.getName() + o.getX_utm() + "," + o.getY_utm());
            marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker, MapView mapView) {
                    Toast.makeText(MapPickActivity.this,o.getName() ,Toast.LENGTH_LONG).show();
                    geoX = o.getLatitude();
                    geoY = o.getLongitude();
                    geoString = o.getName();
                    Intent i = new Intent(MapPickActivity.this,ForureningsUdsigt.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    i.putExtra("geoX",geoY);
                    i.putExtra("geoY",geoX);
                    i.putExtra("geoString", geoString);
                    setResult(2,i);
                    finish();
                    return true;
                }
            });
            mapView.getOverlays().add(marker);
        }

    }

    private double geoX;
    private double geoY;
    private String geoString;

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
                    i.putExtra("geoX",geoX);
                    i.putExtra("geoY",geoY);
                    i.putExtra("geoString", geoString);

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