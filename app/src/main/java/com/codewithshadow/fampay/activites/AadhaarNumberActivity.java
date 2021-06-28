package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.codewithshadow.fampay.R;

public class AadhaarNumberActivity extends AppCompatActivity {
    CardView continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar_number);

        //Hooks
        initializeViews();

        //Continue Button for  sending the user to next activity
        continueBtn.setOnClickListener( v -> {
            Intent intent = new Intent(this,ApiScreenActivity.class);
            startActivity(intent);
        });
    }

    private void initializeViews() {
        continueBtn = findViewById(R.id.continue_btn);
    }
}