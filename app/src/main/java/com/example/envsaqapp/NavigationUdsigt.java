package com.example.envsaqapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class NavigationUdsigt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static float userX;
    private static float userY;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_udsigt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDrawerLayout = findViewById(R.id.NavDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
    }

    private void setNavigationViewListener() {
        navigationView = findViewById(R.id.NavNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {

        }

        return super.onOptionsItemSelected(item);
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}