package com.gsyntax.faran;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class Helper extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Select Version
    String[] SelectVerison = {"GLOBAL", "KR", "VN","TW"};
    public static final int RQS_OPEN_DOCUMENT_TREE = 2;
    public static final int PERMISSION_REQUEST_CODE = 1;
    //prograss dilaog
    ProgressDialog progressDoalog;
    //deklarasi publik
    public TextView StatusConfig, StatusActive, StatusGUS, StatusGP, StatusRAW, StatusInstalasi, StatusUserEngine, StatusUserGame;

    public RelativeLayout InfoLayer;

    //sound quality GLOBAL VERSION
    File root = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android");
    String soundq = "UserSettings.ini";

    //sound quality VN VERSION
    File rootKR = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android");

    //    sound quality KR VERSION
    File rootVN = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android");

    File rootTW = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android");

    //importexport
    public static final int REQUEST_PATH = 1;
    String curFileName, curDir;

    public int PICKFILE_ACTIVESAV = 1;
    public int PICKFILE_CONFIG = 1;
    public int FOLDER_PICKER_CODE = 2;

    //Switch inisialisasi
    Switch safemode, forceRungame;
    public static final String SWITCH_PARTIDOS_STATE = "switchPartidosState";
    public SharedPreferences sharedPreferences;

    public static final String SWITCH_FORCERUNGAME = "switchRunGame";
    public SharedPreferences sharedPreferencesRunGame;

    //set permission
    private static final int REQUEST_WRITE_STORAGE = 112;

    //GLOBAL VERSION
    public String copypastesav = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames";
    public String copypastepaks = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks";
    public String copypasteconfig = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android";
    public String copypasteuserengineGB = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra";

    //KR VERSION
    public String copypastesavKR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames";
    public String copypastepaksKR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks";
    public String copypasteconfigKR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android";
    public String copypasteuserengineKR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra";

    //VN VERSION
    public String copypastesavVN = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames";
    public String copypastepaksVN = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks";
    public String copypasteconfigVN = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android";
    public String copypasteuserengineVN = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra";

    //TW VERSION
    public String copypastesavTW = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames";
    public String copypastepaksTW = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks";
    public String copypasteconfigTW = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android";
    public String copypasteuserengineTW = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra";

    public String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GSyntax";
    public String paksdownload = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";

    //Cek Files
    File scactivesav = new File(path2 + "/Active.sav");
    File scusercustom = new File(path2 + "/UserCustom.ini");
    File scenjoy = new File(path2 + "/EnjoyCJZC.ini");
    File editorperproject = new File(path2 + "/EditorPerProject.ini");
    File scGU = new File(path2 + "/GameUserSettings.ini");
    File scgamepatch = new File(path2 + "/game_patch_0.17.0.11802.pak");
    File scrawdata = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
    File scinstalasi = new File(path2 + "/BackupDevice.ini");
    File scUserEngine = new File(path2 + "/UserEngine.ini");
    File scUserGame = new File(path2 + "/UserGame.ini");
    File scupdater = new File(path2 + "/Updater.ini");

    public static final String TAG = "MainActivity.java";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        safemode = findViewById(R.id.safemode);
        sharedPreferences = getSharedPreferences("myname", Context.MODE_PRIVATE);
        safemode.setChecked(sharedPreferences.getBoolean(SWITCH_PARTIDOS_STATE, false));

        forceRungame = findViewById(R.id.forcerungame);
        sharedPreferencesRunGame = getSharedPreferences("forcerungame", Context.MODE_PRIVATE);
        forceRungame.setChecked(sharedPreferencesRunGame.getBoolean(SWITCH_FORCERUNGAME, false));
    }

    //set permission android 10
    public void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, REQUEST_WRITE_STORAGE); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public void cekStatus() {
        StatusConfig = findViewById(R.id.statusConfig);
        StatusActive = findViewById(R.id.statusSav);
        StatusGUS = findViewById(R.id.statusGU);
        StatusGP = findViewById(R.id.gamepatch);
        StatusRAW = findViewById(R.id.rawstatus);
        StatusInstalasi = findViewById(R.id.statusinstalasi);
        StatusUserEngine = findViewById(R.id.Userengine);
        StatusUserGame = findViewById(R.id.UserGame);

        if (scUserEngine.exists()){
            StatusConfig.setText("UserEngine.ini");
            StatusUserEngine.setText("Installed");
        }
        if (scUserGame.exists()){
            StatusUserGame.setText("Installed");
        }
        if (scactivesav.exists()) {
            StatusActive.setText("Installed");
        }
        if (scenjoy.exists()) {
            StatusConfig.setText("EnjoyCJZC.ini");
        } else if (scusercustom.exists()) {
            StatusConfig.setText("UserCustom.ini");
        } else if (editorperproject.exists()){
            StatusConfig.setText("EditorPerProject.ini");
        }
        if (scGU.exists()) {
            StatusGUS.setText("Custom");
        }
        if (scgamepatch.exists()) {
            StatusGP.setText("Custom");
        }
        if (scrawdata.exists()) {
            StatusRAW.setText("Custom");
        }
        if (scinstalasi.exists()) {
            StatusInstalasi.setText("Installation Installed");
            StatusInstalasi.setTextColor(Color.parseColor("#5dcf7c"));
        } else {
            StatusInstalasi.setText("*Installation Not Installed");
            StatusInstalasi.setTextColor(Color.parseColor("#FF4500"));
        }
    }

    public void copypasteconfig() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // set the selected value of the spinner
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/FinalUserCustom.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/UserCustom.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Log.v(TAG, "Copy file successful.");
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/FinalUserCustom.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/UserCustom.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/FinalUserCustom.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/UserCustom.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/FinalUserCustom.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/UserCustom.ini");
            // just to take note of the location sources

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        }
    }

    public void copypastesav() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Active.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesav + "/Active.sav");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Active.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavKR + "/Active.sav");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 2) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/Active.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavVN + "/Active.sav");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/Active.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavTW + "/Active.sav");

            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void copypastetruesav() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Trueaim.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesav + "/Trueaim.sav");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Trueaim.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavKR + "/Trueaim.sav");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 2) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/Trueaim.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavVN + "/Trueaim.sav");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Trueaim.sav");
            // make sure your target location folder exists!
            File targetLocation = new File(copypastesavTW + "/Trueaim.sav");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);
                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        }
    }


    public void copypastedefaultligmass() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultLightmass.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/DefaultLightmass.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultLightmass.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/DefaultLightmass.ini");


                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultLightmass.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/DefaultLightmass.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultLightmass.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/DefaultLightmass.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
                }
    }

    public void copypasteeditorperproject() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EditorPerProject.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/EditorPerProject.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EditorPerProject.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/EditorPerProject.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EditorPerProject.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/EditorPerProject.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/EditorPerProject.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/EditorPerProject.ini");

            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void copypasteUserEngine() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserEngine.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteuserengineGB + "/Config/UserEngine.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserEngine.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteuserengineKR + "/Config/UserEngine.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserEngine.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteuserengineVN + "/Config/UserEngine.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserEngine.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteuserengineTW + "/Config/UserEngine.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void copypasteUserGame() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserGame.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/UserGame.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserGame.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/UserGame.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserGame.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/UserGame.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserGame.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/UserGame.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        }
    }

    public void copypasteupdater() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Updater.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/Updater.ini");
                    // make sure the target file exists
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Updater.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/Updater.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Copy file failed. Source file missing", Toast.LENGTH_SHORT).show();
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/Updater.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/Updater.ini");
                    // make sure the target file exists
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/Updater.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/Updater.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        }
    }

    public void copypasteUT() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserUT.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/UserUT.ini");

            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserUT.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/UserUT.ini");

            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserUT.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/UserUT.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/UserUT.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/UserUT.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        }
    }

    public void copypastedefaultcompat() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultCompat.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/DefaultCompat.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultCompat.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/DefaultCompat.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultCompat.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/DefaultCompat.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/DefaultCompat.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/DefaultCompat.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        }
    }

    public void copypastecenjoy() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EnjoyCJZC.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/EnjoyCJZC.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
            //KR VERSION ENJOY
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EnjoyCJZC.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/EnjoyCJZC.ini");
                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Toast.makeText(this, "Copy file successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
//            VN VERSION ENJOY
        } else if (spinnerValue == 2) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/EnjoyCJZC.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/EnjoyCJZC.ini");

                    if (sourceLocation.exists()) {
                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        Log.v(TAG, "Copy file successful.");
                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/EnjoyCJZC.ini");
            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/EnjoyCJZC.ini");
            if (sourceLocation.exists()) {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.v(TAG, "Copy file successful.");
            } else {
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        }
    }

    public void copypasteGameUser() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/GameUserSettings.ini");

            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfig + "/GameUserSettings.ini");

                    if (sourceLocation.exists()) {

                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();

                        Log.v(TAG, "Copy file successful.");

                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        } else if (spinnerValue == 1) {
            // the file to be moved or copied
            File sourceLocation = new File(path2 + "/GameUserSettings.ini");

            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigKR + "/GameUserSettings.ini");

                    if (sourceLocation.exists()) {

                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();

                        Log.v(TAG, "Copy file successful.");

                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        } else if (spinnerValue == 2) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/GameUserSettings.ini");

            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigVN + "/GameUserSettings.ini");

                    if (sourceLocation.exists()) {

                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();

                        Log.v(TAG, "Copy file successful.");

                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        } else if (spinnerValue == 3) {
// the file to be moved or copied
            File sourceLocation = new File(path2 + "/GameUserSettings.ini");

            // make sure your target location folder exists!
            File targetLocation = new File(copypasteconfigTW + "/GameUserSettings.ini");
                    if (sourceLocation.exists()) {

                        InputStream in = new FileInputStream(sourceLocation);
                        OutputStream out = new FileOutputStream(targetLocation);

                        // Copy the bits from instream to outstream
                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        in.close();
                        out.close();

                        Log.v(TAG, "Copy file successful.");

                    } else {
                        Log.v(TAG, "Copy file failed. Source file missing.");
                    }
        }
    }

    public void deleterawdata() {
        final String odpaks = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata";
        final String odpakskr = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata";
        final String odpaksvn = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata";
        final String odpakstw = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata";
        progressDoalog = new ProgressDialog(Helper.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please Wait...........");
        progressDoalog.setTitle("Delete Rawdata!");
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

                        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                        if (spinnerValue == 0) {
                            deleteFiles(odpaks);
                            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                            folder.mkdirs();
                        } else if (spinnerValue == 1) {
                            deleteFiles(odpakskr);
                            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                            folder.mkdirs();
                        } else if (spinnerValue == 2) {
                            deleteFiles(odpaksvn);
                            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                            folder.mkdirs();
                        } else if (spinnerValue == 3) {
                            deleteFiles(odpakstw);
                            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
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
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // See which child activity is calling us back.
        super.onActivityResult(requestCode, resultCode, data);

        //sdelect rawdata/folder
        if (requestCode == FOLDER_PICKER_CODE) {
            if (resultCode == Activity.RESULT_OK && data.hasExtra("data")) {
                String lokasi = data.getExtras().getString("data");
                File targetlokasi = new File(lokasi);

                //RAWDATANYA

                if (lokasi.contains("rawdata")){

                    SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                    if (spinnerValue == 0) {

                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                        File destinasirawdata = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            destinasirawdata.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, destinasirawdata);

                            SharedPreferences sp = getSharedPreferences("key5", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue5", lokasi);
//perintah akhir
                            sedt.commit();

                            Toast.makeText(getApplicationContext(), "Rawdata Succesfull *GB", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 1) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                        File destinasirawdata = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            destinasirawdata.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, destinasirawdata);

                            SharedPreferences sp = getSharedPreferences("key5", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue5", lokasi);
//perintah akhir
                            sedt.commit();

                            Toast.makeText(getApplicationContext(), "Rawdata Succesfull *KR", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 2) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                        File destinasirawdata = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            destinasirawdata.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, destinasirawdata);

                            SharedPreferences sp = getSharedPreferences("key5", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue5", lokasi);
//perintah akhir
                            sedt.commit();

                            Toast.makeText(getApplicationContext(), "Rawdata Succesfull *VN", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (spinnerValue == 3){
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
                        File destinasirawdata = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            destinasirawdata.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, destinasirawdata);

                            SharedPreferences sp = getSharedPreferences("key5", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue5", lokasi);
//perintah akhir
                            sedt.commit();

                            Toast.makeText(getApplicationContext(), "Rawdata Succesfull *TW", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (lokasi.contains("Config")){

                    SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                    if (spinnerValue == 0) {

                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config");
                        File path2 = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Config");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            path2.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, path2);

                            Toast.makeText(this, "Folder Config Sukses Copy *GB", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 1) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config");
                        File path2 = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Config");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            path2.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, path2);

                            Toast.makeText(this, "Folder Config Sukses Copy *KR", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 2) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config");
                        File path2 = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Config");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            path2.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, path2);

                            Toast.makeText(this, "Folder Config Sukses Copy *VN", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (spinnerValue == 3){
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config");
                        File path2 = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Config");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                            path2.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);
                            copyDirectory(targetlokasi, path2);

                            Toast.makeText(this, "Folder Config Sukses Copy *TW", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (lokasi.contains("com.tencent.ig") || lokasi.contains("com.pubg.krmobile") || lokasi.contains("com.vng.pubgmobile") || lokasi.contains("com.rekoo.pubgm")){
                    SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                    if (spinnerValue == 0) {

                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);

                            SharedPreferences sp = getSharedPreferences("key2", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue2", lokasi);
                            //perintah akhir
                            sedt.commit();

                            Toast.makeText(this, "Folder com.tencent.ig Sukses Copy *GB", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 1) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);

                            SharedPreferences sp = getSharedPreferences("key2", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue2", lokasi);
                            //perintah akhir
                            sedt.commit();

                            Toast.makeText(this, "Folder com.pubg.krmobile Sukses Copy *KR", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (spinnerValue == 2) {
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);


                            SharedPreferences sp = getSharedPreferences("key2", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue2", lokasi);
                            //perintah akhir
                            sedt.commit();

                            Toast.makeText(this, "Folder com.vng.pubgmobile Sukses Copy *VN", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (spinnerValue == 3){
                        File destinasi = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm");
                        if (destinasi == null) {
                            destinasi.mkdirs();
                        }
                        try {
                            copyDirectory(targetlokasi, destinasi);

                            Toast.makeText(this, "Folder com.rekoo.pubgm Sukses Copy *TW", Toast.LENGTH_SHORT).show();

                            SharedPreferences sp = getSharedPreferences("key2", 0);
                            SharedPreferences.Editor sedt = sp.edit();
                            sedt.putString("textvalue2", lokasi);
                            //perintah akhir
                            sedt.commit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    Toast.makeText(this, "Folder Does Not Supported Pls Contact @faranramdan for next update", Toast.LENGTH_LONG).show();
                }
            }
        }
        //select config
        else {
            if (requestCode == REQUEST_PATH) {
                if (resultCode == RESULT_OK) {
                    curFileName = data.getStringExtra("GetFileName");
                    curDir = data.getStringExtra("GetPath");

                    final String FinalPath = curDir + "/" + curFileName;

                    //enjoy
                    String Enjoy = curDir + "/" + "Enjoycjzc.ini";
                    Boolean Enjoyfinal = Enjoy.contains("Enjoy");

                    //gameusersetting
                    String GUser = curDir + "/" + "GameUserSettings.ini";
                    Boolean GUserFinal = GUser.contains("Game");

                    Uri resultUri = data.getData();

                    //eksekusi
                    //--------------------------------- Untuk Active sav
                    if (FinalPath.contains(".sav")) {

                        if (FinalPath.contains("Active.sav")){
                            if (requestCode == PICKFILE_ACTIVESAV && resultCode == Helper.RESULT_OK) {
                                if (resultCode == RESULT_OK) {

                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Active.sav");

                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                    SharedPreferences.Editor sedt = sp.edit();
                                    sedt.putString("textvalue", FinalPath);
                                    sedt.commit();


                                    try {
                                        copy(finalsource, dest);
                                        if (dest.exists()){
                                            Toast.makeText(getApplicationContext(), "Processing...", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Failed, Check Your Permission...", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Or Android Not Supported", Toast.LENGTH_LONG).show();
                                        }
                                        copypastesav();
                                        Toast.makeText(getApplicationContext(), "Active sav Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else if (FinalPath.contains("Trueaim.sav")){
                            if (requestCode == PICKFILE_ACTIVESAV && resultCode == Helper.RESULT_OK) {
                                if (resultCode == RESULT_OK) {

                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Trueaim.sav");
                                    try {
                                        copy(finalsource, dest);
                                        copypastetruesav();
                                        Toast.makeText(getApplicationContext(), "TrueAim.sav Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else if (FinalPath.contains("Cached.sav")){
                            if (requestCode == PICKFILE_ACTIVESAV && resultCode == Helper.RESULT_OK) {
                                if (resultCode == RESULT_OK) {

                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Cached.sav");
                                    try {
                                        copy(finalsource, dest);
                                        copypastetruesav();
                                        Toast.makeText(getApplicationContext(), "Cached.sav Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        //--------------------------------- Untuk Game Patch
                    } else if (FinalPath.contains("pak")) {
                            Toast.makeText(getApplicationContext(), "PUBGM 1.0 Paks Not Support In GSyntax", Toast.LENGTH_LONG).show();
                        //--------------------------------- Untuk Zip
                    } else if (FinalPath.contains(".zip")) {
                        curFileName = data.getStringExtra("GetFileName");

                        progressDoalog = new ProgressDialog(Helper.this);
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
                                        //menghapus 4 bagian string yaitu (.zip) dari belakang
                                        String curFileFinal = curFileName.substring(0, curFileName.length() - 4);

                                        //eksekusi
                                        Decompress d = new Decompress(FinalPath, curDir + "/GSyntax Unzip/" + curFileFinal + "/");
                                        d.unzip();

                                        Toast.makeText(getApplicationContext(), "Unzip Succesfull"
                                                , Toast.LENGTH_LONG).show();
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

                        //--------------------------------- Untuk Usercustom.ini
                    } else if (FinalPath.contains(".ini")) {
                        if (requestCode == PICKFILE_CONFIG && resultCode == Helper.RESULT_OK) {
                            if (resultCode == RESULT_OK) {
                                //--------------------------------- Untuk Enjoy....ini
                                if (FinalPath.contains("Enjoy") == Enjoyfinal) {
                                    if (requestCode == PICKFILE_CONFIG && resultCode == Helper.RESULT_OK) {
                                        if (resultCode == RESULT_OK) {

                                            File finalsource = new File(FinalPath);
                                            File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/EnjoyCJZC.ini");

                                            try {
                                                copy(finalsource, dest);
                                                copypastecenjoy();

                                                SharedPreferences sp = getSharedPreferences("key2", 0);
                                                SharedPreferences.Editor sedt = sp.edit();
                                                sedt.putString("textvalue2", FinalPath);
                                                //perintah akhir
                                                sedt.commit();

                                                deleteuserengine();
                                                Toast.makeText(getApplicationContext(), "File EnjoyCJZC Terpilih", Toast.LENGTH_LONG).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    //--------------------------------- Untuk Gameusersetting.ini
                                } else if (FinalPath.contains("GameUserSettings") == GUserFinal) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/GameUserSettings.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteGameUser();

                                        SharedPreferences sp = getSharedPreferences("key3", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue3", FinalPath);
//perintah akhir
                                        sedt.commit();

                                        deleteuserengine();
                                        Toast.makeText(getApplicationContext(), "File GameUserSettings Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("DefaultCompat.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/DefaultCompat.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypastedefaultcompat();

                                        SharedPreferences sp = getSharedPreferences("key2", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue2", FinalPath);
                                        //perintah akhir

                                        deleteuserengine();
                                        sedt.commit();

                                        Toast.makeText(getApplicationContext(), "File DefaultCompat Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("DefaultLightmass.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/DefaultLightmass.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypastedefaultligmass();

                                        SharedPreferences sp = getSharedPreferences("key2", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue2", FinalPath);
                                        //perintah akhir

                                        deleteuserengine();
                                        sedt.commit();

                                        Toast.makeText(getApplicationContext(), "File DefaultLightmass Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("EditorPerProject.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/EditorPerProject.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteeditorperproject();

                                        SharedPreferences sp = getSharedPreferences("key2", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue2", FinalPath);
                                        //perintah akhir

                                        deleteuserengine();
                                        sedt.commit();

                                        Toast.makeText(getApplicationContext(), "File EditorPerProject Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("UserEngine.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/UserEngine.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteUserEngine();


                                        Toast.makeText(getApplicationContext(), "File UserEngine Terpilih", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(), "Prossesing Optimize UserEngine..", Toast.LENGTH_LONG).show();

                                        SharedPreferences sp = getSharedPreferences("key2", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue2", FinalPath);

                                        //Hapus Bagian Folder "Config > Android" Karena UserWEngine Tidak Perlu Folder tersebut
                                        deleteFiles(copypasteuserengineGB + "/Saved/Config");
                                        deleteFiles(copypasteuserengineKR + "/Saved/Config");
                                        deleteFiles(copypasteuserengineVN + "/Saved/Config");
                                        deleteFiles(copypasteuserengineTW + "/Saved/Config");

                                        deleteFiles(path2 + "/UserCustom.ini");
                                        deleteFiles(path2 + "/EnjoyCJZC.ini");


                                        Toast.makeText(getApplicationContext(), "Finish..", Toast.LENGTH_SHORT).show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("UserGame.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/UserGame.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteUserGame();

                                        Toast.makeText(getApplicationContext(), "File UserGame Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("Updater.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/Updater.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteupdater();

                                        Toast.makeText(getApplicationContext(), "File Updater Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (FinalPath.contains("UserUT.ini")) {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/UserUT.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteUT();

                                        Toast.makeText(getApplicationContext(), "File UserUT.ini Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    File finalsource = new File(FinalPath);
                                    File dest = new File(Environment.getExternalStorageDirectory(), "/GSyntax/UserCustom.ini");

                                    try {
                                        copy(finalsource, dest);
                                        copypasteconfig();

                                        SharedPreferences sp = getSharedPreferences("key2", 0);
                                        SharedPreferences.Editor sedt = sp.edit();
                                        sedt.putString("textvalue2", FinalPath);
//perintah akhir
                                        deleteuserengine();
                                        sedt.commit();

                                        Toast.makeText(getApplicationContext(), "File UserCustom.ini Terpilih", Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    public class Decompress {
        private String _zipFile;
        private String _location;

        public Decompress(String zipFile, String location) {
            _zipFile = zipFile;
            _location = location;

            _dirChecker("");
        }

        public void unzip() {
            try {
                FileInputStream fin = new FileInputStream(_zipFile);
                ZipInputStream zin = new ZipInputStream(fin);
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    Log.v("Decompress", "Unzipping " + ze.getName());

                    if (ze.isDirectory()) {
                        _dirChecker(ze.getName());
                    } else {
                        FileOutputStream fout = new FileOutputStream(_location + ze.getName());
                        for (int c = zin.read(); c != -1; c = zin.read()) {
                            fout.write(c);
                        }

                        zin.closeEntry();
                        fout.close();
                    }

                }
                zin.close();
            } catch (Exception e) {
                Log.e("Decompress", "unzip", e);
            }

        }

        private void _dirChecker(String dir) {
            File f = new File(_location + dir);

            if (!f.isDirectory()) {
                f.mkdirs();
            }
        }
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }


    // If targetLocation does not exist, it will be created.
    public void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists() && !targetLocation.mkdirs()) {
                throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            // make sure the directory we plan to store the recording in exists
            File directory = targetLocation.getParentFile();
            if (directory != null && !directory.exists() && !directory.mkdirs()) {
                throw new IOException("Cannot create dir " + directory.getAbsolutePath());
            }

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    //for delete folder
    public static void deleteFiles(String path) {

        File file = new File(path);

        if (file.exists()) {
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) {
            }
        }
    }

    public void deleteGUS() {
        if (scGU.exists()) {
            new AlertDialog.Builder(Helper.this)
                    .setTitle("Do You Want To Delete GameUserSettings ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                            if (spinnerValue == 0) {
                                deleteFiles(copypasteconfig + "/GameUserSettings.ini");
                                deleteFiles(path2 + "/GameUserSettings.ini");
                            } else if (spinnerValue == 1) {
                                deleteFiles(copypasteconfigKR + "/GameUserSettings.ini");
                                deleteFiles(path2 + "/GameUserSettings.ini");
                            } else if (spinnerValue == 2) {
                                deleteFiles(copypasteconfigVN + "/GameUserSettings.ini");
                                deleteFiles(path2 + "/GameUserSettings.ini");
                            } else if (spinnerValue == 3) {
                                deleteFiles(copypasteconfigTW + "/GameUserSettings.ini");
                                deleteFiles(path2 + "/GameUserSettings.ini");
                            }
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
            Toast.makeText(getApplicationContext(), "Not use GameUserSettings", Toast.LENGTH_LONG).show();
        }
    }

    public void deletGP() {
        if (scgamepatch.exists()) {
            new AlertDialog.Builder(Helper.this)
                    .setTitle("Do You Want To Delete GamePaks ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                            if (spinnerValue == 0) {
                                deleteFiles(copypastepaks + "/game_patch_0.17.0.11802.pak");
                                deleteFiles(path2 + "/game_patch_0.17.0.11802.pak");
                            } else if (spinnerValue == 1) {
                                deleteFiles(copypastepaksKR + "/game_patch_0.17.0.11802.pak");
                                deleteFiles(path2 + "/game_patch_0.17.0.11802.pak");

                            } else if (spinnerValue == 2) {
                                deleteFiles(copypastepaksVN + "/game_patch_0.17.0.11802.pak");
                                deleteFiles(path2 + "/game_patch_0.17.0.11802.pak");
                            } else if (spinnerValue == 3){
                                deleteFiles(copypastepaksTW + "/game_patch_0.17.0.11802.pak");
                                deleteFiles(path2 + "/game_patch_0.17.0.11802.pak");
                            }

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
            Toast.makeText(getApplicationContext(), "Not use GamePaks", Toast.LENGTH_LONG).show();
        }
    }


    public void DEBUG() {

        File backupdevice = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/GSyntax/BackupDevice.ini");
        File usercustom = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/GSyntax/UserCustom.ini");

        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(backupdevice));
            BufferedReader br2 = new BufferedReader(new FileReader(usercustom));

            String line;
            String line2;

            while ((line2 = br2.readLine()) != null) {
                text.append(line2);
                text.append('\n');
            }
            text.append("\n");
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            br2.close();

            // this will create a new name everytime and unique
            // if external memory exists and folder with name Notes

            File filepath = new File(path2, "/FinalUserCustom.ini");  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append(text);
            writer.flush();
            writer.close();

            copypasteconfig();

//            debug.setText("DEBUG BETA 3.4 \n" +
//                    "GSYNTAX TESTER \n");
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
    }

    //3.6.1
    public void detekrawdata() {
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue == 0) {
            File source = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
            File dest = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
            if (source.exists()) {
                try {
                    copyDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (spinnerValue == 1) {
            File source = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
            File dest = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
            if (source.exists()) {
                try {
                    copyDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (spinnerValue == 2) {
            File source = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
            File dest = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
            if (source.exists()) {
                try {
                    copyDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (spinnerValue == 3){
            File source = new File(Environment.getExternalStorageDirectory(), "/GSyntax/rawdata");
            File dest = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/rawdata");
            if (source.exists()) {
                try {
                    copyDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }
        }
    }

    //ATTEND DO NOT CALL copypastesav(); cause active sav will be reset!!
    public void RunGame() throws IOException {
        final File cekconfig = new File(path2 + "/UserCustom.ini");
        final File cekbackupdevice = new File(path2 + "/BackupDevice.ini");

        final File ceklightmass = new File(path2 + "/DefaultLightmass.ini");
        if (ceklightmass.exists()) {
            copypastedefaultligmass();
        }

        final File cekcompat = new File(path2 + "/DefaultLightmass.ini");
        if (cekcompat.exists()) {
            copypastedefaultcompat();
        }

        final File cekuserengine = new File(path2 + "/UserEngine.ini");

        if (cekuserengine.exists()){
             copypasteUserEngine();

            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
            if (spinnerValue == 0) {
                String packageName = "com.tencent.ig";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Memakai UserEngine.ini *GB", Toast.LENGTH_LONG).show();
                finish();
            } else if (spinnerValue == 1) {
                String packageName = "com.pubg.krmobile";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Memakai UserEngine.ini *KR", Toast.LENGTH_LONG).show();
                finish();
            } else if (spinnerValue == 2) {
                String packageName = "com.vng.pubgmobile";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Memakai UserEngine.ini *VN", Toast.LENGTH_LONG).show();
                finish();
            } else if (spinnerValue == 3){
                String packageName = "com.rekoo.pubgm";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                Toast.makeText(getApplicationContext(), "Memakai UserEngine.ini *TW", Toast.LENGTH_LONG).show();
                startActivity(intent);

                finish();
            } else {
                Toast.makeText(this, "EROR CODE: RunGame() cek userengine ", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Pls Contact Developer @faranramdan", Toast.LENGTH_LONG).show();
            }

        } else {

            if (safemode.isChecked()) {
                new AlertDialog.Builder(this)

                        .setTitle("You Are Enable Safemode!")
                        .setMessage("Please Open PUBG Mobile Manually ")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//with safemode
                                //jika ada enjoycjzc
                                if (scenjoy.exists()) {
                                    SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                    if (spinnerValue == 0) {
                                        try {
                                            copypastecenjoy();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        detekrawdata();
                                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *GLOBAL", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else if (spinnerValue == 1) {
                                        try {
                                            copypastecenjoy();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        detekrawdata();
                                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *KR", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else if (spinnerValue == 2) {
                                        try {
                                            copypastecenjoy();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        detekrawdata();
                                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *VN", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else if (spinnerValue == 3){
                                        try {
                                            copypastecenjoy();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        detekrawdata();
                                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *TW", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(Helper.this, "Error System Enjoy (safemode on) pls contact dev", Toast.LENGTH_SHORT).show();
                                    }
                                    finish();
                                } else {
                                    //pakai usercustom
                                    if (cekbackupdevice.exists()) {
                                        if (cekconfig.exists()) {

                                            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                            if (spinnerValue == 0) {
                                                String packageName = "com.tencent.ig";
                                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                if (intent != null) {
                                                    try {
                                                        copypasteconfig();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *Global", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "PUBGM Global Not Installed!", Toast.LENGTH_LONG).show();
                                                }
                                            } else if (spinnerValue == 1) {
                                                String packageName = "com.pubg.krmobile";
                                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                if (intent != null) {
                                                    try {
                                                        copypasteconfig();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *KR", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "PUBGM KR Not Installed!", Toast.LENGTH_LONG).show();
                                                }
                                            } else if (spinnerValue == 2) {
                                                String packageName = "com.vng.pubgmobile";
                                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                if (intent != null) {
                                                    try {
                                                        copypasteconfig();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *VN", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "PUBGM VN Not Installed!", Toast.LENGTH_LONG).show();
                                                }
                                            } else if (spinnerValue == 3){
                                                String packageName = "com.rekoo.pubgm";
                                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                if (intent != null) {
                                                    try {
                                                        copypasteconfig();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *TW", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "PUBGM TW Not Installed!", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Please SELECT CONFIG First", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    } else {
//Force Switch No Instalasi
                                        if (forceRungame.isChecked()){
                                            if (scenjoy.exists()) {
                                                SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                                int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                                if (spinnerValue == 0) {
                                                    try {
                                                        copypastecenjoy();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *GLOBAL", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else if (spinnerValue == 1) {
                                                    try {
                                                        copypastecenjoy();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *KR", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else if (spinnerValue == 2) {
                                                    try {
                                                        copypastecenjoy();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *VN", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else if (spinnerValue == 3){
                                                    try {
                                                        copypastecenjoy();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    detekrawdata();
                                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *TW", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(Helper.this, "Error System Enjoy (safemode on) pls contact dev", Toast.LENGTH_SHORT).show();
                                                }
                                                finish();
                                            } else if (cekconfig.exists()) {
                                                SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                                int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                                if (spinnerValue == 0) {
                                                    String packageName = "com.tencent.ig";
                                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                    if (intent != null) {
                                                        try {
                                                            copypasteconfig();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        detekrawdata();
                                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *Global", Toast.LENGTH_LONG).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "PUBGM Global Not Installed!", Toast.LENGTH_LONG).show();
                                                    }
                                                } else if (spinnerValue == 1) {
                                                    String packageName = "com.pubg.krmobile";
                                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                    if (intent != null) {
                                                        try {
                                                            copypasteconfig();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        detekrawdata();
                                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *KR", Toast.LENGTH_LONG).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "PUBGM KR Not Installed!", Toast.LENGTH_LONG).show();
                                                    }
                                                } else if (spinnerValue == 2) {
                                                    String packageName = "com.vng.pubgmobile";
                                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                    if (intent != null) {
                                                        try {
                                                            copypasteconfig();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        detekrawdata();
                                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *VN", Toast.LENGTH_LONG).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "PUBGM VN Not Installed!", Toast.LENGTH_LONG).show();
                                                    }
                                                } else if (spinnerValue == 3){
                                                    String packageName = "com.rekoo.pubgm";
                                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                                    if (intent != null) {
                                                        try {
                                                            copypasteconfig();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        detekrawdata();
                                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *TW", Toast.LENGTH_LONG).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "PUBGM TW Not Installed!", Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Please SELECT CONFIG First", Toast.LENGTH_LONG).show();
                                                dialog.dismiss();
                                            }
                                        } else {
                                            installationloss();
                                        }
                                    }
                                }
                            }
                        })
                        .create().show();

                //no safemode
            } else {
                //ENJOY GLOBAL NO SAFEMODE
                if (scenjoy.exists()) {
                    SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                    if (spinnerValue == 0) {
                        String packageName = "com.tencent.ig";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                        copypastecenjoy();
                        detekrawdata();
                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *GLOBAL", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    } else if (spinnerValue == 1) {
                        String packageName = "com.pubg.krmobile";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                        copypastecenjoy();
                        detekrawdata();
                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *KR", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    } else if (spinnerValue == 2) {
                        String packageName = "com.vng.pubgmobile";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                        copypastecenjoy();
                        detekrawdata();
                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *VN", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    } else if (spinnerValue == 3) {
                        String packageName = "com.rekoo.pubgm";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                        copypastecenjoy();
                        detekrawdata();
                        Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *TW", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Helper.this, "Error System Enjoy (safemode off) pls contact dev", Toast.LENGTH_SHORT).show();
                    }
                }
                //USERCUSTOM NO SAFEMODE
                else {
                    if (cekbackupdevice.exists()) {
                        if (cekconfig.exists()) {

                            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                            if (spinnerValue == 0) {
                                String packageName = "com.tencent.ig";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    copypasteconfig();
                                    detekrawdata();
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM Not Installed!", Toast.LENGTH_LONG).show();
                                }
                                finish();
                            } else if (spinnerValue == 1) {
                                String packageName = "com.pubg.krmobile";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    copypasteconfig();
                                    detekrawdata();
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM KR Not Installed!", Toast.LENGTH_LONG).show();
                                }
                                finish();
                            } else if (spinnerValue == 2) {
                                String packageName = "com.vng.pubgmobile";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    copypasteconfig();
                                    detekrawdata();
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM VN Not Installed!", Toast.LENGTH_LONG).show();
                                }
                                finish();
                            } else if (spinnerValue == 3){
                                String packageName = "com.rekoo.pubgm";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    copypasteconfig();
                                    detekrawdata();
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM TW Not Installed!", Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Please SELECT CONFIG First", Toast.LENGTH_LONG).show();
                        }
                    } else {

                        if (forceRungame.isChecked()){
                            if (scenjoy.exists()) {
                                SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                if (spinnerValue == 0) {
                                    String packageName = "com.tencent.ig";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    copypastecenjoy();
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *GLOBAL", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else if (spinnerValue == 1) {
                                    String packageName = "com.pubg.krmobile";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    copypastecenjoy();
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *KR", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else if (spinnerValue == 2) {
                                    String packageName = "com.vng.pubgmobile";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    copypastecenjoy();
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *VN", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else if (spinnerValue == 3) {
                                    String packageName = "com.rekoo.pubgm";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    copypastecenjoy();
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai EnjoyCJZC.ini *TW", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Helper.this, "Error System Enjoy (safemode off) pls contact dev", Toast.LENGTH_SHORT).show();
                                }
                            } if (cekconfig.exists()) {

                                SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                                int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                                if (spinnerValue == 0) {
                                    String packageName = "com.tencent.ig";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    if (intent != null) {
                                        copypasteconfig();
                                        detekrawdata();
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "PUBGM Not Installed!", Toast.LENGTH_LONG).show();
                                    }
                                    finish();
                                } else if (spinnerValue == 1) {
                                    String packageName = "com.pubg.krmobile";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    if (intent != null) {
                                        copypasteconfig();
                                        detekrawdata();
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "PUBGM KR Not Installed!", Toast.LENGTH_LONG).show();
                                    }
                                    finish();
                                } else if (spinnerValue == 2) {
                                    String packageName = "com.vng.pubgmobile";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    if (intent != null) {
                                        copypasteconfig();
                                        detekrawdata();
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "PUBGM VN Not Installed!", Toast.LENGTH_LONG).show();
                                    }
                                    finish();
                                } else if (spinnerValue == 3){
                                    String packageName = "com.rekoo.pubgm";
                                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                    if (intent != null) {
                                        copypasteconfig();
                                        detekrawdata();
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "PUBGM TW Not Installed!", Toast.LENGTH_LONG).show();
                                    }
                                    finish();
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Please SELECT CONFIG First", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            installationloss();
                        }
                    }
                }
            }
        }


    }

    //FUNGSI MENGENDALIKAN RUNNABLE
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDoalog.incrementProgressBy(1);
        }
    };

    //FUNGSI ALL MAINACTIVITY.CLASS TOMBOL INFO
    public void Trakteer(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://trakteer.id/faranramdan"));
        startActivity(intent);
    }

    public void paypal(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.paypal.me/farhanul"));
        startActivity(intent);
    }

    public void Subscribeyt(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.youtube.com/channel/UCwJ7oWuLzonbdO8eC6on1Sw"));
        startActivity(intent);
    }

    public void ind(View view) {
        watchYoutubeVideo("WBkw35ken7o");
    }

    public void ing(View view) {
        watchYoutubeVideo("Bleq9LBZM34");

    }

    //instlasi
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

    //FUNGSI TOMBOL SELECT VERSION
//Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        final Spinner spin = findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();

                switch (position) {
                    case 0:
                        prefEditor.putInt("userChoiceSpinner", 0);
                        prefEditor.commit();

                        break;
                    case 1:
                        prefEditor.putInt("userChoiceSpinner", 1);
                        prefEditor.commit();

                        break;
                    case 2:
                        prefEditor.putInt("userChoiceSpinner", 2);
                        prefEditor.commit();


                        break;
                    case 3:
                        prefEditor.putInt("userChoiceSpinner", 3);
                        prefEditor.commit();


                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void globalultra(){
        try {
            // jika folder blum tersedia
            if (!root.exists()) {
                root.mkdirs();
            }
            //mulai eksekusi
            File filepath = new File(root, soundq);  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append("[SoundQuality]\n" +
                    "+CVars=SoundQualityType=2"); //write isi file
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void krultra() {
        try {
            // jika folder blum tersedia
            if (!rootKR.exists()) {
                rootKR.mkdirs();
            }
            //mulai eksekusi
            File filepath = new File(rootKR, soundq);  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append("[SoundQuality]\n" +
                    "+CVars=SoundQualityType=2"); //write isi file
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void vnultra() {
        try {
            // jika folder blum tersedia
            if (!rootVN.exists()) {
                rootVN.mkdirs();
            }
            //mulai eksekusi
            File filepath = new File(rootVN, soundq);  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append("[SoundQuality]\n" +
                    "+CVars=SoundQualityType=2"); //write isi file
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void twultra() {
        try {
            // jika folder blum tersedia
            if (!rootTW.exists()) {
                rootTW.mkdirs();
            }
            //mulai eksekusi
            File filepath = new File(rootTW, soundq);  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append("[SoundQuality]\n" +
                    "+CVars=SoundQualityType=2"); //write isi file
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deteksiteksUltra_Sound(){
        new Thread(new Runnable(){

            public void run(){
                final ArrayList<String> urls=new ArrayList<String>(); //to read each line
                //TextView t; //to show the result, please declare and find it inside onCreate()
                try {
                    // Create a URL for the desired page
                    URL url = new URL("https://raw.githack.com/vizerweb/GSyntax-PUBG/master/VUltradetek.txt"); //My text file location
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

                Helper.this.runOnUiThread(new Runnable(){
                    public void run(){

//                        TextView ultra = findViewById(R.id.Ultra);
//                        ultra.setText(urls.get(0));

                        //source dan tujuan file
                        File directorysound = new File(Environment.getExternalStorageDirectory(), "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks");
                        File finaldrcsound = new File(directorysound + "/" + urls.get(0));

                        File directorysoundKR = new File(Environment.getExternalStorageDirectory(), "/android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks");
                        File finaldrcsoundKR = new File(directorysoundKR + "/" + urls.get(0));

                        File directorysoundVN = new File(Environment.getExternalStorageDirectory(), "/android/data/com.vng.pubgmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks");
                        File finaldrcsoundVN = new File(directorysoundVN + "/" +urls.get(0));

                        File directorysoundTW = new File(Environment.getExternalStorageDirectory(), "/android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Paks");
                        File finaldrcsoundTW = new File(directorysoundTW + "/" + urls.get(0));

                        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

                        // MULAIII EKSEKUSI COPY FILE!!
                        if (scenjoy.exists()) {
                            Toast.makeText(getApplicationContext(), "EnjoyCJZC Not Support Ultra Sound", Toast.LENGTH_LONG).show();

                            //jika gak pake enjoycjzc maka
                        } else {
                            if (spinnerValue == 0) {

                                if (finaldrcsound.exists()) {
                                    Toast.makeText(Helper.this, "Sukses Change Ultra Sound *Global", Toast.LENGTH_SHORT).show();
                                    globalultra();
                                } else {
                                    globalultra();

                                    File folderdownloadexist = new File(paksdownload + "/" +urls.get(0));
                                    if (folderdownloadexist.exists()){

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 5000ms
                                                File sourceLocation = new File(paksdownload + "/" + urls.get(0));
                                                File targetLocation = new File(copypastepaks + "/" + urls.get(0));

                                                try {
                                                    // make sure the target file exists
                                                    if (sourceLocation.exists()) {
                                                        InputStream in = new FileInputStream(sourceLocation);
                                                        OutputStream out = new FileOutputStream(targetLocation);
                                                        // Copy the bits from instream to outstream
                                                        byte[] buf = new byte[1024];
                                                        int len;

                                                        while ((len = in.read(buf)) > 0) {
                                                            out.write(buf, 0, len);
                                                        }
                                                        in.close();
                                                        out.close();

                                                    }
                                                } catch (NullPointerException e) {
                                                    e.printStackTrace();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 2500);
                                        Toast.makeText(Helper.this, "Copying Ultra Sound File From Download Folder *Global", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Intent intent = new Intent(Helper.this, ultrasound.class);
                                        startActivity(intent);
                                    }
                                }

                                //VERSI KR
                            } else if (spinnerValue == 1) {

                                if (finaldrcsoundKR.exists()) {
                                    krultra();
                                    Toast.makeText(Helper.this, "Sukses Change Ultra Sound *KR", Toast.LENGTH_SHORT).show();
                                } else {
                                    vnultra();

                                    File folderdownloadexist = new File(paksdownload + "/" +urls.get(0));
                                    if (folderdownloadexist.exists()){

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 5000ms
                                                File sourceLocation = new File(paksdownload + "/" + urls.get(0));
                                                File targetLocation = new File(copypastepaksKR + "/" + urls.get(0));

                                                try {
                                                    // make sure the target file exists
                                                    if (sourceLocation.exists()) {
                                                        InputStream in = new FileInputStream(sourceLocation);
                                                        OutputStream out = new FileOutputStream(targetLocation);
                                                        // Copy the bits from instream to outstream
                                                        byte[] buf = new byte[1024];
                                                        int len;

                                                        while ((len = in.read(buf)) > 0) {
                                                            out.write(buf, 0, len);
                                                        }
                                                        in.close();
                                                        out.close();

                                                    }
                                                } catch (NullPointerException e) {
                                                    e.printStackTrace();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 2500);
                                        Toast.makeText(Helper.this, "Copying Ultra Sound File From Download Folder *KR", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Intent intent = new Intent(Helper.this, ultrasound.class);
                                        startActivity(intent);
                                    }

                                }
                                //VERSI VN
                            } else if (spinnerValue == 2) {
                                if (finaldrcsoundVN.exists()) {
                                    vnultra();
                                    Toast.makeText(Helper.this, "Sukses Change Ultra Sound *VN", Toast.LENGTH_SHORT).show();
                                } else {
                                    vnultra();

                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 5s = 5000ms
                                            File folderdownloadexist = new File(paksdownload + "/" +urls.get(0));
                                            if (folderdownloadexist.exists()){
                                                File sourceLocation = new File(paksdownload + "/" + urls.get(0));
                                                // make sure your target location folder exists!
                                                File targetLocation = new File(copypastepaksVN + "/" + urls.get(0));

                                                try {
                                                    // 1 = move the file, 2 = copy the file
                                                    int actionChoice = 2;
                                                    // moving the file to another directory
                                                    if (actionChoice == 1) {
                                                        if (sourceLocation.renameTo(targetLocation)) {
                                                        } else {
                                                        }
                                                    }
                                                    // we will copy the file
                                                    else {
                                                        // make sure the target file exists
                                                        if (sourceLocation.exists()) {
                                                            InputStream in = new FileInputStream(sourceLocation);
                                                            OutputStream out = new FileOutputStream(targetLocation);
                                                            // Copy the bits from instream to outstream
                                                            byte[] buf = new byte[1024];
                                                            int len;

                                                            while ((len = in.read(buf)) > 0) {
                                                                out.write(buf, 0, len);
                                                            }
                                                            in.close();
                                                            out.close();
                                                            Log.v(TAG, "Move Ultra Sound Sukses *VN.");

                                                        } else {
                                                            Log.v(TAG, "Move Ultra Sound Failed *VN.");
                                                        }

                                                    }

                                                } catch (NullPointerException e) {
                                                    e.printStackTrace();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                Intent intent = new Intent(Helper.this, ultrasound.class);
                                                startActivity(intent);
                                            }
                                            Toast.makeText(Helper.this, "Copying Ultra Sound File From Download Folder *VN", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 2500);

                                }
                                //VERSI TW
                            } else if (spinnerValue == 3) {

                                if (finaldrcsoundTW.exists()) {
                                    twultra();
                                    Toast.makeText(Helper.this, "Sukses Change Ultra Sound *TW", Toast.LENGTH_SHORT).show();
                                } else {
                                    twultra();

                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 5s = 5000ms
                                            File folderdownloadexist = new File(paksdownload + "/" +urls.get(0));
                                            if (folderdownloadexist.exists()){
                                                File sourceLocation = new File(paksdownload + "/" + urls.get(0));
                                                // make sure your target location folder exists!
                                                File targetLocation = new File(copypastepaksTW + "/" + urls.get(0));

                                                try {
                                                    // 1 = move the file, 2 = copy the file
                                                    int actionChoice = 2;
                                                    // moving the file to another directory
                                                    if (actionChoice == 1) {
                                                        if (sourceLocation.renameTo(targetLocation)) {
                                                        } else {
                                                        }
                                                    }
                                                    // we will copy the file
                                                    else {
                                                        // make sure the target file exists
                                                        if (sourceLocation.exists()) {
                                                            InputStream in = new FileInputStream(sourceLocation);
                                                            OutputStream out = new FileOutputStream(targetLocation);
                                                            // Copy the bits from instream to outstream
                                                            byte[] buf = new byte[1024];
                                                            int len;

                                                            while ((len = in.read(buf)) > 0) {
                                                                out.write(buf, 0, len);
                                                            }
                                                            in.close();
                                                            out.close();
                                                            Log.v(TAG, "Move Ultra Sound Sukses *TW.");

                                                        } else {
                                                            Log.v(TAG, "Move Ultra Sound Failed *TW.");
                                                        }

                                                    }

                                                } catch (NullPointerException e) {
                                                    e.printStackTrace();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                Intent intent = new Intent(Helper.this, ultrasound.class);
                                                startActivity(intent);
                                            }
                                            Toast.makeText(Helper.this, "Copying Ultra Sound File From Download Folder *TW", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 2500);

                                }


                            }
                        }
                    }
                });

            }
        }).start();
    }

    public void installationloss(){
        final File cekconfig = new File(path2 + "/UserCustom.ini");
        new AlertDialog.Builder(Helper.this)
                .setTitle("YOU NOT SAVE INSTALLATION!")
                .setMessage("if installation missing some devices don't work")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cekconfig.exists()) {

                            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
                            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
                            if (spinnerValue == 0) {
                                String packageName = "com.tencent.ig";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    try {
                                        copypasteconfig();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *Global", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM Global Not Installed!", Toast.LENGTH_LONG).show();
                                }
                            } else if (spinnerValue == 1) {
                                String packageName = "com.pubg.krmobile";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    try {
                                        copypasteconfig();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *KR", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM KR Not Installed!", Toast.LENGTH_LONG).show();
                                }
                            } else if (spinnerValue == 2) {
                                String packageName = "com.vng.pubgmobile";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    try {
                                        copypasteconfig();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *VN", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM VN Not Installed!", Toast.LENGTH_LONG).show();
                                }
                            } else if (spinnerValue == 3){
                                String packageName = "com.rekoo.pubgm";
                                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                                if (intent != null) {
                                    try {
                                        copypasteconfig();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    detekrawdata();
                                    Toast.makeText(getApplicationContext(), "Memakai UserCustom.ini *TW", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "PUBGM TW Not Installed!", Toast.LENGTH_LONG).show();
                                }
                            }

                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please SELECT CONFIG First", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Please Installation First", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Helper.this, Instalasi.class);
                        startActivity(intent);
                    }
                })
                .create().show();
    }

    public void deleteuserengine(){
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

        File targetGB = new File(copypasteuserengineGB + "/Config");
        File targetKR = new File(copypasteuserengineKR + "/Config");
        File targetVN = new File(copypasteuserengineVN + "/Config");
        File targetTW = new File(copypasteuserengineTW + "/Config");

        if (spinnerValue == 0) {
            if(targetGB.exists()){
deleteFiles(copypasteuserengineGB + "/Config");
            }

        } else if (spinnerValue == 1) {
            if(targetKR.exists()){
                deleteFiles(copypasteuserengineKR + "/Config");
            }

        } else if (spinnerValue == 2) {
            if(targetVN.exists()){
                deleteFiles(copypasteuserengineVN + "/Config");
            }

        } else if (spinnerValue == 3){
            if(targetTW.exists()){
                deleteFiles(copypasteuserengineTW + "/Config");
            }

        }
    }
}


