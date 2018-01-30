import java.io.*;

/**
 * Tests the class <code>ProductArray</code>
 *
 * @author Grover Zhu
 * @version 1.0.0
 * @see productArray
 * @see Product
 */
public class TestProductArray {

	/* Standard output stream */
	private static PrintWriter stdOut = new PrintWriter(System.out, true);

	/* Standard error stream */
	private static PrintWriter stdErr = new PrintWriter(System.err, true);

	/* Variables that contains the test objects */
	private Product first;
	private Product second;
	private Product third;
	private Product fourth;
	private Product[] empty;
	private Product[] array;
	private int nonProductId;

	/**
	 * Tests methods of class {@link ProductArray}
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String args[]) {

		stdOut.println("");
		stdOut.println("Testing class ProductArray...");

		TestProductArray tester = new TestProductArray();
		if (tester.testMakeArray() & tester.testCopyArray() & tester.testGetProduct() & tester.testCountLowerPrice()
				& tester.testSumPrice() & tester.testGetLowestPrice() & tester.testIncreaseAll()
				& tester.testDisplayAll()) {
			stdOut.println("All tests passed");
		}
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
	public static boolean assertTrue(String message, boolean condition) {

		if (!condition) {
			stdErr.print("** Test failure ");
			stdErr.println(message);

			return false;
		} else {

			return true;
		}

	}

	/** Assign the initial value to the test variables */
	private void setUp() {

		first = new Product(101, "First", 1000.0);
		second = new Product(102, "Second", 100.0);
		third = new Product(103, "Third", 1000.0);
		fourth = new Product(104, "Fourth", 500.0);

		nonProductId = 106;

		empty = new Product[0];

		array = new Product[4];
		array[0] = first;
		array[1] = second;
		array[2] = third;
		array[3] = fourth;
	}

	/**
	 * Tests the method <code>makeArray</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testMakeArray() {

		setUp();

		boolean test = true;

		Product[] result = ProductArray.makeArray(first, second, third);

		test = test && assertTrue("1, testing method makeArray", result instanceof Product[]);
		test = test && assertTrue("2, testing method makeArray", result.length == 3);
		test = test && assertTrue("3, testing method makeArray", result[0].equals(first));
		test = test && assertTrue("4, testing method makeArray", result[1].equals(second));
		test = test && assertTrue("5, testing method makeArray", result[2].equals(third));

		return test;
	}

	/**
	 * Tests the method <code>copyArray</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testCopyArray() {

		setUp();

		boolean test = true;

		Product[] resultEmpty = ProductArray.copyArray(empty);

		test = test && assertTrue("1, testing method copyArray", resultEmpty instanceof Product[]);
		test = test && assertTrue("2, testing method copyArray", resultEmpty.length == 0);

		Product resultArray[] = ProductArray.copyArray(array);

		test = test && assertTrue("3, testing method copyArray", resultArray instanceof Product[]);
		test = test && assertTrue("4, testing method copyArray", resultArray.length == 4);
		test = test && assertTrue("5, testing method copyArray", resultArray[0] == first);
		test = test && assertTrue("6, testing method copyArray", resultArray[1] == second);
		test = test && assertTrue("7, testing method copyArray", resultArray[2] == third);
		test = test && assertTrue("8, testing method copyArray", resultArray[3] == fourth);

		return test;
	}

	/**
	 * Tests the method <code>getProduct</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testGetProduct() {

		setUp();

		boolean test = true;

		test = test && assertTrue("1, testing method getProduct", ProductArray.getProduct(empty, nonProductId) == null);

		Product product;

		product = ProductArray.getProduct(array, first.getId());
		test = test && assertTrue("2, testing method getProduct", product != null && product.equals(first));
		product = ProductArray.getProduct(array, second.getId());
		test = test && assertTrue("3, testing method getProduct", product != null && product.equals(second));
		product = ProductArray.getProduct(array, third.getId());
		test = test && assertTrue("4, testing method getProduct", product != null && product.equals(third));
		product = ProductArray.getProduct(array, fourth.getId());
		test = test && assertTrue("5, testing method getProduct", product != null && product.equals(fourth));
		test = test && assertTrue("6, testing method getProduct", ProductArray.getProduct(array, nonProductId) == null);

		return test;
	}

	/**
	 * Tests the method <code>countLowerPrice</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testCountLowerPrice() {

		setUp();

		boolean test = true;

		test = test && assertTrue("1, testing method countLowerPrice", ProductArray.countLowerPrice(empty, 0.0) == 0);

		test = test && assertTrue("2, testing method countLowerPrice", ProductArray.countLowerPrice(array, 0.0) == 0);
		test = test
				&& assertTrue("3, testing method countLowerPrice", ProductArray.countLowerPrice(array, 200.0) == 1);
		test = test && assertTrue("4, testing method countLowerPrice",
				ProductArray.countLowerPrice(array, 100000.0) == 4);

		return test;
	}

	/**
	 * Tests the method <code>sumPrice</code>
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testSumPrice() {

		setUp();

		boolean test = true;

		test = test && assertTrue("1, testing method sumPrice", ProductArray.sumPrice(empty) == 0.0);

		test = test && assertTrue("2, testing method sumPrice", ProductArray.sumPrice(array) == first.getPrice()
				+ second.getPrice() + third.getPrice() + fourth.getPrice());

		return test;
	}

	/**
	 * Tests the method <code>getLowestPrice</code>
	 *
	 * @return <code>true</code> if test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testGetLowestPrice() {

		setUp();

		boolean test = true;

		test = test && assertTrue("1, testing method getLowestPrice", ProductArray.getLowestPrice(array) == 100);

		Product[] arrayTwo = new Product[5];
		arrayTwo[0] = new Product(101, "First", 2000.0);
		arrayTwo[1] = new Product(102, "Second", 100.0);
		arrayTwo[2] = new Product(103, "Third", 1000.0);
		arrayTwo[3] = new Product(104, "Fourth", 500.0);
		arrayTwo[4] = new Product(105, "Fifth", 500.0);

		test = test && assertTrue("2, testing method getLowestPrice", ProductArray.getLowestPrice(arrayTwo) == 100);

		return test;
	}

	/**
	 * Tests the method <code>increaseAll</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testIncreaseAll() {

		setUp();

		boolean test = true;

		double amount = 100.0;
		double salaryFirst = first.getPrice();
		double salarySecond = second.getPrice();
		double salaryThird = third.getPrice();
		double salaryFourth = fourth.getPrice();

		ProductArray.increaseAll(array, amount);
		test = test && assertTrue("1, testing method increaseAll", first.getPrice() == salaryFirst + amount);
		test = test && assertTrue("2, testing method increaseAll", second.getPrice() == salarySecond + amount);
		test = test && assertTrue("3, testing method increaseAll", third.getPrice() == salaryThird + amount);
		test = test && assertTrue("4, testing method increaseAll", fourth.getPrice() == salaryFourth + amount);

		return test;
	}

	/**
	 * Tests the method <code>displayAll</code>.
	 *
	 * @return <code>true</code> if all test passed; otherwise returns
	 *         <code>false</code>.
	 */
	public boolean testDisplayAll() {

		setUp();

		boolean test = true;

		test = test && assertTrue("1, testing method displayAll", ProductArray.displayAll(empty).equals(""));

		String result = ProductArray.displayAll(array);
		test = test && assertTrue("2, testing method displayAll", ProductArray.displayAll(array).equals(
				first.toString() + "\n" + second.toString() + "\n" + third.toString() + "\n" + fourth.toString()));

		return test;
	}
}