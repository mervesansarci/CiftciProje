package com.denemeler.projeciftci;

public class KullaniciBilgi {


    String ad,soyad, dogumGunu,statu,muhendisNo,eposta,profilFoto;


    public KullaniciBilgi() {
    }

    public KullaniciBilgi(String ad, String soyad, String dogumGunu, String statu, String muhendisNo, String eposta, String profilFoto) {
        this.ad = ad;
        this.soyad = soyad;
        this.dogumGunu = dogumGunu;
        this.statu = statu;
        this.muhendisNo = muhendisNo;
        this.eposta = eposta;
        this.profilFoto = profilFoto;
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

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getMuhendisNo() {
        return muhendisNo;
    }

    public void setMuhendisNo(String muhendisNo) {
        this.muhendisNo = muhendisNo;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(String profilFoto) {
        this.profilFoto = profilFoto;
    }
}
