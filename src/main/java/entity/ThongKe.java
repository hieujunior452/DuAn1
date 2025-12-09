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
public class ThongKe {
    private int IDSanPham;
    private String TenSanPham;
    private BigDecimal DonGiaBan;
    private int SoLuongBan;
    private BigDecimal DoanhThu;

    public ThongKe() {
    }

    public ThongKe(int IDSanPham, String TenSanPham, BigDecimal DonGiaBan, int SoLuongBan, BigDecimal DoanhThu) {
        this.IDSanPham = IDSanPham;
        this.TenSanPham = TenSanPham;
        this.DonGiaBan = DonGiaBan;
        this.SoLuongBan = SoLuongBan;
        this.DoanhThu = DoanhThu;
    }
    
    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public BigDecimal getDonGiaBan() {
        return DonGiaBan;
    }

    public void setDonGiaBan(BigDecimal DonGiaBan) {
        this.DonGiaBan = DonGiaBan;
    }

    public int getSoLuongBan() {
        return SoLuongBan;
    }

    public void setSoLuongBan(int SoLuongBan) {
        this.SoLuongBan = SoLuongBan;
    }

    public BigDecimal getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(BigDecimal DoanhThu) {
        this.DoanhThu = DoanhThu;
    }
    
    
}
