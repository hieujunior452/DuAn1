/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.jpThongKe;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class SanPham_Daoimpl implements SanPham_Dao {

    private String sqlFindAll = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id";
    private String sqlFindById = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where sp.id like ?";
    private String sqlFindByTenSp = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where sp.tenGiay like ?";
    private String sqlFindByMau = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where m.tenMau like ?";
    private String sqlFindByKichCo = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where kc.tenKichCo like ?";
    private String sqlFindByLoai = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where lg.tenLoai like ?";
    private String sqlFindByChatLieu = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where cl.tenChatLieu like ?";
    private String sqlFindByNhaCungCap = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where ncc.tenNCC like ?";
    private String sqlCreate = "insert into SanPham values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private String sqlFindByID = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where sp.id = ?";
    private String sqlDelete = "delete from SanPham where id = ?";
    private String sqlUpdate = "update SanPham set tenGiay=?, soLuong=?,giaNhap=?,idMau=?,size=?,idLoaiGiay=?,idChatLieu=?,hinh=?,donGia=?,trangThai=?,moTa=?,idNhaCungCap=? where id=?";
    private String sqlUpdateSoLuong = "update SanPham set soLuong=? where id=?";

    @Override
    public void create(SanPham entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getTenGiay(), entity.getSoLuong(), entity.getGiaNhap(), entity.getIdMau(), entity.getSize(), entity.getIdLoaiGiay(), entity.getIdchatLieu(), entity.getHinh(), entity.getDonGia(), entity.isTrangThai(), entity.getMoTa(), entity.getIdNhaCungCap());
    }

    @Override
    public void update(SanPham entity) {
        XJdbc.executeUpdate(sqlUpdate, entity.getTenGiay(), entity.getSoLuong(), entity.getGiaNhap(), entity.getIdMau(), entity.getSize(), entity.getIdLoaiGiay(), entity.getIdchatLieu(), entity.getHinh(), entity.getDonGia(), entity.isTrangThai(), entity.getMoTa(), entity.getIdNhaCungCap(), entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        int delete = XJdbc.executeUpdate(sqlDelete, id);
        if (delete > 0) {
            JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateSoLuong(int soLuong, int idSP) {
        XJdbc.executeUpdate(sqlUpdateSoLuong, soLuong, idSP);

    }

    @Override
    public List<SanPham> findAll() {
        return XQuery.getBeanList(SanPham.class, sqlFindAll);
    }

    @Override
    public SanPham findById(Integer id) {
        return XQuery.getSingleBean(SanPham.class, sqlFindByID, id);
    }

    public List<SanPham> findByToTable(String id, int index) {
        switch (index) {
            case 0:
                return XQuery.getBeanList(SanPham.class, sqlFindById, id);
            case 1:
                return XQuery.getBeanList(SanPham.class, sqlFindByTenSp, id);
            case 2:
                return XQuery.getBeanList(SanPham.class, sqlFindByMau, id);
            case 3:
                return XQuery.getBeanList(SanPham.class, sqlFindByKichCo, id);
            case 4:
                return XQuery.getBeanList(SanPham.class, sqlFindByLoai, id);
            case 5:
                return XQuery.getBeanList(SanPham.class, sqlFindByChatLieu, id);
            case 6:
                return XQuery.getBeanList(SanPham.class, sqlFindByNhaCungCap, id);
            default:
                return null;
        }
    }

    public int findSPThongKe(int index) {

        try {
            String sql = "";
            ResultSet rs;
            switch (index) {
                case 0:
                    sql = "SELECT SUM(soLuong) FROM SanPham";
                    rs= XJdbc.executeQuery(sql);
                    break;
                case 1:
                    sql = "SELECT SUM(soLuong) FROM HoaDonChiTiet";
                    rs= XJdbc.executeQuery(sql);
                    break;
                case 2:
                    sql = "SELECT SUM(soLuong) FROM HoaDonChiTiet hdct join HoaDon hd on hdct.maHoaDon = hd.maHoaDon WHERE CAST(hd.ngayThanhToan AS DATE) BETWEEN ? AND ?";
                    rs= XJdbc.executeQuery(sql, jpThongKe.tu, jpThongKe.den);
                    break;
                case 3:
                    sql = "SELECT SUM(soLuong) FROM HoaDonChiTiet hdct join HoaDon hd on hdct.maHoaDon = hd.maHoaDon WHERE MONTH(hd.ngayThanhToan) BETWEEN ? AND ? AND YEAR(hd.ngayThanhToan) = ?";
                    rs= XJdbc.executeQuery(sql, jpThongKe.tu.getMonthValue(), jpThongKe.den.getMonthValue(), jpThongKe.tu.getYear());
                    break;
                case 4:
                    sql = "SELECT SUM(soLuong) FROM HoaDonChiTiet hdct join HoaDon hd on hdct.maHoaDon = hd.maHoaDon WHERE YEAR(hd.ngayThanhToan) BETWEEN ? AND ?";
                    rs= XJdbc.executeQuery(sql, jpThongKe.tu.getYear(), jpThongKe.den.getYear());
                    break;
                default:
                    return -1;
            }

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
