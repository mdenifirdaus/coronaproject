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

import com.qif.id.paragraph.Model.GlobalDataCountryModel;
import com.qif.id.paragraph.Model.ProvinceDataAdapter;

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

public class ProvinsiDetail extends AppCompatActivity {

    Toolbar mToolbar;
    ProgressDialog progressDialog;
    ProvinceDataAdapter provinsiDetail;
    RecyclerView recyclerProvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi_detail);

        recyclerProvDetail = (RecyclerView) findViewById(R.id.recyclerProvDetail);
        mToolbar = (Toolbar) findViewById(R.id.toolbarProvDetail);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        mToolbar.setTitle("Daftar Provinsi Terinfeksi");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        GetDataProvince(this);
    }

    private void GetDataProvince(final Context context){
        try {

            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Mohon Tunggu...");
            progressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TargetDomain.DomainTarget)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIs apIs = retrofit.create(APIs.class);
            Call<List<GlobalDataCountryModel>> modelCall = apIs.GetDataIndonesiaProvince();
            modelCall.enqueue(new Callback<List<GlobalDataCountryModel>>() {
                @Override
                public void onResponse(Call<List<GlobalDataCountryModel>> call, Response<List<GlobalDataCountryModel>> response) {
                    List<GlobalDataCountryModel> arr = response.body();

                    provinsiDetail = new ProvinceDataAdapter(arr,context);
                    recyclerProvDetail.setAdapter(provinsiDetail);
                    recyclerProvDetail.setLayoutManager(new LinearLayoutManager(context));

                    progressDialog.dismiss();

                }

                @Override
                public void onFailure(Call<List<GlobalDataCountryModel>> call, Throwable t) {
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
