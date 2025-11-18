/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.ChucVu;

/**
 *
 * @author Administrator
 */
public interface ChucVu_Dao extends Dao_CRUD<ChucVu, Integer>{
    ChucVu findByName(String name);
    void deleteByMaCV(String maCV);
    ChucVu findbyMaChucVu(String maCV);
}
