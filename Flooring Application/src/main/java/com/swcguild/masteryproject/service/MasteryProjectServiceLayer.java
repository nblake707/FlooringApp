/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;
import com.swcguild.masteryproject.dto.Order;
import com.swcguild.masteryproject.dto.Product;
import com.swcguild.masteryproject.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public interface MasteryProjectServiceLayer {

    Order createOrder(String name, String productName, String stateAbb, BigDecimal area) throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException, InvalidProductException;

    Order validateOrder() throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException;

    List<Order> getAllOrders(String date) throws InvalidDateException, MasteryProjectPersistenceException;

    Order getOrder(String orderDate, int orderId) throws InvalidDateException, MasteryProjectPersistenceException;

    void deleteOrder(int orderId, String date) throws InvalidDateException, MasteryProjectPersistenceException;

    Order editOrder(String orderDate, int orderId, String name, String productName, String stateAbb, BigDecimal orderArea) throws InvalidDateException, InvalidStateException, InvalidAreaException, InvalidProductException, NoOrderFoundException, MasteryProjectPersistenceException;

    List<Product> getProducts() throws MasteryProjectPersistenceException;

    Product getProduct(String ProductName) throws MasteryProjectPersistenceException;

    List<Tax> getTaxes() throws InvalidStateException, MasteryProjectPersistenceException;

    Tax readByAbbr(String abbreviation) throws InvalidStateException, MasteryProjectPersistenceException;

//    void LoadTaxes() throws InvalidStateException, MasteryProjectPersistenceException;
    void Save();

}
