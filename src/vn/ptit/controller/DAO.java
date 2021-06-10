/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vn.ptit.model.Player;

/**
 *
 * @author CuongPham
 */
public class DAO {

    private Connection connection;
    private String DB_URL = "jdbc:mysql://localhost:3306/puzzle";
    private String USER_NAME = "root";
    private String PASSWORD = "root";

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean addPlayer(Player player){
        String sql = "INSERT INTO xep_hang(ten, thoi_gian) VALUES(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, player.getTen());
            ps.setString(2, player.getThoi_gian());
            
            return  ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Player> getPlayers(){
        List<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM xep_hang";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Player p = new Player();
                p.setTen(rs.getString("ten"));
                p.setThoi_gian(rs.getString("thoi_gian"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
