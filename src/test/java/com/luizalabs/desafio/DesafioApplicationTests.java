package com.luizalabs.desafio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class DesafioApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void readFolderFilesTest(){
		String folderPath = "src/test/resources/"; // replace with your folder path

		try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
			paths.filter(Files::isRegularFile)
					.filter(p -> p.toString().endsWith(".txt"))
					.forEach(DesafioApplicationTests::readFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void readFile(Path path) {

		List<UserOrder> userOrders = new ArrayList<>();
		Optional<UserOrder> userOrder;
		// read the file
		try {
			List<String> lines = Files.readAllLines(path);
			// process lines
			for (String line : lines) {
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

				} else {
					userOrders.add(createUserOrder(line));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createNewOrderProductAddToOrders(Optional<UserOrder> userOrder, String line) {

		String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
		BigDecimal productValue = new BigDecimal(value);
		int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
		int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));
		String orderDateString = line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate orderDate = LocalDate.parse(orderDateString, formatter);

		Product product = new Product();
		product.setProduct_id(productId);
		product.setValue(productValue);
		List<Product> products = new ArrayList<>();
		products.add(product);

		Order order = new Order();
		order.setOrder_id(orderId);
		order.setDate(orderDate);
		order.setProducts(products);
		order.calculateTotal();
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

	private static UserOrder createUserOrder(String line) {

		int userId = Integer.parseInt(line.substring(FieldsLocation.USER_ID_BEGIN_INDEX, FieldsLocation.USER_ID_END_INDEX));
		int productId = Integer.parseInt(line.substring(FieldsLocation.PRODUCT_ID_BEGIN_INDEX, FieldsLocation.PRODUCT_ID_END_INDEX));
		int orderId = Integer.parseInt(line.substring(FieldsLocation.ORDER_ID_BEGIN_INDEX, FieldsLocation.ORDER_ID_END_INDEX));
		String value = line.substring(FieldsLocation.PRODUCT_VALUE_BEGIN_INDEX, FieldsLocation.PRODUCT_VALUE_END_INDEX).trim();
		BigDecimal productValue = new BigDecimal(value);
		String userName = line.substring(FieldsLocation.USER_ID_NAME_BEGIN_INDEX, FieldsLocation.USER_ID_NAME_END_INDEX);
		String orderDateString = line.substring(FieldsLocation.ORDER_DATE_BEGIN_INDEX, FieldsLocation.ORDER_DATE_END_INDEX);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate orderDate = LocalDate.parse(orderDateString, formatter);

		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setProduct_id(productId);

		product.setValue(productValue);
		products.add(product);

		Order order = new Order();
		order.setOrder_id(orderId);
		order.setDate(orderDate);

		order.setProducts(products);
		order.calculateTotal();

		List<Order> orders = new ArrayList<>();
		orders.add(order);

		User user = new User();
		user.setUser_id(userId);
		user.setName(userName);

		UserOrder createUserOrder = new UserOrder();
		createUserOrder.setUser(user);
		createUserOrder.setOrders(orders);

		return createUserOrder;

	}

}
