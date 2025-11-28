/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.ChatLieu;
import java.util.List;
import javax.swing.JOptionPane;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class ChatLieu_Daoimpl implements ChatLieu_Dao {

    private String sqlFindAll = "select * from ChatLieu";
    private String sqlFindByName = "select * from ChatLieu where tenChatLieu = ?";
    private String sqlCreate = "insert into ChatLieu values (?)";
    private String sqlDeleteByName = "delete from ChatLieu where tenChatLieu = ?";

    @Override
    public void create(ChatLieu entity) {
        XJdbc.executeUpdate(sqlCreate, entity.getTenChatLieu());
    }

    @Override
    public void update(ChatLieu entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatLieu> findAll() {
        return XQuery.getBeanList(ChatLieu.class, sqlFindAll);
    }

    @Override
    public ChatLieu findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteByName(String name) {
        try {
            XJdbc.executeUpdate(sqlDeleteByName, name);
        } catch (Exception e) {
            if (e.getMessage().contains("The DELETE statement conflicted with the REFERENCE constraint")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa đang có sản phẩm thuộc chất liệu này", "Thông báo!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public ChatLieu findByName(String ten) {
        return XQuery.getSingleBean(ChatLieu.class, sqlFindByName, ten);
    }
    
}
