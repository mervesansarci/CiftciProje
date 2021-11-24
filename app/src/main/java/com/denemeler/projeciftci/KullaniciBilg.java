package com.denemeler.projeciftci;

public class KullaniciBilg {

    String ad,soyad, dogumGunu;


    public KullaniciBilg() {
    }

    public KullaniciBilg(String ad, String soyad, String dogumGunu) {
        this.ad = ad;
        this.soyad = soyad;
        this.dogumGunu = dogumGunu;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDogumGunu() {
        return dogumGunu;
    }

    public void setDogumGunu(String dogumGunu) {
        this.dogumGunu = dogumGunu;
    }
}
