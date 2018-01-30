import java.util.List;

public class HTMLSalesFormatter implements SalesFormatter {
	private static HTMLSalesFormatter singletonInstance;
    /**
     * 
     * @return html sales formatter
     */
	public static HTMLSalesFormatter getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new HTMLSalesFormatter();
		}
		return singletonInstance;
	}
	HTMLSalesFormatter() {
	}
	
    
	public String formatSales(Sale sales) {
		String HTML = new String(
				"<html>\n  <body>\n\t<center><h2>Orders</h2></center>\n");
		List<CurrentOrder> orderList = sales.getOrderList();
		for (CurrentOrder order : orderList) {
			HTML = HTML + "\t<hr>\n\t<h4>Total = " + order.getOrderTotal()
					+ "</h4>\n";
			List<OrderItem> items = order.getOrderItemList();
			for (OrderItem item : items) {
				HTML = HTML + "\t  <p>\n\t\t<b>code:</b>"
						+ item.getProduct().getCode() + "<br>\n";
				HTML = HTML + "\t\t<b>quantity:</b> " + item.getQuantity()
						+ "<br>\n";
				HTML = HTML + "\t\t<b>price:</b> " + item.getProduct().getPrice()
						+ "\n";
				HTML = HTML + "\t  </p>\n";
			}
		}
		HTML = HTML + "  </body>\n</html>";
		return HTML;
	}
}
