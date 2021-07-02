package com.qif.id.paragraph.ViewModel;

public class IndonesiaProvinceDataModel {
    public com.qif.id.paragraph.ViewModel.attributes getAttributes() {
        return attributes;
    }

    public attributes attributes;
}

class attributes {

    public int FID;
    public int Kode_Provi;
    public String Provinsi;
    public String Kasus_Posi;
    public String Kasus_Semb;
    public String Kasus_Meni;

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public int getKode_Provi() {
        return Kode_Provi;
    }

    public void setKode_Provi(int kode_Provi) {
        Kode_Provi = kode_Provi;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
    }

    public String getKasus_Posi() {
        return Kasus_Posi;
    }

    public void setKasus_Posi(String kasus_Posi) {
        Kasus_Posi = kasus_Posi;
    }

    public String getKasus_Semb() {
        return Kasus_Semb;
    }

    public void setKasus_Semb(String kasus_Semb) {
        Kasus_Semb = kasus_Semb;
    }

    public String getKasus_Meni() {
        return Kasus_Meni;
    }

    public void setKasus_Meni(String kasus_Meni) {
        Kasus_Meni = kasus_Meni;
    }

}