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
 * @author Grover Zhu
 * @version 1.1.0
 */
public class BrightFreshMilk {

	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter stdOut = new PrintWriter(System.out, true);
	private static PrintWriter stdErr = new PrintWriter(System.err, true);

	private Sale sales;

	private SalesFormatter salesFormatter;

	/**
	 * Loads data into the catalog and starts the application.
	 *
	 * @param args
	 *            String arguments. Not used.
	 * @throws IOException
	 *             if there are errors in the input.
	 */
	public static void main(String[] args) throws IOException {

		BrightFreshMilk application = new BrightFreshMilk();
		application.run();

	}

	/**
	 * Constructs a <code>GourmetCoffee</code> object and initializes the
	 * catalog and sales data.
	 *
	 * @param initialCatalog
	 * a product catalog
	 */
	private BrightFreshMilk() {

		this.sales = new Sale();

		this.salesFormatter = PlainTextSalesFormatter.getSingletonInstance();

		loadSales(loadCatalog());
	}

	/**
	 * Creates an empty catalog and then add products to it.
	 *
	 * @return a product catalog
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
	 * Initializes the sales object.
	 */
	private void loadSales(Catalog catalog) {

		List<CurrentOrder> orderList = new ArrayList<CurrentOrder>();

		CurrentOrder orderOne = new CurrentOrder();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderItemList.add(new OrderItem(catalog.findProductByCode("C001"), 5));
		orderOne.setOrderItemList(orderItemList);
		orderOne.setOrderTotal(new BigDecimal("25"));
		orderList.add(orderOne);

		CurrentOrder orderTwo = new CurrentOrder();
		List<OrderItem> orderItemListTwo = new ArrayList<OrderItem>();
		orderItemListTwo.add(new OrderItem(catalog.findProductByCode("C002"), 2));
		orderItemListTwo.add(new OrderItem(catalog.findProductByCode("A001"), 2));
		orderTwo.setOrderItemList(orderItemListTwo);
		orderTwo.setOrderTotal(new BigDecimal("40"));
		orderList.add(orderTwo);

		CurrentOrder orderThree = new CurrentOrder();
		List<OrderItem> orderItemListThree = new ArrayList<OrderItem>();
		orderItemListThree.add(new OrderItem(catalog.findProductByCode("B002"), 1));
		orderThree.setOrderItemList(orderItemListThree);
		orderThree.setOrderTotal(new BigDecimal("14"));
		orderList.add(orderThree);

		sales.setOrderList(orderList);
	}

	/**
	 * Displays a menu of options and verifies the user's choice.
	 *
	 * @return an integer in the range [0,3]
	 */
	private int getChoice() throws IOException {

		int input;

		do {
			try {
				stdErr.println();
				stdErr.print("[0]  Quit\n" + "[1]  Display sales (Plain Text)\n" + "[2]  Display sales (HTML)\n"
						+ "[3]  Display sales (XML)\n" + "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 3 >= input) {
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

	/**
	 * Changes the sales formatter.
	 *
	 * @param newFormatter
	 *            a sales formatter
	 */
	private void setSalesFormatter(SalesFormatter newFormatter) {
		salesFormatter = newFormatter;
	}

	/**
	 * Displays the sales information in the current format.
	 */
	private void displaySales() {
		stdOut.println(salesFormatter.formatSales(sales));
	}

	/**
	 * Presents the user with a menu of options and executes the selected task.
	 */
	private void run() throws IOException {
		int choice = getChoice();
		SalesFormatter newSalesFormatter;
		while (choice != 0){
		if (choice == 1){
			newSalesFormatter = PlainTextSalesFormatter.getSingletonInstance();
			setSalesFormatter(newSalesFormatter);
			displaySales();
		}else if (choice == 2){
			newSalesFormatter = HTMLSalesFormatter.getSingletonInstance();
			setSalesFormatter(newSalesFormatter);
			displaySales();
		}else if(choice == 3){
			newSalesFormatter = XMLSalesFormatter.getSingletonInstance();
			setSalesFormatter(newSalesFormatter);
			displaySales();
		}
		choice = getChoice();
	}
		}
}