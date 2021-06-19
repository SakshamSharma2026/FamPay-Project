package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codewithshadow.fampay.adapter.AppDescriptionSliderAdapter;
import com.codewithshadow.fampay.R;

public class OnBoardingScreenActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    CardView continueBtn;

    AppDescriptionSliderAdapter appDescriptionSliderAdapter;
    TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        //Hooks
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dots);
        continueBtn = findViewById(R.id.continue_btn);


        continueBtn.setOnClickListener(v ->{
            Intent intent = new Intent(OnBoardingScreenActivity.this,LoginActivity.class);
            startActivity(intent);
        });

        //Call Adapter
        appDescriptionSliderAdapter = new AppDescriptionSliderAdapter(this);
        viewPager.setAdapter(appDescriptionSliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    private void addDots(int position)
    {
        dots = new TextView[2];
        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(30);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0) {
            dots[position].setTextColor(getResources().getColor(R.color.black));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}