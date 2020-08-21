package com.example.envsaqapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ForureningsUdsigt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static double userX;
    private static double userY;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forurenings_udsigt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setNavigationViewListener();
        mDrawerLayout = findViewById(R.id.ForUdsigtDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);
    }


    public void ChangeActivity(Integer ID){
        if (ID == item1ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, ForureningHer.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, MainActivity.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, ForureningsUdsigt.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, Info.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, GroenRute.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, Notifikationer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
    }


    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){

        }

        return super.onOptionsItemSelected(item);
        }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item1ID = item.getItemId();
                ChangeActivity(item1ID);
                return true;
            case R.id.item2:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item2ID = item.getItemId();
                ChangeActivity(item2ID);
                return true;
            case R.id.item3:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item3ID = item.getItemId();
                ChangeActivity(item3ID);
                return true;
            case R.id.item4:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item4ID = item.getItemId();
                ChangeActivity(item4ID);
                return true;
            case R.id.item5:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item5ID = item.getItemId();
                //ChangeActivity(item5ID);
                return true;
            case R.id.item6:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item6ID = item.getItemId();
                //ChangeActivity(item6ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.ForUdsigtNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}