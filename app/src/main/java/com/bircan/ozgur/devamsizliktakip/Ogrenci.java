package com.bircan.ozgur.devamsizliktakip;

/**
 * Created by ozgur on 23.4.2016.
 */
public class Ogrenci {
    private String dersAdi;
    private String tarih;

    public Ogrenci(String dersAdi, String tarih) {
        this.dersAdi = dersAdi;
        this.tarih = tarih;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String     tarih) {
        this.tarih = tarih;
    }

    public Ogrenci() {
    }

    public String getDersAdi() {
        return dersAdi;
    }


}
