package com.guw.gubook.Action;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Koneksi {

    private final String URL = "bukutamu-via.cloud.revoluz.io";
    private final MediaType Json = MediaType.parse("application/json");
    private String tesUrl;
    private String id_pengunjung, nim, nama, alamat, jk, asal, angkatan, email, no_hp;

    public void postJson(String result, String[] data) {
        final int valHadir = Integer.valueOf(result);

        //Data User Lokal
        if (data.length == 9) {
            id_pengunjung = data[0];
            nim = data[1];
            nama = data[2];
            alamat = data[3];
            jk = data[4];
            asal = data[5];
            angkatan = data[6];
            email = data[7];
            no_hp = data[8];
        } else {
            //Data User Luar
            id_pengunjung = data[0];
            nim = data[1];
            nama = data[2];
            alamat = data[3];
            jk = data[4];
            asal = data[5];
            angkatan = " ";
            email = data[6];
            no_hp = data[7];
        }

// POST PENGUNJUNG

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(URL)
                .addPathSegments("G-API/API/api.php")
                .addQueryParameter("apicall", "createPengunjung")
                .build();
        tesUrl = url + "";

        OkHttpClient client = new OkHttpClient();
        MultipartBody request = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id_pengunjung", id_pengunjung)
                .addFormDataPart("nim", nim)
                .addFormDataPart("nama", nama)
                .addFormDataPart("alamat", alamat)
                .addFormDataPart("jk", jk)
                .addFormDataPart("asal", asal)
                .addFormDataPart("angkatan", angkatan)
                .addFormDataPart("email", email)
                .addFormDataPart("no_hp", no_hp)
                .addFormDataPart("hadir", result)
                .build();

        Request request1 = new Request.Builder()
                .url(url)
                .post(request)
                .build();

        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("response", response.body().string());
                Log.i("url", tesUrl);
                Log.i("valHadir", "" + valHadir);
            }
        });

    }
}
