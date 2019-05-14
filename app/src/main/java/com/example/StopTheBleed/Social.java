package com.example.my_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.androidhire.splashscreen.R;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Social extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        webview =(WebView)findViewById(R.id.webView);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://www.google.com");

    }
}
