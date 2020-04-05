/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author daniel
 */
public interface ICrud {
    public List list();
    public int add(Object[] o);
    public int update(Object[] o);
    public void delete(int id);
}
