/*
 * Copyright (c) 2018. MDVK adalah sebuah aplikasi abal-abal yang dibangun atas keisengan semata :D
 * Umumnya kegunaan aplikasi MDVK adalah untuk membuat Perangkat kamu terhubung ke(@ wifi.id)
 * secara gratis tanpa harus membeli voucher atau dsb.
 *
 * Aplikasi Ini dibuat oleh : Aditya Pratama , pada tanggal : 6/14/2017
 *
 *
 */

package com.guw.gubook.Action;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    public static final String CEK_LOGIN = "IsLogin";
    // Shared preferences file name
    public static final String PREF_NAME = "PREF_GUBOOK";
    private static final String CEK_SUDAH_LOGIN = "sessionLogin";
    private static final String CEK_SUDAH_SCAN = "sudahScan";
    private static final String CEK_HARIAN = "harian";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    // context
    public PrefManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    // session login
    public boolean sessionLogin() {
        return pref.getBoolean(CEK_SUDAH_LOGIN, false);
    }

    public void setSudahLogin(boolean sudahLogin, String[] data) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);

        //Data User Lokal
        if (data.length == 9) {
            editor.putString("id_pengunjung", data[0]);
            editor.putString("nim", data[1]);
            editor.putString("nama", data[2]);
            editor.putString("alamat", data[3]);
            editor.putString("jk", data[4]);
            editor.putString("asal", data[5]);
            editor.putString("angkatan", data[6]);
            editor.putString("email", data[7]);
            editor.putString("no_hp", data[8]);
        } else {
            //Data User Luar
            editor.putString("id_pengunjung", "77777" + data[0]);
            editor.putString("nama", data[1]);
            editor.putString("alamat", data[2]);
            editor.putString("jk", data[3]);
            editor.putString("asal", data[4]);
            editor.putString("angkatan", " ");
            editor.putString("email", data[5]);
            editor.putString("no_hp", data[6]);
        }
        editor.commit();
    }

    // ini method khusus pemanggilan button logout
    // karena logout tidak memerlukan data user;
    public void setSudahLogin(boolean sudahLogin) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);
        editor.commit();
    }

    // -------------------------------------------------------- //

//    //sesionSudah Scan

    public boolean getSudahScan() {
        return pref.getBoolean(CEK_SUDAH_SCAN, false);
    }

    public void setSudahScan(boolean sudahScan) {
        editor.putBoolean(CEK_SUDAH_SCAN, sudahScan);
        editor.commit();
    }

    public String getIdPengunjung() {
        return pref.getString("id_pengunjung", "");
    }

    public String getHariIni() {
        return pref.getString("Hari ini", "");
    }

    public void setHariIni(String hariIni) {
        editor.putString("Hari ini", hariIni);
        editor.commit();
    }
}
