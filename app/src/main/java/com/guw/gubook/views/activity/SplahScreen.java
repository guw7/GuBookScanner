package com.guw.gubook.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.guw.gubook.Action.PrefManager;
import com.guw.gubook.R;

public class SplahScreen extends AppCompatActivity {

    PrefManager manager;
    int _TIMER = 1700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);

        manager = new PrefManager(this);
        Handler handler = new Handler();

        if (manager.sessionLogin() == true) {

            // Timer
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplahScreen.this, MenuFragment.class);
                    startActivity(intent);
                    finish();
                }
            }, _TIMER);

        } else {
            // Timer
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent ii = new Intent(SplahScreen.this, Utama.class);
                    startActivity(ii);
                    finish();
                }
            }, _TIMER);
        }
    }
}
