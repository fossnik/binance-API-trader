import java.io.IOException;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * Some Useful Documentation
 * 	https://github.com/binance-exchange/binance-java-api
 * 	https://python-binance.readthedocs.io/en/latest/index.html
 */

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("Acquiring Ticker Symbols...");
		TreeSet<String> tickers = acquireTickers.getTickers();
		int count = 0;
		for (String s : tickers)
			if (++count % 10 == 0)
				System.out.println(s);
			else
				System.out.print(s + " \t");
		System.out.println("\n< Valid Symbols: " + count + " >\n");

		// get coin symbol
		Scanner scanner = new Scanner(System.in);
		String choice = "";

		// validate user's input symbol ticker
		while (!tickers.contains(choice)) {
			if (choice.isEmpty())
				System.out.println("Enter your choice: ");
			else
				System.out.println(choice + " Is NOT a Valid Ticker!");

			choice = scanner.next().toUpperCase();
			scanner.nextLine();
		}

		System.out.println("\n Validated Symbol: " + choice);
	}
}
