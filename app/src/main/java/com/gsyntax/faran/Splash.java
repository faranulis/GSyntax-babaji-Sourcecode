package com.gsyntax.faran;

import android.app.AppOpsManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;


import java.io.File;
import java.io.IOException;

public class Splash extends Helper {
    VideoView videoView;
    DisplayMetrics dm;
    Button btneditConfig,skipAnim;

    File cekconfig = new File (path2 + "/UserCustom.ini");
    File cekbackupdevice = new File (path2 + "/BackupDevice.ini");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //android 10 req storangwe
        requestAppPermissions();
        if (!isAccessGranted()) {
            new AlertDialog.Builder(this)

                    .setTitle("Please allow the application to access")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }

        videoView = (VideoView) findViewById(R.id.videoViewUtama);
        dm = new DisplayMetrics();
        btneditConfig =  (Button) findViewById(R.id.OtherConfig);
        skipAnim = (Button) findViewById(R.id.skipAnim);

        //Create Folder
        File folder = new File(Environment.getExternalStorageDirectory().toString()+"/GSyntax/ODPaks");
        File folderKR = new File(Environment.getExternalStorageDirectory().toString()+"/GSyntax/ODPaksKR");
        File folderVN = new File(Environment.getExternalStorageDirectory().toString()+"/GSyntax/ODPaksVN");
        File copypasteSAV = new File(copypastesav);
        folder.mkdirs();
        folderKR.mkdirs();
        folderVN.mkdirs();
        copypasteSAV.mkdirs();

        //buat jadi fullscreen dengan bantuan manifest android:theme="@style/Theme.AppCompat.NoActionBar"
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DEBUG();

        btneditConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.stopPlayback();
                finish();
                Intent intent = new Intent(Splash.this, FullscreenActivity.class);
                startActivity(intent);
            }
        });

        // ambil ukuran layar
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;

        // lebar dan tinggi video view diberi nilai agar menyesuaikan ukuran layout
        videoView.setMinimumWidth(width);
        videoView.setMinimumHeight(height);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                try {
                    startNextActivity();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        videoView.start();

        skipAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RunGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isAccessGranted() {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void showStartDialog() {
        new AlertDialog.Builder(this)

                .setTitle("Notif ini Hanya Tampil Sekali!")
                .setMessage("Accept if Show storage permission!" + "\n" + "\n"
                + "1. Select Active sav Dan Config, Klik OK" + "\n"
                + "2. Jika Ingin Relog/Main PUBG Mobile Selalu Buka G Syntax Agar Config Tidak Tereset")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Splash.this, Instalasi.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

        public void startNextActivity() throws IOException {
            //Notif First Start
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                showStartDialog();
            } else {
                RunGame();
            }
    }
}