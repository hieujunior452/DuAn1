/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.NhanVien;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface NhanVien_Dao extends Dao_CRUD<NhanVien, Integer>{
    void updatePassword(String passworld, String maNhanVien);
    NhanVien findByMaNV(String maNV);
    List<NhanVien> findByToTable(String id, int index);
    void deleteByMaSV(String maSV);
}
