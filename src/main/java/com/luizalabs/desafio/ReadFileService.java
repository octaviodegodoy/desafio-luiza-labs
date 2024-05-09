package com.luizalabs.desafio;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadFileService {



    public void readTextFile(){
        String filePath = "D:\\Dev\\luiza_labs\\desafio-luiza-labs\\src\\main\\resources\\data_test_luiza_labs.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                    int lineLength = line.length();
                  Product product = new Product();
                  product.setProduct_id(Integer.valueOf(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX)));
                  String productValue = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
                  product.setValue(new BigDecimal(productValue));


                  Order order = new Order();
                  order.setOrder_id(Integer.valueOf(line.substring(FieldsLocation.ORDER_BEGIN_INDEX, FieldsLocation.ORDER_END_INDEX)));
                  order.setDate(line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX));

                  List<Order> orders = new ArrayList<>();
                  orders.add(order);
                  User user = new User();

                  System.out.println(user.getUser_id());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
