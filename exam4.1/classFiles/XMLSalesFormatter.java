import java.util.List;

public class XMLSalesFormatter implements SalesFormatter {
	private static XMLSalesFormatter singletonInstance;

	public static XMLSalesFormatter getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new XMLSalesFormatter();
		}
		return singletonInstance;
	}

	public XMLSalesFormatter() {
	}

	public String formatSales(Sale sales) {
		String XML = new String("<Sales>\n");
		List<CurrentOrder> orderList = sales.getOrderList();
		for (CurrentOrder order : orderList) {
			XML = XML + "  <Order total = " + order.getOrderTotal() + ">\n";
			List<OrderItem> items = order.getOrderItemList();
			for (OrderItem item : items) {
				XML = XML + "\t<OrderItem> quantity = \"" + item.getQuantity()
						+ "\" price = \"" + item.getProduct().getPrice()
						+ "\">" + item.getProduct().getCode()
						+ "</OrderItem>\n";
			}
			XML = XML + "  </Order>\n";
		}
		XML = XML + "</Sales>";
		return XML;
	}
}
