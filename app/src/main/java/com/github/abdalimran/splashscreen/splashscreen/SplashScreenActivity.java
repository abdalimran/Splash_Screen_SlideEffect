package com.github.abdalimran.splashscreen.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.abdalimran.splashscreen.MainActivity;
import com.github.abdalimran.splashscreen.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends Activity {
    final static int SPLASH_TIME_OUT = 4000;
    private boolean scheduled = false;
    private Timer timer;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        LinearLayout linlay=(LinearLayout) findViewById(R.id.lin_lay);
        linlay.clearAnimation();
        linlay.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        ImageView splashimg = (ImageView) findViewById(R.id.splashview);
        splashimg.clearAnimation();
        splashimg.startAnimation(anim);

        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                Intent home_page = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(home_page);
                finish();
            }}, SPLASH_TIME_OUT);
        this.scheduled=true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (this.scheduled) {
            this.timer.cancel();
        }
        this.timer.purge();
    }
}