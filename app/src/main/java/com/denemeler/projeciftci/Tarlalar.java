package com.denemeler.projeciftci;

public class Tarlalar {

    String tarlaAdi, tarlaKonum;

    public Tarlalar() {
    }

    public Tarlalar(String tarlaAdi, String tarlaKonum) {
        this.tarlaAdi = tarlaAdi;
        this.tarlaKonum = tarlaKonum;
    }

    public String getTarlaAdi() {
        return tarlaAdi;
    }

    public void setTarlaAdi(String tarlaAdi) {
        this.tarlaAdi = tarlaAdi;
    }

    public String getTarlaKonum() {
        return tarlaKonum;
    }

    public void setTarlaKonum(String tarlaKonum) {
        this.tarlaKonum = tarlaKonum;
    }
}
