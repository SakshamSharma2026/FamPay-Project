package com.codewithshadow.fampay.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.codewithshadow.fampay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class HelloActivity extends AppCompatActivity {
    BottomSheetBehavior bottomSheetBehavior;
    ImageView up_arrow;
    ConstraintLayout root;
    LinearLayout continueBtn;
    View bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        //Hooks
        initializeViews();


        //BottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(80);
        bottomSheetBehavior.setHideable(false);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setPeekHeight(80);
                    bottomSheet.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffSet) {

            }
        });


        //Continue Button for  sending the user to next activity
        continueBtn.setOnClickListener( v -> {
            Intent intent = new Intent(this,AadhaarNumberActivity.class);
            startActivity(intent);
        });

    }

    private void initializeViews() {
        bottomSheet = findViewById(R.id.layout);
        up_arrow = findViewById(R.id.up_arrow);
        root = findViewById(R.id.root);
        continueBtn = findViewById(R.id.continue_btn);
    }
}