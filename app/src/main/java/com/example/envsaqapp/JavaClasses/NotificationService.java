package com.example.envsaqapp.JavaClasses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {


    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 5;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);

        startTimer();

        return START_STICKY;
    }

    double pointX;
    double pointY;
    double no2;
    double pm2_5;
    double pm10;
    @Override
    public void onCreate() {
        /*Log.e(TAG, "onCreate");
        MainActivity mainActivity = new MainActivity();
        pointX = mainActivity.pointX;
        pointY = mainActivity.pointY;
        Log.d(TAG, pointX + pointY + "");
        searchForLocation();
        while (true){
            YOURNOTIFICATIONFUNCTION();
        }*/
    }
    /*public void searchForLocation() {
        HttpUrl url = HttpUrl.parse("http://10.28.0.241:3000/rpc/get_nearest_house?x_long="+ pointX +"&y_lat="+ pointY);
        DataService dataService = ApiUtils.getTrackService();
        Call<ArrayList<Data>> queueSong = dataService.SearchForLocation(url.toString());
        queueSong.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if (response.isSuccessful()) {
                    Log.d("QUERY", " " + response.code());
                    Log.d("QUERY", response.body().toString());
                    Log.d("QUERY", url.toString());
                    Data responseObject1 = response.body().get(0);
                    //address = responseObject1.getStreet_nam() + " " + responseObject1.getHouse_num();
                    no2 = responseObject1.getNo2_street();
                    pm2_5 = responseObject1.getPM2_5();
                    pm10 = responseObject1.getPM10();
                    Log.d("RESPONSEOBJECTS", responseObject1.toString());
                    //Toast.makeText(ForureningHer.this, "REQUEST SUCCESSFULL" + response.body().toString(), Toast.LENGTH_LONG).show();
                    //Log.d("TESTING", SongsInQueue.toString());


                } else {
                    String message = "Problem " + response.code() + " " + response.message() + " " + response.raw();
                    Toast.makeText(NotificationService.this, "REQUEST NOT SUCCESSFULL", Toast.LENGTH_LONG).show();
                    Log.d("Queue", message);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                Toast.makeText(NotificationService.this, "REQUEST FAILED", Toast.LENGTH_LONG).show();
                Log.d("Queue", t.toString());
            }
        });
    }*/

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        stoptimertask();
        super.onDestroy();


    }

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        //TODO CALL NOTIFICATION FUNC
                        //YOURNOTIFICATIONFUNCTION();

                    }
                });
            }
        };
    }

    private void YOURNOTIFICATIONFUNCTION() {

        if (no2 > 1) {
            Intent i = new Intent(NotificationService.this, PaamindelseNoti.class);
            PendingIntent pendingintent = PendingIntent.getBroadcast(NotificationService.this, 0, i, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long currenttime = System.currentTimeMillis();
            long timeout = 1000 * 0;
            alarmManager.set(AlarmManager.RTC_WAKEUP, currenttime + timeout, pendingintent);
        }
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
