package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.envsaqapp.JavaClasses.PaamindelseNoti;
import com.example.envsaqapp.R;
import com.google.android.material.navigation.NavigationView;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class Notifikationer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private static double userX;
    private static double userY;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button button;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private Integer item8ID;
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
    In the method onClick an alarmManager i created and set to trigger a notification after 10 seconds.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikationer);
        button = findViewById(R.id.notiButton);
        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);

        mDrawerLayout = findViewById(R.id.NotiDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setNavigationViewListener();
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this, "notifikationen virker", Toast.LENGTH_SHORT).show();
                SendNotifikation();
            }
        });


    } //end of onCreate()
    //Start of Comments SendNotifikation()
    /*
    Basically all this method does is send a notification. It makes an intent to go to the PaamindelseNoti to get the information it needs.
    It then gets the ALARM_SERVICE from the android device services, and cast the service to an AlarmManager object. Then it sets the AlarmManager to go off after 1000 milliseconds.
    And then it wakes up the phone with a notification, containing the information it got from PaamindelseNoti.
     */
    public void SendNotifikation() {
        Intent i = new Intent(Notifikationer.this, PaamindelseNoti.class);
        PendingIntent pendingintent = PendingIntent.getBroadcast(Notifikationer.this, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long currenttime = System.currentTimeMillis();
        long timeout = 1000 * 0;
        alarmManager.set(AlarmManager.RTC_WAKEUP, currenttime + timeout, pendingintent);
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
                    Intent i = new Intent(Notifikationer.this, ForureningHer.class);
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
                    Intent i = new Intent(Notifikationer.this, MainActivity.class);
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
                    Intent i = new Intent(Notifikationer.this, NavigationUdsigt.class);
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
                    Intent i = new Intent(Notifikationer.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(Notifikationer.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Notifikationer.this, Notifikationer.class);
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
                    Intent i = new Intent(Notifikationer.this, Forureningskala.class);
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
                    Intent i = new Intent(Notifikationer.this, Info.class);
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
                    Intent i = new Intent(Notifikationer.this, webViewActivity.class);
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
    //Start of Comments setNavigationViewListener
    /*
    This method finds the NavigationView with the findViewById() method, and then adds a listener to the navigationView that checks if an item in the list has been pressed or not.
    If an item has been pressed, it sets the value to 'true', so the method onNavigationItemSelected() knows it should execute.
    */
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.NotiNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    //endregion Methods
}