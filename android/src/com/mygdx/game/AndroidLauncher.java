package com.mygdx.game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.mygdx.game.MyGdxGame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AndroidLauncher extends FragmentActivity implements  AndroidFragmentApplication.Callbacks{
	static ConstraintLayout contraitOyunSonu,contraitKarsilama,contraitSatis;
	static FrameLayout OyunFrame;
	static TextView txtSkor;
	TextView TextC;
	GameFragment libgdxFragment;

	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txtSkor = findViewById(R.id.txtSkor);
		contraitOyunSonu = findViewById(R.id.constraitOyunSonu);
		contraitKarsilama = findViewById(R.id.constraitKarsilama);
		OyunFrame = findViewById(R.id.content_framelayout);
		findViewById(R.id.btnOyunBaslat).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				contraitKarsilama.setVisibility(View.INVISIBLE);
				contraitSatis.setVisibility(View.INVISIBLE);
				libgdxFragment = new GameFragment();
				getSupportFragmentManager().beginTransaction().
						add(R.id.content_framelayout, libgdxFragment).commit();
			}
		});
		TextC = findViewById(R.id.txtCoin);

		try {
			
		} catch (FileNotFoundException e) {
			Para = 0;
			CoinYazdir();

		} catch (IOException e) {
			e.printStackTrace();
		}

		findViewById(R.id.btnSatinAl).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(contraitSatis.getVisibility()==View.INVISIBLE)
					contraitSatis.setVisibility(View.VISIBLE);
				else
					contraitSatis.setVisibility(View.INVISIBLE);
			}
		});
        findViewById(R.id.btnDurDevam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	if(Oyun.YandiMi ==false)
                	if(Oyun.OyunDurum == true){
                 	   Oyun.OyunDurum = false;
              	  }else{
               	     Oyun.OyunDurum = true;
            	  }
        });
        findViewById(R.id.btnTekrar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
        findViewById(R.id.btnDevam).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


			}
		});
        findViewById(R.id.btnBitir).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				System.exit(0);
			}
		});

	}

    @Override
    public void exit() {

    }




	@
}