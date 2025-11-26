/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.HoaDon;
import java.util.List;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class HoaDon_Daoimpl implements HoaDon_Dao {

    private String sqlFindAll = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id";
    private String sqlFindByMaHD = "select * from HoaDon hd join KhachHang kh on hd.idKhachHang = kh.id where hd.maHoaDon = ?";
    private String sqlDeleteById = "delete from HoaDon where id = ?";
    private String sqlUpdtaeByMaHD = "update HoaDon set tongTien = ?, tienKhachDua = ?, tienTraLai = ?, trangThai = ?, ghiChu = ? where maHoaDon = ?";
    private String sqlUpdtaeByMaHDCK = "update HoaDon set tongTien = ?, trangThai = ?, ghiChu = ?  where maHoaDon = ?";
    private String sqlCreate = "INSERT INTO HoaDon (maHoaDon, ngayTao, idKhachHang, maNhanVien, phuongThucThanhToan) " + "VALUES (?, ?, ?, ?, ?)";

    @Override
    public void create(HoaDon entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getMaHoaDon(), entity.getNgayTao(), entity.getIdKhachHang(), entity.getMaNhanVien(), entity.getPhuongThucThanhToan());
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdtaeByMaHD, entity.getTongTien(), entity.getTienKhachDua(), entity.getTienTraLai(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaHoaDon());
    }
    public void updateCK(HoaDon entity) {
        XJdbc.executeUpdate(sqlUpdtaeByMaHDCK, entity.getTongTien(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaHoaDon());
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
}
