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
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class KhachHang_Daoimpl implements KhachHang_Dao {

    private String sqlCreate = "INSERT INTO Khachhang (hoVaTen, sDT, email) OUTPUT INSERTED.id VALUES (?,?,?)";
    private String sqlFindByPhone = "SELECT * FROM Khachhang WHERE sDT = ?";
    private String sqlFindByID = "SELECT * FROM Khachhang WHERE id = ?";

    public int getOrCreateCustomer(String name, String phone, String email) {
        KhachHang kh = findByPhone(phone);

        if (kh != null) {
            return kh.getId();
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

    @Override
    public KhachHang findById(Integer id) {
        return XQuery.getSingleBean(KhachHang.class, sqlFindByID, id);
    }

    public KhachHang findByPhone(String phone) {
        return XQuery.getSingleBean(KhachHang.class, sqlFindByPhone, phone);
    }
}
