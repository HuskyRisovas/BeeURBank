package com.example.mathr.burb01;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.mathr.burb01.Fragments.BalancoFragment;
import com.example.mathr.burb01.Fragments.GuiasFragment;
import com.example.mathr.burb01.Fragments.PerfilFragment;
import com.example.mathr.burb01.Geral.SharedPref;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Home extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;

    SharedPref sharedPref;

    static int currentItem;

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PerfilFragment perfilFragment = new PerfilFragment();
        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
        ft3.replace(R.id.frame,perfilFragment);
        ft3.commit();

        AHBottomNavigationItem item1,item2,item3,item4,item5;
        item1 = new AHBottomNavigationItem(getResources().getString(R.string.menu_inferior_bolsa),R.drawable.ic_import_export_black_24dp);
        item2 = new AHBottomNavigationItem(getResources().getString(R.string.menu_inferior_guias),R.drawable.ic_menu_black_24dp);
        item3 = new AHBottomNavigationItem(getResources().getString(R.string.menu_inferior_perfil),R.drawable.ic_person_black_24dp);
        item4 = new AHBottomNavigationItem(getResources().getString(R.string.menu_inferior_balanco),R.drawable.ic_account_balance_wallet_black_24dp);
        item5 = new AHBottomNavigationItem(getResources().getString(R.string.menu_inferior_config),R.drawable.ic_settings_black_24dp);

        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.setCurrentItem(2);

        if(sharedPref.loadNightModeState()) {
            bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#303030"));
            bottomNavigation.setAccentColor(Color.parseColor("#FFFFFFFF"));
            bottomNavigation.setInactiveColor(Color.parseColor("#FFA5A5A5"));
        }else{
            bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
            bottomNavigation.setAccentColor(Color.parseColor("#FF131313"));
            bottomNavigation.setInactiveColor(Color.parseColor("#FF999999"));
        }
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        mAuth = FirebaseAuth.getInstance();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position)
                {
                    case 0:
                        currentItem = 0;
                        Intent bolsas = new Intent(Home.this,Bolsas.class);
                        startActivity(bolsas);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;
                    case 1:
                        currentItem = 1;
                        GuiasFragment guiasFragment = new GuiasFragment();
                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.frame,guiasFragment);
                        ft1.commit();
                        return true;
                    case 2:
                        currentItem = 2;
                        PerfilFragment perfilFragment = new PerfilFragment();
                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.frame,perfilFragment);
                        ft3.commit();
                        return true;
                    case 3:
                        currentItem = 3;
                        BalancoFragment balancoFragment = new BalancoFragment();
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.frame,balancoFragment);
                        ft4.commit();
                        return true;
                    case 4:
                        currentItem = 4;
                        Intent config = new Intent(Home.this,SettingsActivity.class);
                        startActivity(config);
                        finish();

                }
                return false;
            }
        });

        //Again check if the user is Already Logged in or Not
        if(mAuth.getCurrentUser() == null)
        {
//User NOT logged In
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

    }

}



