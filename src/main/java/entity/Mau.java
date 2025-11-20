/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Administrator
 */
public class Mau {

    public int id;
    public String tenMau;

    public Mau() {
    }

    public Mau(String tenMau) {
        this.tenMau = tenMau;
    }

    public Mau(int id, String tenMau) {
        this.id = id;
        this.tenMau = tenMau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

}
