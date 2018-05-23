package com.guw.gubook.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.guw.gubook.R;

public class PageRegister extends AppCompatActivity {

    Button btnMasuk, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnMasuk = (Button) findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(PageRegister.this, PageHome.class);
                startActivity(aa);
                finish();
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(PageRegister.this, PageLogin.class);
                startActivity(aa);
                finish();
            }
        });
    }

    //  Button Back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PageRegister.this, Utama.class);
        startActivity(i);
        finish();
    }
}
