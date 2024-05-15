package com.luizalabs.desafio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExtractFileControllerTest {

    @InjectMocks
    private ExtractFileController extractFileController;

    @Mock
    private ReadFileService readFileService;

    @Test
    public void retrieveOrdersByIdWithoutOrderIdTest() {
        List<UserOrder> expectedUserOrders = createUserOrders();
        List<Order> expectedOrders = expectedUserOrders.stream()
                .flatMap(userOrder -> userOrder.getOrders().stream())
                .collect(Collectors.toList());

        when(readFileService.readFolderFiles()).thenReturn(expectedUserOrders);

        List<Order> actualUserOrders = extractFileController.retrieveOrdersById(null);

        assertEquals(expectedOrders, actualUserOrders);
    }

    @Test
    public void testRetrieveOrdersByIdWithOrderId() {

        int orderId = 1;
        List<UserOrder> expectedUserOrders = createUserOrders();
        List<Order> expectedOrders = expectedUserOrders.stream()
                .flatMap(userOrder -> userOrder.getOrders().stream())
                .filter(order -> order.getOrder_id() == orderId)
                .collect(Collectors.toList());

        when(readFileService.readFolderFiles()).thenReturn(expectedUserOrders);

        List<Order> actualUserOrders = extractFileController.retrieveOrdersById(orderId);

        assertEquals(expectedOrders, actualUserOrders);
    }

    @Test
    public void testRetrieveOrdersByDatesWithNullDates() {
        List<UserOrder> expectedUserOrders = createUserOrders();
        List<Order> expectedOrders = expectedUserOrders.stream()
                .flatMap(userOrder -> userOrder.getOrders().stream())
                .collect(Collectors.toList());


        when(readFileService.readFolderFiles()).thenReturn(createUserOrders());


        List<Order> actualOrders = extractFileController.retrieveOrdersByDates(null, null);

        Assertions.assertEquals(expectedUserOrders.size(), actualOrders.size());
    }

    @Test
    public void testRetrieveOrdersByDatesWithStartAndEndDates() {
        // Arrange
        String start = "20220101";
        String end = "20220131";
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<UserOrder> expectedUserOrders = createUserOrders();

        List<Order> expectedOrders = expectedUserOrders.stream()
                .flatMap(userOrder -> userOrder.getOrders().stream())
                .filter(order -> !order.getDate().isBefore(startDate) && !order.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        when(readFileService.readFolderFiles()).thenReturn(expectedUserOrders);

        // Act
        List<Order> actualOrders = extractFileController.retrieveOrdersByDates(start, end);

        // Assert
        assertEquals(expectedOrders, actualOrders);
    }

    private static List<UserOrder> createUserOrders() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        UserOrder userOrder = new UserOrder();
        User user = new User();
        user.setName("JUNIT TEST");
        user.setUser_id(1);

        userOrder.setUser(user);

        Product product = new Product();
        product.setProduct_id(1);
        product.setValue(new BigDecimal("9.99"));

        List<Product> products = new ArrayList<>();
        products.add(product);

        Order order = new Order();
        order.setOrder_id(1);

        String strDate = "20220103";
        LocalDate orderDate = LocalDate.parse(strDate, formatter);
        order.setDate(orderDate);
        order.setProducts(products);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        userOrder.setOrders(orders);

        List<UserOrder> expectedUserOrders = new ArrayList<>();
        expectedUserOrders.add(userOrder);
        return expectedUserOrders;
    }

}