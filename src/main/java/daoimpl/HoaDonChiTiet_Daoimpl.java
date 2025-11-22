/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;
import dao.*;
import entity.HoaDonChiTiet;
import java.util.List;
import util.XJdbc;
import util.XQuery;
/**
 *
 * @author Administrator
 */
public class HoaDonChiTiet_Daoimpl implements HoaDonChiTiet_Dao{
    private String sqlFindAll = "select * from HoaDonChiTiet";
    private String sqlFindByID = "select * from HoaDonChiTiet where idHoaDon = ?";
    private String sqlDeleteByIDHoaDon = "delete from HoaDonChiTiet where idHoaDon = ?";
    private String sqlCreate = "INSERT INTO HoaDonChiTiet (idHoaDon, idSanPham, soLuong, donGia, giamGia, thanhTien) VALUES(?,?,?,?,?,?)";
    @Override
    public void create(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getIdHoaDon(), entity.getIdSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getGiamGia(), entity.getThanhTien());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void deleteByIdHoaDon(Integer id) {
        XJdbc.executeUpdate(sqlDeleteByIDHoaDon, id);
    }
    @Override
    public List<HoaDonChiTiet> findAll() {
        return XQuery.getBeanList(HoaDonChiTiet.class, sqlFindAll);
                
    }

    @Override
    public HoaDonChiTiet findById(Integer id) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, sqlFindByID, id);
    }
    
}
