package com.guw.gubook.Action;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TamuLuar {

    @SerializedName("id_pengunjung_luar")
    @Expose
    private Integer idPengunjungLuar;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("u_password")
    @Expose
    private String uPassword;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jk")
    @Expose
    private String jk;
    @SerializedName("asal")
    @Expose
    private String asal;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_hp")
    @Expose
    private String noHp;

    public Integer getIdPengunjungLuar() {
        return idPengunjungLuar;
    }

    public void setIdPengunjungLuar(Integer idPengunjungLuar) {
        this.idPengunjungLuar = idPengunjungLuar;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

}
