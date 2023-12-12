package com.example.my_smk_app.Models;

import com.google.gson.annotations.SerializedName;

public class MapelModel {

    @SerializedName("id_mapel")
    private int idMapel;

    @SerializedName("nama_mapel")
    private String namaMapel;

    @SerializedName("nip_id")
    private int nipId;

    @SerializedName("kelas_id")
    private int kelasId;

    @SerializedName("rombel")
    private RombelModel rombel;

    @SerializedName("guru")
    private GuruModel guru;

    public MapelModel(int idMapel, String namaMapel, int nipId, int kelasId, RombelModel rombel, GuruModel guru) {
        this.idMapel = idMapel;
        this.namaMapel = namaMapel;
        this.nipId = nipId;
        this.kelasId = kelasId;
        this.rombel = rombel;
        this.guru = guru;
    }

    public int getIdMapel() {
        return idMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public int getNipId() {
        return nipId;
    }

    public int getKelasId() {
        return kelasId;
    }

    public RombelModel getRombel() {
        return rombel;
    }

    public GuruModel getGuru() {
        return guru;
    }
}
