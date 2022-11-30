package com.gsyntax.faran;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import java.io.File;

public class Odpaks extends Helper {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odpack);

        Button backupodpaks = findViewById(R.id.backupodpaks);
        Button restoreodpaks = findViewById(R.id.restoreodpaks);
        Button deletekodpack = findViewById(R.id.deletekodpack);

        backupodpaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File source = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                final File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaks");

                final File sourceKR = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                final File destKR = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksKR");

                final File sourceVN = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                final File destVN = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksVN");

                final File sourceTW = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                final File destTW = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksTW");

                progressDoalog = new ProgressDialog(Odpaks.this);
                progressDoalog.setMax(100);
                progressDoalog.setMessage("Please Wait...........");
                progressDoalog.setTitle("Backup ODPaks!");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show();
                progressDoalog.setCanceledOnTouchOutside(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDoalog.getProgress() <= progressDoalog
                                    .getMax()) {

                                //beban yang akan di porgress
                                SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
                                int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
                                if(spinnerValue == 0) {
                                    copyDirectory(source, dest);
                                } else if (spinnerValue == 1){
                                    copyDirectory(sourceKR, destKR);
                                } else if (spinnerValue == 2){
                                    copyDirectory(sourceVN, destVN);
                                } else if (spinnerValue == 3){
                                    copyDirectory(sourceTW, destTW);
                                }


                                handle.sendMessage(handle.obtainMessage());
                                if (progressDoalog.getProgress() == progressDoalog
                                        .getMax()) {
                                    progressDoalog.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        restoreodpaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File source = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaks");
                final File dest = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");

                final File sourceKR = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksKR");
                final File destKR = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");

                final File sourceVN = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksVN");
                final File destVN = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");

                final File sourceTW = new File(Environment.getExternalStorageDirectory(), "/GSyntax/ODPaksTW");
                final File destTW = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");

                progressDoalog = new ProgressDialog(Odpaks.this);
                progressDoalog.setMax(100);
                progressDoalog.setMessage("Please Wait...........");
                progressDoalog.setTitle("Restore ODPaks!");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show();
                progressDoalog.setCanceledOnTouchOutside(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDoalog.getProgress() <= progressDoalog
                                    .getMax()) {

                                //beban yang akan di porgress

                                SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
                                int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
                                if(spinnerValue == 0) {
                                    copyDirectory(source, dest);
                                } else if (spinnerValue == 1){
                                    copyDirectory(sourceKR, destKR);
                                } else if (spinnerValue == 2){
                                    copyDirectory(sourceVN, destVN);
                                } else if (spinnerValue == 3){
                                    copyDirectory(sourceTW, destTW);
                                }

                                handle.sendMessage(handle.obtainMessage());
                                if (progressDoalog.getProgress() == progressDoalog
                                        .getMax()) {
                                    progressDoalog.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        deletekodpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Odpaks.this)
                        .setTitle("DO YOU WANT TO DELETE ODPAKS?")
                        .setMessage("All Skins Will Be Removed!\n" +
                                "Note:\n" +
                                "- Removing ODPaks makes PUBGM Smoothly\n" +
                                "- Make sure you have Backup ODPaks if something happens or bug")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                final String odpaks = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks";
                                final String odpakskr = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks";
                                final String odpaksvn = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks";
                                final String odpakstw = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks";
                                progressDoalog = new ProgressDialog(Odpaks.this);
                                progressDoalog.setMax(100);
                                progressDoalog.setMessage("Please Wait...........");
                                progressDoalog.setTitle("Delete ODPaks!");
                                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                progressDoalog.show();
                                progressDoalog.setCanceledOnTouchOutside(false);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            while (progressDoalog.getProgress() <= progressDoalog
                                                    .getMax()) {

                                                //beban yang akan di porgress

                                                SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
                                                int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
                                                if(spinnerValue == 0) {
                                                    deleteFiles(odpaks);
                                                    File folder = new File(Environment.getExternalStorageDirectory().toString()+"/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                                                    folder.mkdirs();
                                                } else if (spinnerValue == 1){
                                                    deleteFiles(odpakskr);
                                                    File folder = new File(Environment.getExternalStorageDirectory().toString()+"/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                                                    folder.mkdirs();
                                                } else if (spinnerValue == 2){
                                                    deleteFiles(odpaksvn);
                                                    File folder = new File(Environment.getExternalStorageDirectory().toString()+"/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                                                    folder.mkdirs();
                                                } else if (spinnerValue == 3){
                                                    deleteFiles(odpakstw);
                                                    File folder = new File(Environment.getExternalStorageDirectory().toString()+"/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks/ODPaks");
                                                    folder.mkdirs();
                                                }
                                                handle.sendMessage(handle.obtainMessage());
                                                if (progressDoalog.getProgress() == progressDoalog
                                                        .getMax()) {
                                                    progressDoalog.dismiss();
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        dialog.dismiss();
                                    }
                                }).start();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
    }



}
