package com.qif.id.paragraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.qif.id.paragraph.Model.CountryDataAdapter;
import com.qif.id.paragraph.Model.DataCountIndonesiaModel;
import com.qif.id.paragraph.Model.GlobalCaseCountModel;
import com.qif.id.paragraph.Model.GlobalDataCountryModel;
import com.qif.id.paragraph.Model.NotifModel;
import com.qif.id.paragraph.Model.ProvinceDataAdapter;
import com.qif.id.paragraph.ToolsHelper.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView mCountGlobal, mCountCountryInfected, mCountInfectedProvinsi, mLihatProv, mNotifCount, mTentang;
    TextView mTotalPositifIndonesia, mTotalSembuhIndonesia, mTotalMeninggalIndonesia, mLihatSemuaNegara;
    ProgressDialog progressDialog;
    RecyclerView mRecyclerCountry, mRecyclerProvince;
    CountryDataAdapter countryDataAdapter;
    ProvinceDataAdapter dataProvinsiAdapter;
    RelativeLayout mLihatTanyaJawab, mLihatHoaxBust;
    ImageView imgNotif;

    public static double mLongtitude;
    public static double mLatitude;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTentang = (TextView) findViewById(R.id.lblLinkTentang);
        mLihatHoaxBust = (RelativeLayout) findViewById(R.id.layoutLihatHoaxBuster);
        mLihatProv = (TextView) findViewById(R.id.lblLihatProv);
        mCountGlobal = (TextView) findViewById(R.id.lblCountPositive);
        mTotalPositifIndonesia = (TextView) findViewById(R.id.lblIndonesiaPositifCount);
        mTotalSembuhIndonesia = (TextView) findViewById(R.id.lblIndonesiaSembuhCount);
        mTotalMeninggalIndonesia = (TextView) findViewById(R.id.lblIndonesiaMeninggalCount);
        mRecyclerCountry = (RecyclerView) findViewById(R.id.recyclerTopCountry);
        mCountCountryInfected = (TextView) findViewById(R.id.lblCountInfeksiNegara);
        mRecyclerProvince = (RecyclerView) findViewById(R.id.recyclerIndonesiaProv);
        mCountInfectedProvinsi = (TextView) findViewById(R.id.lblTotalInfeksiProvinsi);
        mLihatSemuaNegara = (TextView) findViewById(R.id.lblLihatSemuaNegara);
        mNotifCount = (TextView) findViewById(R.id.lblNotifCount);
        imgNotif = (ImageView) findViewById(R.id.imgNotif);
        imgNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notif.class));
            }
        });

        mLihatTanyaJawab = (RelativeLayout) findViewById(R.id.layoutTanyaJawab);
        mLihatTanyaJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TanyaJawab.class));
            }
        });

        mLihatProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProvinsiDetail.class));
            }
        });

        mLihatSemuaNegara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DetailNegaraLain.class));
            }
        });

        mLihatHoaxBust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HoaxBuster.class));
            }
        });

        mTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTentang(v.getContext());
            }
        });

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        GetCountGlobal();
        GetNotif();
        GetCountIndonesia();
        GetKasusByCountry(this);
        GetDataProvince(this);
        //GetPetaSebaran();
    }

    private void GetCountGlobal(){
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TargetDomain.DomainTarget)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIs apIs = retrofit.create(APIs.class);
            Call<GlobalCaseCountModel> modelCall = apIs.GetCountGlobalInffected();
            modelCall.enqueue(new Callback<GlobalCaseCountModel>() {
                @Override
                public void onResponse(Call<GlobalCaseCountModel> call, Response<GlobalCaseCountModel> response) {
                    GlobalCaseCountModel arr = response.body();
                    mCountGlobal.setText(arr.value);
                }

                @Override
                public void onFailure(Call<GlobalCaseCountModel> call, Throwable t) {
                    String err = t.toString();
                }
            });

        } catch (Exception ex) {
            String err = ex.getMessage();
        }
    }

    private void GetNotif(){
        try {

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
                    String x = String.valueOf(arr.size());
                    if (arr.size() == 0) {
                        mNotifCount.setVisibility(View.INVISIBLE);
                    }else {
                        if (arr.size() > 10) {
                            mNotifCount.setText("9+");
                        }else {
                            mNotifCount.setText(String.valueOf(arr.size()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<NotifModel>> call, Throwable t) {
                    String err = t.toString();
                }
            });

        } catch (Exception ex) {
            String err = ex.toString();
        }
    }

    public void ShowTentang(final Context context){

        try {
            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View dialogView = layoutInflater.inflate(R.layout.custom_dialog_tentang,null);
            alertDialog.setCancelable(true);
            alertDialog.setView(dialogView);
            alertDialog.show();
        }catch (Exception ex) {
            String er = ex.toString();
        }
    }

    private void  GetPetaSebaran(final Context context){
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
                    if (arr.size() > 0) {

                        for (int i = 0; i < arr.size(); i++) {

                            LatLng locationResponder = new LatLng(mLatitude,mLongtitude);
                            MarkerOptions marker = new MarkerOptions().position(locationResponder).title("");

                            marker.icon(Tools.bitmapDescriptorFromVector(context,R.drawable.ic_virus_red));
                            marker.title("");

                            mMap.addMarker(marker);
                            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(locationResponder, 17);
                            mMap.animateCamera(location);

                        }
                    }
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

    private void GetKasusByCountry(final Context context){

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
                    mCountCountryInfected.setText("Telah menginfeksi " + NumberFormat.getNumberInstance(Locale.US).format(arr.size()) + " negara");
                    for (int i = 0; i < arr.size() - 100; i++) {

                        if (i == 10) {
                            break;
                        }
                    }

                    if (arr.size() > 0) {
                        countryDataAdapter = new CountryDataAdapter(arr,context);
                        mRecyclerCountry.setAdapter(countryDataAdapter);
                        mRecyclerCountry.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                    }
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

    private void GetDataProvince(final Context context){
        try {

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

                    mCountInfectedProvinsi.setText("Telah menginfeksi " + arr.size() + " provinsi");

                    dataProvinsiAdapter = new ProvinceDataAdapter(arr,context);
                    mRecyclerProvince.setAdapter(dataProvinsiAdapter);
                    mRecyclerProvince.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                }

                @Override
                public void onFailure(Call<List<GlobalDataCountryModel>> call, Throwable t) {
                    String err = t.toString();

                }
            });

        } catch (Exception ex) {
            String err = ex.toString();
        }
    }

    private void GetCountIndonesia()
    {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TargetDomain.DomainTarget)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIs apIs = retrofit.create(APIs.class);
            Call<List<DataCountIndonesiaModel>> modelCall = apIs.GetDataCountIndonesia();
            modelCall.enqueue(new Callback<List<DataCountIndonesiaModel>>() {
                @Override
                public void onResponse(Call<List<DataCountIndonesiaModel>> call, Response<List<DataCountIndonesiaModel>> response) {
                    List<DataCountIndonesiaModel> arr = response.body();
                    if (arr.size() > 0) {
                        mTotalPositifIndonesia.setText(arr.get(0).positif);
                        mTotalSembuhIndonesia.setText(arr.get(0).sembuh);
                        mTotalMeninggalIndonesia.setText(arr.get(0).meninggal);
                    }
                }

                @Override
                public void onFailure(Call<List<DataCountIndonesiaModel>> call, Throwable t) {
                    String err = t.toString();
                }
            });

        } catch (Exception ex) {
            String err = ex.toString();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
