import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Bright Fresh Milk System.
 *
 */
public class BrightFreshMilkGUI extends JPanel {

	/* Standar error stream */
	static private PrintWriter stdErr = new PrintWriter(System.err, true);

	/* Window width in pixels */
	static private int WIDTH = 620;

	/* Window height in pixels */
	static private int HEIGHT = 530;

	/* Size of the catalog list cell in pixels */
	static private int CATALOG_CELL_SIZE = 50;

	/* Visible rows in catalog list */
	static private int CATALOG_LIST_ROWS = 14;

	/* Size of the order list cell in pixels */
	static private int ORDER_CELL_SIZE = 100;

	/* Visible rows in order list */
	static private int ORDER_LIST_ROWS = 6;

	/* Size quantity text field */
	static private int QUANTITY__TEXTFIELD_SIZE = 5;

	/* Size total text field */
	static private int TOTAL__TEXTFIELD_SIZE = 8;

	/* Rows in status text area rows */
	static private int STATUS_ROWS = 10;

	/* Rows in status text area cols */
	static private int STATUS_COLS = 40;

	private JList catalogList;
	private JList orderList;
	private JButton addModifyButton;
	private JButton removeButton;
	private JButton registerSaleButton;
	private JButton displaySalesButton;
	private JButton saveSalesButton;
	private JPanel productPanel;
	private JLabel quantityLabel;
	private JLabel totalLabel;
	private JTextField quantityTextField;
	private JTextField totalTextField;
	private JTextArea statusTextArea;
	private JRadioButton plainRadioButton;
	private JRadioButton HTMLRadioButton;
	private JRadioButton XMLRadioButton;

	private JFileChooser fileChooser;

	private Catalog catalog;
	private CurrentOrder currentOrder;
	private Sale sales;
	private SalesFormatter salesFormatter;
	private NumberFormat dollarFormatter;

	/**
	 * Loads a product catalog and starts the application.
	 *
	 * @param args
	 *            String arguments. Not used.
	 * @throws IOException
	 *             if there are errors in the loading the catalog.
	 */
	public static void main(String[] args) throws IOException {

		String filename = "";

		if (args.length != 1) {
			filename = "catalog.dat";
		} else {
			filename = args[0];
		}
		try {
			Catalog catalog = (new FileCatalogLoader()).loadCatalog(filename);

			JFrame frame = new JFrame("Bright Fresh Milk");

			frame.setContentPane(new BrightFreshMilkGUI(catalog));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			frame.setResizable(true);
			frame.setVisible(true);

		} catch (FileNotFoundException fnfe) {
			stdErr.println("The file does not exist");

			System.exit(1);

		} catch (DataFormatException dfe) {
			stdErr.println("The file contains malformed data: "
					+ dfe.getMessage());

			System.exit(1);
		} catch (ParseException e) {
			stdErr.println(e.getMessage());

			System.exit(1);
		}
	}

