/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;
import dao.*;
import entity.KichCo;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;
/**
 *
 * @author Administrator
 */
public class KichCo_Daoimpl implements KichCo_Dao{

    private String sqlFindAll = "select * from KichCo";
    private String sqlFindByName = "select * from KichCo where tenKichCo = ?";
    private String sqlCreate = "insert into KichCo values (?)";
    private String sqlDeleteByName = "delete from KichCo where tenKichCo = ?";
    @Override
    public void create(KichCo entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getTenKichCo());
    }

    @Override
    public void update(KichCo entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KichCo> findAll() {
        return XQuery.getBeanList(KichCo.class, sqlFindAll);
    }

    @Override
    public KichCo findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByName(String ten) {
        try {
        XJdbc.executeUpdate(sqlDeleteByName, ten);
        } catch (Exception e) {
            if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa đang có sản phẩm thuộc kích cỡ này", "Thông báo!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public KichCo findByName(String ten) {
        return XQuery.getSingleBean(KichCo.class, sqlFindByName, ten);
    }
    
}
