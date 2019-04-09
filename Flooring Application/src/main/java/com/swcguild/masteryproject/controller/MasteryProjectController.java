/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.controller;

import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;
import com.swcguild.masteryproject.dto.Order;
import com.swcguild.masteryproject.service.InvalidAreaException;
import com.swcguild.masteryproject.service.InvalidDateException;
import com.swcguild.masteryproject.service.InvalidProductException;
import com.swcguild.masteryproject.service.InvalidStateException;
import com.swcguild.masteryproject.service.MasteryProjectServiceLayer;
import com.swcguild.masteryproject.service.NoOrderFoundException;
import com.swcguild.masteryproject.ui.MasteryProjectView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public class MasteryProjectController {

    private final MasteryProjectServiceLayer service;
    private final MasteryProjectView view;

    public MasteryProjectController(MasteryProjectServiceLayer service, MasteryProjectView view) {
        this.service = service;
        this.view = view;
    }

    public void Run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {

                    case 1:
                        viewOrderByDate();
                        break;

                    case 2:
                        createOrder();
                        break;

                    case 3:
                        editOrder();
                        break;

                    case 4:
                        deleteOrder();
                        break;

                    case 5:
                        saveOrder();
                        break;
                    case 6:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();

                }
            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

  


    public void createOrder() throws InvalidDateException, InvalidStateException, InvalidProductException, MasteryProjectPersistenceException {
        String name = view.getcustomerName();
        String productName = view.getProductName();
        String strArea = view.getOrderArea();
        String stateAbb = view.getStateAbb();
        BigDecimal area = new BigDecimal(strArea);
        Order currentOrder = service.createOrder(name, productName, stateAbb, area);
        view.displayOrderInfo(currentOrder);
    }

    public void editOrder() throws InvalidDateException, InvalidStateException, InvalidProductException, MasteryProjectPersistenceException, InvalidAreaException, NoOrderFoundException  {
        String orderDate = view.getOrderDate();
        int orderId = view.getOrderNumber();
        String name = view.getcustomerName();
        String productName = view.getProductName();
        
        String stateAbb = view.getStateAbb();
        BigDecimal orderArea = new BigDecimal(view.getOrderArea());
        
      service.editOrder(orderDate, orderId, name, productName, stateAbb, orderArea);
    }

    public void deleteOrder() throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException, InvalidAreaException  {
        String orderDate = view.getOrderDate();
        int orderId = view.getOrderNumber();
        service.deleteOrder(orderId, orderDate);
    }
    
    public void saveOrder(){
        service.Save();
    }

    public void viewOrderByDate() throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException {
       String date = view.getOrderDate();
       int orderId = view.getOrderNumber();
       List <Order> orders = service.getAllOrders(date);
       view.displayList(orders);
       
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
