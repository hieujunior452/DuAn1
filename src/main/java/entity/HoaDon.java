/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class HoaDon {
    private int id;
    private String maHoaDon;
    private Date ngayTao;
    private int idKhachHang;
    private String maNhanVien;
    private BigDecimal tongTien;
    private BigDecimal tienKhachDua;
    private BigDecimal tienTraLai;
    private String phuongThucThanhToan;
    private boolean trangThai;
    private String ghiChu;
    private String hoVaTen;
    public HoaDon() {
    }

    public HoaDon(String maHoaDon, BigDecimal tongTien,String phuongThucThanhToan, boolean trangThai, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public HoaDon(String maHoaDon, BigDecimal tongTien, BigDecimal tienKhachDua, BigDecimal tienTraLai, String phuongThucThanhToan, boolean trangThai, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienTraLai = tienTraLai;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }
    
    

    public HoaDon(int id, String maHoaDon, Date ngayTao, int idKhachHang, String maNhanVien, BigDecimal tongTien, BigDecimal tienKhachDua, BigDecimal tienTraLai, String phuongThucThanhToan, boolean trangThai, String ghiChu) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.idKhachHang = idKhachHang;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienTraLai = tienTraLai;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public String gethoVaTen() {
        return hoVaTen;
    }

    public void sethoVaTen(String tenKhachHang) {
        this.hoVaTen = tenKhachHang;
    }

    public BigDecimal getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(BigDecimal tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public BigDecimal getTienTraLai() {
        return tienTraLai;
    }

    public void setTienTraLai(BigDecimal tienTraLai) {
        this.tienTraLai = tienTraLai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
