package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.envsaqapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import Models.Data;
import REST.ApiUtils;
import REST.DataService;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ForureningHer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private static float userX;
    private static float userY;
    private TextView forureningHerTextViewAddress;
    private TextView forureningHerTextViewNo2;
    private TextView forureningHerTextViewPM10;
    private TextView forureningHerTextViewPM25;
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
    private String address;
    public double no2;
    public double pm2_5;
    public double pm10;
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
    We then set a navigationViewListener to the navigationview, to check for when an item is pressed.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forurening_her);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        userX = intent.getFloatExtra("userX", userX);
        userY = intent.getFloatExtra("userY", userY);
        mDrawerLayout = findViewById(R.id.ForHerDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setNavigationViewListener();
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        searchForLocation();


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
                    Intent i = new Intent(ForureningHer.this, ForureningHer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        } else if (ID == item2ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, MainActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item3ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, ForureningsUdsigt.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item4ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(ForureningHer.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, Notifikationer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, Forureningskala.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item7ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningHer.this, Info.class);
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
                    Intent i = new Intent(ForureningHer.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
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
        NavigationView navigationView = (NavigationView) findViewById(R.id.ForHerNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Start of comments searchForLocation()
    /*
    This is the method that gets the nearest location, and then shows the address and house number.It then gets the values of NO2, PM10 and PM2.5 and displays it.
    It searches for the nearest address next to the already given location, and then gives the user that location.
    The error handling in this method checks if the request is valid, and if it is not, it gives an error message "REQUEST NOT SUCCESFUL".

    */

    public void searchForLocation() {
        HttpUrl url = HttpUrl.parse("http://10.28.0.241:3000/rpc/get_nearest_house?x_long=" + userX + "&y_lat=" + userY);
        DataService dataService = ApiUtils.getDataService();
        Call<ArrayList<Data>> queueSong = dataService.SearchForLocation(url.toString());
        queueSong.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if (response.isSuccessful()) {
                    Log.d("QUERY", " " + response.code());
                    Log.d("QUERY", response.body().toString());
                    Log.d("QUERY", url.toString());
                    Data responseObject1 = response.body().get(0);
                    address = responseObject1.getStreet_nam() + " " + responseObject1.getHouse_num();
                    no2 = responseObject1.getNo2_street();
                    pm2_5 = responseObject1.getPM2_5();
                    pm10 = responseObject1.getPM10();
                    Log.d("RESPONSEOBJECTS", responseObject1.toString());
                    PopulateTextView();
                    //Toast.makeText(ForureningHer.this, "REQUEST SUCCESSFULL" + response.body().toString(), Toast.LENGTH_LONG).show();
                    //Log.d("TESTING", SongsInQueue.toString());


                } else {
                    String message = "Problem " + response.code() + " " + response.message() + " " + response.raw();
                    Toast.makeText(ForureningHer.this, "REQUEST NOT SUCCESSFULL", Toast.LENGTH_LONG).show();
                    Log.d("Queue", message);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                Toast.makeText(ForureningHer.this, "REQUEST FAILED", Toast.LENGTH_LONG).show();
                Log.d("Queue", t.toString());
            }
        });
    }

    // Start of comments PopulateTextView()
    /*
    In this method we added some logic for the text colour. It is some simple if-statements, that checks the value of NO2, PM10 and PM2.5, and displays either
    red, green or yellow regarding to the value.
     */
    private void PopulateTextView() {
        forureningHerTextViewAddress = findViewById(R.id.ForureningHerTextViewAddress);
        forureningHerTextViewAddress.setText(address);

        if (no2 >= 50) {
            forureningHerTextViewNo2 = findViewById(R.id.ForureningHerTextViewNo2);
            forureningHerTextViewNo2.setText("" + no2);
            forureningHerTextViewNo2.setTextColor(Color.RED);

        }
        if (no2 < 50 && no2 >= 30) {
            forureningHerTextViewNo2 = findViewById(R.id.ForureningHerTextViewNo2);
            forureningHerTextViewNo2.setText("" + no2);
            forureningHerTextViewNo2.setTextColor(Color.YELLOW);

        }
        if (no2 < 30) {
            forureningHerTextViewNo2 = findViewById(R.id.ForureningHerTextViewNo2);
            forureningHerTextViewNo2.setText("" + no2);
            forureningHerTextViewNo2.setTextColor(Color.GREEN);

        }


        if (pm2_5 >= 15) {
            forureningHerTextViewPM25 = findViewById(R.id.ForureningHerTextViewPM2_5);
            forureningHerTextViewPM25.setText("" + pm2_5);
            forureningHerTextViewPM25.setTextColor(Color.RED);

        }
        if (pm2_5 < 15 && pm2_5 >= 5) {
            forureningHerTextViewPM25 = findViewById(R.id.ForureningHerTextViewPM2_5);
            forureningHerTextViewPM25.setText("" + pm2_5);
            forureningHerTextViewPM25.setTextColor(Color.YELLOW);

        }
        if (pm2_5 < 5) {
            forureningHerTextViewPM25 = findViewById(R.id.ForureningHerTextViewPM2_5);
            forureningHerTextViewPM25.setText("" + pm2_5);
            forureningHerTextViewPM25.setTextColor(Color.GREEN);

        }


        if (pm10 >= 30) {
            forureningHerTextViewPM10 = findViewById(R.id.ForureningHerTextViewPM10);
            forureningHerTextViewPM10.setText("" + pm10);
            forureningHerTextViewPM10.setTextColor(Color.RED);

        }

        if (pm10 < 30 && pm10 >= 10) {
            forureningHerTextViewPM10 = findViewById(R.id.ForureningHerTextViewPM10);
            forureningHerTextViewPM10.setText("" + pm10);
            forureningHerTextViewPM10.setTextColor(Color.YELLOW);

        }

        if (pm10 < 10) {
            forureningHerTextViewPM10 = findViewById(R.id.ForureningHerTextViewPM10);
            forureningHerTextViewPM10.setText("" + pm10);
            forureningHerTextViewPM10.setTextColor(Color.GREEN);

        }
    }
//endregion Methods
}



