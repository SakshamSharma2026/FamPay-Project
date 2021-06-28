package com.codewithshadow.fampay.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codewithshadow.fampay.R;

public class WebActivity extends AppCompatActivity {
    ProgressBar bar;
    WebView webView;
    ImageView backbtn;
    TextView urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webview);
        bar = findViewById(R.id.progressbar);
        backbtn = findViewById(R.id.backbtn);
        urlText = findViewById(R.id.link_text);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Intent Data

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        urlText.setText(url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        //Pregress View

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                bar.setProgress(progress);
                if (progress == 100) {
                    bar.setVisibility(View.GONE);

                } else {
                    bar.setVisibility(View.VISIBLE);

                }
            }
        });
        webView.getSettings().setDisplayZoomControls(true);
        webView.loadUrl(url);
    }
}