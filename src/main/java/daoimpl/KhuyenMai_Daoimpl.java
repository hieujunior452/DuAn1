/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;
import dao.*;
import entity.KhuyenMai;
import java.util.List;
import util.XQuery;
/**
 *
 * @author Administrator
 */
public class KhuyenMai_Daoimpl implements KhuyenMai_Dao{
    
    private String sqlFindAll = "select * from KhuyenMai";
    private String sqlFindBytenKM = "select * from KhuyenMai where tenKM = ?";

    @Override
    public void create(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhuyenMai> findAll() {
        return null;
    }

    @Override
    public KhuyenMai findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public KhuyenMai findBytenKM(String id) {
        return XQuery.getSingleBean(KhuyenMai.class, sqlFindBytenKM, id);
    }
    
}
