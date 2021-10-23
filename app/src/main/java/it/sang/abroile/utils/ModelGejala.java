package it.sang.abroile.utils;

public class ModelGejala {
    String id_gejala;

    public ModelGejala(String id_gejala, String nama_gejala) {
        this.id_gejala = id_gejala;
        this.nama_gejala = nama_gejala;
    }

    String nama_gejala;

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }
}
