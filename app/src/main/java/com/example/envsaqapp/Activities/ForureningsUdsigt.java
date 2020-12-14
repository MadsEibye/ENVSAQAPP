package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.envsaqapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Models.Data;
import Models.ForureningsDataModel;
import REST.ApiUtils;
import REST.DataService;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ForureningsUdsigt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private double userX;
    private double userY;
    private int regionNumber;
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
    LineChart linechart1;
    LineChart linechart2;
    LineChart linechart3;
    LineDataSet set1,set2;
    private String component;
    private double geoX;
    private double geoY;
    private String geoString;
    TextView locationHeader;
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
        setContentView(R.layout.activity_forurenings_udsigt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setNavigationViewListener();
        mDrawerLayout = findViewById(R.id.ForUdsigtDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        linechart1 = findViewById(R.id.LineChart);
        linechart2 = findViewById(R.id.LineChart2);
        linechart3 = findViewById(R.id.LineChart3);
        Button button1 = findViewById(R.id.Udsigtbutton1);
        Button button2 = findViewById(R.id.Udsigtbutton2);
        Button button3 = findViewById(R.id.Udsigtbutton3);
        button1.bringToFront();
        button2.bringToFront();
        button3.bringToFront();
        locationHeader = findViewById(R.id.LocationHeader);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX",userX);
        userY = intent.getDoubleExtra("userY",userY);
        Log.d("USERLOCATION","FU: " + userX + ", " + userY);
        regionNumber = intent.getIntExtra("region",regionNumber);
        component = intent.getStringExtra("componentExtra");
        Toast.makeText(ForureningsUdsigt.this,component,Toast.LENGTH_LONG).show();
        geoString = "Your location";
        GetData(component,"",userX,userY,geoString);
        //GetDataForCharts(component);
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
                    Intent i = new Intent(ForureningsUdsigt.this, NavigationUdsigt.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(ForureningsUdsigt.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item5ID) {
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
        else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, Forureningskala.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, Info.class);
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
                    Intent i = new Intent(ForureningsUdsigt.this, webViewActivity.class);
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
        if (mDrawerToggle.onOptionsItemSelected(item)){

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
        NavigationView navigationView = (NavigationView) findViewById(R.id.ForUdsigtNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private String DbName;
    private void GetDataForCharts(String component){
        if (component == "No2"){
            if (regionNumber == 1){
                DbName = "Sjaelland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 2){
                DbName = "Fyn_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 3){
                DbName = "Sonderjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 4){
                DbName = "Midtjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 5){
                DbName = "Nordjylland_udsigt";
                //GetData(component,DbName);
            }
        }
        else if (component == "O3"){
            if (regionNumber == 1){
                DbName = "Sjaelland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 2){
                DbName = "Fyn_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 3){
                DbName = "Sonderjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 4){
                DbName = "Midtjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 5){
                DbName = "Nordjylland_udsigt";
                //GetData(component,DbName);
            }
        }
        else if (component == "PM25"){
            if (regionNumber == 1){
                DbName = "Sjaelland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 2){
                DbName = "Fyn_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 3){
                DbName = "Sonderjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 4){
                DbName = "Midtjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 5){
                DbName = "Nordjylland_udsigt";
                //GetData(component,DbName);
            }
        }
        else if (component == "PM10"){
            if (regionNumber == 1){
                DbName = "Sjaelland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 2){
                DbName = "Fyn_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 3){
                DbName = "Sonderjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 4){
                DbName = "Midtjylland_udsigt";
                //GetData(component,DbName);
            }
            if (regionNumber == 5){
                DbName = "Nordjylland_udsigt";
                //GetData(component,DbName);
            }
        }

    }

    private Integer i;
    private Integer j;
    private Integer x_utm;
    private Integer y_utm;
    private double No2;
    private double O3;
    private double PM2_5;
    private double PM10;
    private Integer Hour;
    private Integer Day;
    //private ArrayList<ForureningsDataModel> dataList;


    public ArrayList<ForureningsDataModel> GetData(String component, String DbName, double userX, double userY, String name){
        HttpUrl url = HttpUrl.parse("http://10.28.0.241:3000/rpc/get_nearest_grid?x_long=" + userY + "&y_lat=" + userX);
        DataService dataService = ApiUtils.getDataService();
        Call<ArrayList<ForureningsDataModel>> searchForData = dataService.GetForureningsData(url.toString());
        searchForData.enqueue(new Callback<ArrayList<ForureningsDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ForureningsDataModel>> call, Response<ArrayList<ForureningsDataModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() >= 1) {
                        Log.d("QUERY", " " + response.code());
                        Log.d("QUERY", response.body().toString());
                        Log.d("QUERY", url.toString());
                        ForureningsDataModel responseObject1 = response.body().get(0);
                        dataList = response.body();
                        No2 = responseObject1.getNo2();
                        O3 = responseObject1.getO3();
                        PM2_5 = responseObject1.getPM2_5();
                        PM10 = responseObject1.getPM10();
                        locationHeader.setText(name);
                        Log.d("RESPONSEOBJECTS", responseObject1.toString());
                        //Toast.makeText(ForureningHer.this, "REQUEST SUCCESSFULL" + response.body().toString(), Toast.LENGTH_LONG).show();
                        //Log.d("TESTING", SongsInQueue.toString());
                        PopulateCharts();
                    }
                    else {
                        Toast.makeText(ForureningsUdsigt.this,"Fandt ikke noget data",Toast.LENGTH_LONG).show();
                    }
                } else {
                    String message = "Problem " + response.code() + " " + response.message() + " " + response.raw();
                    Toast.makeText(ForureningsUdsigt.this, "REQUEST NOT SUCCESSFULL", Toast.LENGTH_LONG).show();
                    Log.d("Queue", message);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ForureningsDataModel>> call, Throwable t) {
                Toast.makeText(ForureningsUdsigt.this, "REQUEST FAILED", Toast.LENGTH_LONG).show();
                Log.d("Queue", t.toString());
            }
        });
        return dataList;
    }

    ArrayList<ForureningsDataModel> dataList = new ArrayList<ForureningsDataModel>();



    ArrayList dag1data;
    ArrayList dag2data;
    ArrayList dag3data;
    List dag1 = new ArrayList<ForureningsDataModel>();
    List dag2 = new ArrayList<ForureningsDataModel>();
    List dag3 = new ArrayList<ForureningsDataModel>();

    private void PopulateCharts() {


        Integer i = 1;
        for (ForureningsDataModel o:dataList
             ) {
            o.setHour(i);
            i++;
        }

        dag1 = dataList.subList(0,24);
        dag1data = new ArrayList<ForureningsDataModel>(dag1);
        dag2 = dataList.subList(24,48);
        dag2data = new ArrayList<ForureningsDataModel>(dag2);
        dag3 = dataList.subList(48,72);
        dag3data = new ArrayList<ForureningsDataModel>(dag3);

        CustomizeLinechart(linechart1,component,"16/11/2020",dag1data);
        CustomizeLinechart(linechart2,component,"17/11/2020",dag2data);
        CustomizeLinechart(linechart3,component,"18/11/2020",dag3data);
    }



    private void CustomizeLinechart(LineChart linechart,String component,String Date,ArrayList linedata){
        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(linedata),component);
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);
        linechart.getDescription().setText(Date);
        linechart.getDescription().setTextSize(12);
        linechart.getDescription().setYOffset(-25);
        linechart.getDescription().setTextColor(Color.GRAY);
        linechart.animateX(1000);
        LineData lineData = new LineData(iLineDataSets);
        linechart.setData(lineData);
        linechart.invalidate();
        linechart.setBackgroundColor(getResources().getColor(R.color.Lightblue));
        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(R.color.colorPrimaryDark);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setCircleHoleColor(Color.GRAY);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setFillColor(R.color.colorPrimaryDark);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillAlpha(120);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineData.setValueTypeface(Typeface.SERIF);
        lineDataSet.setCircleColor(Color.GRAY);
        linechart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        linechart.getAxisRight().setEnabled(false);
        linechart.getXAxis().setLabelCount(10,true);
        linechart.setDoubleTapToZoomEnabled(false);
        linechart.setScaleEnabled(false);
        linechart.getAxisLeft().setLabelCount(8);
        linechart.getAxisLeft().setXOffset(10);
    }

    private ArrayList<Entry> lineChartDataSet(ArrayList<ForureningsDataModel> dataList){
        ArrayList<Entry> dataSet = new ArrayList<Entry>();
        for (ForureningsDataModel o:dataList
             ) {
            if (component.equals("No2")) {
                dataSet.add(new Entry(o.getHour(), (float) o.getNo2()));
            }
            else if (component.equals("O3")){
                dataSet.add(new Entry(o.getHour(), (float) o.getO3()));
            }
            else if (component.equals("PM2_5")){
                dataSet.add(new Entry(o.getHour(), (float) o.getPM2_5()));
            }
            else {
                dataSet.add(new Entry(o.getHour(), (float) o.getPM10()));
            }
        }
        return dataSet;
    }

    public void Toast(View view) {
        Toast.makeText(ForureningsUdsigt.this,"REEEEEEEEEEEEEEE",Toast.LENGTH_LONG).show();
    }

    public void LocationPick(View view){
        Intent i = new Intent(ForureningsUdsigt.this, MapPickActivity.class);
        i.putExtra("userX",userX);
        i.putExtra("userY",userY);
        i.putExtra("component",component);
        startActivityForResult(i,2);
    }
    private boolean isLocationPicked = false;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            Log.d("GetData", "Den kom ind");
            geoX = data.getDoubleExtra("geoX",geoX);
            geoY = data.getDoubleExtra("geoY",geoY);
            geoString = data.getStringExtra("geoString");
            component = data.getStringExtra("component");
            isLocationPicked = true;
            GetData(component,"",geoY,geoX,geoString);
        }
    }

    public void refreshData(View view){
        if (isLocationPicked == false) {
            GetData(component, "", userX, userY, geoString);
        }
        else{
            GetData(component,"",geoY,geoX,geoString);
        }
    }

    //endregion Methods
}