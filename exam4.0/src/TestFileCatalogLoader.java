import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tests the class <code>FileCatalogLoader</code>
 *
 * @author iCarnegie
 * @version 1.0.0
 * @see FileCatalogLoader
 */
public class TestFileCatalogLoader {

	/* Standard output stream */
	private static PrintWriter stdOut = new PrintWriter(System.out, true);

	/* Standard error stream */
	private static PrintWriter stdErr = new PrintWriter(System.err, true);

	/**
	 * Tests methods of class {@link FileCatalogLoader}
	 *
	 * @param args
	 *            not used.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static void main(String args[]) throws IOException {

		stdOut.println("");
		stdOut.println("Testing class FileCatalogLoader...");

		TestFileCatalogLoader tester = new TestFileCatalogLoader();

		tester.testLoadCatalog();
		stdOut.println("All tests passed");
		stdOut.println("");
	}

	/**
	 * Displays a message in the standard error stream if the value specified by
	 * parameter <code>condition<code> is <code>false</code>.
	 *
	 * @param message
	 *            the error message.
	 * @param condition
	 *            the test condition.
	 * @return the value of <code>condition</code>
	 */
	public static void assertTrue(String message, boolean condition) {

		if (!condition) {
			stdErr.print("** Test failure ");
			stdErr.println(message);

			System.exit(1);
		}

	}

	/**
	 * Displays a message in the standard error stream.
	 *
	 * @param message
	 *            the error message.
	 * @return <code>false</code>;
	 */
	public static void fail(String message) {

		stdErr.print("** Test failure ");
		stdErr.println(message);

		System.exit(1);
	}

	/**
	 * Tests the method <code>loadCatalog</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public void testLoadCatalog() throws IOException {

		CatalogLoader loader = new FileCatalogLoader();

		try {
			// Testing an empty file
			Catalog emptyCatalog = loader.loadCatalog("empty.dat");

			assertTrue("1, testing method read with an empty file", emptyCatalog instanceof Catalog);
			assertTrue("2, testing method read with an empty file" + emptyCatalog.getProductList().size()
					+ " products loaded", emptyCatalog.getProductList().size() == 0);

			// Testing a not empty file
			Catalog catalog = loader.loadCatalog("catalog.dat");

			assertTrue("3, testing method loadCatalog", catalog instanceof Catalog);
			assertTrue("4, testing method loadCatalog: " + catalog.getProductList().size() + " products loaded",
					catalog.getProductList().size() == 8);

			// Testing product C001
			Product product = catalog.findProductByCode("C001");

			assertTrue("5, testing method loadCatalog" + product.toString(), product instanceof Yogurt);

			Yogurt yogurtC001 = (Yogurt) product;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			assertTrue("6, testing method loadCatalog: " + yogurtC001.toString(),
					yogurtC001.getCode().equals("C001") && yogurtC001.getDescription().equals("chocolate flavor,270ml")
							&& yogurtC001.getPrice().equals(new BigDecimal("5"))
							&& yogurtC001.getProductionDate().equals(sdf.parse("2017-10-20"))
							&& yogurtC001.getShelfLife().equals("40 days")
							&& yogurtC001.getFlavor().equals("chocolate"));

			// Testing product C002

			product = catalog.findProductByCode("C002");

			assertTrue("7, testing method loadCatalog" + product.toString(), product instanceof Yogurt);

			Yogurt yogurtC002 = (Yogurt) product;

			assertTrue("8, testing method loadCatalog: " + yogurtC002.toString(),
					yogurtC002.getCode().equals("C002") && yogurtC002.getDescription().equals("strawberry flavor,400ml")
							&& yogurtC002.getPrice().equals(new BigDecimal("10"))
							&& yogurtC002.getProductionDate().equals(sdf.parse("2017-10-20"))
							&& yogurtC002.getShelfLife().equals("40 days")
							&& yogurtC002.getFlavor().equals("strawberry"));

			// Testing product A001
			product = catalog.findProductByCode("A001");

			assertTrue("9, testing method loadCatalog: " + product.toString(), product instanceof PureMilk);

			assertTrue("10, testing method loadCatalog: " + product.toString(),
					product.getCode().equals("A001") && product.getDescription().equals("milk,400ml")
							&& product.getPrice().equals(new BigDecimal("10")));

			// Testing product B002
			product = catalog.findProductByCode("B002");

			assertTrue("11, testing method loadCatalog: " + product.toString(), product instanceof Jelly);

			Jelly productB002 = (Jelly) product;

			assertTrue("12, testing method loadCatalog: " + productB002.toString(),
					productB002.getCode().equals("B002") && productB002.getDescription().equals("solid,400ml"));

		} catch (Exception e) {
			fail("13, testing method loadCatalog: " + e.getMessage());
		}
	}
}