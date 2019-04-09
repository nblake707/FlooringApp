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
public class Product {
    private String name;
    private BigDecimal costSqFt;
    private BigDecimal LaborCostSqFt;

    public Product(String name, BigDecimal costSqFt, BigDecimal LaborCostSqFt) {
        this.name = name;
        this.costSqFt = costSqFt;
        this.LaborCostSqFt = LaborCostSqFt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCostSqFt() {
        return costSqFt;
    }

    public void setCostSqFt(BigDecimal costSqFt) {
        this.costSqFt = costSqFt;
    }

    public BigDecimal getLaborCostSqFt() {
        return LaborCostSqFt;
    }

    public void setLaborCostSqFt(BigDecimal LaborCostSqFt) {
        this.LaborCostSqFt = LaborCostSqFt;
    }
    
    
    
}
