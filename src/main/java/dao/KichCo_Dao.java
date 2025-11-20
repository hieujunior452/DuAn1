/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.KichCo;

/**
 *
 * @author Administrator
 */
public interface KichCo_Dao extends Dao_CRUD<KichCo, Integer>{
    void deleteByName(String ten);
    KichCo findByName(String ten);
}
