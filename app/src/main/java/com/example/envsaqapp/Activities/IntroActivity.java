package com.example.envsaqapp.Activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.envsaqapp.R;


public class IntroActivity extends AppCompatActivity {
    //region Instance Fields
    private static int TIME_OUT = 5000;
    private ProgressBar progressBar;
    private int mLoading = 0;
    //endregion Instance Fields

    //region Methods
    //Start of Comments onCreate
    /*
    This method shows the intro screen in fullscreen mode for five seconds and then change activity to webViewActivity.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroActivity.this, webViewActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, TIME_OUT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBar.getProgress() < 100) {
                    mLoading++;
                    progressBar.setProgress(mLoading);
                    android.os.SystemClock.sleep(50);
                }
            }
        }).start();
    }
    //endregion Methods

};