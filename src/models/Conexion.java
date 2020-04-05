/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author daniel
 */
public class Conexion {
    
    Connection conn;
    String url = "jdbc:mysql://localhost:3306/supermarket_db";
    String user = "root";
    String pass = "Br1tney$2=";
    
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        
        return conn;
    }
    
}
