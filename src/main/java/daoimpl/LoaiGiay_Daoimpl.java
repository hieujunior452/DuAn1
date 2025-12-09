/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.LoaiGiay;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class LoaiGiay_Daoimpl implements LoaiGiay_Dao {

    private String sqlFindAll = "select * from LoaiGiay";
    private String sqlFindByName = "select * from LoaiGiay where tenLoai = ?";
    private String sqlCreate = "insert into LoaiGiay values (?)";
    private String sqlDeleteByName = "delete from LoaiGiay where tenLoai = ?";
    private String sqlFindByID = "select * from LoaiGiay where id = ?";

    @Override
    public void create(LoaiGiay entity) {
        try {
        XJdbc.executeUpdate(sqlCreate, entity.getTenLoai());
        } catch (Exception e) {
            if (e.getMessage().contains("UNIQUE")) {
                JOptionPane.showMessageDialog(null, "Loại đã có trong hệ thống");
            }
        }
    }

    @Override
    public void update(LoaiGiay entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LoaiGiay> findAll() {
        return XQuery.getBeanList(LoaiGiay.class, sqlFindAll);
    }

    @Override
    public LoaiGiay findById(Integer id) {
        return XQuery.getSingleBean(LoaiGiay.class, sqlFindByID, id);
    }

    public void deleteByName(String name) {
        try {
            XJdbc.executeUpdate(sqlDeleteByName, name);
        } catch (Exception e) {
            if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa đang có sản phẩm thuộc loại này", "Thông báo!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public LoaiGiay findByName(String ten) {
        return XQuery.getSingleBean(LoaiGiay.class, sqlFindByName, ten);
    }

}
