package com.luizalabs.desafio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Order {
    private int order_id;
    private BigDecimal total;
    private LocalDate date;
    private List<Product> products;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void calculateTotal(){
        if (this.products != null) {
            this.total = this.products.stream()
                    .map(Product::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }


    }
}