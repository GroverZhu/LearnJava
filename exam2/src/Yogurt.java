import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Yogurt extends Product {
	private String flavor;
	/**
	 * 
	 * @param initialCode
	 * @param initialDescription
	 * @param initialPrice
	 * @param initialProductionDate
	 * @param initialShelfLife
	 * @param initialFlavor
	 */
	public Yogurt(String initialCode, String initialDescription, 
			BigDecimal initialPrice, Date initialProductionDate, String initialShelfLife,
			String initialFlavor){
		super(initialCode, initialDescription, initialPrice, initialProductionDate, initialShelfLife);
		flavor = initialFlavor;
	}
	/**
	 * @return the flavor
	 */
	public String getFlavor() {
		return flavor;
	}
	/**
	 * @param flavor the flavor to set
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public String toString(){
		return "Yogurt [" + super.toString() + ", Flavor = " + this.getFlavor() + "]";
	}
}
