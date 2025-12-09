/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.HoaDonChiTiet;
import entity.ThongKe;
import java.util.List;
import ui.jpThongKe;
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

    public List<ThongKe> findSpThongKe(int index) {
        switch (index) {
            case 0:
                return XQuery.getBeanList(ThongKe.class, "SELECT \n"
                        + "    sp.id AS IDSanPham,\n"
                        + "    sp.tenGiay AS TenSanPham,\n"
                        + "    hdct.donGia AS DonGiaBan,\n"
                        + "    SUM(hdct.soLuong) AS SoLuongBan,\n"
                        + "    SUM(hdct.soLuong * hdct.donGia) AS DoanhThu\n"
                        + "FROM HoaDonChiTiet hdct\n"
                        + "JOIN SanPham sp ON hdct.idSanPham = sp.id\n"
                        + "JOIN HoaDon hd ON hdct.idHoaDon = hd.id\n"
                        + "WHERE CAST(hd.ngayThanhToan AS DATE) BETWEEN ? AND ?\n"
                        + "  AND hd.trangThai = 1\n"
                        + "GROUP BY sp.id, sp.tenGiay, hdct.donGia\n"
                        + "ORDER BY DoanhThu DESC", jpThongKe.tu, jpThongKe.den);
            case 1:
                return XQuery.getBeanList(ThongKe.class, "SELECT \n"
                        + "    sp.id AS IDSanPham,\n"
                        + "    sp.tenGiay AS TenSanPham,\n"
                        + "    hdct.donGia AS DonGiaBan,\n"
                        + "    SUM(hdct.soLuong) AS SoLuongBan,\n"
                        + "    SUM(hdct.soLuong * hdct.donGia) AS DoanhThu\n"
                        + "FROM HoaDonChiTiet hdct\n"
                        + "JOIN SanPham sp ON hdct.idSanPham = sp.id\n"
                        + "JOIN HoaDon hd ON hdct.idHoaDon = hd.id\n"
                        + "WHERE MONTH(hd.ngayThanhToan) BETWEEN ? AND ?\n"
                        + "  AND YEAR(hd.ngayThanhToan) = ?\n"
                        + "  AND hd.trangThai = 1\n"
                        + "GROUP BY sp.id, sp.tenGiay, hdct.donGia\n"
                        + "ORDER BY DoanhThu DESC;", jpThongKe.tu.getMonthValue(), jpThongKe.den.getMonthValue(), jpThongKe.tu.getYear());
            case 2:
                return XQuery.getBeanList(ThongKe.class, "SELECT \n"
                        + "    sp.id AS IDSanPham,\n"
                        + "    sp.tenGiay AS TenSanPham,\n"
                        + "    hdct.donGia AS DonGiaBan,\n"
                        + "    SUM(hdct.soLuong) AS SoLuongBan,\n"
                        + "    SUM(hdct.soLuong * hdct.donGia) AS DoanhThu\n"
                        + "FROM HoaDonChiTiet hdct\n"
                        + "JOIN SanPham sp ON hdct.idSanPham = sp.id\n"
                        + "JOIN HoaDon hd ON hdct.idHoaDon = hd.id\n"
                        + "WHERE YEAR(hd.ngayThanhToan) BETWEEN ? AND ?\n"
                        + "  AND hd.trangThai = 1\n"
                        + "GROUP BY sp.id, sp.tenGiay, hdct.donGia\n"
                        + "ORDER BY DoanhThu DESC;", jpThongKe.tu.getYear(), jpThongKe.den.getYear());
            default:
                return XQuery.getBeanList(ThongKe.class, "SELECT \n"
                        + "    sp.id AS IDSanPham,\n"
                        + "    sp.tenGiay AS TenSanPham,\n"
                        + "    hdct.donGia AS DonGiaBan,\n"
                        + "    SUM(hdct.soLuong) AS SoLuongBan,\n"
                        + "    SUM(hdct.soLuong * hdct.donGia) AS DoanhThu\n"
                        + "FROM HoaDonChiTiet hdct\n"
                        + "JOIN SanPham sp ON hdct.idSanPham = sp.id\n"
                        + "JOIN HoaDon hd ON hdct.idHoaDon = hd.id\n"
                        + "  AND hd.trangThai = 1\n"
                        + "GROUP BY sp.id, sp.tenGiay, hdct.donGia\n"
                        + "ORDER BY DoanhThu DESC;");
        }
    }
;
}
