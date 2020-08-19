package com.example.envsaqapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
public class IntroActivity extends AppCompatActivity {
    private static int TIME_OUT = 5000;
    private ProgressBar progressBar;
    private int mLoading = 0;
    private Handler mHandler = new Handler();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
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
};