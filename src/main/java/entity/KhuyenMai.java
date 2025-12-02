/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Administrator
 **/
import java.time.LocalDateTime;

public class KhuyenMai {

    public int id;
    public String tenKM;
    public int phanTramGiam;
    public int idLoai;
    public LocalDateTime ngayBatDau;
    public LocalDateTime ngayKetThuc;
    public boolean trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String tenKM, int phanTramGiam, int idLoai, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, boolean trangThai) {
        this.tenKM = tenKM;
        this.phanTramGiam = phanTramGiam;
        this.idLoai = idLoai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }
    
    public KhuyenMai(int id, String tenKM, int phanTramGiam, int idLoai, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, boolean trangThai) {
        this.id = id;
        this.tenKM = tenKM;
        this.phanTramGiam = phanTramGiam;
        this.idLoai = idLoai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }
    
    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
