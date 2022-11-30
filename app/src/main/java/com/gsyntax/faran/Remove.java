package com.gsyntax.faran;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Remove extends com.gsyntax.faran.Helper {

    Button RGPatch,RRawdata,REnjoy,RGUS,RODPaks,RUC,REPP,RUE,RUG,PufferPatch,RU;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove);

        RGPatch = findViewById(R.id.remove_gamepatch);
        RRawdata = findViewById(R.id.remove_rawdata);
        REnjoy = findViewById(R.id.remove_enjoy);
        RODPaks = findViewById(R.id.remove_odpaks);
        RGUS = findViewById(R.id.remove_GUS);
        RUC = findViewById(R.id.remove_GUC);
        REPP = findViewById(R.id.remove_GEPP);
        RUG = findViewById(R.id.remove_Usergame);
        RUE = findViewById(R.id.remove_Userengine);
        PufferPatch = findViewById(R.id.remove_pufferpatch);
        RU = findViewById(R.id.remove_updater);

        RGPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletGP();
            }
        });

        RRawdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleterawdata();
            }
        });

        REnjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteenjoy();
            }
        });

        RODPaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Remove.this, Odpaks.class);
                startActivity(intent);
            }
        });

        RGUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGUS();
            }
        });

        RUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteusercustom();
            }
        });

        REPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteeditorperproject();
            }
        });

        RUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteuserengine();
            }
        });

        RUG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteusergame();
            }
        });

        PufferPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Remove.this, "Loading Server Lag, Click Again", Toast.LENGTH_SHORT).show();
remove_pufferpatch2();
remove_pufferpatch1();
            }
        });

        RU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
