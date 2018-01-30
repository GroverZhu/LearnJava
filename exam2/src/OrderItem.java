/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class OrderItem {
    private int quantity;
    private Product product;
    /**
     * 
     * @param initialProduct
     * @param initialQuantity
     */
    public OrderItem(Product initialProduct, int initialQuantity){
    	quantity = initialQuantity;
    	product = initialProduct;
    }
    
    /**
     * @return
     */
    public int getQuantity(){
    	return this.quantity;
    }
    /**
     * 
     * @param _quantity
     */
    public void setQuantity(int _quantity){
    	quantity = _quantity;
    }
    /**
     * 
     * @return
     */
    public Product getProduct(){
    	return product;
    }
    /**
     * 
     * @param _product
     */
    public void setProduct(Product _product){
    	product = _product;
    }
    /**
     * @return the attributes of the class
     */
    public String toString(){
    	return "[ quantity = " + quantity + ", code = " + product.getCode() + 
    			", description = " +product.getDescription() + "price = " + 
    			product.getPrice() + "]";
    }
}
