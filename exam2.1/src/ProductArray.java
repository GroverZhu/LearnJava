/**
 * This class contains methods to process arrays of {@link Product} objects.
 *
 * @author  Grover Zhu
 * @version  1.0.0
 * @see  Product
 */
public class  ProductArray  {

	/**
	 * Creates an array with three objects of class {@link Product}.
	 * <p>
	 * The first element of the array is the object
	 * <code>first</code>, the second element of the array is
	 * the object <code>second</code>, and  the third element of the
	 * array is the object <code>third</code>.
	 * </p>
	 *
	 * @param first  a {@link Product} object.
	 * @param second  a {@link Product} object.
	 * @param third  a {@link Product} object.
	 * @return  an array with the objects <code>first</code>,
	 *           <code>second</code>, and <code>third</code>.
	 */
	public static Product[] makeArray(Product  first, Product  second,
	                                   Product  third)  {

		Product[] product = new Product[3];
		product[0] = first;
		product[1] = second;
		product[2] = third;
		return product;
		/* PLACE YOUR CODE HERE */
//		return null; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Creates a new array from the specified array of {@link Product}
	 * objects.
	 * <p>
	 * The elements in the new array have the same order as those in
	 * the specified array.
	 * </p>
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @return  a <i>new</i> array of the objects in the specified array.
	 */
	public static Product[] copyArray(Product[]  array)  {
		
		/* PLACE YOUR CODE HERE */
		if (array == null){
			return null;
		}else{
			Product[] product = array.clone();
			return product;
		}
		
	}


	/**
	 * Returns the {@link Product} object with the specified ID.
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @param id  an Product ID.
	 * @return  The {@link Product} object with the specifed
	 *          ID. Returns <code>null</code> if there are no Products
	 *          in the specified array with the specifed ID.
	 */
	public static Product getProduct(Product[]  array, int id)  {
		
		/* PLACE YOUR CODE HERE */
		for (Product product : array){
			if (product.getId() == id){
				return product;
			}
		}
		return null; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns the number of Products whose price is Lower than the specified
	 * dollar amount.
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @param amount  a dollar amount.
	 * @return  the number of Products whose price is Lower than the
	 *          specified dollar amount.
	 */
	public static int countLowerPrice(Product[]  array, double amount)  {

		/* PLACE YOUR CODE HERE */
		int number = 0;
		for (Product product : array){
			if (product.getPrice() < amount){
				number++;
			}
		}
		return number; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns the sum of the price of the Products in the specified
	 * array.
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @return  the sum of the price of the Products in the specified
	 *          array.
	 */
//	for-each loop
	public static double sumPrice(Product[]  array)  {

		/* PLACE YOUR CODE HERE */
		double sum = 0;
		for (Product product : array){
			sum = sum + product.getPrice();
		}
		return sum; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Obtains the Lowest price in the specified array.
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @return  the Lowest price in the specified array.
	 */
	
//	for-each loop
	public static double getLowestPrice(Product[]  array)  {

		/* PLACE YOUR CODE HERE */
		double price = array[0].getPrice();
		for (Product product : array){
			if (product.getPrice() < price){
				price = product.getPrice();
			}
		}
		return price; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Increases the Price of every Product in the specified array by the
	 * specified amount.
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 */
//	for-each loop
	public static void increaseAll(Product[] array, double amount)  {
		
		/* PLACE YOUR CODE HERE */
		for (Product product : array){
			product.setPrice(product.getPrice() + amount);
		}

	}

	/**
	 * Returns a string representation of the specified
	 * {@link Product} array.
	 * <p>
	 * Uses the method <code>toString</code> in class <code>Product</code>
	 * to obtain the string representation of each object in the array.
	 * </p>
	 * A new line character ( \n ) separates the string representations
	 * of each <code>Product</code> object. For example:
	 * </p>
	 * <pre>
	 * Product[102,cruise,68250.0]\n
	 * Product[101,domestic,36000.0]\n
	 * Product[103,outbound,92175.0]
	 * </pre>
	 * <p>
	 * Note that the string returned does <i>not</i> end with a new line
	 * character (\n).
	 * </p>
	 * <p>
	 * This method assumes that every element in the specified array
	 * contains a valid reference to an <code>Product</code> object.
	 * </p>
	 *
	 * @param array  an array that contains objects of class {@link Product}.
	 * @return  the string representation of the specified array
	 */
	public static String displayAll(Product[]  array)  {

		/* PLACE YOUR CODE HERE */
		String str = new String();
		if (array.length  == 0) {
			return "";
		} else {
			int length = array.length;
			for (int i = 0; i < length - 1; i++) {
				str = str + "Product[" + array[i].getId() + "," + array[i].getName() + "," + array[i].getPrice() + "]\n";
			}
			str = str + "Product[" + array[length - 1].getId() + "," + array[length - 1].getName() + "," + array[length - 1].getPrice() + "]";
			return str; // REMOVE; USED SO THIS FILE COMPILES
		}
	}
}