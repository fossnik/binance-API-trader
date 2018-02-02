import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.*;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * Some Useful Documentation
 * 	https://github.com/binance-exchange/binance-java-api
 * 	https://python-binance.readthedocs.io/en/latest/index.html
 */

public class Main {

	public static void main(String[] args) throws IOException {

		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
		BinanceApiRestClient client = factory.newRestClient();

		Scanner scanner = new Scanner(System.in);

		float orderSize = -1;
		while (orderSize < 0) {
			System.out.println("\nWhat is the intended size of your order (in BTC)?");
			orderSize = scanner.nextFloat();

			System.out.println("Order size: " + orderSize + " BTC (" +
					orderSize * Float.parseFloat(client.get24HrPriceStatistics("BTCUSDT").getLastPrice()) +
					" USD)");

			System.out.println("\n> Enter 'y' to confirm <");

			if (!scanner.next().equals("y"))
				orderSize = -1;
		}

		System.out.println("Acquiring Ticker Symbols...");
		TreeSet<String> tickers = acquireTickers.getTickers();
		int count = 0;
		for (String s : tickers)
			if (++count % 8 == 0) {
				System.out.println(s);
			}
			else {
				System.out.print(s + " \t");
			}

		System.out.println("\n\t<  - - - - - - -  " + count +
				" Valid Symbols  - - - - - - -  >\n");

		// get and validate user's input symbol
		String choice = "";
		while (!tickers.contains(choice)) {
			if (choice.isEmpty())
				System.out.println("Enter your choice: ");
			else
				System.out.println(choice + " is NOT a Valid Ticker!");

			choice = scanner.next().toUpperCase();
			scanner.nextLine();
		}
		System.out.println("\n Validated Symbol: " + choice);

		// delineate order parameters
		float lastPrice = Float.parseFloat(client.get24HrPriceStatistics(choice + "BTC").getLastPrice());
	}
}
