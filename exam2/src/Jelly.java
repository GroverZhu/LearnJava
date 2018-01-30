import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class Jelly extends Product {
	private String type;
	private String diluteConcentration;
	/**
	 * 
	 * @param initialCode
	 * @param initialDescription
	 * @param initialPrice
	 * @param initialProductionDate
	 * @param initialShelfLife
	 * @param initialType
	 * @param initialDiluteConcentration
	 */
	public Jelly(String initialCode, String initialDescription, 
			BigDecimal initialPrice, Date initialProductionDate, 
			String initialShelfLife, String initialType, String initialDiluteConcentration){
		super(initialCode, initialDescription, initialPrice, initialProductionDate, initialShelfLife);
		type = initialType;
		diluteConcentration = initialDiluteConcentration;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the diluteConcentration
	 */
	public String getDiluteConcentration() {
		return diluteConcentration;
	}
	/**
	 * @param diluteConcentration the diluteConcentration to set
	 */
	public void setDiluteConcentration(String diluteConcentration) {
		this.diluteConcentration = diluteConcentration;
	}
	/**
	 * @return String
	 */
	public String toString(){
		return "Jelly [" + super.toString() + ", Type = " + this.getType()
		+ ", DiluteConcentration = " + this.getDiluteConcentration() + "]";
	}
}
