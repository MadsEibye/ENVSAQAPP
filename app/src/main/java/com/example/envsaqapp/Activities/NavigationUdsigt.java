package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.envsaqapp.R;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.util.GeoPoint;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class NavigationUdsigt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region instance fields
    private static double userX, userY;
    private Integer item1ID, item2ID, item3ID, item4ID, item5ID, item6ID, item7ID, item8ID;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private static String componentExtra;
    private double X_UTM, Y_UTM;
    private int UTM_Zone, regionnumber;
    private char UTM_Letter;
    private Switch modeswitch;
    private boolean darkmode;
    private ViewFlipper viewFlipper;
    private String No2 = "No2", O3 = "O3", PM25 = "PM2_5", PM10 = "PM10";
    //endregion

    //region methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_udsigt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDrawerLayout = findViewById(R.id.NavDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        LinearLayout firstlayout = findViewById(R.id.firstlayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
        viewFlipper = findViewById(R.id.viewflipper);
        modeswitch = findViewById(R.id.modeSwtich);
        modeswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!darkmode) {
                    modeswitch.setThumbResource(R.drawable.ic_sunny);
                    viewFlipper.showNext();
                    firstlayout.setBackgroundColor(getResources().getColor(R.color.Gray));
                    darkmode = true;
                } else {
                    modeswitch.setThumbResource(R.drawable.ic_moon);
                    viewFlipper.showNext();
                    firstlayout.setBackgroundColor(getResources().getColor(R.color.Lightblue));
                    darkmode = false;
                }
            }
        });
        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);
        X_UTM = intent.getDoubleExtra("X_UTM", X_UTM);
        Y_UTM = intent.getDoubleExtra("Y_UTM", Y_UTM);
        UTM_Zone = intent.getIntExtra("UTM_Zone", UTM_Zone);
        UTM_Letter = intent.getCharExtra("UTM_Letter", UTM_Letter);

        Log.d("UTMCOORDINATES", " " + X_UTM + ", " + Y_UTM + " | " + UTM_Zone + UTM_Letter);
        //getRegion(userY, userX);
        Log.d("USERLOCATION", "NAV: " + userX + "" + userY);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {

        }

        return super.onOptionsItemSelected(item);
    }

    private void setNavigationViewListener() {
        navigationView = findViewById(R.id.NavNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void ChangeActivity(Integer ID) {
        if (ID == item1ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(NavigationUdsigt.this, ForureningHer.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, MainActivity.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, NavigationUdsigt.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(NavigationUdsigt.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(NavigationUdsigt.this, Notifikationer.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, Forureningskala.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, Info.class);
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
                    Intent i = new Intent(NavigationUdsigt.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
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
                return true;  /*
            case R.id.UdsigtItem3:
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

    public void UdsigtNo2(View view) {
        Intent i = new Intent(NavigationUdsigt.this, ForureningsUdsigt.class);
        i.putExtra("userX", userX);
        i.putExtra("userY", userY);
        i.putExtra("region", regionnumber);
        i.putExtra("componentExtra", No2);
        i.putExtra("darkmode", darkmode);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void UdsigtO3(View view) {
        Intent i = new Intent(NavigationUdsigt.this, ForureningsUdsigt.class);
        i.putExtra("userX", userX);
        i.putExtra("userY", userY);
        i.putExtra("region", regionnumber);
        i.putExtra("componentExtra", O3);
        i.putExtra("darkmode", darkmode);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void Udsigtpm25(View view) {
        Intent i = new Intent(NavigationUdsigt.this, ForureningsUdsigt.class);
        i.putExtra("userX", userX);
        i.putExtra("userY", userY);
        i.putExtra("region", regionnumber);
        i.putExtra("componentExtra", PM25);
        i.putExtra("darkmode", darkmode);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void Udsigtpm10(View view) {
        Intent i = new Intent(NavigationUdsigt.this, ForureningsUdsigt.class);
        i.putExtra("userX", userX);
        i.putExtra("userY", userY);
        i.putExtra("region", regionnumber);
        i.putExtra("componentExtra", PM10);
        i.putExtra("darkmode", darkmode);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    //endregion

    //region TingTilHorisontalopdeling

    /*regionnumbers | 1 - Sjælland | 2 - Fyn | 3 - Sønderjylland | 4 - Midtjylland | 5 - Nordjylland | 6 - Bornholm |
    //lon <  && lon > && lat < && lat >
    private String regionString;
    GeoPoint geoPointVestJylland = new GeoPoint(55.561068, 8.072119);
    GeoPoint geoPointØstJylland = new GeoPoint(55.466399, 10.802307);

    private void getRegion(double lon, double lat) {
        if (lon <= 12.823929 && lon >= 10.947876 && lat <= 56.134281 && lat >= 54.554544) {
            regionnumber = 1;
            regionString = "Sjælland";

        } else if (lon < 10.947876 && lon > 9.700178 && lat < 55.647727 && lat > 54.708756) {
            regionnumber = 2;
            regionString = "Fyn";
        } else if (lon <= 9.700178 && lon >= 8.065962 && lat <= 55.783032 && lat >= 54.796079) {
            regionnumber = 3;
            regionString = "Sønderjylland";
        } else if (lon < 10.972346 && lon > 8.065962 && lat < 56.549574 && lat > 54.796079 || lon < 11.668604 && lon > 11.501063 && lat < 56.740811 && lat > 56.683083) {
            regionnumber = 4;
            regionString = "Midtjylland";
        } else if (lon <= 10.596563 && lon >= 8.031006 && lat <= 57.759402 && lat >= 56.549574 || lon < 11.208552 && lon > 10.851496 && lat < 57.365995 && lat > 57.192291) {
            regionnumber = 5;
            regionString = "Nordjylland";
        } else if (lon < 15.181857 && lon > 14.660007 && lat < 55.309965 && lat > 54.971022) {
            regionnumber = 1;
            regionString = "Bornholm";
        }
        Log.d("regionNumber", +regionnumber + " " + regionString);
    }

     */
    //endregion
}