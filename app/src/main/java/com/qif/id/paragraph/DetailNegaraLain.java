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
import com.qif.id.paragraph.Model.NegaraDetailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailNegaraLain extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    NegaraDetailAdapter negaraDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_negara_lain);

        mToolbar = (Toolbar) findViewById(R.id.toolbarNegaraLain);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNegaraLain);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        mToolbar.setTitle("Daftar Negara Terinfeksi");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        GetDetailNegara(this);
    }

    private void GetDetailNegara(final Context context){

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
            Call<List<GlobalDataCountryModel>> modelCall = apIs.GetGlobalDataByCountry();
            modelCall.enqueue(new Callback<List<GlobalDataCountryModel>>() {
                @Override
                public void onResponse(Call<List<GlobalDataCountryModel>> call, Response<List<GlobalDataCountryModel>> response) {
                    List<GlobalDataCountryModel> arr = response.body();

                    negaraDetailAdapter = new NegaraDetailAdapter(arr,context);
                    recyclerView.setAdapter(negaraDetailAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

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
