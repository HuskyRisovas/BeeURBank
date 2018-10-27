package com.example.mathr.burb01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.View.GONE;

public class InfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[] dotsTV;
    private int[] layouts;
    private Button btnPular;
    private Button btnFinalizar;

    private RelativeLayout relativeLayout;

    private InfoSliderAdapter infoSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isFirstTimeStartApp())
        {
            startMainActivity();
            finish();
        }


        setStatusBarTransparent();

        setContentView(R.layout.activity_info);
        relativeLayout = (RelativeLayout)findViewById(R.id.infoLayout);
        relativeLayout.setBackgroundResource(R.drawable.fundo2);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        layoutDot = (LinearLayout)findViewById(R.id.dotLayout);
        btnPular = (Button)findViewById(R.id.btn_pular);
        btnFinalizar = (Button)findViewById(R.id.btn_Finalizar);

        btnPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage<layouts.length)
                {
                    viewPager.setCurrentItem(currentPage);
                }else{
                    startMainActivity();
                }
            }
        });


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
        layouts =new int[]{R.layout.info_slide_1,R.layout.info_slide_2,R.layout.info_slide_3};

        infoSliderAdapter = new InfoSliderAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(infoSliderAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==layouts.length-1)
                {
                    btnPular.setText(R.string.VamoLá);
                    btnFinalizar.setVisibility(View.GONE);
                }else{
                    btnPular.setText(R.string.Proximo);
                    btnFinalizar.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }

    private boolean isFirstTimeStartApp()
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag",true);
    }

    private void setFirstTimeStartStatus(boolean stt)
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag",stt);
        editor.commit();
    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotsTV = new TextView[layouts.length];
        for(int i = 0;i<dotsTV.length;i++)
        {
            dotsTV[i] = new TextView(this);
            dotsTV[i].setText(Html.fromHtml("&#8226"));
                    dotsTV[i].setTextSize(30);
                            dotsTV[i].setTextColor(Color.parseColor("#a9b4bb"));
                            layoutDot.addView(dotsTV[i]);
        }
        if(dotsTV.length>0)
        {
            dotsTV[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void startMainActivity()
    {
        setFirstTimeStartStatus(true);
        startActivity(new Intent(InfoActivity.this,MainActivity.class));
        finish();
    }

    private void setStatusBarTransparent(){
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