deleteupdater();
            }
        });
    }

    public void deleteenjoy() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (scenjoy.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EnjoyCJZC ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/EnjoyCJZC.ini");
                                deleteFiles(path2 + "/EnjoyCJZC.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EnjoyCJZC", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (scenjoy.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EnjoyCJZC ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/EnjoyCJZC.ini");
                                deleteFiles(path2 + "/EnjoyCJZC.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EnjoyCJZC", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (scenjoy.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EnjoyCJZC ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/EnjoyCJZC.ini");
                                deleteFiles(path2 + "/EnjoyCJZC.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EnjoyCJZC", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (scenjoy.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EnjoyCJZC ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/EnjoyCJZC.ini");
                                deleteFiles(path2 + "/EnjoyCJZC.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EnjoyCJZC", Toast.LENGTH_LONG).show();
            }
        }


    }
    public void deleteusercustom() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (scusercustom.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserCustom ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/UserCustom.ini");
                                deleteFiles(path2 + "/UserCustom.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserCustom", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (scusercustom.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserCustom ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/UserCustom.ini");
                                deleteFiles(path2 + "/UserCustom.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserCustom", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (scusercustom.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserCustom ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/UserCustom.ini");
                                deleteFiles(path2 + "/UserCustom.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserCustom", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (scusercustom.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserCustom ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/UserCustom.ini");
                                deleteFiles(path2 + "/UserCustom.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserCustom", Toast.LENGTH_LONG).show();
            }
        }


    }
    public void deleteeditorperproject() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (editorperproject.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EditorPerProject ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/EditorPerProject.ini");
                                deleteFiles(path2 + "/EditorPerProject.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EditorPerProject", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (editorperproject.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EditorPerProject ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/EditorPerProject.ini");
                                deleteFiles(path2 + "/EditorPerProject.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EditorPerProject", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (editorperproject.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EditorPerProject ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/EditorPerProject.ini");
                                deleteFiles(path2 + "/EditorPerProject.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EditorPerProject", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (editorperproject.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete EditorPerProject ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/EditorPerProject.ini");
                                deleteFiles(path2 + "/EditorPerProject.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EditorPerProject", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteupdater() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (scupdater.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete Updater.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/Updater.ini");
                                deleteFiles(path2 + "/Updater.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use Updater", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (scupdater.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete Updater.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/Updater.ini");
                                deleteFiles(path2 + "/Updater.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use Updater.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (scupdater.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete Updater.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/Updater.ini");
                                deleteFiles(path2 + "/Updater.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use Updater.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (scupdater.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete Updater.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/Updater.ini");
                                deleteFiles(path2 + "/Updater.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use Updater.ini", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void deleteusergame() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (scUserGame.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/UserGame.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use EditorPerProject", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (scUserGame.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/UserGame.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserGame.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (scUserGame.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/UserGame.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserGame.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (scUserGame.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/UserGame.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserEngine.ini", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteuserengine() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            if (scUserEngine.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserEngine.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfig + "/UserEngine.ini");
                                deleteFiles(path2 + "/UserEngine.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserEngine.ini", Toast.LENGTH_LONG).show();
            }

        } else if (spinnerValue == 1) {

            if (scUserEngine.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserEngine.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigKR + "/UserEngine.ini");
                                deleteFiles(path2 + "/UserEngine.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserEngine.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 2) {

            if (scUserEngine.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigVN + "/UserEngine.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserGame.ini", Toast.LENGTH_LONG).show();
            }
        } else if (spinnerValue == 3){
            if (scUserEngine.exists()) {
                new AlertDialog.Builder(Remove.this)
                        .setTitle("Do You Want To Delete UserGame.ini ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFiles(copypasteconfigTW + "/UserGame.ini");
                                deleteFiles(path2 + "/UserGame.ini");
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                Toast.makeText(getApplicationContext(), "Not use UserEngine.ini", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void remove_pufferpatch2(){
        new Thread(new Runnable(){

            public void run(){
                final ArrayList<String> urls=new ArrayList<String>(); //to read each line
                //TextView t; //to show the result, please declare and find it inside onCreate()
                try {
                    // Create a URL for the desired page
                    URL url = new URL("https://raw.githubusercontent.com/vizerweb/GSyntax-PUBG/master/puffer2detek.txt"); //My text file location
                    //First open the connection
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000); // timing out in a minute

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String str;
                    while ((str = in.readLine()) != null) {
                        urls.add(str);
                    }
                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag",e.toString());
                }

                //since we are in background thread, to post results we have to go back to ui thread. do the following for that

                Remove.this.runOnUiThread(new Runnable(){
                    public void run(){

//                        TextView ultra = findViewById(R.id.Ultra);
//                        ultra.setText(urls.get(0));

                        //source dan tujuan file wajib string buat deletefiles();
                        String finaldrcsound = copypastepaks + "/" + urls.get(0);
                        String finaldrcsoundKR = copypastepaksKR + "/" + urls.get(0);
                        String finaldrcsoundVN = copypastepaksVN + "/" + urls.get(0);
                        String finaldrcsoundTW = copypasteconfigTW + "/" + urls.get(0);


                        File finaldrcsoundString = new File(finaldrcsound);
                        File finaldrcsoundStringKR = new File(finaldrcsoundKR);
                        File finaldrcsoundStringVN = new File(finaldrcsoundVN);
                        File finaldrcsoundStringTW = new File(finaldrcsoundTW);

                        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

                        // MULAIII EKSEKUSI COPY FILE!!
                        if (spinnerValue == 0) {
                            //Mengahpus File/Folder Harus Pakai String!!
                            if (finaldrcsoundString.exists()) {
                                deleteFiles(finaldrcsound);
                                Toast.makeText(Remove.this, "Success Remove pufferpatch2 *GB", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Remove.this, "Deleted2 *GB", Toast.LENGTH_SHORT).show();
                            }
                            //VERSI KR
                        } else if (spinnerValue == 1) {
                            if (finaldrcsoundStringKR.exists()) {
                                deleteFiles(finaldrcsoundKR);
                                Toast.makeText(Remove.this, "Success Remove pufferpatch2 *KR", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Remove.this, "Deleted2 *KR", Toast.LENGTH_SHORT).show();
                            }
                            //VERSI VN
                        } else if (spinnerValue == 2) {
                            if (finaldrcsoundStringVN.exists()) {
                                deleteFiles(finaldrcsoundVN);
                                Toast.makeText(Remove.this, "Success Remove pufferpatch2 *VN", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Remove.this, "Deleted2 *VN", Toast.LENGTH_SHORT).show();
                            }
                            //VERSI TW
                        } else if (spinnerValue == 3) {
                            if (finaldrcsoundStringTW.exists()) {
                                deleteFiles(finaldrcsoundTW );
                                Toast.makeText(Remove.this, "Success Remove pufferpatch2 *TW", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Remove.this, "Deleted2 *TW", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
        }).start();
    }
    public void remove_pufferpatch1(){
        new Thread(new Runnable(){

            public void run(){
                final ArrayList<String> urls=new ArrayList<String>(); //to read each line
                //TextView t; //to show the result, please declare and find it inside onCreate()
                try {
                    // Create a URL for the desired page
                    URL url = new URL("https://raw.githubusercontent.com/vizerweb/GSyntax-PUBG/master/puffer1detek.txt"); //My text file location
                    //First open the connection
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000); // timing out in a minute

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String str;
                    while ((str = in.readLine()) != null) {
                        urls.add(str);
                    }
                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag",e.toString());
                }

                //since we are in background thread, to post results we have to go back to ui thread. do the following for that

                Remove.this.runOnUiThread(new Runnable(){
                    public void run(){

//                        TextView ultra = findViewById(R.id.Ultra);
//                        ultra.setText(urls.get(0));

                        //source dan tujuan file

                        String finaldrcsound = copypastepaks + "/" + urls.get(0);
                        String finaldrcsoundKR = copypastepaksKR + "/" + urls.get(0);
                        String finaldrcsoundVN = copypastepaksVN + "/" + urls.get(0);
                        String finaldrcsoundTW = copypasteconfigTW + "/" + urls.get(0);


                        File finaldrcsoundString = new File(finaldrcsound);
                        File finaldrcsoundStringKR = new File(finaldrcsoundKR);
                        File finaldrcsoundStringVN = new File(finaldrcsoundVN);
                        File finaldrcsoundStringTW = new File(finaldrcsoundTW);

                        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

                        // MULAIII EKSEKUSI COPY FILE!!
                            if (spinnerValue == 0) {
                                if (finaldrcsoundString.exists()) {
                                    deleteFiles(finaldrcsound);
                                    Toast.makeText(Remove.this, "Success Remove pufferpatch1 *GB", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Remove.this, "Deleted1 *GB", Toast.LENGTH_SHORT).show();
                                }
                                //VERSI KR
                            } else if (spinnerValue == 1) {
                                if (finaldrcsoundStringKR.exists()) {
                                    deleteFiles(finaldrcsoundKR);
                                    Toast.makeText(Remove.this, "Success Remove pufferpatch1 *KR", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Remove.this, "Deleted1 *KR", Toast.LENGTH_SHORT).show();
                                }
                                //VERSI VN
                            } else if (spinnerValue == 2) {
                                if (finaldrcsoundStringVN.exists()) {
                                    deleteFiles(finaldrcsoundVN);
                                    Toast.makeText(Remove.this, "Success Remove pufferpatch1 *VN", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Remove.this, "Deleted1 *VN", Toast.LENGTH_SHORT).show();
                                }
                                //VERSI TW
                            } else if (spinnerValue == 3) {

                                if (finaldrcsoundStringTW.exists()) {
                                    deleteFiles(finaldrcsoundTW);
                                    Toast.makeText(Remove.this, "Success Remove pufferpatch1 *TW", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Remove.this, "Deleted1 *TW", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                });

            }
        }).start();
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Remove.this, FullscreenActivity.class);
        startActivity(intent);
    }
}
