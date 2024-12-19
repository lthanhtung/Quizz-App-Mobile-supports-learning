package com.example.quizz_app.ui.CauHoi;

public class CauHoi {
    private String MaBaiHoc;
    private String CauHoi;
    private String DapAn_A;
    private String DapAn_B;
    private String DapAn_C;
    private String DapAn_D;
    private String KetQua;

    public CauHoi(String maBaiHoc, String cauHoi, String dapAn_A, String dapAn_B, String dapAn_C, String dapAn_D, String ketQua) {
        MaBaiHoc = maBaiHoc;
        CauHoi = cauHoi;
        DapAn_A = dapAn_A;
        DapAn_B = dapAn_B;
        DapAn_C = dapAn_C;
        DapAn_D = dapAn_D;
        KetQua = ketQua;
    }

    public CauHoi() {
    }

    public String getMaBaiHoc() {
        return MaBaiHoc;
    }

    public void setMaBaiHoc(String maBaiHoc) {
        MaBaiHoc = maBaiHoc;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getDapAn_A() {
        return DapAn_A;
    }

    public void setDapAn_A(String dapAn_A) {
        DapAn_A = dapAn_A;
    }

    public String getDapAn_B() {
        return DapAn_B;
    }

    public void setDapAn_B(String dapAn_B) {
        DapAn_B = dapAn_B;
    }

    public String getDapAn_C() {
        return DapAn_C;
    }

    public void setDapAn_C(String dapAn_C) {
        DapAn_C = dapAn_C;
    }

    public String getDapAn_D() {
        return DapAn_D;
    }

    public void setDapAn_D(String dapAn_D) {
        DapAn_D = dapAn_D;
    }

    public String getKetQua() {
        return KetQua;
    }

    public void setKetQua(String ketQua) {
        KetQua = ketQua;
    }
}
