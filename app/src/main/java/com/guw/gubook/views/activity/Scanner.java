package com.guw.gubook.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.guw.gubook.Action.Koneksi;
import com.guw.gubook.Action.PrefManager;
import com.guw.gubook.apiHelper.BaseApiService;
import com.guw.gubook.apiHelper.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private ZXingScannerView scannerView;
    private Koneksi koneksi;
    PrefManager manager;
    BaseApiService mApiService;
    private Calendar calendar, calendar2, calendar3;
    String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;
        koneksi = new Koneksi(this);

        manager = new PrefManager(this);
        mApiService = UtilsApi.getAPIService(); // panggil apihelper

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 17);
        calendar2.set(Calendar.MINUTE, 0);

        calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(System.currentTimeMillis());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GUBOOK SCANNER");


        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        today = dateFormat.format(date);


        if (!today.equals(manager.getHariIni())) {
            manager.setSudahScan(false);
        }

    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    //    }
    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(Scanner.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());

        if (result.getText().equals("27946274a201346f0322e3861909b5ff") && calendar3.getTimeInMillis() > calendar.getTimeInMillis() && calendar3.getTimeInMillis() < calendar2.getTimeInMillis() && manager.getSudahScan() != true) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Selamat Datang di \n Perpustakaan Politeknik Aceh");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    manager.setHariIni(today);
                    manager.setSudahScan(true);
                    finish();
                }
            });
            builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.politeknikaceh.ac.id/"));
                    startActivity(browserIntent);
                }
            });

//        String welcome = String.valueOf(R.string.welcome);
//        builder.setMessage(result.getText());
            builder.setMessage("Terima kasih telah mengunjungi perpustakaan Politeknik Aceh :)");

            AlertDialog alert1 = builder.create();
            alert1.show();

            koneksi.postJson(result.getText().toString());

        } else if (calendar3.getTimeInMillis() < calendar.getTimeInMillis() || calendar3.getTimeInMillis() > calendar2.getTimeInMillis()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("UPPS! \n Belum waktunya untuk berbuka ;)");
            builder.setPositiveButton("Coba Lagi!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alert1 = builder.create();
            alert1.show();

        } else if (manager.getSudahScan()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("UPPSS, Anda sudah Scan hari ini, \n Coba lagi esok hari ;)");
            builder.setPositiveButton("Coba Lagi!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alert1 = builder.create();
            alert1.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Maaf QR-Code yg anda scan, SALAH!");
            builder.setPositiveButton("Coba Lagi!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alert1 = builder.create();
            alert1.show();
        }
    }

    //  Button Back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Scanner.this, MenuFragment.class);
        startActivity(i);
        finish();
    }
}