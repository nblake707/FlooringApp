/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.ui;

import com.swcguild.masteryproject.dto.Order;
import com.swcguild.masteryproject.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public class MasteryProjectView {

    private UserIO io;

    public MasteryProjectView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders By Date");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    //this is code to display an individual product
    public void displayProductChoices(Product product) {
        if (product != null) {
            io.print((product.getName() + ":"
                      + product.getCostSqFt() + ":"
                        + product.getLaborCostSqFt()));
        }
    }
    
    
    public String getcustomerName() {
        String customerName = io.readString("please enter Customer Name");
        return customerName;
    }
    
    public String getProductName(){
        String productName = io.readString("please enter product name.");
        return productName;
    }
    
    public String getOrderDate(){
        String orderDate = io.readString("Please enter order Date.");
        return orderDate;
    }
    
    public String getStateAbb(){
        String state = io.readString("Please enter state abbreviation.");
        return state;
    }
    
    public int getOrderNumber(){
        int orderNumber = io.readInt("Please enter an order number.");
        return orderNumber;
    }
    
    public String getOrderArea(){
        String orderArea = io.readString("Please enter area.");
//        BigDecimal area = new BigDecimal(orderArea);
        return orderArea;
    }
    
    public String getDate(){
        String date = io.readString("Please enter the date.");
        return date;
        
    }
    
    public void displayList (List <Order> orders) {
        for ( Order listOrders : orders) {
            displayOrderInfo(listOrders);
        }
    }
    
    public void displayOrderInfo (Order order) {
        io.print("Name: " + order.getCustomerName());
        io.print("Product: " + order.getProductChosen());
        io.print("CostSqft: " + order.getCostSqFt());
        io.print("Labor Cost per Square Foot: " + order.getLaborCostSqFt());
        io.print("State: " + order.getAbb());
        io.print("State Tax: " + order.getStateTax());
        io.print("Total Labor Cost: " + order.getTotalLaborCost());
        io.print("Area: " + order.getArea());
        io.print("Total Tax: " + order.getTotalTax());
        io.print(("Total Material Cost: " + order.getTotalMaterialCost()));
        io.print(("Total: " + order.getTotal()));
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayUnknownCommandBanner(){
        io.print("=== UNKNOWN COMMAND ===");
       
    }
    
    public void displayExitBanner(){
        io.print("Good Bye!");
    }

  
    
    
}
