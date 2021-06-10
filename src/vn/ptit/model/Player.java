/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.model;

/**
 *
 * @author Cuong Pham
 */
public class Player {
    private String ten;
    private String thoi_gian;

    public Player() {

    }

    public Player(String ten, String thoi_gian) {
        this.ten = ten;
        this.thoi_gian = thoi_gian;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThoi_gian() {
        return thoi_gian;
    }

    public void setThoi_gian(String thoi_gian) {
        this.thoi_gian = thoi_gian;
    } 
    
    public int getTimePlay(){
        String datas[] = thoi_gian.split(" : ");
        int time=Integer.parseInt(datas[0])*60+Integer.parseInt(datas[1]);
        return time;
    }
    
}
