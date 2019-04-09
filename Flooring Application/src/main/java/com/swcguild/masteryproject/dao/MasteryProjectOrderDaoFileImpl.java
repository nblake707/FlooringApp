/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import com.swcguild.masteryproject.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blake
 */
public class MasteryProjectOrderDaoFileImpl implements MasteryProjectOrderDao {

    public static String ORDER_FILE = "";
    public static final String DELIMITER = ",";

    private Map<String, Map<Integer, Order>> ordersByDate = new HashMap<>();

    @Override
    public String currentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

//    LocalDate result = localDate.parse(io.readString("What Date").dtf);
    @Override
    public Order createOrder(Order order) throws MasteryProjectPersistenceException {
        if (ordersByDate.containsKey(currentTime()) == false) {
            loadOrder(order.getCreatedDate());
        }
        int nextId = 0;
        Map<Integer, Order> childMap = ordersByDate.get(order.getCreatedDate());
        for (int id : childMap.keySet()) {
            if (id > nextId) {
                nextId = id;
            }
        }
        nextId = nextId + 1;
        order.setOrderId(nextId);
        childMap.put(nextId, order);
//        writeOrder();
        return order;
    }

    @Override
    public Order getOrder(int orderId, String date) throws MasteryProjectPersistenceException {
        if (ordersByDate.containsKey(date) == false) {
            loadOrder(date);
        }
        Map<Integer, Order> allOrders = ordersByDate.get(date);
        return allOrders.get(orderId);
    }

    @Override
    public List<Order> getAllOrdersByDate(String date) throws MasteryProjectPersistenceException {
        if (ordersByDate.containsKey(date) == false) {
            loadOrder(date);
        }
        Map<Integer, Order> allOrders = ordersByDate.get(date);
        return new ArrayList<>(allOrders.values());
    }

    @Override
    public void editOrder(int orderId, String date, Order order) throws MasteryProjectPersistenceException {
        if (ordersByDate.containsKey(date) == false) {
            loadOrder(date);
        }
        Map<Integer, Order> allOrders = ordersByDate.get(date);
        allOrders.put(orderId, order);
        writeOrder();

    }

    @Override
    public void removeOrder(int orderId, String date) throws MasteryProjectPersistenceException {
        if (ordersByDate.containsKey(date) == false) {
            loadOrder(date);
        }
        Map<Integer, Order> allOrders = ordersByDate.get(date);
        Order removedOrder = allOrders.remove(orderId);

    }

    private void loadOrder(String date) throws MasteryProjectPersistenceException {

        String fileName = "Orders_" + date + ".txt";
        Scanner scanner;
        Map<Integer, Order> childMap = new HashMap<>();

        try {
            //scanner opening up file
            scanner = new Scanner(
                    //lets you read from the file line by line
                    new BufferedReader(
                            //used to read from a specific file (indicated by ORDER_FILE variable)
                            new FileReader(fileName)));
        } catch (FileNotFoundException e) {
            this.ordersByDate.put(date, childMap);
            return;
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();
            //Makes code readable
            currentOrder.setOrderId(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setAbb(currentTokens[2]);
            currentOrder.setStateTax(new BigDecimal(currentTokens[3]));

            currentOrder.setProductChosen(currentTokens[4]);
            currentOrder.setArea(new BigDecimal(currentTokens[5]));
            currentOrder.setCostSqFt(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostSqFt(new BigDecimal(currentTokens[7]));
            currentOrder.setTotalMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotal(new BigDecimal(currentTokens[11]));

            //creating a new order to put inside of the hashmap
            //reads everything stored in the file and save it to a hashmap
            // why? bc after the program runs the info will no longer exist
            childMap.put(currentOrder.getOrderId(), currentOrder);

        }
        this.ordersByDate.put(date, childMap);
        scanner.close();
    }

    private void writeOrder() throws MasteryProjectPersistenceException {

        PrintWriter out;

        //getting all the dates as a string and saving to an arrayList
        Set<String> listOfDates = this.ordersByDate.keySet();

        //for loop going through list of dates 
        for (String date : listOfDates) {

            //helps select the order that will be written to next
            ORDER_FILE = "Orders_" + date + ".txt";

            //writing actual 
            try {
                out = new PrintWriter(new FileWriter(ORDER_FILE));

                //Still within original for loop. writes the student object to the file
                List<Order> orderList = new ArrayList<>(this.ordersByDate.get(date).values());
                for (Order currentOrder : orderList) {
                    out.println(currentOrder.getOrderId() + DELIMITER
                            + currentOrder.getCustomerName() + DELIMITER
                            + currentOrder.getAbb() + DELIMITER
                            + currentOrder.getStateTax() + DELIMITER
                            + currentOrder.getProductChosen() + DELIMITER
                            + currentOrder.getArea() + DELIMITER
                            + currentOrder.getCostSqFt() + DELIMITER
                            + currentOrder.getLaborCostSqFt() + DELIMITER
                            + currentOrder.getTotalMaterialCost() + DELIMITER
                            + currentOrder.getTotalLaborCost() + DELIMITER
                            + currentOrder.getTotalTax() + DELIMITER
                            + currentOrder.getTotal() + DELIMITER);
                    //force printwriter to write line to the file
                    out.flush();
                }
            } catch (IOException e) {
                throw new MasteryProjectPersistenceException(
                        "could not save data", e);
            }

            //Clean up
            out.close();
        }

    }

    @Override
    public void save() {
        try {
            writeOrder();
        } catch (MasteryProjectPersistenceException ex) {
            Logger.getLogger(MasteryProjectOrderDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
