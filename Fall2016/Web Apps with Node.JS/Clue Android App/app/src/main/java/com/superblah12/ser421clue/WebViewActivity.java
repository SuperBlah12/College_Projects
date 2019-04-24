package com.superblah12.ser421clue;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {


    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        WebAppInterface webAppInterface = new WebAppInterface(this);
        webAppInterface.setUsername(intent.getStringExtra(MainActivity.USERNAME));

        mWebView = (WebView) findViewById(R.id.activity_web_view_webview);
        mWebView.addJavascriptInterface(webAppInterface, "Android");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl("file:///android_asset/www/clue.html");
    }

    public class WebAppInterface {
        Context mContext;
        String username = "";

        public void setUsername(String username) {
            this.username = username;
        }

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getUsername() {
            return username;
        }

        @JavascriptInterface
        public String getRecord() {
            return MainActivity.RECORD;
        }

        @JavascriptInterface
        public void revertToNameEntry(String last_game, String record) {
            MainActivity.RECORD = record;
            MainActivity.LAST_GAME = last_game;
            Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
