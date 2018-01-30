import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 */

/**
 * @author Grover Zhu
 *
 */
public class MilkDrink extends Product {
	private String flavor;
	private String sugar;
	/**
	 * 
	 * @param initialCode
	 * @param initialDescription
	 * @param initialPrice
	 * @param initialProductionDate
	 * @param initialShelfLife
	 * @param initialFlavor
	 * @param initialSugar
	 */
	public MilkDrink(String initialCode, String initialDescription, 
			BigDecimal initialPrice, Date initialProductionDate, String initialShelfLife,
			String initialFlavor, String initialSugar){
		super(initialCode, initialDescription, initialPrice, initialProductionDate, initialShelfLife);
		flavor = initialFlavor;
		sugar = initialSugar;
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
	/**
	 * @return the sugar
	 */
	public String getSugar() {
		return sugar;
	}
	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}
	public String toString(){
		return "MilkDrink [" + super.toString() + ", Flavor = " + this.getFlavor() + ", Sugar = " + this.getSugar() + "]";
	}
	

}
