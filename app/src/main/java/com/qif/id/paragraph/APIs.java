package com.qif.id.paragraph;

import com.qif.id.paragraph.Model.DataCountIndonesiaModel;
import com.qif.id.paragraph.Model.GlobalCaseCountModel;
import com.qif.id.paragraph.Model.GlobalDataCountryModel;
import com.qif.id.paragraph.Model.NotifModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIs {

    @GET("https://api.kawalcorona.com/")
    Call<List<GlobalDataCountryModel>> GetGlobalDataByCountry();

    @GET("/indonesia/")
    Call<List<DataCountIndonesiaModel>> GetDataCountIndonesia();

    @GET("/positif")
    Call<GlobalCaseCountModel> GetCountGlobalInffected();

    @GET("/indonesia/provinsi")
    Call<List<GlobalDataCountryModel>> GetDataIndonesiaProvince();

    @GET("get_notif.php")
    Call<List<NotifModel>> GetNotif();
}
