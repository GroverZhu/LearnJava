import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class implements a Bright Fresh Milk System.
 *
 * @author author name
 * @version 1.0
 */
public class BrightFreshMilkSystem {

	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter stdOut = new PrintWriter(System.out, true);
	private static PrintWriter stdErr = new PrintWriter(System.err, true);

	private Catalog catalog;
	private Sale sale;
	private CurrentOrder currentOrder;

	/**
	 * Loads data and starts the application.
	 *
	 * @param args
	 *            String arguments. Not used.
	 * @throws IOException
	 *             if there are errors in the input.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		BrightFreshMilkSystem application = new BrightFreshMilkSystem();
		application.run();

	}

	/**
	 *
	 * @param initial
	 */
	private BrightFreshMilkSystem() {

		this.catalog = loadCatalog();
		this.sale = loadSale();
		this.currentOrder = loadCurrentOrder();
	}

	/**
	 * Initializes the Catalog object.
	 */
	private Catalog loadCatalog() {

		Catalog catalog = new Catalog();
		List<Product> productList = new ArrayList<Product>();

		productList.add(new PureMilk("A001", "milk,400ml", new BigDecimal("10"), new Date(), "25 days", "China", "3.5%",
				"2.9%"));
		productList.add(new PureMilk("A002", "skim milk,800ml", new BigDecimal("18"), new Date(), "30 days", "China",
				"3.1%", "3.9%"));

		productList.add(new Jelly("B001", "solid,250ml", new BigDecimal("8.5"), new Date(), "15 days", "solid", "80%"));
		productList.add(new Jelly("B002", "solid,400ml", new BigDecimal("14"), new Date(), "16 days", "solid", "85%"));

		productList.add(
				new Yogurt("C001", "chocolate flavor,270ml", new BigDecimal("5"), new Date(), "40 days", "chocolate"));
		productList.add(new Yogurt("C002", "strawberry flavor,400ml", new BigDecimal("10"), new Date(), "40 days",
				"strawberry"));

		productList.add(new MilkDrink("D001", "taro flavor,250ml", new BigDecimal("13.1"), new Date(), "25 days",
				"taro", "2.9%"));
		productList.add(new MilkDrink("D002", "apple flavor,400ml", new BigDecimal("16"), new Date(), "30 days",
				"apple", "3.8%"));

		catalog.setProductList(productList);
		return catalog;
	}

	/**
	 * Initializes the Sale object.
	 */
	private Sale loadSale() {
		Sale sale = new Sale();
		List<CurrentOrder> orderList = new ArrayList<CurrentOrder>();
		sale.setOrderList(orderList);
		return sale;
	}

	/**
	 * Initializes the CurrentOrder object.
	 */
	private CurrentOrder loadCurrentOrder() {
		CurrentOrder currentOrder = new CurrentOrder();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();

		PureMilk pureMilk = new PureMilk("A002", "skim milk,800ml", new BigDecimal("18"), new Date(), "30 days",
				"China", "3.1%", "3.9%");
		orderItemList.add(new OrderItem(pureMilk, 2));

		Yogurt yogurt = new Yogurt("C001", "chocolate flavor,270ml", new BigDecimal("5"), new Date(), "40 days",
				"chocolate");
		orderItemList.add(new OrderItem(yogurt, 3));

		MilkDrink milkDrink = new MilkDrink("D001", "taro flavor,250ml", new BigDecimal("13.1"), new Date(), "25 days",
				"taro", "2.9%");
		orderItemList.add(new OrderItem(milkDrink, 1));

		Jelly jelly = new Jelly("B001", "solid,250ml", new BigDecimal("8.5"), new Date(), "15 days", "solid", "80%");
		orderItemList.add(new OrderItem(jelly, 3));

		currentOrder.setOrderItemList(orderItemList);
		currentOrder.setOrderTotal(new BigDecimal("89.6"));
		return currentOrder;
	}

	/*
	 * Presents the user with a menu of options and executes the selected task.
	 */
	private void run() throws IOException, InterruptedException {

		int choice = getChoice();

		while (choice != 0) {
			if (choice == 1) {
				displayCatalog();
			} else if (choice == 2) {
				displayProductInformationByCode();
			} else if (choice == 3) {
				displayCurrentOrder();
			} else if (choice == 4) {
				addProductToCurrentOrder();
			} else if (choice == 5) {
				removeProductFromCurrentOrder();
			} else if (choice == 6) {
				addCurrentOrderToSaleAndEmptyCurrentOrder();
			} else if (choice == 7) {
				displaySale();
			}
			Thread.sleep(1);
			choice = getChoice();
		}
	}

