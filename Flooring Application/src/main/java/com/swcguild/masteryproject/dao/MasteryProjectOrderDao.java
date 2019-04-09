/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import com.swcguild.masteryproject.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Blake
 */
public interface MasteryProjectOrderDao {
    
    String currentTime();

    Order createOrder(Order order) throws MasteryProjectPersistenceException;

    Order getOrder(int orderId, String date) throws MasteryProjectPersistenceException;

    List <Order> getAllOrdersByDate(String date) throws MasteryProjectPersistenceException;

    void editOrder(int OrderId, String date, Order order) throws MasteryProjectPersistenceException;

    void removeOrder(int orderId, String date) throws MasteryProjectPersistenceException;

    public void save();

}
