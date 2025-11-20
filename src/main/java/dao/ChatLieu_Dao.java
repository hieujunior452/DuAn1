/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.ChatLieu;

/**
 *
 * @author Administrator
 */
public interface ChatLieu_Dao extends Dao_CRUD<ChatLieu, Integer>{
    void deleteByName(String name);
    ChatLieu findByName(String ten);
}
