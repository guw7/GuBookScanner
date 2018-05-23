package com.guw.gubook.Action;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

@SuppressLint("Registered")
public class ActionClass extends AppCompatActivity {

    // CONSTANT_STRING
    public static final String _BASE_API_LOGIN = "http://bukutamu-via.cloud.revoluz.io/G-API/API/loginLuar.php?";

    // PRINT
    public void prinT(String msg) {
        System.out.println("---> " + msg);
    }

    // TOAST SHORT
    public void SH_TOAST(String query) {
        Toast.makeText(ActionClass.this, query, Toast.LENGTH_SHORT).show();
    }

    // TOASH LONG
    public void SH_TOAST2(String query) {
        Toast.makeText(ActionClass.this, query, Toast.LENGTH_LONG).show();
    }

    // ALERT DIALOG
    public void SH_ALERT(String judul, String msg, String button) {
        AlertDialog alertDialog = new AlertDialog.Builder(ActionClass.this).create();
        alertDialog.setTitle(judul);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // SNACKBAR
    public void SH_SB(String msg, String button) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
                .setAction(button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    // INTENT
    public void openActivity(Class target) {
        Intent open = new Intent(ActionClass.this, target);
        startActivity(open);
    }

    // INTENT w/ Finish
    public void moveActivity(Class target) {
        Intent open = new Intent(ActionClass.this, target);
        startActivity(open);
        finish();
    }

}

