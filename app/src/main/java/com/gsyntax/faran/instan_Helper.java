package com.gsyntax.faran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class instan_Helper extends AppCompatActivity {

    Button FPS25, FPS30, FPS40, FPS60, Select_User, Select_Enjoy, Select_Config;
    Button Enjoy360P, Enjoy480P, Enjoy540P, Enjoy620P, Enjoy720P, Enjoy1080P;

    LinearLayout PActive, PResolusiEnjoy;

    String path = Environment.getExternalStorageDirectory() + "/GSyntax/";

    public String pathEnjoy = "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android";
    public String pathsav = "/android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames";

    public void copyAsset(String folder, String filename, String output) {
        String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + output;

        File dir = new File(dirpath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(folder + filename);
            File outFile = new File(dirpath, filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Toast.makeText(this, "saved!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed!", Toast.LENGTH_LONG).show();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void copyEnjoyAssetOnly(String folder, String folder2, String filename, String output) {
        String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + output;

        File dir = new File(dirpath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(folder + folder2 + filename);
            File outFile = new File(dirpath, filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Toast.makeText(this, "saved!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed!", Toast.LENGTH_LONG).show();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
