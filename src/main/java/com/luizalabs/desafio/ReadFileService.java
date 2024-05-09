package com.luizalabs.desafio;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReadFileService {

    public static final String USER_ID = "user_id";

    public Map<String,String> readTextFile(){
        String filePath = "D:\\Dev\\luiza_labs\\desafio-luiza-labs\\src\\main\\resources\\data_test_luiza_labs.txt";

        Map<String,String> orderMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                  String[] words = line.split("\\s+");  // split line into words
                  String orderLine = words[0];
                  String[] fields = orderLine.split(",");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderMap;
    }
}
