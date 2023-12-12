package com.example.my_smk_app.Models;

import com.google.gson.annotations.SerializedName;

public class ListMateriModel {
    @SerializedName("id_materi")
    private int idMateri;

    @SerializedName("id_mapel")
    private int idMapel;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("kelas_id")
    private int kelasId;

    @SerializedName("judul_materi")
    private String judulMateri;

    @SerializedName("file_materi")
    private String fileMateri;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("pertemuan")
    private int pertemuan;

    @SerializedName("mapel")
    private MapelModel mapel;

    @SerializedName("user")
    private UserModel user;

    @SerializedName("rombel")
    private RombelModel rombel;

    public ListMateriModel(int idMateri, int idMapel, int userId, int kelasId, String judulMateri, String fileMateri, String deskripsi, int pertemuan, MapelModel mapel, UserModel user, RombelModel rombel) {
        this.idMateri = idMateri;
        this.idMapel = idMapel;
        this.userId = userId;
        this.kelasId = kelasId;
        this.judulMateri = judulMateri;
        this.fileMateri = fileMateri;
        this.deskripsi = deskripsi;
        this.pertemuan = pertemuan;
        this.mapel = mapel;
        this.user = user;
        this.rombel = rombel;
    }

    public int getIdMateri() {
        return idMateri;
    }

    public int getIdMapel() {
        return idMapel;
    }

    public int getUserId() {
        return userId;
    }

    public int getKelasId() {
        return kelasId;
    }

    public String getJudulMateri() {
        return judulMateri;
    }

    public String getFileMateri() {
        return fileMateri;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getPertemuan() {
        return pertemuan;
    }

    public MapelModel getMapel() {
        return mapel;
    }

    public UserModel getUser() {
        return user;
    }

    public RombelModel getRombel() {
        return rombel;
    }
}
