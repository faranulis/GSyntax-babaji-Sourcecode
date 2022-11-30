package com.gsyntax.faran;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;



import java.io.File;


public class setting extends Helper {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
//SAFEMODE
        safemode = findViewById(R.id.safemode);
        sharedPreferences = getSharedPreferences("myname", MODE_PRIVATE);
        safemode.setChecked(sharedPreferences.getBoolean(SWITCH_PARTIDOS_STATE, false));
        safemode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean(SWITCH_PARTIDOS_STATE, isChecked).commit();
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Enable Safemode",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Disable Safemode",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //SAFEMODE
        forceRungame = findViewById(R.id.forcerungame);
        sharedPreferencesRunGame = getSharedPreferences("forcerungame", MODE_PRIVATE);
        forceRungame.setChecked(sharedPreferencesRunGame.getBoolean(SWITCH_FORCERUNGAME, false));
        forceRungame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferencesRunGame.edit().putBoolean(SWITCH_FORCERUNGAME, isChecked).commit();
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Enable Without Installation",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Disable Without Installation",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button Reset = findViewById(R.id.reset);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(setting.this)
                        .setTitle("Back To Default ?")
                        .setMessage("Notes : Delete IF PUBGM Update!\n" +
                                "- All GSyntax Settings Will Be Back To Default")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                //folder yang akan di hapus "/GSyntax"
                                final String hapus = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GSyntax";
                                final String hapus2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/";
                                final String hapus3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/";
                                final String hapus4 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/";

                                //beban yang akan di porgress
                                deleteFiles(hapus);
                                deleteFiles(hapus2  + "Config/Android");
                                deleteFiles(hapus3  + "Config/Android");
                                deleteFiles(hapus4  + "Config/Android");
                                Toast.makeText(setting.this, "/android/data/com... Saved/Config/Android Deleted!", Toast.LENGTH_SHORT).show();

                                deleteFiles(hapus2 + "rawdata");
                                deleteFiles(hapus3 + "rawdata");
                                deleteFiles(hapus4 + "rawdata");
                                Toast.makeText(setting.this, "/android/data/com... Saved/rawdata", Toast.LENGTH_SHORT).show();

                                deleteFile(hapus2 + "SaveGames/Active.sav");
                                deleteFile(hapus3 + "SaveGames/Active.sav");
                                deleteFile(hapus4 + "SaveGames/Active.sav");
                                Toast.makeText(setting.this, "/android/data/com... Active.sav", Toast.LENGTH_SHORT).show();

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/GSyntax/ODPaks");
                                folder.mkdirs();
                                Toast.makeText(setting.this, "Re-clean GSyntax Folders...", Toast.LENGTH_SHORT).show();

                                StatusActive.setText("Default");
                                StatusConfig.setText("Default");
                                StatusGUS.setText("Default");
                                StatusGP.setText("Default");
                                StatusRAW.setText("Default");
                                StatusInstalasi.setText("*Installation Not Installed");
                                //Set Color On TextView  With Java
                                StatusInstalasi.setTextColor(Color.parseColor("#FF4500"));


                                SharedPreferences sp = getSharedPreferences("key", 0);
                                SharedPreferences.Editor sedt = sp.edit();
                                sedt.putString("textvalue", "Nothing..");
                                sedt.commit();

                                SharedPreferences sp1 = getSharedPreferences("key5", 0);
                                SharedPreferences.Editor sedt1 = sp1.edit();
                                sedt1.putString("textvalue5", "Nothing..");
                                sedt1.commit();

                                SharedPreferences sp2 = getSharedPreferences("key2", 0);
                                SharedPreferences.Editor sedt2 = sp2.edit();
                                sedt2.putString("textvalue2", "Nothing");
                                sedt2.commit();

                                Toast.makeText(setting.this, "GSyntax Settings Back To Default.. Success", Toast.LENGTH_SHORT).show();

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

    public void checkUpdate(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.faranramdan.com/2020/12/full-version-of-gfx-g-syntax-best-of.html"));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(setting.this, FullscreenActivity.class);
        startActivity(intent);
    }

}
