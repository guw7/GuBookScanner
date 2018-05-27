package com.guw.gubook.views.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.guw.gubook.R;
import com.guw.gubook.apiHelper.BaseApiService;
import com.guw.gubook.apiHelper.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageRegister extends AppCompatActivity {

    EditText fullname, email, password, alamat, asal, noHp;
    RadioGroup jk;
    RadioButton rdLaki, rdPerempuan;
    Button btnRegister, btnMasuk;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    String jenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        //Init
        fullname = findViewById(R.id.idFullname);
        email = findViewById(R.id.idEmail);
        password = findViewById(R.id.idPassword);
        alamat = findViewById(R.id.idAlamat);
        asal = findViewById(R.id.idAsal);
        noHp = findViewById(R.id.idNoHp);

        btnRegister = findViewById(R.id.btnRegister);
        btnMasuk = findViewById(R.id.btnLogin);

        jk = findViewById(R.id.rgJK);
        jk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (jk.getCheckedRadioButtonId()) {
                    case R.id.rdLaki:
                        jenisKelamin = "Laki-laki";
                        break;
                    case R.id.rdPerempuan:
                        jenisKelamin = "Perempuan";
                        break;
                }
            }
        });

        //Daftar User Luar
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong!", Toast.LENGTH_LONG).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (alamat.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Alamat tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (asal.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Asal tidak boleh ksoong!", Toast.LENGTH_SHORT).show();
                } else if (noHp.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No HP tidak boleh ksoong!", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(mContext, null,
                            "Harap Tunggu...",
                            true, false);
                    requestRegister(); // tester
                }
            }
        });

        //Pindah Page Login
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(PageRegister.this, PageLogin.class);
                startActivity(aa);
                finish();
            }
        });
    }

    private void requestRegister() {
        mApiService.registerRequest(
                fullname.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                jenisKelamin,
                asal.getText().toString(),
                alamat.getText().toString(),
                noHp.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), PageLogin.class);
                    startActivity(intent);
                    finish();
                }

//                    Log.i("message", response.body().toString());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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


