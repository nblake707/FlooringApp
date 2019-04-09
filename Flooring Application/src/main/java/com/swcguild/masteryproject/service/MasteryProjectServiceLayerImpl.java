/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

import com.swcguild.masteryproject.advice.MasteryProjectAuditDao;
import com.swcguild.masteryproject.dao.MasteryProjectOrderDao;
import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;
import com.swcguild.masteryproject.dao.MasteryProjectProductDao;
import com.swcguild.masteryproject.dao.MasteryProjectTaxDao;
import com.swcguild.masteryproject.dto.Order;
import com.swcguild.masteryproject.dto.Product;
import com.swcguild.masteryproject.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public class MasteryProjectServiceLayerImpl implements MasteryProjectServiceLayer {

    private final MasteryProjectOrderDao dao;
    private final MasteryProjectTaxDao tax;
    private final MasteryProjectProductDao product;
    private final MasteryProjectAuditDao audit;

    public MasteryProjectServiceLayerImpl(MasteryProjectOrderDao dao, MasteryProjectTaxDao tax, MasteryProjectProductDao product, MasteryProjectAuditDao audit) {
        this.dao = dao;
        this.tax = tax;
        this.product = product;
        this.audit = audit;
    }

    @Override
    public Order createOrder(String name, String productName, String stateAbb, BigDecimal area) throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException, InvalidProductException {
        Product product = getProduct(productName);
        Tax newtax = readByAbbr(stateAbb);

        if (product == null) {
            //TODO: throw not valid product exception
            throw new InvalidProductException(
            "Error: This product does not exist!");
        }

        if (newtax == null) {
            //TODO: throw InvalidStateExcpetion();
            throw new InvalidStateException(
            "Error: Please enter a valid state.");
        }

        BigDecimal costSqFt = product.getCostSqFt();
        BigDecimal laborCostSqFt = product.getLaborCostSqFt();
        BigDecimal taxRate = newtax.getTax();

        BigDecimal totalaborCost = area.multiply(laborCostSqFt);
        BigDecimal totalMaterialCost = area.multiply(costSqFt);
        BigDecimal subTotal = totalaborCost.add(totalMaterialCost);
        BigDecimal finalTax = taxRate.multiply(subTotal);
        BigDecimal total = subTotal.add(finalTax);
        Order order = new Order(name, productName, stateAbb, area);
        order.setCreatedDate(dao.currentTime());
        order.setStateTax(taxRate);
        order.setLaborCostSqFt(laborCostSqFt);
        order.setTotalLaborCost(totalaborCost);
        order.setArea(area);
        order.setCostSqFt(product.getCostSqFt());
        order.setTotalMaterialCost(costSqFt.multiply(area));
        order.setTotal(total);
        order.setTotalTax(finalTax);
        return dao.createOrder(order);
    }

    @Override
    public List<Order> getAllOrders(String date) throws InvalidDateException, MasteryProjectPersistenceException {
        return dao.getAllOrdersByDate(date);
    }

    @Override
    public Order getOrder(String orderDate, int orderId) throws InvalidDateException, MasteryProjectPersistenceException {
        return dao.getOrder(orderId, orderDate);
    }

    @Override
    public void deleteOrder(int orderId, String date) throws InvalidDateException, MasteryProjectPersistenceException {
        dao.removeOrder(orderId, date);
    }

    @Override
    public List<Product> getProducts() throws MasteryProjectPersistenceException {
        return product.getAllProducts();
    }

    @Override
    public Product getProduct(String productName) throws MasteryProjectPersistenceException {
        return product.getProductsByName(productName);
    }

    @Override
    public List<Tax> getTaxes() throws InvalidStateException, MasteryProjectPersistenceException {
        return tax.getTaxes();
    }

    @Override
    public Tax readByAbbr(String abbreviation) throws InvalidStateException, MasteryProjectPersistenceException {
        return tax.readByAbbr(abbreviation);
    }

    @Override
    public void Save() {
        dao.save();
    }

    @Override
    public Order validateOrder() throws InvalidDateException, InvalidStateException, MasteryProjectPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(String orderDate, int orderId, String name, String productName, String stateAbb, BigDecimal area) throws InvalidDateException, InvalidStateException, InvalidAreaException, InvalidProductException, NoOrderFoundException, MasteryProjectPersistenceException {
        Product producto = getProduct(productName);
        Tax newtax = readByAbbr(stateAbb);
        Order order = dao.getOrder(orderId, orderDate);

        if (product == null) {
            //TODO: throw not valid product exception
            throw new InvalidProductException(
            "Error: This product does not exist!");
        }

        if (newtax == null) {
            //TODO: throw InvalidStateExcpetion();
            throw new InvalidStateException(
            "Error: Please enter a valid State!");
        }

        if (order == null) {
            //TODO: throw NoOrderFoundException();
            throw new NoOrderFoundException(
            "Error: This order does not exist! ");
        }

        BigDecimal costSqFt = producto.getCostSqFt();
        BigDecimal laborCostSqFt = producto.getLaborCostSqFt();
        BigDecimal taxRate = newtax.getTax();

        BigDecimal totalaborCost = area.multiply(laborCostSqFt);
        BigDecimal totalMaterialCost = area.multiply(costSqFt);
        BigDecimal subTotal = totalaborCost.add(totalMaterialCost);
        BigDecimal finalTax = taxRate.multiply(subTotal);
        BigDecimal total = subTotal.add(finalTax);

        order.setStateTax(taxRate);
        order.setLaborCostSqFt(laborCostSqFt);
        order.setTotalLaborCost(totalaborCost);
        order.setArea(area);
        order.setCostSqFt(producto.getCostSqFt());
        order.setTotalMaterialCost(costSqFt.multiply(area));
        order.setTotal(total);
        order.setTotalTax(finalTax);
        order.setOrderId(orderId);
        order.setAbb(stateAbb);
        order.setCustomerName(name);
        order.setCreatedDate(orderDate);
        order.setProductChosen(productName);

        dao.editOrder(orderId, orderDate, order);
        return order;
    }

}
