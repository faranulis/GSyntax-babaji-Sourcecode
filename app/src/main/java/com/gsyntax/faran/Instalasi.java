package com.gsyntax.faran;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Instalasi extends Helper {
    Button save, ind, ing;
    TextView lang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instalasi);
        final EditText enterText = findViewById(R.id.enterText);
        save = findViewById(R.id.save);
        ind = findViewById(R.id.IND);
        ing = findViewById(R.id.ING);

        //Load File UserCustom.ini
        File sdcard = Environment.getExternalStorageDirectory();
//Get the text file
        //GLOBAL VERSION
        final File file = new File(sdcard, "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/UserCustom.ini");
        //KRVERSION
        final File fileKR = new File(sdcard, "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/UserCustom.ini");
        //VNVERSION
        final File fileVN = new File(sdcard, "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/UserCustom.ini");


        //INSTANCE tombol SELECT VERSION
        final Spinner spin = findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, SelectVerison);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        //fungsi spinner
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                switch (position) {
                    case 0:
                        prefEditor.putInt("userChoiceSpinner", 0);
                        prefEditor.commit();

                        // set the selected value of the spinner
                        //Read text from file
                        StringBuilder text = new StringBuilder();
                        try {

                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = br.readLine()) != null) {
                                text.append(line);
                                text.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
//Find the view by its id
//Set the text
                        enterText.setText(text);
                        GLOBAL();
                        break;
                    case 1:
                        prefEditor.putInt("userChoiceSpinner", 1);
                        prefEditor.commit();

                        // set the selected value of the spinner
                        //Read text from file
                        StringBuilder textKR = new StringBuilder();
                        try {

                            BufferedReader br = new BufferedReader(new FileReader(fileKR));
                            String line;
                            while ((line = br.readLine()) != null) {
                                textKR.append(line);
                                textKR.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
//Find the view by its id
//Set the text
                        enterText.setText(textKR);
                        KR();
                        break;
                    case 2:
                        prefEditor.putInt("userChoiceSpinner", 2);
                        prefEditor.commit();

                        // set the selected value of the spinner
                        //Read text from file
                        StringBuilder textVN = new StringBuilder();
                        try {

                            BufferedReader br = new BufferedReader(new FileReader(fileVN));
                            String line;
                            while ((line = br.readLine()) != null) {
                                textVN.append(line);
                                textVN.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
//Find the view by its id
//Set the text
                        enterText.setText(textVN);
                        VN();
                        break;
                    case 3:
                        prefEditor.putInt("userChoiceSpinner", 3);
                        prefEditor.commit();

                        // set the selected value of the spinner
                        //Read text from file
                        StringBuilder textTW = new StringBuilder();
                        try {

                            BufferedReader br = new BufferedReader(new FileReader(fileVN));
                            String line;
                            while ((line = br.readLine()) != null) {
                                textTW.append(line);
                                textTW.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
//Find the view by its id
//Set the text
                        enterText.setText(textTW);
                        TW();
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //ngambil data spinner
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue != -1) {
            // set the selected value of the spinner
            spin.setSelection(spinnerValue);
        }

        Button Hint = findViewById(R.id.hint);
        Hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater objek2 = getLayoutInflater();
                View v2 = objek2.inflate(R.layout.instalasi_helper, null);
                AlertDialog.Builder a = new AlertDialog.Builder(Instalasi.this);
                a.setView(v2);
                a.show();
                a.setCancelable(true);
            }
        });
    }

    public void GLOBAL() {
        final EditText enterText = findViewById(R.id.enterText);
        //Tombol Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().isEmpty()) {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (Build.VERSION.SDK_INT <= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Save Failed, Check Your Permission Storage", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void KR() {
        final EditText enterText = findViewById(R.id.enterText);
        //Tombol Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().isEmpty()) {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (Build.VERSION.SDK_INT <= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Save Failed, Check Your Permission Storage", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void VN() {
        final EditText enterText = findViewById(R.id.enterText);
        //Tombol Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().isEmpty()) {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (Build.VERSION.SDK_INT <= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Save Failed, Check Your Permission Storage", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void TW () {
        final EditText enterText = findViewById(R.id.enterText);
        //Tombol Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().isEmpty()) {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (Build.VERSION.SDK_INT <= 23) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/GSyntax");
                            dir.mkdir();
                            File file = new File(dir, "/BackupDevice.ini");
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(file);
                                os.write(enterText.getText().toString().getBytes());
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Save Succesfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Instalasi.this, FullscreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Save Failed, Check Your Permission Storage", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    //fuyngsi tombol how to use
    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://youtu.be/" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}