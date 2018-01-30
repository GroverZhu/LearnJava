import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Grover Zhu
 *
 */
public class Product {
	private String code;
	private String description;
	private BigDecimal price;
	private Date productionDate;
	private String ShelfLife;
	/**
	 * 
	 * @param initialCode
	 * @param initialDescription
	 * @param initialPrice
	 * @param initialProductionDate
	 * @param initialShelfLife
	 */
	public Product(String initialCode, String initialDescription, 
			BigDecimal initialPrice, Date initialProductionDate, String initialShelfLife){
		code = initialCode;
		description = initialDescription;
		price = initialPrice;
		productionDate = initialProductionDate;
		ShelfLife = initialShelfLife;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the productionDate
	 */
	public Date getProductionDate() {
		return productionDate;
	}
	/**
	 * @param productionDate the productionDate to set
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	/**
	 * @return the shelfLife
	 */
	public String getShelfLife() {
		return ShelfLife;
	}
	/**
	 * @param shelfLife the shelfLife to set
	 */
	public void setShelfLife(String shelfLife) {
		ShelfLife = shelfLife;
	}
	/**
	 * @return String Description of the product
	 */
	public String toString(){
		return "code = " + this.getCode() + ", Description = " + this.getDescription() 
		+ ", ShelfLife = " + this.getShelfLife()
		+ ", Price = " + this.getPrice() + ", ProductionDate = " + this.getProductionDate();
	}
	
}
