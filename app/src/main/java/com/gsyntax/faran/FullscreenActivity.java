package com.gsyntax.faran;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gsyntax.faran.Filemanager.FileChooser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import lib.folderpicker.FolderPicker;

import static android.os.Build.VERSION.SDK_INT;


public class FullscreenActivity extends Helper {
    public Button info, odpaks, soundq1, soundq2, soundq3, Instalasi, Save, rawSelect, StatusInfo, StatusInfo2, StatusInfo3;
    TextView SConfig, SSav, SGus, SPaks, SRaw, Ultra;

    public WebView Utama;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("NotifApps", "NotifyApps", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        getCurrentFirebaseToken();

        Bundle bundle = getIntent().getExtras();

        TextView textView = findViewById(R.id.text);
        TextView title = findViewById(R.id.title);

        root.mkdirs();
        rootKR.mkdirs();
        rootVN.mkdirs();
        rootTW.mkdirs();

        File mkdirGB = new File(copypasteuserengineGB + "/Config");
        File mkdirKR = new File(copypasteuserengineGB + "/Config");
        File mkdirVN = new File(copypasteuserengineGB + "/Config");
        File mkdirTW = new File(copypasteuserengineGB + "/Config");
        mkdirGB.mkdirs();
        mkdirKR.mkdirs();
        mkdirVN.mkdirs();
        mkdirTW.mkdirs();

        if(bundle != null)  {
            title.setText(bundle.getString("title"));
            textView.setText(bundle.getString("message"));
        }
    }


    public void getCurrentFirebaseToken(){

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
//ngambil data spinner
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue != -1) {
            // set the selected value of the spinner
            spin.setSelection(spinnerValue);
        }

        InfoLayer =  findViewById(R.id.LayerInfo);
        info = (Button) findViewById(R.id.info);
        rawSelect = findViewById(R.id.selectraw);
        odpaks = (Button) findViewById(R.id.deletekodpack);
        soundq1 = findViewById(R.id.sound1);
        soundq2 = findViewById(R.id.sound2);
        soundq3 = findViewById(R.id.sound3);
        StatusInfo = findViewById(R.id.infostatus);
        StatusInfo2 = findViewById(R.id.infostatus2);
        StatusInfo3 = findViewById(R.id.infostatus3);
        Utama = findViewById(R.id.Utama);

        SConfig = findViewById(R.id.SConfig);
        SSav = findViewById(R.id.SSav);
        SGus = findViewById(R.id.SGus);
        SPaks = findViewById(R.id.SPaks);
        SRaw = findViewById(R.id.SRaw);
        Ultra = findViewById(R.id.Ultra);

        SConfig.setVisibility(View.GONE);
        SSav.setVisibility(View.GONE);
        SGus.setVisibility(View.GONE);
        SPaks.setVisibility(View.GONE);
        SRaw.setVisibility(View.GONE);
        StatusInfo2.setVisibility(View.GONE);
        StatusInfo3.setVisibility(View.GONE);

        InfoLayer.setVisibility(View.GONE);

        Save = findViewById(R.id.saveid);

        Instalasi = findViewById(R.id.instalasi);

        //Cek Status Config
        cekStatus();
        // FinalCustom
        DEBUG();

