import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Grover Zhu
 *
 */
public class PureMilk extends Product {
	private String countryOfOrigin;
	private String butterfat;
	private String Protein;
	/**
	 * 
	 * @param initialCode
	 * @param initialDescription
	 * @param initialPrice
	 * @param date
	 * @param initialShelfLife
	 * @param initialCountryOfOrigin
	 * @param initialButterfat
	 * @param initialProtein
	 */
	public PureMilk(String initialCode, String initialDescription, 
			BigDecimal initialPrice, Date date, String initialShelfLife,
			String initialCountryOfOrigin, String initialButterfat, String initialProtein){
		super(initialCode, initialDescription, initialPrice, date, initialShelfLife);
		countryOfOrigin = initialCountryOfOrigin;
		butterfat = initialButterfat;
		Protein = initialProtein;
	}
	/**
	 * @return the countryOfOrigin
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	/**
	 * @param countryOfOrigin the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	/**
	 * @return the butterfat
	 */
	public String getButterfat() {
		return butterfat;
	}
	/**
	 * @param butterfat the butterfat to set
	 */
	public void setButterfat(String butterfat) {
		this.butterfat = butterfat;
	}
	/**
	 * @return the protein
	 */
	public String getProtein() {
		return Protein;
	}
	/**
	 * @param protein the protein to set
	 */
	public void setProtein(String protein) {
		Protein = protein;
	}
	public String toString(){
		return "PureMilk [" + super.toString() + ", CountryOfOrigin = " + this.getCountryOfOrigin() + ", Butterfat =  "
		+ this.getButterfat() + ", Protein = " + this.getProtein() + "]";
	}
}
