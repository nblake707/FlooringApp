/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import com.swcguild.masteryproject.dto.Product;
import java.util.List;

/**
 *
 * @author Blake
 */
public interface MasteryProjectProductDao {

    //Method takes no arguments and returns a list of products
    List<Product> getAllProducts() throws MasteryProjectPersistenceException;

    //Takes in a product name and returns a product
    Product getProductsByName(String productName) throws MasteryProjectPersistenceException;
}
