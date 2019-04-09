/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import com.swcguild.masteryproject.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Blake
 */
public class MasteryProjectProductDaoFileImpl implements MasteryProjectProductDao {

    //Saved product.txt to a string named PRODUCT_FILE
    public static final String PRODUCT_FILE = "product.txt";

    //values in product.txt are separated by ","
    public static final String DELIMITER = ",";

    //created a hashmap where the key is a string and the product is the value.
    private Map<String, Product> products = new HashMap<>();

    //Takes in no arguments and returns a list of products
    //method must first call loadLibrary() to access values saved in the product.txt file
    //then it gathers the values of the products hashMap and saves it to an arrayList
    //returns all values
    @Override
    public List<Product> getAllProducts() throws MasteryProjectPersistenceException {
        loadLibrary();
        return new ArrayList<>(products.values());

    }
    
    //Takes in a product name as an argument and returns a product
    //calls loadLibrary to access values in the text file
    //returns the value when productName is passed in as the key
    @Override
    public Product getProductsByName(String productName) throws MasteryProjectPersistenceException {
        loadLibrary();
        return products.get(productName);
        
    }

    public void loadLibrary() throws MasteryProjectPersistenceException {
        Scanner scanner;

        try {
            //creates a new scanner
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new MasteryProjectPersistenceException("could not load Inventory data into memory.", e);
        }
        //created a String variable called currentLine
        String currentLine;
        //created an array called currentTokens that will hold strings 
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            String name = (currentTokens[0]);
            BigDecimal costSqFt = new BigDecimal(currentTokens[1]);
            BigDecimal LaborCostSqFt = new BigDecimal(currentTokens[2]);

            Product currentProduct = new Product(name, costSqFt, LaborCostSqFt);

            products.put(currentProduct.getName(), currentProduct);
        }
        scanner.close();
    }

}
