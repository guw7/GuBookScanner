package com.guw.gubook.views.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guw.gubook.Action.ActionClass;
import com.guw.gubook.Action.PrefManager;
import com.guw.gubook.R;
import com.guw.gubook.apiHelper.BaseApiService;
import com.guw.gubook.apiHelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class PageLogin extends ActionClass {

    Button btnMasuk, btnRegister;
    EditText email, password;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    PrefManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this; // .. <- class ini
        mApiService = UtilsApi.getAPIService(); // panggil apihelper

        manager = new PrefManager(this);

        // init
        email = findViewById(R.id.idUsername);
        password = findViewById(R.id.idPassword);
        btnMasuk = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // aksi
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NIM/Email tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(mContext, null,
                            "Harap Tunggu...",
                            true, false);
                    requestLogin(); // tester
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(PageLogin.this, PageRegister.class);
                startActivity(aa);
                finish();
            }
        });
    }

    //  Button Back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PageLogin.this, Utama.class);
        startActivity(i);
        finish();
    }


    // ---------------------------------------------------------
    // MA FUNC -> requestLogin
    // ---------------------------------------------------------
    private void requestLogin() {
        if (email.getText().toString().contains("@")) {

            //POST Tamu Luar
            mApiService.loginRequestLuar(email.getText().toString(), password.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                    // jika sukses
                                    if (jsonRESULTS.getString("error").equals("false")) {
                                        SH_TOAST2("Hai '" + jsonRESULTS.getJSONObject("user").getString("nama") + "'");

                                        // parsing data
                                        String id = jsonRESULTS.getJSONObject("user").getString("id_pengunjung_luar");
                                        String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                        String alamat = jsonRESULTS.getJSONObject("user").getString("alamat");
                                        String jk = jsonRESULTS.getJSONObject("user").getString("jk");
                                        String asal = jsonRESULTS.getJSONObject("user").getString("asal");
                                        String email = jsonRESULTS.getJSONObject("user").getString("email");
                                        String no_hp = jsonRESULTS.getJSONObject("user").getString("no_hp");

                                        manager.setSudahLogin(true, new String[]{id, nama, alamat, jk, asal, email, no_hp});

                                        Intent intent = new Intent(mContext, MenuFragment.class);
                                        startActivity(intent);

                                    } else {
                                        // Jika login gagal
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        SH_ALERT("Error", error_message, "OK");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            prinT("onFailure: ERROR > " + t.toString());
                            loading.dismiss();
                        }
                    });
        } else {
            //POST Tamu Lokal
            mApiService.loginRequestLokal(email.getText().toString(), password.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                    // jika sukses
                                    if (jsonRESULTS.getString("error").equals("false")) {
                                        SH_TOAST2("Hai '" + jsonRESULTS.getJSONObject("user").getString("nama_mhs") + "'");

                                        // parsing data
                                        String id = jsonRESULTS.getJSONObject("user").getString("id_mahasiswa");
                                        String nim = jsonRESULTS.getJSONObject("user").getString("nim_mhs");
                                        String nama = jsonRESULTS.getJSONObject("user").getString("nama_mhs");
                                        String alamat = jsonRESULTS.getJSONObject("user").getString("alamat");
                                        String jk = jsonRESULTS.getJSONObject("user").getString("jenis_kelamin");
                                        String asal = jsonRESULTS.getJSONObject("user").getString("jurusan");
                                        String angkatan = jsonRESULTS.getJSONObject("user").getString("angkatan");
                                        String email = jsonRESULTS.getJSONObject("user").getString("email");
                                        String no_hp = jsonRESULTS.getJSONObject("user").getString("no_hp");

                                        manager.setSudahLogin(true, new String[]{id, nim, nama, alamat, jk, asal, angkatan, email, no_hp});

                                        Intent intent = new Intent(mContext, MenuFragment.class);
                                        startActivity(intent);

                                    } else {
                                        // Jika login gagal
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        SH_ALERT("Error", error_message, "OK");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            prinT("onFailure: ERROR > " + t.toString());
                            Toast.makeText(mContext, "UPPSS .. \n\n Koneksi Internet Bermasalah \n\n Periksa kembali koneksi anda!", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    });
        }


    }
}
