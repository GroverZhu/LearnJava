import java.util.*;

public class PlainTextSalesFormatter implements SalesFormatter {
	private static PlainTextSalesFormatter singletonInstance;

	public static PlainTextSalesFormatter getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new PlainTextSalesFormatter();
		}
		return singletonInstance;
	}

	
	private PlainTextSalesFormatter() {
		// TODO Auto-generated constructor stub
	}

	public String formatSales(Sale sales) {
		String plainText = new String();
		String dashLine = new String("------------------------");
		List<CurrentOrder> orderList = sales.getOrderList();
		int number = 1;
		for (CurrentOrder order : orderList) {
			plainText = plainText + dashLine + "\n";
			plainText = plainText + "Order " + number + "\n\n";
			List<OrderItem> items = order.getOrderItemList();
			for (OrderItem item : items) {
				plainText = plainText + item.getQuantity() + " "
						+ item.getProduct().getCode() + " "
						+ item.getProduct().getPrice() + "\n\n";
			}
			plainText = plainText + "Total = " + order.getOrderTotal() + "\n";

			number++;
		}
		return plainText;
	}
}
