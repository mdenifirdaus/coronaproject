package com.qif.id.paragraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.qif.id.paragraph.Model.NotifAdapter;
import com.qif.id.paragraph.Model.NotifModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * author:
 * M Deni Firdaus - Paragraph.id
 * 2020
 */
public class Notif extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar mToolbar;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    NotifAdapter notifAdapter;
    RelativeLayout mLayoutNoNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        mToolbar = (Toolbar) findViewById(R.id.toolbarNotif);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNotif);
        mLayoutNoNotif = (RelativeLayout) findViewById(R.id.layoutNotNotifikasi);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        mToolbar.setTitle("Notifikasi");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        GetNotif(this);
    }

    private void GetNotif(final Context context){
        try {

            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Mohon Tunggu...");
            progressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TargetDomain.ParagraphDomainAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIs apIs = retrofit.create(APIs.class);
            Call<List<NotifModel>> modelCall = apIs.GetNotif();
            modelCall.enqueue(new Callback<List<NotifModel>>() {
                @Override
                public void onResponse(Call<List<NotifModel>> call, Response<List<NotifModel>> response) {
                    List<NotifModel> arr = response.body();
                    if (arr.size() > 0) {
                        mLayoutNoNotif.setVisibility(View.GONE);
                        notifAdapter = new NotifAdapter(context,arr);
                        recyclerView.setAdapter(notifAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }else {
                        recyclerView.setVisibility(View.GONE);
                        mLayoutNoNotif.setVisibility(View.VISIBLE);
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<NotifModel>> call, Throwable t) {
                    String err = t.toString();
                    progressDialog.dismiss();
                }
            });

        } catch (Exception ex) {
            String err = ex.toString();
            progressDialog.dismiss();
        }
    }

}
