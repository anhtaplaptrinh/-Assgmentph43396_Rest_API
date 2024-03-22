package com.example.assgment1_ph43396_rest_api;

public class SanphamModel {
    private  String _id;
    private  String ten;
    private  double gia;
    private  int  soluong;
    private boolean tonkho;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public SanphamModel(String _id, String ten, double gia, int soluong, boolean tonkho) {
        this._id = _id;
        this.ten = ten;
        this.gia = gia;
        this.soluong = soluong;
        this.tonkho = tonkho;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public boolean isTonkho() {
        return tonkho;
    }

    public void setTonkho(boolean tonkho) {
        this.tonkho = tonkho;
    }
}
