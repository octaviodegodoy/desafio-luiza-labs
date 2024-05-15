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


                         //TODO Implementar logica de agrupamento de pedidos e valores
                         
                         System.out.println("User found with userId: " + userOrder.get().getUser().getUser_id());
                     } else {
                         //TODO add new user and order
                         System.out.println("User not found");
                     }
                     
                 }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userOrders;
    }
}
