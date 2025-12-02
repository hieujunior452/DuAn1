/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.KhuyenMai;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class KhuyenMai_Daoimpl implements KhuyenMai_Dao {

    private String sqlFindAll = "select * from KhuyenMai";
    private String sqlFindBytenKM = "select * from KhuyenMai where tenKM = ?";
    private String sqlFindByID = "select * from KhuyenMai where id = ?";
    private String sqlCreate = "insert into KhuyenMai values (?,?,?,?,?,?)";
    private String sqlUpdate = "update KhuyenMai set tenKM=?,phanTramGiam=?,idLoai=?,ngayBatDau=?,ngayKetThuc=?,trangThai=? where id = ?";
    private String sqlDelete = "delete from KhuyenMai where id = ?";

    @Override
    public void create(KhuyenMai entity) {
        int i = XJdbc.executeUpdate(sqlCreate, entity.getTenKM(), entity.getPhanTramGiam(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.isTrangThai(), entity.getIdLoai());
        if (i > 0) {
            JOptionPane.showMessageDialog(null, "Thêm phiếu giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void update(KhuyenMai entity) {
        int i = XJdbc.executeUpdate(sqlUpdate, entity.getTenKM(), entity.getPhanTramGiam(), entity.getIdLoai(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.isTrangThai(), entity.getId());
        if (i > 0) {
            JOptionPane.showMessageDialog(null, "Sửa phiếu giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void deleteById(Integer id) {
        int i = XJdbc.executeUpdate(sqlDelete, id);
        if (i > 0) {
            JOptionPane.showMessageDialog(null, "Xóa phiếu giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public List<KhuyenMai> findAll() {
        return XQuery.getBeanList(KhuyenMai.class, sqlFindAll);
    }

    @Override
    public KhuyenMai findById(Integer id) {
        return XQuery.getSingleBean(KhuyenMai.class, sqlFindByID, id);
    }

    public KhuyenMai findBytenKM(String id) {
        return XQuery.getSingleBean(KhuyenMai.class, sqlFindBytenKM, id);
    }

}
