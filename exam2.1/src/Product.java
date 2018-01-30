/**
 * This class models an Product. The following information is maintained:
 * <ol>
 * <li>the ID of the Product, an <code>int</code></li>
 * <li>the name of the Product, a <code>String</code></li>
 * <li>the price of the Product, a <code>double</code></li>
 * </ol>
 *
 * @author  Grover Zhu
 * @version  1.0.0
 */
public class  Product {

	/* The ID of the Product */
	private int id;

	/* The name of the Product */
	private String  name;

	/* The price of the Product */
	private double  price;

	/**
	 * Creates an Product object with the specified ID, name and price.
	 *
	 * @param initialId  the ID of the Product.
	 * @param initialName  the name of the Product.
	 * @param initialprice  the price of the Product.
	 */
	public Product(int initialId, String initialName, double initialPrice) {

		this.id = initialId;
		this.name = initialName;
		this.price = initialPrice;
	}

	/**
	 * Returns the ID of this Product.
	 *
	 * @return  the ID of this Product.
	 */
	public int  getId()  {

		return  this.id;
	}

	/**
	 * Returns the name of this Product.
	 *
	 * @return  the name of this Product.
	 */
	public String  getName()  {

		return  this.name;
	}

	/**
	 * Returns the Price of this Product.
	 *
	 * @return  the Price of this Product.
	 */
	public double  getPrice()  {

		return  this.price;
	}

	/**
	 * Modifies the Price of this Product.
	 *
	 * @param newPrice  the new Price of this Product.
	 */
	public void  setPrice(double newPrice)  {

		this.price = newPrice;
	}

	/**
	 * Returns the string representation of this Product in the following
	 * format: Product[<i>ID</i>,<i>name</i>,<i>Price</i>]
	 *
	 * @return a string representation of this Product.
	 */
	public String  toString()  {

		return "Product[" + getId() + "," + getName() + "," + getPrice() +"]";
	}
}