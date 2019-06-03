package com.example.tr534.wynews.splash.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tr534.wynews.MainActivity;
import com.example.tr534.wynews.R;
import com.example.tr534.wynews.splash.bean.Action;

import java.io.Serializable;

/**
 * Created by tr534 on 2018/12/20.
 */

public class WebViewActivity extends Activity {
    public  static final String  WER_ACTION="action";
    public WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Action action = (Action) getIntent().getSerializableExtra(WER_ACTION);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webview);
        //启用javaScript权限
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(action.getLink_url());
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return  true;
//            }
//        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return  true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {

            Intent intent=new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        super.onBackPressed();
    }
}
