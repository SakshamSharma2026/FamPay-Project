package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.codewithshadow.fampay.R;

public class VerifyOTPActivity extends AppCompatActivity {
    CardView continueBtn;
    TextView number;
    String stringPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);

        //Hooks
        initializeViews();

        //Intent
        Intent intent = getIntent();
        stringPhoneNumber = intent.getStringExtra("number");

        //Set Phone Number
        number.setText("+91 " + stringPhoneNumber);

        //Continue Button for verifying the OTP and send the user to next activity
        continueBtn.setOnClickListener( v -> {
            Intent intent1 = new Intent(this,FamPayCardActivity.class);
            intent1.putExtra("number",stringPhoneNumber);
            startActivity(intent1);
        });
    }

    private void initializeViews() {
        continueBtn = findViewById(R.id.continue_btn);
        number = findViewById(R.id.number);
    }
}