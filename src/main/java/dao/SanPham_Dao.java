/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.SanPham;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface SanPham_Dao extends Dao_CRUD<SanPham, Integer> {
    List<SanPham> findByToTable(String id, int index);
}
