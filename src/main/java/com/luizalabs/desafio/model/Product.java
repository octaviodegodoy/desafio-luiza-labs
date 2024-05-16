package com.luizalabs.desafio.model;

import java.math.BigDecimal;

public class Product {

    private int product_id;
    private BigDecimal value;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