        final ProgressBar spin2 = findViewById(R.id.spin);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                this.selesai();
                spin2.setVisibility(View.GONE);
            }
            private void selesai(){
                //auto
            }
        },2000);

        final WebView webView = (WebView) findViewById(R.id.Utama);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUserAgentString(null);

        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //------------------------------//
        //next aktifity
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //deteksi dalam html apakah ada (string) maka akan berubah fungsi ke sini dan di deklarasi
                if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    view.loadUrl(url);
                }
                if (url.startsWith("https://www.you")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.google.android.youtube");
                    startActivity(intent);
                }
                return false;
            }
        });
        webView.loadUrl("https://www.faranramdan.com/p/list-config-pubg-mobile-update-tiap.html");
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimetype);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                url, contentDisposition, mimetype));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }
        });

        //download compleate
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {
                Toast.makeText(ctxt, "Download Finish" + "", Toast.LENGTH_SHORT).show();
            }
        };
        //register receiver for when .apk download is compete
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Button LayerConfig = findViewById(R.id.select_Config);
        LayerConfig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater objek2 = getLayoutInflater();
                View v2 = objek2.inflate(R.layout.select_configlayer, null);
                AlertDialog.Builder a = new AlertDialog.Builder(FullscreenActivity.this);
                a.setView(v2);
                a.show();
                a.setCancelable(true);
            }
        });

        Button LayerSound = findViewById(R.id.sound);
        LayerSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater objek2 = getLayoutInflater();
                View v2 = objek2.inflate(R.layout.select_soundlayer, null);
                AlertDialog.Builder a = new AlertDialog.Builder(FullscreenActivity.this);
                a.setView(v2);
                a.show();
                a.setCancelable(true);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater objek2 = getLayoutInflater();
                View v2 = objek2.inflate(R.layout.infoapk, null);
                AlertDialog.Builder a = new AlertDialog.Builder(FullscreenActivity.this);
                a.setView(v2);
                a.show();
                a.setCancelable(true);
            }
        });

        Instalasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullscreenActivity.this, Instalasi.class);
                startActivity(intent);
                finish();
            }
        });

        final Button Remove = findViewById(R.id.remove);
        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullscreenActivity.this, Remove.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void percobaan(View view) throws IOException {
        Toast.makeText(this, "tombol klik", Toast.LENGTH_SHORT).show();

        deleteRecursive(new File(Environment.getExternalStorageDirectory()+"/android/data/kitkat.txt"));

        File appvc = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "android/data/com.tencent.ig/files");

        if (appvc.isDirectory()) {
            String[] children = appvc.list();
            for (int i = 0; i < children.length; i++) {
                new File(appvc, children[i]).delete();
            }
        }
    }

    public void savefile(URI sourceuri)
    {
        String sourceFilename= sourceuri.getPath();
        String destinationFilename = android.os.Environment.getExternalStorageDirectory().getPath()+File.separatorChar+"android/data/com.tencent.ig/abc.mp3";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while(bis.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteDirectory( File file ) throws IOException, InterruptedException {

        if ( file.exists() ) {

            String deleteCommand = "rm -rf " + file.getAbsolutePath();
            Runtime runtime = Runtime.getRuntime();

            Process process = runtime.exec( deleteCommand );
            process.waitFor();

            return true;
        }

        return false;

    }

    public static void deleteRecursive(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                File temp = new File(dir, children[i]);
                deleteRecursive(temp);
            }

        }

        if (dir.delete() == false)
        {
            Log.d("DeleteRecursive", "DELETE FAIL");
        }
    }

    public void sound1(View view){
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

        //GLOBAL VERSION
        if (spinnerValue == 0) {
            try {
                // jika folder blum tersedia
                if (!root.exists()) {
                    root.mkdirs();
                }
                //mulai eksekusi
                File filepath = new File(root, soundq);  // file path to save
                FileWriter writer = new FileWriter(filepath);
                writer.append("[SoundQuality]\n" +
                        "+CVars=SoundQualityType=0"); //write isi file
                writer.flush();
                writer.close();
                Toast.makeText(getApplicationContext(), "Success Change Global Low Audio", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (spinnerValue == 1) {
            try {
                // jika folder blum tersedia
                if (!rootKR.exists()) {
                    rootKR.mkdirs();
                }
                //mulai eksekusi
                File filepath = new File(rootKR, soundq);  // file path to save
                FileWriter writer = new FileWriter(filepath);
                writer.append("[SoundQuality]\n" +
                        "+CVars=SoundQualityType=0"); //write isi file
                writer.flush();
                writer.close();
                Toast.makeText(getApplicationContext(), "Success Change KR Low Audio", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (spinnerValue == 2) {
            try {
                // jika folder blum tersedia
                if (!rootVN.exists()) {
                    rootVN.mkdirs();
                }
                //mulai eksekusi
                File filepath = new File(rootVN, soundq);  // file path to save
                FileWriter writer = new FileWriter(filepath);
                writer.append("[SoundQuality]\n" +
                        "+CVars=SoundQualityType=0"); //write isi file
                writer.flush();
                writer.close();
                Toast.makeText(getApplicationContext(), "Success Change VN Low Audio", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (spinnerValue == 3){
            try {
                // jika folder blum tersedia
                if (!rootTW.exists()) {
                    rootTW.mkdirs();
                }
                //mulai eksekusi
                File filepath = new File(rootTW, soundq);  // file path to save
                FileWriter writer = new FileWriter(filepath);
                writer.append("[SoundQuality]\n" +
                        "+CVars=SoundQualityType=0"); //write isi file
                writer.flush();
                writer.close();
                Toast.makeText(getApplicationContext(), "Success Change TW Low Audio", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void sound2(View view){
        if (scenjoy.exists()) {
            Toast.makeText(getApplicationContext(), "EnjoyCJZC Not Support High Sound, Pls Use Usercustom", Toast.LENGTH_LONG).show();
        } else {

            SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
            int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

            //GLOBAL VERSION
            if (spinnerValue == 0) {
                try {
                    // jika folder blum tersedia
                    if (!root.exists()) {

                        root.mkdirs();
                    }
                    //mulai eksekusi
                    File filepath = new File(root, soundq);  // file path to save
                    FileWriter writer = new FileWriter(filepath);
                    writer.append("[SoundQuality]\n" +
                            "+CVars=SoundQualityType=1"); //write isi file
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Success Change Global High Audio", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //KR VERISON
            } else if (spinnerValue == 1) {
                try {
                    // jika folder blum tersedia
                    if (!rootKR.exists()) {
                        rootKR.mkdirs();
                    }
                    //mulai eksekusi
                    File filepath = new File(rootKR, soundq);  // file path to save
                    FileWriter writer = new FileWriter(filepath);
                    writer.append("[SoundQuality]\n" +
                            "+CVars=SoundQualityType=1"); //write isi file
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Success Change KR High Audio", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //VN VERSION
            } else if (spinnerValue == 2) {
                try {
                    // jika folder blum tersedia
                    if (!rootVN.exists()) {
                        rootVN.mkdirs();
                    }
                    //mulai eksekusi
                    File filepath = new File(rootVN, soundq);  // file path to save
                    FileWriter writer = new FileWriter(filepath);
                    writer.append("[SoundQuality]\n" +
                            "+CVars=SoundQualityType=1"); //write isi file
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Success Change VN High Audio", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (spinnerValue == 3){
                try {
                    // jika folder blum tersedia
                    if (!rootTW.exists()) {
                        rootTW.mkdirs();
                    }
                    //mulai eksekusi
                    File filepath = new File(rootTW, soundq);  // file path to save
                    FileWriter writer = new FileWriter(filepath);
                    writer.append("[SoundQuality]\n" +
                            "+CVars=SoundQualityType=1"); //write isi file
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Success Change TW High Audio", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void sound3(View view){

        deteksiteksUltra_Sound();
    }

    public void selectRaw(View view) {
        Intent intent = new Intent(this, FolderPicker.class);
        startActivityForResult(intent, FOLDER_PICKER_CODE);
    }

    public void selectSav(View view) {
        Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1, REQUEST_PATH);
    }
    public void selectInstan(View view){
        Intent intent = new Intent(FullscreenActivity.this, Instan.class);
        startActivity(intent);
        finish();
    }

    //save config usercustom.ini config dan usercustom.ini di folder android
    public void buttonSave(View view) throws IOException {
        RunGame();
    }

    public void infostatus(View view) {
        SharedPreferences Config = getSharedPreferences("key2", 0);
        SConfig.setText(Config.getString("textvalue2", ""));

        SharedPreferences Sav = getSharedPreferences("key", 0);
        SSav.setText(Sav.getString("textvalue", ""));

        SharedPreferences Gus = getSharedPreferences("key3", 0);
        SGus.setText(Gus.getString("textvalue3", ""));

        SharedPreferences Paks = getSharedPreferences("key4", 0);
        SPaks.setText(Paks.getString("textvalue4", ""));

        SharedPreferences Raw = getSharedPreferences("key5", 0);
        SRaw.setText(Raw.getString("textvalue5", ""));



        SConfig.setVisibility(View.VISIBLE);
        SSav.setVisibility(View.VISIBLE);
        SGus.setVisibility(View.VISIBLE);
        SPaks.setVisibility(View.VISIBLE);
        SRaw.setVisibility(View.VISIBLE);

        StatusConfig.setVisibility(View.GONE);
        StatusActive.setVisibility(View.GONE);
        StatusGUS.setVisibility(View.GONE);
        StatusGP.setVisibility(View.GONE);
        StatusRAW.setVisibility(View.GONE);
        StatusInfo.setVisibility(View.GONE);
        StatusInfo2.setVisibility(View.VISIBLE);
        InfoLayer.setVisibility(View.VISIBLE);
        StatusInfo3.setVisibility(View.GONE);
        Utama.setVisibility(View.GONE);
    }

    public void infostatus2(View view) {
        StatusConfig.setVisibility(View.VISIBLE);
        StatusActive.setVisibility(View.VISIBLE);
        StatusGUS.setVisibility(View.VISIBLE);
        StatusGP.setVisibility(View.VISIBLE);
        StatusRAW.setVisibility(View.VISIBLE);

        SConfig.setVisibility(View.GONE);
        SSav.setVisibility(View.GONE);
        SGus.setVisibility(View.GONE);
        SPaks.setVisibility(View.GONE);
        SRaw.setVisibility(View.GONE);

        StatusInfo.setVisibility(View.GONE);
        StatusInfo2.setVisibility(View.GONE);
        StatusInfo3.setVisibility(View.VISIBLE);
    }
    public void infostatus3(View view){
        InfoLayer.setVisibility(View.GONE);
        StatusInfo.setVisibility(View.VISIBLE);
        StatusInfo2.setVisibility(View.GONE);
        StatusInfo3.setVisibility(View.GONE);
        Utama.setVisibility(View.VISIBLE);

        Utama.loadUrl("https://www.faranramdan.com/p/list-config-pubg-mobile-update-tiap.html");
    }

    public class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void setting(View view) {
        finish();
        Intent intent = new Intent(FullscreenActivity.this, setting.class);
        startActivity(intent);
    }

    //fungsi back dan deklarasi ketika ada webview
    @Override
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.Utama);
        if (webView.canGoBack()) {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

}
