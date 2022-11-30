package com.gsyntax.faran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Instan extends com.gsyntax.faran.instan_Helper {

Button klasik360;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ginstan);

        FPS25 = findViewById(R.id.fps25);
        FPS30 = findViewById(R.id.fps30);
        FPS40 = findViewById(R.id.fps40);
        FPS60 = findViewById(R.id.fps60);

        PActive = findViewById(R.id.pActive);
        PResolusiEnjoy = findViewById(R.id.pResolusiEnjoy);

        Enjoy360P = findViewById(R.id.ec360);
        Enjoy480P = findViewById(R.id.ec480);
        Enjoy540P = findViewById(R.id.ec540);
        Enjoy620P = findViewById(R.id.ec620);
        Enjoy720P = findViewById(R.id.ec720);
        Enjoy1080P = findViewById(R.id.ec1080);

        //Hilangin awal
        PActive.setVisibility(View.VISIBLE);
        PResolusiEnjoy.setVisibility(View.GONE);

        FPS25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyAsset("25FPS/", "Active.sav", "/Gsyntax");
                copyAsset("25FPS/", "Active.sav", pathsav);

                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue", "With GInstan 25FPS");
                sedt.commit();
                PActive.setVisibility(View.GONE);
                PResolusiEnjoy.setVisibility(View.VISIBLE);
            }
        });

        FPS30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyAsset("30FPS/", "Active.sav", "/Gsyntax");
                copyAsset("30FPS/", "Active.sav", pathsav);
                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue", "With GInstan 30FPS");
                sedt.commit();
                PActive.setVisibility(View.GONE);
                PResolusiEnjoy.setVisibility(View.VISIBLE);
            }
        });
        FPS40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyAsset("40FPS/", "Active.sav", "/Gsyntax");
                copyAsset("40FPS/", "Active.sav", pathsav);
                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue", "With GInstan 40FPS");
                sedt.commit();
                PActive.setVisibility(View.GONE);
                PResolusiEnjoy.setVisibility(View.VISIBLE);
            }
        });
        FPS60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyAsset("60FPS/", "Active.sav", "/Gsyntax");
                copyAsset("60FPS/", "Active.sav", pathsav);
                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue", "With GInstan 60FPS");
                sedt.commit();

                PActive.setVisibility(View.GONE);
                PResolusiEnjoy.setVisibility(View.VISIBLE);
            }
        });

        //----------------- ENJOYCJZC --------
        Enjoy360P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "360p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "360p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 360P");
                sedt.commit();
            }
        });
        Enjoy480P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "480p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "480p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 480P");
                sedt.commit();
            }
        });
        Enjoy540P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "540p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "540p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 540P");
                sedt.commit();
            }
        });
        Enjoy620P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "620p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "620p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 620P");
                sedt.commit();
            }
        });
        Enjoy720P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "720p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "720p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 720P");
                sedt.commit();
            }
        });
        Enjoy1080P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyEnjoyAssetOnly("Enjoy/", "1080p/", "EnjoyCJZC.ini", "/Gsyntax");
                copyEnjoyAssetOnly("Enjoy/", "1080p/", "EnjoyCJZC.ini", pathEnjoy);

                SharedPreferences sp = getSharedPreferences("key2", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue2", "With GInstan Enjoycjzc 1080P");
                sedt.commit();
            }
        });

    }
    //----------------------------------------------------

//    public void klasik360(View view){
//        copyEnjoyAssetOnly("360P/", "60FPS/", "classic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("360P/", "60FPS/", "classic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Classic 360P");
//        sedt.commit();
//    }
//
//    public void colorful360(View view) {
//        copyEnjoyAssetOnly("360P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("360P/", "60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Colorfull 360P");
//        sedt.commit();
//    }
//
//    public void movie360(View view) {
//        copyEnjoyAssetOnly("360P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("360P/", "60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Movie 360P");
//        sedt.commit();
//    }
//
//    public void soft360(View view) {
//        copyEnjoyAssetOnly("360P/", "60FPS/", "soft.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("360P/", "60FPS/", "soft.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Soft 360P");
//        sedt.commit();
//    }
//
//    public void realistik360(View view) {
//        copyEnjoyAssetOnly("360P/", "60FPS/", "realistic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("360P/", "60FPS/", "realistic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Realistic 360P");
//        sedt.commit();
//    }
//
//    //111111111 480P
//    public void klasik480(View view){
//        copyEnjoyAssetOnly("480P/", "60FPS/", "classic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("480P/","60FPS/", "classic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Classic 480P");
//        sedt.commit();
//    }
//    public void colorful480(View view) {
//        copyEnjoyAssetOnly("480P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("480P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Colorfull 480P");
//        sedt.commit();
//    }
//
//    public void movie480(View view) {
//        copyEnjoyAssetOnly("480P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("480P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Movie 480P");
//        sedt.commit();
//    }
//
//    public void soft480(View view) {
//        copyEnjoyAssetOnly("480P/", "60FPS/", "soft.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("480P/", "60FPS/", "soft.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Soft 480P");
//        sedt.commit();
//    }
//
//    public void realistik480(View view) {
//        copyEnjoyAssetOnly("480P/", "60FPS/", "realistic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("480P/","60FPS/", "realistic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Realistic 480P");
//        sedt.commit();
//    }
//    //540P
//    public void klasik540(View view){
//        copyEnjoyAssetOnly("540P/", "60FPS/", "classic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("540P/","60FPS/", "classic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Classic 540P");
//        sedt.commit();
//    }
//    public void colorful540(View view) {
//        copyEnjoyAssetOnly("540P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("540P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Colorfull 540P");
//        sedt.commit();
//    }
//
//    public void movie540(View view) {
//        copyEnjoyAssetOnly("540P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("540P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Movie 540P");
//        sedt.commit();
//    }
//
//    public void soft540(View view) {
//        copyEnjoyAssetOnly("540P/", "60FPS/", "soft.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("540P/", "60FPS/", "soft.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Soft 540P");
//        sedt.commit();
//    }
//
//    public void realistik540(View view) {
//        copyEnjoyAssetOnly("540P/", "60FPS/", "realistic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("540P/","60FPS/", "realistic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Realistic 540P");
//        sedt.commit();
//    }
//
//    //620p
//    public void klasik620(View view){
//        copyEnjoyAssetOnly("620P/", "60FPS/", "classic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("620P/","60FPS/", "classic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Classic 620P");
//        sedt.commit();
//    }
//    public void colorful620(View view) {
//        copyEnjoyAssetOnly("620P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("620P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Colorfull 620P");
//        sedt.commit();
//    }
//
//    public void movie620(View view) {
//        copyEnjoyAssetOnly("620P/", "60FPS/", "colorful.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("620P/","60FPS/", "colorful.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Movie 620P");
//        sedt.commit();
//    }
//
//    public void soft620(View view) {
//        copyEnjoyAssetOnly("620P/", "60FPS/", "soft.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("620P/", "60FPS/", "soft.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Soft 620P");
//        sedt.commit();
//    }
//
//    public void realistik620(View view) {
//        copyEnjoyAssetOnly("620P/", "60FPS/", "realistic.ini", "/Gsyntax");
//        copyEnjoyAssetOnly("620P/","60FPS/", "realistic.ini",  pathEnjoy);
//
//        SharedPreferences sp = getSharedPreferences("key2", 0);
//        SharedPreferences.Editor sedt = sp.edit();
//        sedt.putString("textvalue2", "With GInstan Realistic 620P");
//        sedt.commit();
//    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Instan.this, FullscreenActivity.class);
        startActivity(intent);
    }



}
