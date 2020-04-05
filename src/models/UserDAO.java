/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class UserDAO implements ICrud{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;   
    
    Conexion cx = new Conexion();
    
    public User validateUser(String dni,String nick) {
        User user = new User();        
        String sql = "SELECT * FROM users WHERE USR_Dni=? AND USR_Nick=?";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, nick);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                user.setId(rs.getInt(1));
                user.setDni(rs.getString(2));
                user.setDisplayName(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setFlag(rs.getString(6));
            }
        } catch (Exception e) {
        }
        
        return user;        
    }
    
    public User userByName(String name) {
        String sql = "SELECT * FROM users WHERE USR_Names=?";
        User u = new User();
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {                
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setDisplayName(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setPhone(rs.getString(5));
                u.setFlag(rs.getString(6));
            }
        } catch (Exception e) {
        }
        
        return u;
    }

    public User listID(int id) {
        String sql = "SELECT * FROM users WHERE USR_Code=?";
        User u = new User();
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setDisplayName(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setPhone(rs.getString(5));
                u.setFlag(rs.getString(6));
            }
        } catch (Exception e) {
        }
        
        return u;
    }
    
    @Override
    public List list() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setDisplayName(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setPhone(rs.getString(5));
                u.setFlag(rs.getString(6));
                
                users.add(u);
            }
        } catch (Exception e) {
        }
        
        return users;        
    }

    @Override
    public int add(Object[] o) {
        int r=0;
        String sql = "INSERT INTO users (USR_Dni,USR_Names,USR_Nick,USR_Phone,USR_Flag) VALUES (?,?,?,?,?)";
        
        try {
           conn = cx.connect();
           ps = conn.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           ps.setObject(4, o[3]);
           ps.setObject(5, o[4]);
           
           r = ps.executeUpdate();
           
        } catch (Exception e) {
        }
        
        return r;        
    }

    @Override
    public int update(Object[] o) {
        int r=0;
        String sql = "UPDATE users SET USR_Dni=?,USR_Names=?,USR_Nick=?,USR_Phone=?,USR_Flag=? WHERE USR_Code=?";
        
        try {
           conn = cx.connect();
           ps = conn.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           ps.setObject(4, o[3]);
           ps.setObject(5, o[4]);
           ps.setObject(6, o[5]);
           r = ps.executeUpdate();
           
        } catch (Exception e) {
        }
        
        return r; 
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE USR_Code=?";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
    
}
