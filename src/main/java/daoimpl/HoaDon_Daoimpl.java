/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.HoaDon;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import ui.jpHoaDon;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class HoaDon_Daoimpl implements HoaDon_Dao {

    private String sqlFindAll = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id";
    private String sqlFindByMaHD = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id where hd.maHoaDon = ?";
    private String sqlFindListByMaHD = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id where hd.maHoaDon like ?";
    private String sqlDeleteById = "delete from HoaDon where id = ?";
    private String sqlUpdateByMaHD = "update HoaDon set tongTien = ?, ngayThanhToan = ?, phuongThucThanhToan = ?, tienKhachDua = ?, tienTraLai = ?, trangThai = ?, ghiChu = ?, idKhuyenMai = ? where maHoaDon = ?";
    private String sqlUpdateByMaHDCK = "update HoaDon set tongTien = ?, ngayThanhToan = ?, phuongThucThanhToan = ?, trangThai = ?, ghiChu = ?, idKhuyenMai = ?  where maHoaDon = ?";
    private String sqlUpdateKhuyenMaiByMaHD = "update HoaDon set idKhuyenMai = ?  where maHoaDon = ?";
    private String sqlUpdateHuyDon = "update HoaDon set tongTien = ?, trangThai = ?, ghiChu = ?  where maHoaDon = ?";
    private String sqlCreate = "INSERT INTO HoaDon (maHoaDon, ngayTao, idKhachHang, maNhanVien, phuongThucThanhToan) " + "VALUES (?, ?, ?, ?, ?)";
    private String sqlFindByNgayTao = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id where cast(ngayTao as date) between ? and ?";
    private String sqlFindByNgayThanhToan = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id where cast(ngayThanhToan as date) between ? and ?";

    public HoaDon_Daoimpl() {
    }
    @Override
    public void create(HoaDon entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getMaHoaDon(), entity.getNgayTao(), entity.getIdKhachHang(), entity.getMaNhanVien(), entity.getPhuongThucThanhToan());
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdateByMaHD, entity.getTongTien(), entity.getNgayThanhToan(), entity.getPhuongThucThanhToan(), entity.getTienKhachDua(), entity.getTienTraLai(), entity.getTrangThai(), entity.getGhiChu(), entity.getIdKhuyenMai(), entity.getMaHoaDon());
    }
    public void updateHuyDon(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdateHuyDon, entity.getTongTien(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaHoaDon());
    }
    public void updateCK(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdateByMaHDCK, entity.getTongTien(), entity.getNgayThanhToan(), entity.getPhuongThucThanhToan(), entity.getTrangThai(), entity.getGhiChu(), entity.getIdKhuyenMai(), entity.getMaHoaDon());
    }
    public void updateKhuyenMai(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdateKhuyenMaiByMaHD, entity.getIdKhuyenMai(), entity.getMaHoaDon());
    }
    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(sqlDeleteById, id);
    }

    @Override
    public List<HoaDon> findAll() {
        return XQuery.getBeanList(HoaDon.class, sqlFindAll);
    }

    @Override
    public HoaDon findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public HoaDon findByMaHD(String id) {
        return  XQuery.getSingleBean(HoaDon.class, sqlFindByMaHD, id);
    }
    public List<HoaDon> findListByMaHD(String maHD) {
        return XQuery.getBeanList(HoaDon.class, sqlFindListByMaHD, '%'+maHD+'%');
    }
    
    public List<HoaDon> findListByNgayTao(LocalDate ngayTao, LocalDate ngayThanhToan) {
        return XQuery.getBeanList(HoaDon.class, sqlFindByNgayTao, Date.valueOf(ngayTao), Date.valueOf(ngayThanhToan));
    }
    public List<HoaDon> findListByNgayThanhToan(LocalDate ngayTao, LocalDate ngayThanhToan) {
        return XQuery.getBeanList(HoaDon.class, sqlFindByNgayThanhToan, Date.valueOf(ngayTao), Date.valueOf(ngayThanhToan));
    }
    public List<HoaDon> find(int index, String maHD, LocalDate ngayTao, LocalDate ngayThanhToan) {
        switch (index) {
            case 0:
                return findListByMaHD(maHD);
            case 1:
                return findListByNgayTao(ngayTao, ngayThanhToan);
            case 2:
                return findListByNgayThanhToan(ngayTao, ngayThanhToan);
            default:
                return null;
        }
    }
}
