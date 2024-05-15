package com.luizalabs.desafio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ReadFileService {



    public List<UserOrder> readTextFile(){
        String filePath = "D:\\Dev\\luiza_labs\\desafio-luiza-labs\\src\\main\\resources\\data_test_luiza_labs.txt";
        List<UserOrder> userOrders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int userId = Integer.parseInt(line.substring(FieldsLocation.USER_ID_BEGIN_INDEX, FieldsLocation.USER_ID_END_INDEX));

                 if (!userOrders.isEmpty()){

                     Optional<UserOrder> userOrder = userOrders.stream()
                                     .filter(u -> u.getUser().getUser_id() == userId)
                                     .findFirst();

                     if (userOrder.isPresent()) {

                        Product product = new Product();
                        product.setProduct_id(Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX)));
                        product.setValue(new BigDecimal(line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX)));
                        
                        Optional<Order> order = userOrder.get().getOrders().stream()
                                .filter(o -> o.getOrder_id() == Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX)))
                                .findFirst();
                        if (order.isPresent()) {
                            order.get().getProducts().add(product);
                        } else {
                            Order newOrder = new Order();
                            newOrder.setOrder_id(Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX)));
                            newOrder.setDate(line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX));
                            newOrder.getProducts().add(product);
                            userOrder.get().getOrders().add(newOrder);
                        }
                        
                         //TODO Implementar logica de agrupamento de pedidos e valores
                         
                         System.out.println("User found with userId: " + userOrder.get().getUser().getUser_id());
                     } else {
                         //TODO add new user and order
                         System.out.println("User not found");
                     }
                     
                 }

                  User user = new User();
                  user.setUser_id(userId);
                  user.setName(line.substring(FieldsLocation.USER_ID_NAME_BEGIN_INDEX, FieldsLocation.USER_ID_NAME_END_INDEX));

                UserOrder userOrder = new UserOrder();
                userOrder.setUser(user);
                userOrder.setOrders(orders);
                userOrders.add(userOrder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userOrders;
    }
}
