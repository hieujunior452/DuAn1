/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import entity.NhanVien;
import java.util.List;
import util.XJdbc;
import util.XQuery;
import dao.NhanVien_Dao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NhanVien_Daoimpl implements NhanVien_Dao {

    private String sqlFindAll = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id";
    private String sqlFindByMaNV = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where maNhanVien = ?";
    private String sqlFindByIdToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where nv.id like ?";
    private String sqlFindByMaNVToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where nv.maNhanVien like ?";
    private String sqlFindByTenToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where nv.hoVaTen like ?";
    private String sqlFindBySDTToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where nv.sDT like ?";
    private String sqlFindByEmailToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where nv.email like ?";
    private String sqlFindByChucVuToTable = "select * from NhanVien nv join ChucVu cv on nv.idCV = cv.id where tenChucVu = ?";
    private String sqlUpdatePassworld = "update NhanVien set matKhau = ? where maNhanVien = ?";
    private String sqlThemNhanVien = "insert into NhanVien (maNhanVien, matKhau, hoVaTen, ngaySinh, gioiTinh, diaChi, sDT, email, idCV, hinh, trangThai) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String sqlCapNhatNhanVien = "update NhanVien set hoVaTen = ?, ngaySinh = ?, gioiTinh = ?, diaChi = ?, sDT = ?, email = ?, idCV = ?, hinh = ?, trangThai = ? where maNhanVien = ?";
    private String sqlDeleteNhanVien = "delete from NhanVien where maNhanVien = ?";

    @Override
    public NhanVien create(NhanVien entity) {
        try {
            int create = XJdbc.executeUpdate(sqlThemNhanVien, entity.getMaNhanVien(), entity.getMatKhau(), entity.getHoVaTen(), entity.getNgaySinh(),
                    entity.isGioiTinh(), entity.getDiaChi(), entity.getsDT(), entity.getEmail(), entity.getIdCV(), entity.getHinh(), entity.isTrangThai());
            if (create > 0) {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công\n mật khẩu mặc định là : 123456!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            String errorMessage = e.getMessage().toLowerCase();
            if (errorMessage.contains("duplicate") && errorMessage.contains("key")) {
                JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình thêm nhân viên: \n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    @Override
    public void update(NhanVien entity) {
        int update = XJdbc.executeUpdate(sqlCapNhatNhanVien, entity.getHoVaTen(), entity.getNgaySinh(),
                entity.isGioiTinh(), entity.getDiaChi(), entity.getsDT(), entity.getEmail(), entity.getIdCV(), entity.getHinh(), entity.isTrangThai(), entity.getMaNhanVien());
        if (update > 0) {
            JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy mã nhân viên để cập nhật!", "Không thành công", JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public void updatePassword(String passworld, String maNhanVien) {
        XJdbc.executeUpdate(sqlUpdatePassworld, passworld, maNhanVien);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteByMaSV(String maSV) {
        int delete = XJdbc.executeUpdate(sqlDeleteNhanVien, maSV);
        if (delete > 0) {
            JOptionPane.showMessageDialog(null, "xóa nhân viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy mã nhân viên để xóa!", "Không thành công", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public List<NhanVien> findAll() {
        return XQuery.getBeanList(NhanVien.class,
                sqlFindAll);
    }

    @Override
    public NhanVien findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien
            findByMaNV(String maNV) {
        return XQuery.getSingleBean(NhanVien.class,
                sqlFindByMaNV, maNV);
    }

    @Override
    public List<NhanVien> findByToTable(String id, int index) {
        switch (index) {
            case 0:
                return XQuery.getBeanList(NhanVien.class, sqlFindByIdToTable, id);

            case 1:
                return XQuery.getBeanList(NhanVien.class, sqlFindByMaNVToTable, id);

            case 2:
                return XQuery.getBeanList(NhanVien.class, sqlFindByTenToTable, id);

            case 3:
                return XQuery.getBeanList(NhanVien.class, sqlFindBySDTToTable, id);

            case 4:
                return XQuery.getBeanList(NhanVien.class, sqlFindByEmailToTable, id);

            case 5:
                return XQuery.getBeanList(NhanVien.class, sqlFindByChucVuToTable, id);
            default:
                return null;
        }
    }

}
