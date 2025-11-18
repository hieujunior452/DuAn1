/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;
import dao.*;
import entity.ChucVu;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;
/**
 *
 * @author Administrator
 */
public class ChucVu_Daoimpl implements ChucVu_Dao{
    
    private String sqlFindAll = "select * from ChucVu";
    private String sqlFindByName = "select * from ChucVu where id = ?";
    private String sqlFindByMaCV = "select * from ChucVu where id = ?";
    private String sqlDeleteByMaCV = "delete from ChucVu where id = ?";
    private String sqlThemChucVu = "insert into ChucVu(maChucVu, tenChucVu) values (?, ?)";
    private String sqlCapNhatChucVu = "update ChucVu set tenChucVu = ? where id = ?";

    @Override
    public ChucVu create(ChucVu entity) {
        try {
            int create = XJdbc.executeUpdate(sqlThemChucVu, entity.getTenChucVu());
            if (create > 0) {
                JOptionPane.showMessageDialog(null, "Thêm chức vụ thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            String errorMessage = e.getMessage().toLowerCase();
            if (errorMessage.contains("duplicate") && errorMessage.contains("key")) {
                JOptionPane.showMessageDialog(null, "Mã chức vụ đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình thêm chức vụ: \n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    @Override
    public void update(ChucVu entity) {
        XJdbc.executeUpdate(sqlCapNhatChucVu, entity.getTenChucVu(), entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChucVu> findAll() {
        return XQuery.getBeanList(ChucVu.class, sqlFindAll);
    }

    @Override
    public ChucVu findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChucVu findByName(String name) {
        return XQuery.getSingleBean(ChucVu.class, sqlFindByName, name);
    }

    @Override
    public void deleteByMaCV(String maCV) {
        XJdbc.executeUpdate(sqlDeleteByMaCV, maCV);
    }

    @Override
    public ChucVu findbyMaChucVu(String maCV) {
        return XQuery.getSingleBean(ChucVu.class, sqlFindByMaCV, maCV);
    }
    
}
