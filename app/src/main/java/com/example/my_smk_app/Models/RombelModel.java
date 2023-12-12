package com.example.my_smk_app.Models;

import com.google.gson.annotations.SerializedName;

public class RombelModel {

    @SerializedName("id_kelas")
    private int idKelas;

    @SerializedName("nama_kelas")
    private String namaKelas;

    @SerializedName("nip_id")
    private int nipId;

    public RombelModel(int idKelas, String namaKelas, int nipId) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.nipId = nipId;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public int getNipId() {
        return nipId;
    }
}
