package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.example.envsaqapp.GeoServer.GeoserverTileSource;
import com.example.envsaqapp.JavaClasses.ListViewAdapter;
import com.example.envsaqapp.JavaClasses.NotificationService;
import com.example.envsaqapp.R;
import com.example.envsaqapp.GeoServer.TileProviderFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.TilesOverlay;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.wms.WMSEndpoint;
import org.osmdroid.wms.WMSLayer;
import org.osmdroid.wms.WMSParser;
import org.osmdroid.wms.WMSTileSource;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import Models.Data;
import REST.ApiUtils;
import REST.DataService;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.PendingIntent.getActivity;
import static android.net.sip.SipErrorCode.TIME_OUT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private MapView mapView;
    private RotationGestureOverlay rotationGestureOverlay;
    private MapController mapController;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    public float Latitude;
    public float Longitude;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private Point point;
    private FusedLocationProviderClient fusedLocationProviderClient;
    public float pointX;
    public float pointY;
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
    private SearchView searchView;
    private HttpUrl url;
    private SimpleMarkerSymbol symbol;
    private String MarkerTitle;
    private Switch mainNo2Switch;
    private Switch mainPm10Switch;
    private Switch mainPm25Switch;
    private GeoPoint UsergeoPoint;
    ArrayList<Data> arraylist = new ArrayList<Data>();
    private ListView list;
    private ListViewAdapter adapter;
    private String[] DataList;
    public static ArrayList<Data> addressArrayList;
    public static ArrayList<Data> array_sort;
    int textlength = 0;
    private androidx.cursoradapter.widget.CursorAdapter mAdapter;
    ArrayList<String> addressSuggestions = new ArrayList<>();
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
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchView = findViewById(R.id.MainsearchView);
        searchView.bringToFront();
        searchView.setIconified(false);
        searchView.clearFocus();
        mainNo2Switch = findViewById(R.id.MainNO2Switch);
        mainPm10Switch = findViewById(R.id.MainPm10Swtich);
        mainPm25Switch = findViewById(R.id.MainPm25Switch);
        mainPm10Switch.bringToFront();
        mainPm25Switch.bringToFront();
        mainNo2Switch.bringToFront();
        mapView = findViewById(R.id.MainMapView);
        mapView.setUseDataConnection(true);
        setCloseSearchIcon(searchView);
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
        navigationView = (NavigationView) findViewById(R.id.MainNav_view);
        setNavigationViewListener();
        notifikationskanal();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchForAddress();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //getSuggestions(newText);
                //String text = newText;
                //adapter.filter(text);
                return false;
            }
        });
        final String[] from = new String[]{"address"};
        final int[] to = new int[]{android.R.id.text1};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listView);
        list.bringToFront();
        addressArrayList = new ArrayList<>();

        for (int i = 0; i < addressSuggestions.size(); i++) {
            Data address = new Data(addressSuggestions.get(i));
            // Binds all strings into an array
            addressArrayList.add(address);
        }

        adapter = new ListViewAdapter(this);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, addressArrayList.get(i).getAddress(), Toast.LENGTH_SHORT).show();


            }
        });


        Log.d("Rhino", "onCreate: " + runScript(this));
    }//End of OnCreate

    /*
    This method sets the closing icon for the searchView to ic_clear_icon_white
    and if it doesn't exist or it can't get access then it throws an appropriate exception.
     */
    private void setCloseSearchIcon(SearchView searchView) {
        try {
            Field searchField = SearchView.class.getDeclaredField("mCloseButton");
            searchField.setAccessible(true);
            ImageView closeBtn = (ImageView) searchField.get(searchView);
            closeBtn.setImageResource(R.drawable.ic_clear_icon_white);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Start of Comments PlotNewDot()
        /*
        This method plots a marker for the address typed in the seachbar on the map.
        The marker in this method is set to a different color than then user location, so you can see the difference between the user location, and the searched location.
        When the searched location is found by latitude and longitude, it then reloads the map by usage of the LoadMap() method.
        */
    private void PlotNewDot(GeoPoint geoPoint, GeoPoint oldGeopoint) {
        //Overlay graphicsOverlay = new Overlay();
        //mapView.getGraphicsOverlays().add(graphicsOverlay);
        Log.d("GList", mapView.getOverlays().toString());
        if (mapView.getOverlays().size() >= 2) {
            /*
            mapView.getOverlays().remove(mapView.getGraphicsOverlays().size() -1);
            graphicsOverlay.getGraphics().add(newgraphic);
            mapView.getGraphicsOverlays().add(graphicsOverlay);
            Toast.makeText(MainActivity.this,"Graphics deleted and added",Toast.LENGTH_LONG).show();
            LoadMap(Latitude, Longitude);*/
            //mapView.getOverlays().remove(oldGeopoint);
            mapView.getOverlays().clear();
            //mapView.getOverlays().get(mapView.getOverlays().size()-1);
            Marker startMarker = new Marker(mapView);
            startMarker.setPosition(geoPoint);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setIcon(getResources().getDrawable(R.drawable.ic_icon_place_blackgray_36dp));
            startMarker.setTitle(MarkerTitle);
            startMarker.showInfoWindow();
            mapView.getOverlays().add(startMarker);
            updateGPS();
            mapController.setCenter(geoPoint);
            //Toast.makeText(MainActivity.this,"Graphics added and deleted",Toast.LENGTH_LONG).show();
        } else {
            Marker startMarker = new Marker(mapView);
            startMarker.setPosition(geoPoint);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setIcon(getResources().getDrawable(R.drawable.ic_icon_place_blackgray_36dp));
            startMarker.setTitle(MarkerTitle);
            startMarker.showInfoWindow();
            mapView.getOverlays().add(startMarker);
            //Toast.makeText(MainActivity.this,"Graphics added",Toast.LENGTH_LONG).show();
        }
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
        rotationGestureOverlay = new RotationGestureOverlay(mapView);
        rotationGestureOverlay.setEnabled(true);
        mapView.setMultiTouchControls(true);
        mapView.getOverlays().add(this.rotationGestureOverlay);
        mapController.setZoom(13);
        mapController.setCenter(gPt);
        //addMarkerUserLocation(gPt);
    }



    /*
        private void ParseAndShowLayerWMS()
        {
            Toast.makeText(MainActivity.this, "svin", Toast.LENGTH_LONG).show();
            Log.d("ShowThis", "hej");

            try {
                c = (HttpURLConnection) new URL("http://10.28.0.241:8088/geoserver/cite/wms?service=WMS&version=1.1.0&request=GetMap&layers=cite%3Alpdv2k12_" +
                        "kbh_no2&bbox=12.4530162811279%2C55.6159400939941%2C12.6479949951172%2C55.7321701049805&width=768&height=457&srs=EPSG%3A4326&format=appl" +
                        "ication/openlayers").openConnection();
                is = c.getInputStream();
                wmsEndpoint = WMSParser.parse(is);
                Log.d("ShowThis", wmsEndpoint.getLayers().toString());
                Toast.makeText(MainActivity.this, "svin", Toast.LENGTH_LONG).show();
                is.close();
                c.disconnect();

        } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "svin1", Toast.LENGTH_LONG).show();
                Log.d("ShowThis", "hej1");

                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "svin2", Toast.LENGTH_LONG).show();
                Log.d("ShowThis", "hej2");

                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "svin3", Toast.LENGTH_LONG).show();
                Log.d("ShowThis", e.getMessage() + e.getCause() + e.getLocalizedMessage() + e.toString());

                e.printStackTrace();
            }

        }
            private void ShowSelectedLayer() {
            Log.d("ShowThis", wmsEndpoint.getLayers().toString());
                WMSTileSource source = WMSTileSource.createFrom(wmsEndpoint, wmsEndpoint.getLayers().get(0));
                WMSLayer layer = new WMSLayer();
                if (layer.getBbox() != null) {
                    //center map on this location
                    mapView.zoomToBoundingBox(layer.getBbox(), true);
                }
                mapView.setTileSource(source);
            }*/
    //Start of Comments addMarkerUserLocation()
    /*
     The method takes the latitude and longitude of the user location, and then adds a SimpleMarkerSymbol displaying a blue dot.
     It is added as a GraphicsOverlay to the map, and then the map is reloaded using the LoadMap() method.
    */
    public void addMarkerUserLocation(GeoPoint center) {

        Marker marker = new Marker(mapView);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        //marker.setDefaultIcon();
        marker.setIcon(getResources().getDrawable(R.drawable.ic_icon_person_pin_circle_blackgray_36dp));
        //mapView.getOverlays().clear();
        mapView.getOverlays().add(marker);
        mapView.invalidate();
        marker.setTitle("Din lokation");
    }

    //Start of Comments updateGPS()
    /*
    This method checks if it has permission to use the device location and if it has permission, then it executes onSuccess which set latitude and longitude.
    It then calls the method LoadMap().
    */
    public void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Log.d("USERLOCATION", " New Latitude " + location.getLatitude());
                    Log.d("USERLOCATION", " New Longitude " + location.getLongitude());
                    Latitude = (float) location.getLatitude();
                    Longitude = (float) location.getLongitude();
                    Log.d("USERLOCATION", " Old Latitude " + Latitude);
                    Log.d("USERLOCATION", " Old Longitude " + Longitude);
                    UsergeoPoint = new GeoPoint(Latitude, Longitude);
                    LoadMap(UsergeoPoint);
                    addMarkerUserLocation(UsergeoPoint);
                }
            });
        } else {

        }
    }

    //Start of Comments findLocation()
    /*
    This method checks if it has permission to use the device location and if it has permission it calls the method updateGPS()
    if the permission is denied then it makes a toast
    */
    public void findLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Log.d("USERLOCATION", "" + Latitude);
            Log.d("USERLOCATION", "" + Longitude);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateGPS();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
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
    public void ChangeActivity(Integer ID) {
        if (ID == item1ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, ForureningHer.class);
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
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
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
                    Intent i = new Intent(MainActivity.this, ForureningsUdsigt.class);
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
                    Intent i = new Intent(MainActivity.this, GroenRute.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    //startActivity(i);
                    Toast.makeText(MainActivity.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Notifikationer.class);
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
                    Intent i = new Intent(MainActivity.this, Forureningskala.class);
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
                    Intent i = new Intent(MainActivity.this, Info.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item8ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, webViewActivity.class);
                    i.putExtra("userX", pointX);
                    i.putExtra("userY", pointY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
    }

    //Start of Comments onOptionsItemSelected
    /*
    This method is connected to the DrawerLayout. It checks when you use the menu, which item is selected and then returns the item within the OnNavigationItemSelected() method.
    */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {

        }

        return super.onOptionsItemSelected(item);
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
            /*case R.id.UdsigtItem3:
                item3ID = item.getItemId();
                ChangeActivity(item3ID);
                return true;
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

    //Start of Comments setNavigationViewListener
    /*
    This method finds the NavigationView with the findViewById() method, and then adds a listener to the navigationView that checks if an item in the list has been pressed or not.
    If an item has been pressed, it sets the value to 'true', so the method onNavigationItemSelected() knows it should execute.
    */
    private void setNavigationViewListener() {
        navigationView = findViewById(R.id.MainNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Start of Comments notifikationskanal()
    /*
    It check if the build version is bigger or equal to ECLAIR_0_1, then it create a new notification channel
    with id, name and importance and instanciate it.
    */
    private void notifikationskanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) ;
        {
            CharSequence name = "min paamindelses kanal";
            String description = "kanal til luftforurenings app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ny notifikation", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //Start of comments SearchForAddress()
    /*
    This method is connected to the searchbar of the app. This is the logic for when you search for a specific address in the database.
    It has an URL, that holds the table of the database, and then pt_id, lat, long, street_nam, house_num and no2_street as parameters.
    It connects to the table in the database, to see if the chosen address exists in the table, and then returns the position of the address using
    latitude and longitude to place a marker on the map.
    It also contains some error handling, thats checks if the user has spelled correctly or is simply searching for an address that does not exists in the database.
    If it does not exists, it shows a Toast.makeText("") to the user that displays a message.
    */
    private void SearchForAddress() {
        String[] InputArray = searchView.getQuery().toString().split(" ");
        if (InputArray.length >= 2) {
            String streetName = InputArray[0];
            String houseNumber = InputArray[1];
            url = HttpUrl.parse("http://10.28.0.241:3000/lpdv2k12_kbh_no2?select=lat,long,street_nam,house_num,no2_street,pm10_street,pm25_street,address" +
                    "&street_nam=eq." + streetName + "&house_num=eq." + houseNumber);

            DataService dataService = ApiUtils.getDataService();
            Call<ArrayList<Data>> searchForAddress = dataService.SearchForLocation(url.toString());
            searchForAddress.request().toString().replace("%3d", "=");
            searchForAddress.enqueue(new Callback<ArrayList<Data>>() {
                @Override
                public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                    if (response.isSuccessful()) {
                        Log.d("ADRESSSEARCH", " " + response.code());
                        Log.d("ADRESSSEARCH", response.body().toString());
                        Log.d("ADRESSSEARCH", url.toString());
                        if (response.body().size() >= 1) {
                            Data responseObject1 = response.body().get(0);
                            float searchX = responseObject1.getLatitude();
                            float searchY = responseObject1.getLongitude();
                            String searchAddress = responseObject1.getAddress();
                            double searchNo2 = responseObject1.getNo2_street();
                            double searchPM2_5 = responseObject1.getPM2_5();
                            double searchPM10 = responseObject1.getPM10();
                            MarkerTitle = searchAddress + "\n" + "NO2 koncentration: " + searchNo2 + "\n" + "PM2.5 koncentration: " + searchPM2_5 + "\n" +
                                    "PM10 koncentration: " + searchPM10;
                            GeoPoint searchgPt = new GeoPoint(searchX, searchY);
                            PlotNewDot(searchgPt, UsergeoPoint);
                            LoadMap(searchgPt);
                            searchView.clearFocus();
                            Log.d("RESPONSEOBJECTS", responseObject1.toString());
                        } else {
                            Toast.makeText(MainActivity.this, "Noget gik galt. Har du stavet rigtigt?", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        String message = "Problem " + response.code() + " " + response.message() + " " + response.raw();
                        Toast.makeText(MainActivity.this, "REQUEST NOT SUCCESSFULL", Toast.LENGTH_LONG).show();
                        Log.d("Queue", message);
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "REQUEST FAILED", Toast.LENGTH_LONG).show();
                    Log.d("Queue", t.toString());
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Husk at indtaste et husnummer", Toast.LENGTH_LONG).show();
        }
    }

    public void MainNo2switchClicked(View view) {
        mainPm10Switch.setChecked(false);
        mainPm25Switch.setChecked(false);
        mapView.getOverlays().clear();
        final ITileSource No2DotsOverlay = new XYTileSource("demo", 1, 20, 256, ".png",
                new String[]{
                        "http://10.28.0.241:8088/geoserver/gwc/",
                });
        MapTileProviderBasic provider = new MapTileProviderBasic(getApplicationContext());
        String[] stringArrayBaseUrl = new String[]{"http://10.28.0.241:8088/gwc/service/tms/1.0.0/"};
        GeoserverTileSource source = new GeoserverTileSource("cite:lpdv2k12_kbh_no2", stringArrayBaseUrl, "cite:lpdv2k12_kbh_no2");
        provider.setTileSource(source);
        TilesOverlay tilesOverlay = new TilesOverlay(provider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        mapView.getOverlays().add(tilesOverlay);
        addMarkerUserLocation(UsergeoPoint);
        mapController.animateTo(UsergeoPoint);
    }

    public void MainPm25switchClicked(View view) {
        mainNo2Switch.setChecked(false);
        mainPm10Switch.setChecked(false);
        downloadAndParse();
        /*mapView.getOverlays().clear();
        final ITileSource Pm2_5DotsOverlay = new XYTileSource("cite:lpdv2k12_kbh_no2", 1, 20, 256, ".png",
                new String[]{
                        "http://10.28.0.241:8088/geoserver/",
                });
        MapTileProviderBasic provider = new MapTileProviderBasic(getApplicationContext());
        provider.setTileSource(Pm2_5DotsOverlay);
        TilesOverlay tilesOverlay = new TilesOverlay(provider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        mapView.getOverlays().add(tilesOverlay);*/
        addMarkerUserLocation(UsergeoPoint);
        mapController.animateTo(UsergeoPoint);

    }

    public void MainPm10switchClicked(View view) {
        mainNo2Switch.setChecked(false);
        mainPm25Switch.setChecked(false);
        mapView.getOverlays().clear();
        final ITileSource Pm10DotsOverlay = new XYTileSource("OSMPublicTransport", 1, 20, 256, ".png",
                new String[]{
                        "http://openptmap.org/tiles/",
                });
        MapTileProviderBasic provider = new MapTileProviderBasic(getApplicationContext());
        provider.setTileSource(Pm10DotsOverlay);
        TilesOverlay tilesOverlay = new TilesOverlay(provider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        mapView.getOverlays().add(tilesOverlay);
        addMarkerUserLocation(UsergeoPoint);
        mapController.animateTo(UsergeoPoint);
    }


    //region Stuff That Did Not Work

    HttpURLConnection c = null;
    InputStream is = null;
    WMSEndpoint wmsEndpoint = null;
    WMSEndpoint cap;

    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
        for (int i = 0; i < addressSuggestions.size(); i++) {
            if (addressSuggestions.get(i).toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[]{i, addressSuggestions.get(i)});
        }
        mAdapter.changeCursor(c);
    }

    private void getSuggestions(String text) {
        url = HttpUrl.parse("http://10.28.0.241:3000/lpdv2k12_kbh_no2?select=address,kommune" +
                "&address%like%.eq%" + text + "%");
        DataService dataService = ApiUtils.getDataService();
        Call<ArrayList<Data>> getSuggestion = dataService.SearchForLocation(url.toString());
        getSuggestion.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if (response.isSuccessful()) {
                    Log.d("ADRESSSEARCH", " " + response.code());
                    Log.d("ADRESSSEARCH", response.body().toString());
                    Log.d("ADRESSSEARCH", url.toString());
                    Log.d("ADRESSSEARCH", response.body().get(0).getAddress());
                    if (response.body().size() >= 1) {
                        for (Data o : response.body()
                        ) {
                            String searchAddress = o.getAddress();
                            String searchKommune = o.getKommune();
                            String addressandKommune = searchAddress + ", " + searchKommune;
                            addressSuggestions.add(addressandKommune);
                            Log.d("REEEEEEEEEEEE", addressSuggestions.toString());
                        }

                        searchView.clearFocus();
                        //Log.d("RESPONSEOBJECTS", o.toString());
                    } else {
                        Toast.makeText(MainActivity.this, "Noget gik galt. Har du stavet rigtigt?", Toast.LENGTH_LONG).show();
                    }

                } else {
                    String message = "Problem " + response.code() + " " + response.message() + " " + response.raw();
                    Toast.makeText(MainActivity.this, "REQUEST NOT SUCCESSFULL", Toast.LENGTH_LONG).show();
                    Log.d("Queue", message);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        startService(new Intent(this, NotificationService.class));
        super.onDestroy();
    }

    protected String getDefaultUrl() {
        //"http://192.168.1.1:8080/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities"
        //return "http://localhost:8080/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities";
        return "http://10.28.0.241:8088/geoserver/gwc/demo/cite:lpdv2k12_kbh_no2?gridSet=EPSG:4326&format=image/png";
    }

    private void downloadAndParse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ok = false;
                Exception root = null;
                try {
                    Log.d("WMSFejl", "HttpURLConnection null");
                    HttpURLConnection c = null;
                    InputStream is = null;
                    try {
                        Log.d("WMSFejl", "open connection");
                        promptUserForLayerSelection();
                        c = (HttpURLConnection) new URL(getDefaultUrl()).openConnection();
                        is = c.getInputStream();
                        cap = WMSParser.parse(is);

                        ok = true;

                    } catch (Exception ex) {
                        Log.d("WMSFejl", ex.getMessage());
                        ex.printStackTrace();
                        root = ex;
                    } finally {
                        if (is != null) try {
                            Log.d("WMSFejl", "input stream closed");
                            is.close();
                        } catch (Exception ex) {
                            Log.d("WMSFejl", ex.getMessage());
                        }
                        if (c != null)
                            try {
                                Log.d("WMSFejl", "disconnected");
                                c.disconnect();
                            } catch (Exception ex) {
                                Log.d("WMSFejl", ex.getMessage());
                            }
                    }


                } catch (Exception ex) {
                    root = ex;
                    ex.printStackTrace();
                    Log.d("WMSFejl", ex.getMessage());
                }
                if (ok) {
                    Log.d("WMSFejl", "promptUserForLayerSelection()");
                    promptUserForLayerSelection();
                } else {
                    showErrorMessage(root);
                }
            }


        }).start();
    }

    private void promptUserForLayerSelection() {
        //Toast.makeText(MainActivity.this, "this method promptUserForLayerSelection is running", Toast.LENGTH_LONG).show();
        Log.d("WMSFejl", "this method promptUserForLayerSelection is running");
        for (WMSLayer layer : cap.getLayers()) {
            WMSTileSource source = WMSTileSource.createFrom(cap, layer);

            mapView.setTileSource(source);

        }
    }

    private void showErrorMessage(final Exception root) {
        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
    }

    public static String runScript(Context androidContextObject) {
        try {
            Resources resource = androidContextObject.getResources();
            InputStream rawResource = resource.openRawResource(R.raw.config);


            Properties properties = new Properties();
            properties.load(rawResource);

            String source = properties.getProperty("jsExecutes");
            String functionName = "layers";
            Object[] functionParams = new Object[]{"cool"};
            org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();

            rhino.setOptimizationLevel(-1);

            Scriptable scope = rhino.initStandardObjects();

            rhino.evaluateString(scope, source, "JavaScript", 1, null);
            Object object = scope.get(functionName, scope);

            if (object instanceof Function) {
                Function function = (Function) object;
                Object result = function.call(rhino, scope, scope, functionParams);

                String response = org.mozilla.javascript.Context.toString(result);
                return response;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            org.mozilla.javascript.Context.exit();

        }
        return null;
    }

    private void setUpMap() {
        // TODO Auto-generated method stub
        TileProvider geoServerTileProvider = TileProviderFactory
                .getGeoServerTileProvider();
        /*TileOverlay geoServerTileOverlay = mapView.set(
                new TileOverlayOptions()
                        .tileProvider(geoServerTileProvider)
                        .zIndex(10000)
                        .visible(true));
*/
    }

    //endregion Stuff That Did Not Work
}