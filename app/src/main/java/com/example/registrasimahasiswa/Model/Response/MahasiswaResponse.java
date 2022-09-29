package com.example.registrasimahasiswa.Model.Response;

import com.example.registrasimahasiswa.Model.Data.MahasiswaModel;

import java.util.List;

public class MahasiswaResponse {
    private int kode;
    private String pesan;
    private List<MahasiswaModel> data;
    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<MahasiswaModel> getData() {
        return data;
    }

    public void setData(List<MahasiswaModel> data) {
        this.data = data;
    }

}
