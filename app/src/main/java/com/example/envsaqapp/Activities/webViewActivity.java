package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



import com.example.envsaqapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.util.GeoPoint;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class webViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private Integer item8ID;
    public float pointX;
    public float pointY;
    private static double userX;
    private static double userY;
    public float Latitude;
    public float Longitude;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GeoPoint UsergeoPoint;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private WebView webView;
    //endregion Instance Fields

    //region Methods

    //Start of Comments onCreate()
    /*
    OnCreate() is a method that runs when the activity is created. Fx. if you change activity, it will be created before it is shown.
    OnCreate() is basically just a method with logic you want to have executed when the activity starts. Every activity has a OnCreate() method.
    There are also a OnStart() method that is run when the activity starts. Do not confuse these two, they are very different.
    In OnCreate() we set the layoutfile, we set window to be fullscreen. We then create and get an Intent that gets the user coordinates from the recent activity. Then we store the coordinates in
    some instance fields. We find the TextView from the layoutfile with findViewById, and set the TextViews text attribute to show the users coordinates.
    We then do the same with the DrawerLayout but now we create the listeners for the button in the actionBar.
    You have to have a location manager, to find the location of the user. We then run the method findLocation().
    The navigationview is set to the current view. The method setNavigationViewListener() and notifikationskanal() is called.
    We then set a navigationViewListener to the navigationview, to check for when an item is pressed.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.MainNav_view);
        setNavigationViewListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
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
        };
        findLocation();

    }

    //Start of Comments setNavigationViewListener
    /*
    This method finds the NavigationView with the findViewById() method, and then adds a listener to the navigationView that checks if an item in the list has been pressed or not.
    If an item has been pressed, it sets the value to 'true', so the method onNavigationItemSelected() knows it should execute.
    */
    private void setNavigationViewListener() {
            navigationView = findViewById(R.id.webNavView);
            navigationView.setNavigationItemSelectedListener(this);
    }

    //Start of Comments ChangeActivity()
    /*
    ChangeActivity() is the handler for the navigationbar. So when you press an item in the navigationbar, ChangeActivity is run, with the ID you
    get from OnNavigationItemSelected(). The method then takes the ID and checks which ID it matches, and if it matches ID 1 it changes to the first Activity.
    What happens is, that it creates and Intent, which is basically a package, and this intent is labeled with what class/activity it is coming from, and where it should go.
    The method then adds the users location in "userX" and userY" and puts them into the package. The package is then sent in startActivity(), and the app now changes activity to whatever
    activity that was pressed in the navigationbar.
    overridePendingTransition is just the animation that is run when you change the activity, and in this case its a fade_in fade_out. And the finish() method is just closing down the last activity
    */
    public void ChangeActivity (Integer ID){
        if (ID == item1ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, ForureningHer.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        } else if (ID == item2ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, MainActivity.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item3ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, NavigationUdsigt.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item4ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, GroenRute.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    //startActivity(i);
                    Toast.makeText(webViewActivity.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, Notifikationer.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, Forureningskala.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item7ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, Info.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }/*else if (ID == item8ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(webViewActivity.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }*/
    }

    //Start of Comments onOptionsItemSelected
    /*
    This method is connected to the DrawerLayout. It checks when you use the menu, which item is selected and then returns the item within the OnNavigationItemSelected() method.
    */
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (mDrawerToggle.onOptionsItemSelected(item)) {

        }

        return super.onOptionsItemSelected(item);
    }

    //Start of Comments updateGPS()
    /*
    This method checks if it has permission to use the device location and if it has permission, then it executes onSuccess which set latitude and longitude.
    It then calls the method LoadMap().
    */
    public void updateGPS () {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //Log.d("USERLOCATION", " New Latitude " + location.getLatitude());
                    //Log.d("USERLOCATION", " New Longitude " + location.getLongitude());
                    Latitude = (float) location.getLatitude();
                    Longitude = (float) location.getLongitude();
                    pointX = Latitude;
                    pointY = Longitude;
                    Log.d("USERLOCATION", " Latitude " + Latitude);
                    Log.d("USERLOCATION", " Longitude " + Longitude);
                    Log.d("USERLOCATION", "UPDATEGPS");
                    UsergeoPoint = new GeoPoint(Latitude, Longitude);
                    LoadMapWebView();
                }
            });
        } else {
            Toast.makeText(webViewActivity.this,"AirLITE skal bruge adgang til din lokation for at fungere.", Toast.LENGTH_LONG).show();
        }
    }

    //Start of Comments LoadMapWebView()
    /*
    What this method does, is simply just load a url into the webview, and then add JavaScript to the webview, so that if the webpage uses JavaScript
    the app will be able to show that.
     */
    private void LoadMapWebView(){
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://gere8016.uni.au.dk:8080/web_app3_leaflet.html");
        //http://lpdv.spatialsuite.dk/spatialmap
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    //Start of Comments onNavigationItemSelected
    /*
    This method contains a switch case that holds different ID's, one for each item in the menu. It has an item as parameter in the method, and then is uses the ID, to check which
    Activity to navigate to when pressed. When finished, it changes to a new Activity regarding which ID is selected, and then leads the user to a new Activity.
    */
    @Override
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

    //Start of Comments findLocation()
    /*
    This method checks if it has permission to use the device location and if it has permission it calls the method updateGPS()
    if the permission is denied then it makes a toast
    */
    public void findLocation () {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            //Log.d("USERLOCATION", "" + Latitude);
            //Log.d("USERLOCATION", "" + Longitude);
            Log.d("USERLOCATION", "FINDLOCATION");
            updateGPS();
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "You need too grant acess to your location", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    //Start of Comments onRequestPermissionsResult()
    /*
    This method calls the method updateGPS() if it has a request code that matches with the specific permission needed
    */
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                findLocation();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //endregion Methods
}