package com.luizalabs.desafio;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ReadFileService {

    public void readTextFile(){
        String filePath = "D:\\Dev\\luiza_labs\\desafio-luiza-labs\\src\\main\\resources\\data_test_luiza_labs.txt";
        int targetLine = 1;  // change to the line number you're interested in
        int targetColumn = 95;  // change to the column number you're interested in

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber == targetLine) {
                    String[] words = line.split("\\s+");  // split line into words
                    for (String word : words) {
                        if (word.startsWith(String.valueOf(line.charAt(targetColumn - 1)))) {
                            System.out.println(word);
                            break;
                        }
                    }
                    break;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
