import  java.io.*;
import  java.util.*;
import java.util.regex.Pattern;

public class CoffeeShopApplication{

	private static BufferedReader  stdIn =
		new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut =
		new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr =
		new  PrintWriter(System.err, true);

	private CoffeeShop coffeeShop;

	private static final int MIN_NUMBER = 0;
	
	private static final int MAX_NUMBER = 50;

	private static final double MIN_COST = 2.0;
	
	private static final double MAX_COST = 50.0;

	public static void main(String[]  args) throws IOException, InterruptedException  {

		CoffeeShopApplication application = new  CoffeeShopApplication();

		application.run();
	}

	private void run() throws IOException, InterruptedException  {

		coffeeShop = new  CoffeeShop();

		int  choice = getChoice();

		while (choice != 0)  {

			if (choice == 1)  {
				coffeeShop.addCoffee(readCoffee());
			} else if (choice == 2)  {
				stdOut.println(coffeeShop.toString());
			} else if (choice == 3)  {
				stdOut.println("Total Cost: " + coffeeShop.getTotalCost());
			}
			Thread.sleep(1);
			choice = getChoice();
		}
	}

	private int  getChoice() throws IOException  {

		/* PLACE YOUR CODE HERE */
		int input;
		do {
			try {
				stdErr.println();
				stdErr.print("[0] Quit\n" 
				+ "[1] Add Coffee\n"
				+ "[2] Didsplay coffee Shop\n" 
				+ "[3] Display Total Cost\n"
				+ "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && input <= 3) {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException nfe) {
				stdErr.println(nfe);
			}
		} while (true);

		return input; //you need to change it.
	}

	private Coffee readCoffee() throws IOException{
		/* PLACE YOUR CODE HERE */
		do{
		stdErr.print("Input string >");
		stdErr.flush();
		String str = stdIn.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(str, "_");
		int number = stringTokenizer.countTokens();
		if (number != 4) {
			System.out.println("Too much or too few values!");
			continue;
		} else {
			try {
				int id = Integer.parseInt(stringTokenizer.nextToken());
				String name = stringTokenizer.nextToken();
				String tast = stringTokenizer.nextToken();
				double cost = Double.parseDouble(stringTokenizer.nextToken());
				if (id <= 0 || cost <= 0) {
					System.out.println("The number can't be negative");
					continue;
				} else {
					Coffee coffee = new Coffee(id, name, tast, cost);
					return coffee;
				}
			} catch (NumberFormatException nfe) {
				stdErr.println(nfe);
				continue;
			}
		}
	}while(true);
}
	
	
}