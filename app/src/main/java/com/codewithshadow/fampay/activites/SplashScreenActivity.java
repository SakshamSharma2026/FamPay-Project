package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.codewithshadow.fampay.R;

public class SplashScreenActivity extends AppCompatActivity {
    Handler mhandler;
    Runnable mrunnable;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        mrunnable = () -> {

            sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
            boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
            Intent intent=new Intent(SplashScreenActivity.this, OnBoardingScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            if (isFirstTime){
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("firstTime",false);
//                editor.apply();
//
//            }
//            else {
//                Intent intent=new Intent(SplashScreenActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
            finish();
        };
        mhandler = new Handler();
        mhandler.postDelayed(mrunnable,1000);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mhandler!=null && mrunnable!=null) {
            mhandler.removeCallbacks(mrunnable);
        }
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
