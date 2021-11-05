package it.sang.abroile.utils;

public class ModelLaporan {
    String id, nama, no , email, laporan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLaporan() {
        return laporan;
    }

    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }

    public ModelLaporan(String id, String nama, String no, String email, String laporan) {
        this.id = id;
        this.nama = nama;
        this.no = no;
        this.email = email;
        this.laporan = laporan;
    }
}
