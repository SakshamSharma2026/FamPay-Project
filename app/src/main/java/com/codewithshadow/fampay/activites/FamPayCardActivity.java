package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codewithshadow.fampay.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class FamPayCardActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    EasyFlipView flipView;
    EditText firstName,lastName,date;
    CardView continueBtn;
    TextView number;
    String stringPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_pay_card);

        //Hooks
        initializeViews();



        //Intent
        Intent intent = getIntent();
        stringPhoneNumber = intent.getStringExtra("number");

        //Set Phone Number
        number.setText("+91 " + stringPhoneNumber);

        //Continue Button for  sending the user to next activity
        continueBtn.setOnClickListener( v -> {
            Intent intent1= new Intent(this,HelloActivity.class);
            startActivity(intent1);
        });
        
    }

    private void initializeViews() {
        linearLayout = findViewById(R.id.ll);
        flipView = findViewById(R.id.flipCard);
        firstName = findViewById(R.id.name);
        lastName = findViewById(R.id.last_name);
        date = findViewById(R.id.date);
        continueBtn = findViewById(R.id.continue_btn);
        number = findViewById(R.id.number);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Flip the FamPay Card
        flipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {

                    //Check the side of the card
                if (easyFlipView.isBackSide()){
                    linearLayout.setVisibility(View.GONE);
                    flipView.setFlipOnTouch(false);
                    InputMethodManager inputMethodManager =  (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInputFromWindow(firstName.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                    firstName.requestFocus();

                }
                else{
                    flipView.setFlipOnTouch(true);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        //Check the side of the card
        if (flipView.isFrontSide()){
            super.onBackPressed();
            finish();
        }else{
            flipView.flipTheView();
        }
    }
}