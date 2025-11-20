/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Administrator
 */
public class KichCo {

    public int id;
    public String tenKichCo;

    public KichCo() {
    }

    public KichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }
    
    public KichCo(int id, String tenKichCo) {
        this.id = id;
        this.tenKichCo = tenKichCo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }
    
}

