/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author daniel
 */
public class OrderItem {
    
    int id;
    int idProduct;
    int idOrder;
    int qty;
    double subtotal;
    
    public OrderItem() {
        
    }

    public OrderItem(int id, int idProduct, int idOrder, int qty, double subtotal) {
        this.id = id;
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
