package com.shopnow.app.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shopnow.R;


public class SplashScreenActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    protected void init() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this,BaseActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
