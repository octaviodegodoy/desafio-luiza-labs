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
		Optional<UserOrder> userOrder = Optional.empty();

		String filePath = "src/test/resources/data_test_luiza_labs.txt";


		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				int userId = Integer.parseInt(line.substring(FieldsLocation.USER_ID_BEGIN_INDEX, FieldsLocation.USER_ID_END_INDEX));
				int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));

				 	userOrder = userOrders.stream()
							.filter(u -> u.getUser().getUser_id() == userId)
							.findFirst();

					if (userOrder.isPresent()) {

						// Verifica se a ordem ja existe
						Optional<Order> optionalOrder = userOrder.get().getOrders().stream()
								.filter(o -> o.getOrder_id() == orderId)
								.findFirst();
						// Se existe adiciona produtos a ordem e calcula total
						if (optionalOrder.isPresent()) {

							addProductsCalculateTotal(optionalOrder, line);

						} else {
							// Se nao existe cria nova ordem com o produto e adiciona a lista de ordens
							createNewOrderProductAddToOrders(userOrder, line);
						}

					}

				userOrders = createUserOrder(line);


				Assertions.assertNotNull(userOrders);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static void createNewOrderProductAddToOrders(Optional<UserOrder> userOrder, String line) {

		String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
		BigDecimal productValue = new BigDecimal(value);
		int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
		int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));

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

	private static void addProductsCalculateTotal(Optional<Order> optionalOrder, String line) {
		int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
		String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();

		BigDecimal productValue = new BigDecimal(value);
		Product product = new Product();
		product.setProduct_id(productId);
		product.setValue(productValue);
		optionalOrder.get().getProducts().add(product);
		optionalOrder.get().calculateTotal();
	}

	private static List<UserOrder> createUserOrder(String line) {

		int userId = Integer.parseInt(line.substring(FieldsLocation.USER_ID_BEGIN_INDEX, FieldsLocation.USER_ID_END_INDEX));
		int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
		int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));
		String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
		BigDecimal productValue = new BigDecimal(value);
		String userName = line.substring(FieldsLocation.USER_ID_NAME_BEGIN_INDEX, FieldsLocation.USER_ID_NAME_END_INDEX);
		String orderDate = line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX);

		List<UserOrder> userOrders = new ArrayList<>();

		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setProduct_id(productId);

		product.setValue(productValue);
		products.add(product);

		Order order = new Order();
		order.setOrder_id(orderId);
		order.setDate(orderDate);

		order.setProducts(products);

		List<Order> orders = new ArrayList<>();
		orders.add(order);

		User user = new User();
		user.setUser_id(userId);
		user.setName(userName);

		UserOrder createUserOrder = new UserOrder();
		createUserOrder.setUser(user);
		createUserOrder.setOrders(orders);
		userOrders.add(createUserOrder);

		return userOrders;
	}

}
