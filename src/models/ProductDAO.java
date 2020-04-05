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
public class ProductDAO implements ICrud{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion cx = new Conexion();
    
    public int updateStock(int qty, int idp) {
        int r = 0;
        String sql = "UPDATE products SET PROD_Stock=? WHERE PROD_Code=?";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setInt(2, idp);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        
        return r;
    }
    
    public Product listID(int id) {
        String sql = "SELECT * FROM products WHERE PROD_Code=?";
        Product p = new Product();
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setFlag(rs.getString(5));
            }
        } catch (Exception e) {
        }
        
        return p;
    }

    @Override
    public List list() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setFlag(rs.getString(5));
                
                products.add(p);
            }
        } catch (Exception e) {
        }
        
        return products;
    }

    @Override
    public int add(Object[] o) {
        int r=0;
        String sql = "INSERT INTO products (PROD_Name,PROD_Price,PROD_Stock,PROD_Flag) VALUES (?,?,?,?)";
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
        int r=0;
        String sql = "UPDATE products SET PROD_Name=?,PROD_Price=?,PROD_Stock=?,PROD_Flag=? WHERE PROD_Code=?";
        
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
        String sql = "DELETE FROM products WHERE PROD_Code=?";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