	/*
	 * Displays a menu of options and verifies the user's choice.
	 *
	 * @return an integer in the range [0,7]
	 */
	private int getChoice() throws IOException {

		int input;
		do {
			try {
				stdErr.println();
				stdErr.print("[0] Quit\n" + "[1] Display the catalog\n"
						+ "[2] Display the information of a product by its code\n" + "[3] Display the current order\n"
						+ "[4] Add a product to the current order\n" + "[5] Remove a product from the current order\n"
						+ "[6] Adds the current order to the store's sales and empties the current order\n"
						+ "[7] Display the sales including all the orders that have been sold\n" + "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && input <= 7) {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException nfe) {
				stdErr.println(nfe);
			}
		} while (true);

		return input;
	}

	/*
	 * Prompts user for a product code and locates the associated
	 * <code>Product</code> object.
	 *
	 * @return reference to the <code>Product</code> object with the specified
	 * code
	 */
	private Product readProduct() throws IOException {

		stdErr.print("Product code> ");
		stdErr.flush();

		Product product = this.catalog.findProductByCode(stdIn.readLine());
		if (product != null) {
			return product;
		} else {
			stdErr.println("There are no products with that code");
			return null;
		}
	}

	/**
	 * Displays the catalog.
	 */
	public void displayCatalog() {

		int size = this.catalog.getProductList().size();

		if (size == 0) {
			stdErr.println("The catalog is empty");
		} else {
			for (Product product : this.catalog.getProductList()) {
				stdOut.println(product.toString());
				stdOut.flush();
			}
		}
	}

	/**
	 * Displays the information of a product
	 */
	public void displayProductInformationByCode() throws IOException {

		Product product = readProduct();

		while (product == null)
			product = readProduct();

		if (product instanceof Jelly) {
			Jelly jelly = (Jelly) product;
			System.out.println(jelly.toString());
		} else if (product instanceof MilkDrink) {
			MilkDrink milkDrink = (MilkDrink) product;
			System.out.println(milkDrink.toString());
		} else if (product instanceof PureMilk) {
			PureMilk pureMilk = (PureMilk) product;
			System.out.println(pureMilk.toString());
		} else if (product instanceof Yogurt) {
			Yogurt yogurt = (Yogurt) product;
			System.out.println(yogurt.toString());
		} else {
			System.out.println(product.toString());
		}
	}

	/**
	 * Displays the information of a Current Order
	 */
	public void displayCurrentOrder() throws IOException {
		if (currentOrder.getOrderItemList().size() == 0){
			System.out.println("The current order is empty!");
		}else{
		System.out.println(currentOrder.toString());
		}
	}

	/**
	 * Add a product to the current order —if the specified product is already
	 * part of the order, this command will modify the quantity of that product
	 */
	public void addProductToCurrentOrder() throws IOException {
		Product product = readProduct();

		while (product == null)
			product = readProduct();
		currentOrder.setOrderTotal(currentOrder.getOrderTotal().add(product.getPrice()));

		// if the specified product is already part of the order, modify the
		// quantity
		for (int i = 0; i < currentOrder.getOrderItemList().size(); i++) {
			OrderItem orderItem = currentOrder.getOrderItemList().get(i);
			if (product.getCode().equals(orderItem.getProduct().getCode())) {
				orderItem.setQuantity(orderItem.getQuantity() + 1);
				stdErr.println("Add successfully!");
				return;
			}
		}

		// Add the product,if the specified product is not part of the order
		OrderItem newOrderItem = new OrderItem(product, 1);
		List<OrderItem> orderItems = currentOrder.getOrderItemList();
		orderItems.add(newOrderItem);
		currentOrder.setOrderItemList(orderItems);
		stdErr.println("Add successfully!");

	}

	/**
	 * Remove a product from the current order
	 */
	public void removeProductFromCurrentOrder() throws IOException {
		Product product = readProduct();

		while (product == null)
			product = readProduct();

		// find and remove
		for (int i = 0; i < currentOrder.getOrderItemList().size(); i++) {
			OrderItem orderItem = currentOrder.getOrderItemList().get(i);
			if (product.getCode().equals(orderItem.getProduct().getCode())) {
				currentOrder.getOrderItemList().remove(i);

				// subtract the money of the specified product
				BigDecimal money = orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
				currentOrder.setOrderTotal(currentOrder.getOrderTotal().subtract(money));

				stdErr.println("Remove successfully!");
			}
		}
	}

	/**
	 * Adds the current order to the store's sales and empties the current order
	 */
	public void addCurrentOrderToSaleAndEmptyCurrentOrder() throws IOException {
		    
		    sale.getOrderList().add(new CurrentOrder(currentOrder));
		    currentOrder.setOrderTotal(null);
		    int length = currentOrder.getOrderItemList().size();
			for (int index = length - 1; index > -1; index--){
				currentOrder.getOrderItemList().remove(index);
			}
			currentOrder = new CurrentOrder();
			System.out.println("Adds the current order to the sales and empties the "
					+ "current order successfully!");
			

	}

	/**
	 * Display the sales including all the orders that have been sold.
	 */
	public void displaySale() throws IOException {
		List<CurrentOrder> list = sale.getOrderList();
		for (CurrentOrder order : list){
			System.out.println(order.toString());
		}

	}

}