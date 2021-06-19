package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.codewithshadow.fampay.R;

public class LoginActivity extends AppCompatActivity {
    CardView continueBtn;
    TextView tandc;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Hooks
        initializeViews();


        //Terms and Conditions
        String text2 = getResources().getString(R.string.terms_and_conditions);
        SpannableString ss2 = new SpannableString(text2);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan fcsRed2 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.dark_blue));
        ss2.setSpan(styleSpan2, 50, 71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(fcsRed2, 50, 71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tandc.setText(ss2);


        //Continue Button for sending the mobile number to next activity
        continueBtn.setOnClickListener( v -> {
            if (TextUtils.isEmpty(number.getText().toString())){
                number.setError("Enter Number");
                number.requestFocus();
            }else{
                Intent intent = new Intent(this,VerifyOTPActivity.class);
                intent.putExtra("number",number.getText().toString());
                startActivity(intent);
            }

        });
    }

    private void initializeViews() {
        tandc = findViewById(R.id.tandc);
        number = findViewById(R.id.number);
        continueBtn = findViewById(R.id.continue_btn);
    }
}