/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.HoaDonChiTiet;
import java.util.List;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class HoaDonChiTiet_Daoimpl implements HoaDonChiTiet_Dao {

    private String sqlFindAll = "select * from HoaDonChiTiet";
    private String sqlFindByID = "select * from HoaDonChiTiet where idHoaDon = ?";
    private String sqlFindByMaHD = "select * from HoaDonChiTiet hdct join HoaDon hd on hdct.idHoaDon = hd.id where hd.maHoaDon = ?";
    private String sqlDeleteByIDHoaDon = "delete from HoaDonChiTiet where idHoaDon = ?";
    private String sqlDeleteByMaHD = "delete from HoaDonChiTiet where idSanPham = ? and maHoaDon = ?";
    private String sqlCreate = "INSERT INTO HoaDonChiTiet (idHoaDon, idSanPham, soLuong, donGia, giamGia, thanhTien, maHoaDon) VALUES(?,?,?,?,?,?,?)";
    private String sqlUpdate = "Update HoaDonChiTiet set giamGia=?, thanhTien=? where id = ?";
    private String sqlUpdateSL = "Update HoaDonChiTiet set soLuong = ? where idSanPham = ? and maHoaDon = ?";

    @Override
    public void create(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getIdHoaDon(), entity.getIdSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getGiamGia(), entity.getThanhTien(), entity.getMaHoaDon());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(sqlUpdate, entity.getGiamGia(), entity.getThanhTien(), entity.getId());
    }

    public void updateSL(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(sqlUpdateSL, entity.getSoLuong(), entity.getIdSanPham(), entity.getMaHoaDon());
    }

    @Override
    public void deleteById(Integer id) {
//        XJdbc.executeUpdate(sqlDeleteByMaHD, id);
    }

    public void deleteByIdHoaDon(Integer id) {
//        XJdbc.executeUpdate(sqlDeleteByIDHoaDon, id);
    }

    public void deleteByIdSP(Integer idSP, String maHD) {
        XJdbc.executeUpdate(sqlDeleteByMaHD, idSP, maHD);
    }

    @Override
    public List<HoaDonChiTiet> findAll() {
        return XQuery.getBeanList(HoaDonChiTiet.class, sqlFindAll);

    }

    public List<HoaDonChiTiet> findByIdList(Integer id) {
        return XQuery.getBeanList(HoaDonChiTiet.class, sqlFindByID, id);
    }

    public List<HoaDonChiTiet> findByMaHDList(String maHD) {
        return XQuery.getBeanList(HoaDonChiTiet.class, sqlFindByMaHD, maHD);
    }

    @Override
    public HoaDonChiTiet findById(Integer id) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, sqlFindByID, id);
    }

}
