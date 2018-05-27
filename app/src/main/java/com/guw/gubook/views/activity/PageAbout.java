package com.guw.gubook.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guw.gubook.R;

public class PageAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
