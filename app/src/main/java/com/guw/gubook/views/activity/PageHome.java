package com.guw.gubook.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guw.gubook.R;

public class PageHome extends AppCompatActivity {
    TextView tvResultNama;
    String[] resultNama;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_dashboard);
                case R.id.navigation_about:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);

        mTextMessage = (TextView) findViewById(R.id.message);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultNama = extras.getStringArray("result_user");

        Button btnScan;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        btnScan = (Button) findViewById(R.id.btnScan);
        //  Aksi
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bb = new Intent(PageHome.this, Scanner.class);
                bb.putExtra("data", resultNama);
                startActivity(bb);
                finish();
            }
        });
//           return true;
    }
}
