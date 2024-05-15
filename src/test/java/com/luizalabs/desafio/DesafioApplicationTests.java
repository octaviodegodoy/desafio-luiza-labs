package com.luizalabs.desafio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DesafioApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void readFileServiceTest() throws FileNotFoundException {
		List<UserOrder> userOrders = new ArrayList<>();

		String filePath = "src/test/resources/data_test_luiza_labs.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				int userId = Integer.parseInt(line.substring(FieldsLocation.USER_ID_BEGIN_INDEX, FieldsLocation.USER_ID_END_INDEX));
				int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
				int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));
				String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
				BigDecimal productValue = new BigDecimal(value);
				String userName = line.substring(FieldsLocation.USER_ID_NAME_BEGIN_INDEX, FieldsLocation.USER_ID_NAME_END_INDEX);

				Assertions.assertNotNull(userId);
				if (!userOrders.isEmpty()){

					Optional<UserOrder> userOrder = userOrders.stream()
							.filter(u -> u.getUser().getUser_id() == userId)
							.findFirst();

					if (userOrder.isPresent()) {
						List<Product> optionalProducts = new ArrayList<>();

						Optional<Order> optionalOrder = userOrder.get().getOrders().stream()
								.filter(o -> o.getOrder_id() == orderId)
								.findFirst();

						if (optionalOrder.isPresent()) {

							Product product = new Product();
							product.setProduct_id(productId);
							product.setValue(productValue);
							optionalOrder.get().getProducts().add(product);
							optionalOrder.get().calculateTotal();

						} else {
							Product product = new Product();
							product.setProduct_id(productId);
							product.setValue(productValue);
							List<Product> products = new ArrayList<>();
							products.add(product);

							Order order = new Order();
							order.setOrder_id(orderId);
							order.setProducts(products);
							userOrder.get().getOrders().add(order);
						}

					} else {
						List<Product> products = new ArrayList<>();
						Product product = new Product();
						product.setProduct_id(productId);

						product.setValue(productValue);
						products.add(product);

						Order order = new Order();
						order.setOrder_id(orderId);
						order.setDate(line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX));

						order.setProducts(products);

						List<Order> orders = new ArrayList<>();
						orders.add(order);

						User user = new User();
						user.setUser_id(userId);
						user.setName(userName);

						UserOrder newUserOrder = new UserOrder();
						newUserOrder.setUser(user);
						newUserOrder.setOrders(orders);
						userOrders.add(newUserOrder);


					}

				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
