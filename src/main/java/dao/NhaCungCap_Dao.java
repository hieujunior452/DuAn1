/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.NhaCungCap;

/**
 *
 * @author Administrator
 */
public interface NhaCungCap_Dao extends Dao_CRUD<NhaCungCap, Integer>{
    NhaCungCap findByName(String ten);
}
