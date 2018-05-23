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
    private static final String PREF_NAME = "PREF_GUBOOK";
    private static final String CEK_SUDAH_LOGIN = "sessionLogin";
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
        return pref.getBoolean(CEK_SUDAH_LOGIN, true);
    }

    public void setSudahLogin(boolean sudahLogin) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);
        editor.commit();
    }

    // --------------------------------------------------------


}
