import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class CurrentOrder {
	private BigDecimal orderTotal;
	private List<OrderItem> orderItemList; 
	/**
	 * 
	 * @param initialList
	 * @param initialBigDecimal
	 */
//	public CurrentOrder(List<OrderItem> initialList, BigDecimal initialBigDecimal){
//		this.setOrderItemList(initialList);
//		this.setOrderTotal(initialBigDecimal);
//	}
	public CurrentOrder(CurrentOrder _currentOrder){
		this.orderItemList = new ArrayList<OrderItem>(_currentOrder.getOrderItemList());
		this.orderTotal = _currentOrder.getOrderTotal();
	}
	public CurrentOrder(){
		orderItemList = new ArrayList<OrderItem>();
		orderTotal = new BigDecimal("0");
	}

	/**
	 * @return the orderTotal
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the orderItemList
	 */
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String toString(){
		String str = new String();
		for (OrderItem item : orderItemList){
			str = str + item.toString();
			str = str + "\n";
		}
		String s = "orderTotal = ";
		s = s + orderTotal;
		str = str + s;
		return str;
	}
	
}
