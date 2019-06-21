package com.mygdx.game;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import static com.mygdx.game.AndroidLauncher.OyunFrame;
import static com.mygdx.game.AndroidLauncher.OyunIciSatinButton;
import static com.mygdx.game.AndroidLauncher.contraitOyunSonu;
import static com.mygdx.game.AndroidLauncher.txtSkor;


public class Oyun extends ApplicationAdapter {

    SpriteBatch batch;
    Texture Banka,arkaplan,coin;
    OrthographicCamera kamera;
    Rectangle recBanka;
    Array<GelenNesne>Nesneler;
    int KameraGenislik;
    int KameraUzunluk;
    float accelY;
    int Skor;
    long NesneYaratmaZamani;
    static boolean OyunDurum;
    boolean OYUN_CALISTIR = true;
    boolean OYUN_DURDUR = false;
    static boolean YandiMi = false;
    @Override
    public void create () {
        Skor = 0;
        YandiMi = false;
        OyunDurum = OYUN_CALISTIR;
        KameraGenislik = 200*4;
        KameraUzunluk = 200*3;
        batch = new SpriteBatch();
        kamera = new OrthographicCamera(KameraGenislik,KameraUzunluk);
        kamera.position.set(kamera.viewportWidth/2,kamera.viewportHeight/2,0);
        arkaplan = new Texture("arka.jpg");
        Banka = new Texture("bank.png");
        recBanka = new Rectangle(KameraGenislik/2,15,90,75);
        Nesneler = new Array<GelenNesne>();
        NesneYarat();

        boolean available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        int orientation = Gdx.input.getRotation();
        Log.d("Mesaj2",orientation+"  "+available+"\n");

    }

    private void NesneYarat() {
        GelenNesne gn = new GelenNesne(MathUtils.random(0,3));
        gn.x = MathUtils.random(0,KameraGenislik);
        gn.y = KameraUzunluk;
        Nesneler.add(gn);
        Log.d("Mesaj","Nesne Yaratildi  Mod : "+gn.NesneMod);
        NesneYaratmaZamani = TimeUtils.nanoTime();

    }

    @Override
    public void render () {

            accelY = Gdx.input.getAccelerometerY();
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            kamera.update();
            batch.setProjectionMatrix(kamera.combined);
        if(OyunDurum) {
            if (recBanka.x > 0 && recBanka.x < KameraGenislik - 100) {
                recBanka.x += accelY;
            } else {
                if (recBanka.x < 50) {
                    recBanka.x = 1;
                } else if (recBanka.x > KameraGenislik - 150) {
                    recBanka.x = KameraGenislik - 101;

                }
            }
        }else{
        }
            batch.begin();

            batch.draw(arkaplan, 0, 0, KameraGenislik, KameraUzunluk);
            batch.draw(Banka, recBanka.x, recBanka.y, 100, 100);
            for (GelenNesne Nesne : Nesneler) {
                batch.draw(Nesne.NesneResim, Nesne.x, Nesne.y, Nesne.width, Nesne.height);
            }
            batch.end();
        if(OyunDurum) {

            if (TimeUtils.nanoTime() - NesneYaratmaZamani > 750000000) {
                NesneYarat();
            }
            Iterator<GelenNesne> Nesne = Nesneler.iterator();
            while (Nesne.hasNext()) {
                final GelenNesne gn = Nesne.next();
                gn.y -= gn.DusmeHizi * Gdx.graphics.getDeltaTime();
                if (gn.y + gn.width < 0) {
                    Nesne.remove();
                }
                if (recBanka.overlaps(gn)) {
                    Nesne.remove();
                    if (gn.Zararli == true) {
                            OyunDurum = OYUN_DURDUR;
                            YandiMi = true;
                            contraitOyunSonu.post(new Runnable() {
                                @Override
                                public void run() {

                                    contraitOyunSonu.setVisibility(View.VISIBLE);
                                    TextView tv = contraitOyunSonu.findViewById(R.id.txtSonuc);
                                    tv.setText("Skor : "+Skor);
                                }
                            });
                    }else{
                        txtSkor.post(new Runnable() {
                            @Override
                            public void run() {
                                Skor += gn.CoinEkle();
                                txtSkor.setText("Skor: "+Skor);
                            }
                        });
                    }
                }
            }
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}

