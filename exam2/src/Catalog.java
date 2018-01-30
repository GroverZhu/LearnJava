import java.util.List;
import java.util.ArrayList;
/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class Catalog {
	private List<Product> productList;
	
	public Catalog(){
		productList = new ArrayList<Product>();
	}

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public Product findProductByCode(String code){
		for(Product product:productList){
			if (product.getCode().equals(code)){
				return product;
			}
		}
		return null;
	}
	
}
