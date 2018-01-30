import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a Bright Fresh Milk system.
 *
 */
public class BrightFreshMilk {

	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter stdOut = new PrintWriter(System.out, true);
	private static PrintWriter stdErr = new PrintWriter(System.err, true);

	private Catalog catalog;
	private Sale sales;

	private SalesFormatter salesFormatter;

	/**
	 * Loads catalog data from a file and starts the application.
	 * <p>
	 * The name of the file is specified in the command arguments.
	 * </p>
	 *
	 * @param args
	 *            String arguments.
	 * @throws IOException
	 *             if there are errors in the input.
	 */
	public static void main(String[] args) throws IOException {

		Catalog catalog = null;

		if (args.length != 1) {
			stdErr.println("Usage: java BrightFreshMilk filename");
		} else {
			try {
				catalog = (new FileCatalogLoader()).loadCatalog(args[0]);
			} catch (FileNotFoundException fnfe) {
				stdErr.println("The file does not exist");

				System.exit(1);

			} catch (DataFormatException dfe) {
				stdErr.println("The file contains malformed data: " + dfe.getMessage());

				System.exit(1);
			} catch (ParseException e) {
				stdErr.println(e.getMessage());

				System.exit(1);
			}

			BrightFreshMilk application = new BrightFreshMilk(catalog);

			application.run();
		}
	}

	/**
	 * Initializes the catalog data with the value specified in the parameter.
	 *
	 * @param initialCatalog
	 *            a product catalog
	 */
	private BrightFreshMilk(Catalog initialCatalog) {

		this.catalog = initialCatalog;
		this.sales = new Sale();
		this.salesFormatter = PlainTextSalesFormatter.getSingletonInstance();

		loadSales();
	}

	/**
	 * Initializes the sales object.
	 */
	private void loadSales() {

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
	 * Presents the user with a menu of options and executes the selected task.
	 */
	private void run() throws IOException {

		int choice = getChoice();

		while (choice != 0) {
			if (choice == 1) {
				displayCatalog();
			} else if (choice == 2) {
				this.salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
				writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
			} else if (choice == 3) {
				this.salesFormatter = HTMLSalesFormatter.getSingletonInstance();
				writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
			} else if (choice == 4) {
				this.salesFormatter = XMLSalesFormatter.getSingletonInstance();
				writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
			}

			choice = getChoice();
		}
	}

	/**
	 * Displays a menu of options and verifies the user's choice.
	 *
	 * @return an integer in the range [0,4]
	 */
	private int getChoice() throws IOException {

		int input;

		do {
			try {
				stdErr.println();
				stdErr.print("[0]  Quit\n" + "[1]  Display Catalog\n" + "[2]  Save sales (Plain Text)\n"
						+ "[3]  Save sales (HTML)\n" + "[4]  Save sales (XML)\n" + "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 4 >= input) {
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
	 * Displays the catalog.
	 */
	private void displayCatalog() {

		int size = this.catalog.getProductList().size();

		if (size == 0) {
			stdErr.println("The catalog is empty");
		} else {
			for (Product product : this.catalog.getProductList()) {
				stdOut.println(product.getCode() + " " + product.getDescription() + " " + product.getPrice());
			}
		}
	}

	/**
	 * Creates a new file (which has the specified name) and writes the
	 * specified string to the new file.
	 *
	 * @param filename
	 *            name of the file that will store the data
	 * @param content
	 *            data to be stored
	 */
	private void writeFile(String filename, String content) throws IOException {
		FileWriter file = new FileWriter(new File(filename));
        file.write(content);
        file.close();
		//to do
	}

	/**
	 * Prompts the user for a filename (the name of the file that will store the
	 * sales information) and returns the user's response.
	 *
	 * @return name of a file
	 */
	private String readFilename() throws IOException {

		stdErr.print("Filename> ");
		stdErr.flush();

		return stdIn.readLine();
	}
}