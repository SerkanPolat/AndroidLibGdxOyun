package com.mygdx.game;

import android.util.TimeUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class GelenNesne extends Rectangle {

    Texture NesneResim;
    boolean Zararli;
    int NesneMod;
    int DusmeHizi;
    public GelenNesne(int mod){
        NesneMod = mod;
        Random rnd = new Random();
        DusmeHizi = rnd.nextInt(200)+200;
        switch(mod){
            case 0:
                width = 64;
                height = 64;
                Zararli = false;
                NesneResim = new Texture("coin.png");
                break;
            case 1:
                width = 100;
                height = 100;
                Zararli = false;
                NesneResim = new Texture("coin.png");
                break;
            case 2:
                width = 64;
                height = 64;
                Zararli = true;
                NesneResim = new Texture("tas.png");
                break;
            case 3:
                width = 100;
                height = 100;
                Zararli = true;
                NesneResim = new Texture("tas.png");
                break;
        }


    }
    public int CoinEkle(){
        if(NesneMod==1){
            return 5;
        }else{
            return 10;
        }
    }



}
