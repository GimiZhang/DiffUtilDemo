package com.example.administrator.diffutildemo;

import android.app.Activity;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class WebViewActivitys extends Activity {

    private ProgressBar pbLoading;
    private WebView mWebView;
    private Button btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manager);
        initData();
    }
    public void initData() {
//        btnJump = (Button) findViewById(R.id.btn_jumppp);
//        btnJump.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        mWebView = (WebView) findViewById(R.id.wv_webview);
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
//        mWebView.getSettings().setLoadWithOverviewMode(true);  //放大缩小
        settings.setJavaScriptEnabled(true);

        mWebView.setInitialScale(100); //初始化实时缩放
        getHtml();
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse("http://www.hejf.com"));
        request.setDestinationInExternalPublicDir("first","first.html");
        long downloadId=manager.enqueue(request);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(WebViewActivitys.this,"加载开始",Toast.LENGTH_SHORT).show();
                pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.GONE);
            }
        });
        String mUrl = "http://www.hejf.com";
//        String mUrl = "http://192.168.1.20:8899/views/quality/fastResponseList.xhtml";
//        http://192.168.1.20:8899/views/mi/kpiTurnoverRateAnalyze.xhtml
        mWebView.loadUrl(mUrl);
    }

    public boolean getHtml(){

        File folder = new File("first");
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();

    }


}
