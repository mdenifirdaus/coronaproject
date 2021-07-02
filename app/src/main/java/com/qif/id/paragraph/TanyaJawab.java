package com.qif.id.paragraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class TanyaJawab extends AppCompatActivity {

    Toolbar mToolbar;
    WebView webView;
    ProgressDialog progressDialog;
    String url="https://www.covid19.go.id/tanya-jawab/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanya_jawab);

        mToolbar = (Toolbar) findViewById(R.id.toolbarTanyaJawab);

        webView = (WebView) findViewById(R.id.webviewQNA);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient(){
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("Memuat...");
//                progressDialog.show();
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                progressDialog.dismiss();
//                super.onPageFinished(view, url);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        mToolbar.setTitle("Tanya Jawab");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