	/**
	 * Instantiates the components and arranges them in a window.
	 *
	 * @param initialCatalog
	 *            a product catalog.
	 */
	public BrightFreshMilkGUI(Catalog initialCatalog) {

		// create the components
		catalogList = new JList();
		orderList = new JList();
		catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catalogList.setVisibleRowCount(CATALOG_LIST_ROWS);
		catalogList.setFixedCellWidth(CATALOG_CELL_SIZE);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.setVisibleRowCount(ORDER_LIST_ROWS);
		orderList.setFixedCellWidth(ORDER_CELL_SIZE);
		addModifyButton = new JButton("Add|Modify Order Item");
		removeButton = new JButton("Remove Order Item");
		registerSaleButton = new JButton("Register Sale of Current Order");
		displaySalesButton = new JButton("Display Sales");
		saveSalesButton = new JButton("Save Sales");
		quantityLabel = new JLabel("Quantity:");
		totalLabel = new JLabel("Total:");
		quantityTextField = new JTextField("", QUANTITY__TEXTFIELD_SIZE);
		totalTextField = new JTextField("$0.00", TOTAL__TEXTFIELD_SIZE);
		totalTextField.setEditable(false);
		statusTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
		statusTextArea.setEditable(false);
		plainRadioButton = new JRadioButton("Plain", true);
		HTMLRadioButton = new JRadioButton("HTML");
		XMLRadioButton = new JRadioButton("XML");

		ButtonGroup group = new ButtonGroup();

		group.add(plainRadioButton);
		group.add(HTMLRadioButton);
		group.add(XMLRadioButton);

		// Product Information panel
		productPanel = new JPanel();
		productPanel.setBorder(BorderFactory
				.createTitledBorder(
						"Product Information"));

		
		
		
		// Catalog panel
		JPanel catalogPanel = new JPanel();

		catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
		catalogPanel.add(new JScrollPane(catalogList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		// "Add|Modify Product" panel
		JPanel centralPanel = new JPanel(new BorderLayout());
		JPanel addModifyPanel = new JPanel(new GridLayout(2, 1));
		JPanel quantityPanel = new JPanel();

		quantityPanel.add(quantityLabel);
		quantityPanel.add(quantityTextField);
		addModifyPanel.add(quantityPanel);
		addModifyPanel.add(addModifyButton);
		centralPanel.add(productPanel, BorderLayout.CENTER);
		centralPanel.add(addModifyPanel, BorderLayout.SOUTH);

		// Order panel
		JPanel orderPanel = new JPanel(new BorderLayout());

		orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));

		JPanel totalPanel = new JPanel();

		totalPanel.add(totalLabel);
		totalPanel.add(totalTextField);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

		buttonsPanel.add(removeButton);
		buttonsPanel.add(registerSaleButton);
		orderPanel.add(totalPanel, BorderLayout.NORTH);
		orderPanel.add(new JScrollPane(orderList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		orderPanel.add(buttonsPanel, BorderLayout.SOUTH);

		// Status panel
		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.setBorder(BorderFactory.createTitledBorder("Status"));

		JPanel salesButtonsPanel = new JPanel(new GridLayout(1, 5));

		salesButtonsPanel.add(plainRadioButton);
		salesButtonsPanel.add(HTMLRadioButton);
		salesButtonsPanel.add(XMLRadioButton);
		salesButtonsPanel.add(displaySalesButton);
		salesButtonsPanel.add(saveSalesButton);
		bottomPanel.add(new JScrollPane(statusTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		bottomPanel.add(salesButtonsPanel, BorderLayout.SOUTH);

		// arrange panels in window
		setLayout(new BorderLayout());
		add(catalogPanel, BorderLayout.WEST);
		add(centralPanel, BorderLayout.CENTER);
		add(orderPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		// start listening for list and buttons events
		catalogList.addListSelectionListener(new DisplayProductListener());
		addModifyButton.addActionListener(new AddModifyListener());
		removeButton.addActionListener(new RemoveListener());
		registerSaleButton.addActionListener(new RegisterSaleListener());
		displaySalesButton.addActionListener(new DisplaySalesListener());
		saveSalesButton.addActionListener(new SaveSalesListener());
		plainRadioButton.addActionListener(new PlainListener());
		HTMLRadioButton.addActionListener(new HTMLListener());
		XMLRadioButton.addActionListener(new XMLListener());

		// populate the product catalog
		catalog = initialCatalog;
		String[] codes = new String[catalog.getProductList().size()];
		int i = 0;
		for (Product product : catalog.getProductList()) {
			codes[i++] = product.getCode();
		}
		catalogList.setListData(codes);

		currentOrder = new CurrentOrder();
		currentOrder.setOrderItemList(new ArrayList<OrderItem>());
		currentOrder.setOrderTotal(new BigDecimal("0"));

		sales = new Sale();
		sales.setOrderList(new ArrayList<CurrentOrder>());

		salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
		fileChooser = new JFileChooser();
		dollarFormatter = NumberFormat.getCurrencyInstance();
	}

	/*
	 * Obtains a {@link JPanel} object with the information of a product.
	 * 
	 * @param dataFields an {@link ArrayList} of {@link DataField} with the
	 * product information.
	 * 
	 * @return a {@link JPanel} with the product information.
	 */
	private JPanel getDataFieldsPanel(ArrayList<DataField> dataFields) {

		JPanel panel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridLayout(dataFields.size(), 1));
		JPanel leftPanel = new JPanel(new GridLayout(dataFields.size(), 1));

		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(centerPanel, BorderLayout.CENTER);

		for (DataField field : dataFields) {

			JTextField valueTextField = new JTextField(field.getValue(), 17);

			valueTextField.setEditable(false);

			leftPanel.add(new JLabel(field.getName() + ":"));
			centerPanel.add(valueTextField);

		}

		return panel;
	}

	/**
	 * This inner class handles list-selection events.
	 */
	class DisplayProductListener implements ListSelectionListener {

		/**
		 * Displays the information of the selected product.
		 *
		 * @param event
		 *            the event object.
		 */
		public void valueChanged(ListSelectionEvent event) {

			if (!catalogList.getValueIsAdjusting()) {

				String code = (String) catalogList.getSelectedValue();
				Product product = catalog.findProductByCode(code);

				productPanel.removeAll();
				productPanel.setVisible(false); // Use this
				productPanel.add( // to update
						getDataFieldsPanel(getDataFields(product))); // the
																		// panel
				productPanel.setVisible(true); // correctly

				statusTextArea.setText("Product " + code
						+ " has been displayed");
			}
		}

		private ArrayList<DataField> getDataFields(Product product) {
			ArrayList<DataField> result = new ArrayList<DataField>();
			result.add(new DataField("code", product.getCode()));
			result.add(new DataField("description", product.getDescription()));
			result.add(new DataField("price", dollarFormatter.format(product
					.getPrice())));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result.add(new DataField("productionDate", sdf.format(product
					.getProductionDate())));
			result.add(new DataField("shelfLife", product.getShelfLife()));

			if (product instanceof MilkDrink) {
				MilkDrink milkDrink = (MilkDrink) product;
				result.add(new DataField("flavor", milkDrink.getFlavor()));
				result.add(new DataField("sugar", milkDrink.getSugar()));
			} else if (product instanceof Jelly) {
				Jelly jelly = (Jelly) product;
				result.add(new DataField("type", jelly.getType()));
				result.add(new DataField("diluteConcentration", jelly
						.getDiluteConcentration()));
			} else if (product instanceof PureMilk) {
				PureMilk pureMilk = (PureMilk) product;
				result.add(new DataField("countryOfOrigin", pureMilk
						.getCountryOfOrigin()));
				result.add(new DataField("butterfat", pureMilk.getButterfat()));
				result.add(new DataField("protein", pureMilk.getProtein()));
			} else if (product instanceof Yogurt) {
				Yogurt yogurt = (Yogurt) product;
				result.add(new DataField("flavor", yogurt.getFlavor()));
			}
			return result;
		}
	}

	/**
	 * This inner class processes <code>addModifyButton</code> events.
	 */
	class AddModifyListener implements ActionListener {

		/**
		 * Adds an order item to the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			int quantity;
			String code = (String) catalogList.getSelectedValue();

			try {
				quantity = Integer.parseInt(quantityTextField.getText());
			} catch (NumberFormatException nfe) {
				statusTextArea.setText("Please enter an integer.");

				return;
			}
			if (quantity < 1) {
				statusTextArea.setText("Please enter a positive integer.");
			} else if (code == null) {
				statusTextArea
						.setText("Please select a product code from the catalog list.");
			} else {

				Product product = catalog.findProductByCode(code);

				OrderItem item = null;
				for (OrderItem orderItem : currentOrder.getOrderItemList()) {
					if (orderItem.getProduct().equals(product)) {
						item = orderItem;
					}
				}

				if (item == null) {
					item = new OrderItem(product, quantity);
					// add to currentOrder
					currentOrder.getOrderItemList().add(item);
					orderList.setListData(currentOrder.getOrderItemList()
							.toArray(new OrderItem[0]));
					statusTextArea.setText("The product has been added.");
				} else {
					item.setQuantity(quantity);
					orderList.setListData(currentOrder.getOrderItemList()
							.toArray(new OrderItem[0]));
					statusTextArea.setText("The product has been modified.");
				}

				// Calculate the orderTotal
				BigDecimal total = new BigDecimal("0");
				for (OrderItem orderItem : currentOrder.getOrderItemList()) {
					total = total.add(orderItem.getProduct().getPrice()
							.multiply(new BigDecimal(orderItem.getQuantity())));
				}
				currentOrder.setOrderTotal(total);
				totalTextField.setText(dollarFormatter.format(total));
				quantityTextField.setText("");
			}
		}
	}

	/**
	 * This inner class processes <code>removeButton</code> events.
	 */
	class RemoveListener implements ActionListener {

		/**
		 * Removes an order item from the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (currentOrder.getOrderItemList().size() == 0) {
				statusTextArea.setText("The order is empty.");
			} else {

				OrderItem item = (OrderItem) orderList.getSelectedValue();

				if (item == null) {
					statusTextArea
							.setText("Please select an item from the order list.");
				} else {
					currentOrder.getOrderItemList().remove(item);
					orderList.setListData(currentOrder.getOrderItemList()
							.toArray(new OrderItem[0]));

					// Calculate the orderTotal
					BigDecimal total = new BigDecimal("0");
					for (OrderItem orderItem : currentOrder.getOrderItemList()) {
						total = total
								.add(orderItem
										.getProduct()
										.getPrice()
										.multiply(
												new BigDecimal(orderItem
														.getQuantity())));
					}
					currentOrder.setOrderTotal(total);
					totalTextField.setText(dollarFormatter.format(total));
					statusTextArea.setText("The product has been removed.");

				}
			}
		}
	}

	/**
	 * This inner class processes <code>registerSaleButton</code> button events.
	 */
	class RegisterSaleListener implements ActionListener {

		/**
		 * Registers the sale of the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (currentOrder.getOrderItemList().size() == 0) {
				statusTextArea.setText("The order is empty.");
			} else {
				sales.getOrderList().add(currentOrder);
				currentOrder = new CurrentOrder();
				currentOrder.setOrderItemList(new ArrayList<OrderItem>());
				currentOrder.setOrderTotal(new BigDecimal("0"));

				orderList.setListData(currentOrder.getOrderItemList().toArray(
						new OrderItem[0]));
				totalTextField.setText(dollarFormatter.format(0));
				statusTextArea.setText("The sale has been registered.");
			}
		}
	}

	/**
	 * This inner class processes <code>displaySalesButton</code>events.
	 */
	class DisplaySalesListener implements ActionListener {

		/**
		 * Displays the sales information.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {
			/* PLACE YOUR CODE HERE */
			// to do
			
			statusTextArea.setText(event.getActionCommand());
			/***check.equals(plainRadioButton)*/
			if (plainRadioButton.isSelected()) {
				salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
				statusTextArea.setText(salesFormatter.formatSales(sales));
			} else if (HTMLRadioButton.isSelected()){
				salesFormatter = HTMLSalesFormatter.getSingletonInstance();
				statusTextArea.setText(salesFormatter.formatSales(sales));
			} else if (XMLRadioButton.isSelected()){
				salesFormatter = XMLSalesFormatter.getSingletonInstance();
				statusTextArea.setText(salesFormatter.formatSales(sales));
			}

		}
	}

	/*
	 * This inner class processes <code>saveSalesButton</code> events.
	 */
	class SaveSalesListener implements ActionListener {

		/**
		 * Saves the sales informations in a file.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			/* PLACE YOUR CODE HERE */
			// to do
			if (sales.getOrderList().size() == 0) {
				statusTextArea.setText("No orders have been sold.");
			} else {
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					try {
						BufferedWriter fileWrite = new BufferedWriter(new FileWriter(path));
						fileWrite.write(statusTextArea.getText());
						fileWrite.close();
						statusTextArea.setText("The sales information has been saved");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}

		}
	}

	/*
	 * This inner class processes <code>plainRadioButton</code> events.
	 */
	class PlainListener implements ActionListener {

		/**
		 * Sets the sales formatter to plain text.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>HTMLRadioButton</code> events.
	 */
	class HTMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to HTML.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = HTMLSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>XMLRadioButton</code> events.
	 */
	class XMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to XML.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = XMLSalesFormatter.getSingletonInstance();
		}
	}

}