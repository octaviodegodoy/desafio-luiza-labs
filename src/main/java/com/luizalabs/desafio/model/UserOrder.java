package com.luizalabs.desafio.model;

import com.luizalabs.desafio.model.Order;
import com.luizalabs.desafio.model.User;

import java.util.List;

public class UserOrder {
    private User user;
    private List<Order> orders;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
