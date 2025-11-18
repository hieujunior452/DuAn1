/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.*;
import entity.SanPham;
import java.util.List;
import util.XQuery;

/**
 *
 * @author Administrator
 */
public class SanPham_Daoimpl implements SanPham_Dao {

    private String sqlFindAll = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id";
    private String sqlFindById = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where sp.id like ?";
    private String sqlFindByTenSp = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where sp.tenGiay like ?";
    private String sqlFindByMau = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where m.tenMau like ?";
    private String sqlFindByKichCo = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where kc.tenKichCo like ?";
    private String sqlFindByLoai = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where lg.tenLoai like ?";
    private String sqlFindByChatLieu = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where cl.tenChatLieu like ?";
    private String sqlFindByNhaCungCap = "select * from SanPham sp \n"
            + "join Mau m on sp.idMau = m.id\n"
            + "join KichCo kc on sp.size = kc.id\n"
            + "join LoaiGiay lg on sp.idLoaiGiay = lg.id\n"
            + "join ChatLieu cl on sp.idchatLieu = cl.id\n"
            + "join NhaCungCap ncc on sp.idNhaCungCap = ncc.id where ncc.tenNCC like ?";

    @Override
    public SanPham create(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> findAll() {
        return XQuery.getBeanList(SanPham.class, sqlFindAll);
    }

    @Override
    public SanPham findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> findByToTable(String id, int index) {
        switch (index) {
            case 0:
                return XQuery.getBeanList(SanPham.class, sqlFindById, id);
            case 1:
                return XQuery.getBeanList(SanPham.class, sqlFindByTenSp, id);
            case 2:
                return XQuery.getBeanList(SanPham.class, sqlFindByMau, id);
            case 3:
                return XQuery.getBeanList(SanPham.class, sqlFindByKichCo, id);
            case 4:
                return XQuery.getBeanList(SanPham.class, sqlFindByLoai, id);
            case 5:
                return XQuery.getBeanList(SanPham.class, sqlFindByNhaCungCap, id);
            default:
                return null;
        }
    }

}
