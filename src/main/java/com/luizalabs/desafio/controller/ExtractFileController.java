package com.luizalabs.desafio.controller;

import com.luizalabs.desafio.model.Order;
import com.luizalabs.desafio.service.ReadFileService;
import com.luizalabs.desafio.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExtractFileController {

    @Autowired
    ReadFileService readFileService;

    @RequestMapping("/listdata")


    public List<UserOrder> retrieveAllData(){
        return readFileService.readFolderFiles();

    }

    @RequestMapping("/listorders")
    public List<Order> retrieveOrdersById(@RequestParam(value = "orderId", required = false) Integer orderId) {
        if (orderId == null) {
            return readFileService.readFolderFiles().stream()
                    .flatMap(userOrder -> userOrder.getOrders().stream())
                    .collect(Collectors.toList());
        } else {
            return readFileService.readFolderFiles().stream()
                    .flatMap(userOrder -> userOrder.getOrders().stream())
                    .filter(order -> order.getOrder_id() == orderId)
                    .collect(Collectors.toList());
        }
    }

    @RequestMapping("/listdates")
    public List<Order> retrieveOrdersByDates(@RequestParam(value = "startDate", required = false) String start,
                                             @RequestParam(value = "endDate", required = false) String end) {
        // Assuming you have a List of UserOrder objects, and each UserOrder has a date
        List<UserOrder> userOrders = readFileService.readFolderFiles(); // replace this with your method to get the list

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


        if ((start != null) && (end != null)) {

            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);

            return readFileService.readFolderFiles().stream()
                    .flatMap(userOrder -> userOrder.getOrders().stream())
                    .filter(order -> !order.getDate().isBefore(startDate) && !order.getDate().isAfter(endDate))
                    .collect(Collectors.toList());

        } else {

            return readFileService.readFolderFiles().stream()
                    .flatMap(userOrder -> userOrder.getOrders().stream())
                    .collect(Collectors.toList());

        }




    }
}
