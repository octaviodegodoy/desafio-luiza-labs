package com.luizalabs.desafio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void readFileServiceTest() {
		ReadFileService readFileService = new ReadFileService();
		readFileService.readTextFile();

	}

}
