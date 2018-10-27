package com.example.mathr.burb01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.mathr.burb01.Geral.SharedPref;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class SettingsActivity extends AppCompatActivity {

    Spinner periodoSpinner;

    FirebaseAuth mAuth;

    SharedPref sharedPref;

    ArrayList<String> periodoItems;
    ArrayAdapter<String> periodoAdapter;

    ImageView info;

    Switch cor_app;

    FButton logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState())
        {
            setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();

        info = (ImageView) findViewById(R.id.infoActivity);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this,InfoActivity.class));
                finish();
            }
        });

        logOut = (FButton)findViewById(R.id.btnLogOut);

        cor_app = (Switch)findViewById(R.id.switch_cor_app);

        periodoItems = new ArrayList<String>();
        periodoItems.add("Mês");
        periodoItems.add("Semestre");
        periodoItems.add("Ano");

        periodoAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,periodoItems);

        periodoSpinner = (Spinner)findViewById(R.id.balancoPeriodoSpinner);

        periodoSpinner.setAdapter(periodoAdapter);

        if(sharedPref.loadNightModeState())
        {
            cor_app.setChecked(true);
        }

        cor_app.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    sharedPref.setNightModeState(true);
                    restartApp();
                }
                else{
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent inicio = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(inicio);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }

    public void restartApp()
    {
        Intent restartConfig = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(restartConfig);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Home.currentItem = 2;
        Intent home = new Intent(SettingsActivity.this,Home.class);
        startActivity(home);
        finish();
    }
}
