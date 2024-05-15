package com.luizalabs.desafio;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private int order_id;
    private BigDecimal total;
    private String date;
    private List<Product> products;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public void calculateTotal(){

        this.total = this.products.stream()
                .map(Product::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}