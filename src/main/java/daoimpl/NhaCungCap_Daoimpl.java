/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;
import dao.*;
import entity.NhaCungCap;
import java.util.List;
import util.XJdbc;
import util.XQuery;
/**
 *
 * @author Administrator
 */
public class NhaCungCap_Daoimpl implements NhaCungCap_Dao{
    private String sqlFindAll = "select * from NhaCungCap";
    private String sqlFindByName = "select * from NhaCungCap where tenNCC = ?";
    private String sqlFindById = "select * from NhaCungCap where id = ?";
    private String sqlCreate = "insert into NhaCungCap values (?,?,?,?,?)";
    private String sqlUpdate = "update NhaCungCap set tenNCC=?, diaChi=?, sDT=?, email=?, trangThai=? where id=?";
    private String sqlDelete = "delete from NhaCungCap where id = ?";

    @Override
    public void create(NhaCungCap entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getTenNCC(), entity.getDiaChi(), entity.getsDT(), entity.getEmail(), entity.isTrangThai());
    }

    @Override
    public void update(NhaCungCap entity) {
        XJdbc.executeUpdate(sqlUpdate, entity.getTenNCC(), entity.getDiaChi(), entity.getsDT(), entity.getEmail(), entity.isTrangThai(), entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(sqlDelete, id);
    }

    @Override
    public List<NhaCungCap> findAll() {
        return XQuery.getBeanList(NhaCungCap.class, sqlFindAll);
    }

    @Override
    public NhaCungCap findById(Integer id) {
        return XQuery.getSingleBean(NhaCungCap.class, sqlFindById, id);
    }

    @Override
    public NhaCungCap findByName(String ten) {
        return XQuery.getSingleBean(NhaCungCap.class, sqlFindByName, ten);
    }
    
}
