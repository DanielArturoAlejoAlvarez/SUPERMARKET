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
public class OrderDAO implements ICrud{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion cx = new Conexion();
    
    int r;
    
    public String generateSerialNumber() {
        String serial = "";
        String sql = "SELECT max(SALE_Number) FROM sales";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                serial = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return serial;
    }
    
    public String idOrder() {
        String idv = "";
        String sql = "SELECT max(SALE_Code) FROM sales";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                idv = rs.getString(1);
            }
            
        } catch (Exception e) {
        }
        
        return idv;
    }
    
    public int addOrder(Order o) {        
        String sql = "INSERT INTO sales (CLIE_Code,USR_Code,SALE_Number,SALE_Date,SALE_Total,SALE_Flag) VALUES (?,?,?,?,?,?)";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getIdClient());
            ps.setInt(2, o.getIduser());
            ps.setString(3, o.getSerialNumber());
            ps.setString(4, o.getDate());
            ps.setDouble(5, o.getTotal());
            ps.setString(6, o.getFlag());
            r = ps.executeUpdate(); 
            
        } catch (Exception e) {
        }
        
        return r;
    }
    
    public int addOrderItem(OrderItem det) {
        String sql = "INSERT INTO sale_details (PROD_Code,SALE_Code,DETA_Qty,DETA_Subtotal) VALUES (?,?,?,?)";
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, det.getIdProduct());
            ps.setInt(2, det.getIdOrder());
            ps.setInt(3, det.getQty());
            ps.setDouble(4, det.getSubtotal());
            r = ps.executeUpdate(); 
        } catch (Exception e) {
        }
        
        return r;        
    }

    @Override
    public List list() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        
        try {
            conn = cx.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) { 
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setIdClient(rs.getInt(2));
                order.setIduser(rs.getInt(3));
                order.setSerialNumber(rs.getString(4));
                order.setDate(rs.getString(5));
                order.setTotal(rs.getDouble(6));
                order.setFlag(rs.getString(7));
                
                orders.add(order);
            }
        } catch (Exception e) {
        }
        
        return orders;
    }

    @Override
    public int add(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
