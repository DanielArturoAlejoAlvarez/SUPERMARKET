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
public class ClientDAO implements ICrud{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion cx = new Conexion();
    
    
    public Client listID(String dni) {
        Client c = new Client();
        String sql = "SELECT * FROM clients WHERE CLIE_Dni=?";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            
            rs = ps.executeQuery();
            while (rs.next()) {                
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setDisplayName(rs.getString(3));
                c.setAddress(rs.getString(4));
                c.setFlag(rs.getString(5));                
            }
        } catch (Exception e) {
        }
        
        return c;
    }
    

    @Override
    public List list() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Client c = new Client();
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setDisplayName(rs.getString(3));
                c.setAddress(rs.getString(4));
                c.setFlag(rs.getString(5));
                
                clients.add(c);
            }
        } catch (Exception e) {
        }
        
        return clients;
    }

    

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO clients (CLIE_Dni,CLIE_Names,CLIE_Address,CLIE_Flag) VALUES (?,?,?,?)";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            
            r = ps.executeUpdate();           
            
            
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE clients SET CLIE_Dni=?,CLIE_Names=?,CLIE_Address=?,CLIE_Flag=? WHERE CLIE_Code=?";
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
    public void delete(int id) {
        String sql = "DELETE FROM clients WHERE CLIE_Code=?";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

   
    
    
    
}
