package com.example.my_smk_app.Models;

public class ListHomeModel {
    private String namaMapel;
    private int logoMapel;

    public ListHomeModel(String namaMapel, int logoMapel) {
        this.namaMapel = namaMapel;
        this.logoMapel = logoMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public int getLogoMapel() {
        return logoMapel;
    }

    public void setLogoMapel(int logoMapel) {
        this.logoMapel = logoMapel;
    }
}
