/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dto;

import java.math.BigDecimal;

/**
 *
 * @author Blake
 */
public class Tax {
   
    private String abbreviation;
    private BigDecimal Tax;

    public Tax(String abbreviation, BigDecimal Tax) {
        
        this.abbreviation = abbreviation;
        this.Tax = Tax;
    }


    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String Abbreviation) {
        this.abbreviation = Abbreviation;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }
    
    
    
    
}
