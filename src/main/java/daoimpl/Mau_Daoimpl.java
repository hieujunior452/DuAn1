/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.Mau;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class Mau_Daoimpl implements Mau_Dao {

    private String sqlFindAll = "select * from Mau";
    private String sqlFindByName = "select * from Mau where tenMau = ?";
    private String sqlCreate = "insert into Mau values (?)";
    private String sqlDeleteByTen = "delete from Mau where tenMau = ?";

    @Override
    public void create(Mau entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getTenMau());

    }

    @Override
    public void update(Mau entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Mau> findAll() {
        return XQuery.getBeanList(Mau.class, sqlFindAll);
    }

    @Override
    public Mau findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteByName(String ten) {
        try {
            XJdbc.executeUpdate(sqlDeleteByTen, ten);
        } catch (Exception e) {
            if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa đang có sản phẩm thuộc màu này", "Thông báo!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public Mau findByName(String ten) {
        return XQuery.getSingleBean(Mau.class, sqlFindByName, ten);
    }

}
