package com.rahulprasad.logisticsoptimizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.rahulprasad.logisticsoptimizer.adapter.IntroViewPagerAdapter;
import com.rahulprasad.logisticsoptimizer.model.Intro;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {

    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabLayout;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_on_boarding);

        final List<Intro> introList =new  ArrayList<>();

        introList.add(new Intro("Hauling Infinite miles","dummy of description dummy of description dummy of description dummy of description", R.drawable.one));
         introList.add(new Intro("Rerouting for 100% loadability","dummy of description dummy of description dummy of description dummy of description", R.drawable.onboarding2));
         introList.add(new Intro("We Plan to help you Execute","dummy of description dummy of description dummy of description dummy of description", R.drawable.oboarding3));


        final ViewPager viewPager = findViewById(R.id.screenPager);
        final Button next = findViewById(R.id.button);
        tabLayout = findViewById(R.id.tabIndicator);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, introList);
        viewPager.setAdapter(introViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        position = viewPager.getCurrentItem();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < introList.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if(position == introList.size()){
                    savePrefData();
                    Intent intent = new Intent(OnBoarding.this,Home.class);
                    startActivity(intent);
                }


            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    position = tab.getPosition();
                 if(tab.getPosition() == introList.size() - 1){
                     next.setText("Get Started");
                 }
                 else{
                     next.setText("Next");

                 }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void savePrefData(){

        SharedPreferences sharedPreferences =   getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFristTimeLaunch", true);
        editor.apply();

    }

    private boolean restorePref(){

        SharedPreferences sharedPreferences =   getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isFristTimeLaunch", false);
    }
}
