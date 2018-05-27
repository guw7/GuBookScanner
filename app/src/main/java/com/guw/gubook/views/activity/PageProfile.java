package com.guw.gubook.views.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.guw.gubook.Action.PrefManager;
import com.guw.gubook.R;


public class PageProfile extends AppCompatActivity {

    Button btnLogout;
    PrefManager manager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profile);
        btnLogout = findViewById(R.id.btnLogout);
        manager = new PrefManager(this);
        sharedPreferences = this.getSharedPreferences(manager.PREF_NAME, 0);
        editor = sharedPreferences.edit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                manager.setSudahLogin(false);
//                editor.clear();
//                editor.commit();
//
//                Intent intent = new Intent(getApplicationContext(), PageLogin.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    //  Button Back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PageProfile.this, MenuFragment.class);
        startActivity(i);
        finish();
    }

}
