import java.util.ArrayList;
import java.util.List;


/**
 * @author Grover Zhu
 *
 */
public class Sale {
	private List<CurrentOrder> orderList;
	public Sale(){
		orderList = new ArrayList<CurrentOrder>();
	}
	/**
	 * @return the orderList
	 */
	public List<CurrentOrder> getOrderList() {
		return orderList;
	}
	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List<CurrentOrder> orderList) {
		this.orderList = orderList;
	}
	
}
