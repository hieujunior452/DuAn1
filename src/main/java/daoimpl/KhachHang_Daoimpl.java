/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class KhachHang_Daoimpl implements KhachHang_Dao {

    private String sqlCreate = "insert into Khachhang (hoVaTen, sDT, email) output inserted.id values (?,?,?)";
    private String sqlFindByPhone = "select * from Khachhang where sDT = ?";
    private String sqlFindListByPhone = "select * from Khachhang where sDT like ?";
    private String sqlFindByID = "select * from Khachhang where id = ?";
    private String sqlFindAll = "SELECT \n"
            + "    kh.id,\n"
            + "    kh.hoVaTen,\n"
            + "    kh.sDT,\n"
            + "    kh.email,\n"
            + "    COALESCE(SUM(hdct.soLuong), 0) AS soLuong,\n"
            + "    COALESCE(SUM(hd.tongTien), 0) AS tongTien\n"
            + "FROM Khachhang kh\n"
            + "LEFT JOIN HoaDon hd ON kh.id = hd.idKhachHang AND hd.trangThai = 1\n"
            + "LEFT JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon\n" 
            + "GROUP BY kh.id, kh.hoVaTen, kh.sDT, kh.email";
    private String sqlFindTopSoLuong = "SELECT \n"
            + "    kh.id,\n"
            + "    kh.hoVaTen,\n"
            + "    kh.sDT,\n"
            + "    kh.email,\n"
            + "    COALESCE(SUM(hdct.soLuong), 0) AS soLuong,\n"
            + "    COALESCE(SUM(hd.tongTien), 0) AS tongTien\n"
            + "FROM Khachhang kh\n"
            + "LEFT JOIN HoaDon hd ON kh.id = hd.idKhachHang AND hd.trangThai = 1\n"
            + "LEFT JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon\n" 
            + "GROUP BY kh.id, kh.hoVaTen, kh.sDT, kh.email\n"
            + "ORDER BY soLuong DESC";
    private String sqlFindTopSoTien = "SELECT \n"
            + "    kh.id,\n"
            + "    kh.hoVaTen,\n"
            + "    kh.sDT,\n"
            + "    kh.email,\n"
            + "    COALESCE(SUM(hdct.soLuong), 0) AS soLuong,\n"
            + "    COALESCE(SUM(hd.tongTien), 0) AS tongTien\n"
            + "FROM Khachhang kh\n"
            + "LEFT JOIN HoaDon hd ON kh.id = hd.idKhachHang AND hd.trangThai = 1\n"
            + "LEFT JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon\n" 
            + "GROUP BY kh.id, kh.hoVaTen, kh.sDT, kh.email\n"
            + "ORDER BY tongTien DESC";

    public int getOrCreateCustomer(String name, String phone, String email) {
        KhachHang kh = findByPhone(phone);
        if (kh != null && kh.getHoVaTen().equals(name)) {
            return kh.getId();
        } else if (kh != null && !kh.getHoVaTen().equals(name)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã có người sử dụng!\n" + kh.getHoVaTen());
            return -1;
        }
        KhachHang newKH = new KhachHang();
        newKH.setHoVaTen(name);
        newKH.setsDT(phone);
        newKH.setEmail(email);
        return createe(newKH);
    }

    public int createe(KhachHang entity) {
        try {
            ResultSet rs = XJdbc.executeQuery(sqlCreate, entity.getHoVaTen(), entity.getsDT(), entity.getEmail());
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Daoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void create(KhachHang entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getHoVaTen(), entity.getsDT());
    }

    @Override
    public void update(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhachHang> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<KhachHang> findAll(int index) {
        switch (index) {
            case 0:
                return XQuery.getBeanList(KhachHang.class, sqlFindAll);
            case 1:
                return XQuery.getBeanList(KhachHang.class, sqlFindTopSoLuong);
            case 2:
                return XQuery.getBeanList(KhachHang.class, sqlFindTopSoTien);
            default:
                return null;
        }
    }

    @Override
    public KhachHang findById(Integer id) {
        return XQuery.getSingleBean(KhachHang.class, sqlFindByID, id);
    }

    public KhachHang findByPhone(String phone) {
        return XQuery.getSingleBean(KhachHang.class, sqlFindByPhone, phone);
    }
    public List<KhachHang> findListByPhone(String phone) {
        return XQuery.getBeanList(KhachHang.class, sqlFindListByPhone, phone);
    }
}
