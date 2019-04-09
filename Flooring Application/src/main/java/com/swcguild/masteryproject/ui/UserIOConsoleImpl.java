/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.ui;

import com.swcguild.masteryproject.dto.Product;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Blake
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);
    
    //Simpler way to print. 
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        int result = 0;
        String userInput = sc.nextLine();
        result = Integer.parseInt(userInput);
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
       print(prompt);
       int result = 0;
       
       String userInput = sc.nextLine();
       result = Integer.parseInt(userInput);
       
       if (result >= min && result <=max){
           return result;
       } else {
           print("Must be in range");
           return readInt(prompt, min, max);
       }
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(BigDecimal change, String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
}
