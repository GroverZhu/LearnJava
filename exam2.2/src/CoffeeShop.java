import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class CoffeeShop {

	private Collection cart;

	public CoffeeShop() {
		this.cart = new Vector();
	}

	public String toString() {
		if (this.cart.size() == 0)
			return "empty";
		Iterator iterator = this.cart.iterator();
		StringBuffer stringbuffer = new StringBuffer(iterator.next().toString());
		for (; iterator.hasNext(); stringbuffer.append(iterator.next()
				.toString())) {
			stringbuffer.append("\n");
		}
		return stringbuffer.toString();
	}
    /**
     * 
     * @return total cost
     */
	public double getTotalCost() {
		double d = 0.0D;
		if (this.cart.size() == 0) {
			System.err.println("There is no coffee in the cart!");
			return 0.0D;
		}
		for (Iterator iterator = this.cart.iterator(); iterator.hasNext();) {
			d += ((Coffee) iterator.next()).getCost();
		}
		return d;
	}
   /**
    * 
    * @param coffee
    */
	public void addCoffee(Coffee coffee) {
		if (coffee == null) {
			return;
		} else {
			this.cart.add(coffee);
			System.out.println("Add successfully");
		}
	}
}
