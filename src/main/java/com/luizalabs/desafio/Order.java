package com.luizalabs.desafio;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private int order_id;
    private String date;
    private List<Product> products;
    private BigDecimal total = BigDecimal.ZERO;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getTotal() {
        return products.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }    

 
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}