/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class SanPham {
    public int id;
    public String tenGiay;
    public BigDecimal giaNhap;
    public BigDecimal donGia;
    public int soLuong;
    public int idMau;
    public int size;
    public int idLoaiGiay;
    public int idchatLieu;
    public String hinh;
    public boolean trangThai;
    public String moTa;
    public int idNhaCungCap;
    public String tenMau;
    public String tenLoai;
    public String tenChatLieu;
    public String tenNCC;
    public String tenKichCo;

    public SanPham() {
    }

    public SanPham(int id, String tenGiay, int soLuong, BigDecimal giaNhap, int idMau, int size, int idLoaiGiay, int idchatLieu, String hinh, BigDecimal donGia, boolean trangThai, String moTa, int idNhaCungCap) {
        this.id = id;
        this.tenGiay = tenGiay;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.idMau = idMau;
        this.size = size;
        this.idLoaiGiay = idLoaiGiay;
        this.idchatLieu = idchatLieu;
        this.hinh = hinh;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.idNhaCungCap = idNhaCungCap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getIdMau() {
        return idMau;
    }

    public void setIdMau(int idMau) {
        this.idMau = idMau;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIdLoaiGiay() {
        return idLoaiGiay;
    }

    public void setIdLoaiGiay(int idLoaiGiay) {
        this.idLoaiGiay = idLoaiGiay;
    }

    public int getIdchatLieu() {
        return idchatLieu;
    }

    public void setIdchatLieu(int idchatLieu) {
        this.idchatLieu = idchatLieu;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(int idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }
    
}
