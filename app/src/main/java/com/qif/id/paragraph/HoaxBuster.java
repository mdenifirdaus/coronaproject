package com.qif.id.paragraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

/**
 * author:
 * M Deni Firdaus - Paragraph.id
 * 2020
 */

public class HoaxBuster extends AppCompatActivity {

    Toolbar mToolbar;
    WebView webViewHoax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoax_buster);

        mToolbar = (Toolbar) findViewById(R.id.toolbarHoaxBus);
        webViewHoax = (WebView) findViewById(R.id.webViewHoax);

        String url="https://covid19.go.id/p/hoax-buster";
        webViewHoax.getSettings().setJavaScriptEnabled(true);
        webViewHoax.loadUrl(url);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        mToolbar.setTitle("Hoax Buster");
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
