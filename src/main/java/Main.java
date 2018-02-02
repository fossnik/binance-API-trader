import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.*;

import java.io.IOException;
import java.util.InputMismatchException;
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

		BinanceApiClientFactory fty = BinanceApiClientFactory.newInstance();
		BinanceApiRestClient cnt = fty.newRestClient();

		Scanner scanner = new Scanner(System.in);

		double orderSize = -1;
		do {
			System.out.println("\nIntended size of your order (in BTC)?");
			try {
				orderSize = scanner.nextFloat();
			} catch (InputMismatchException e) {
				System.out.println("#! Cannot Parse this Input!");
				scanner.nextLine();
				continue;
			}

			String BTCxUSD =
						cnt.get24HrPriceStatistics("BTCUSDT").getLastPrice();
			BTCxUSD = BTCxUSD.substring(0, BTCxUSD.indexOf('.') + 3);

			System.out.println("Order size: " + orderSize +
					" BTC ($" + BTCxUSD + " USD)");

			System.out.println("\n> Enter 'y' to confirm <");

			if (!scanner.next().equals("y"))
				orderSize = -1;

		} while (orderSize < 0);

		System.out.println("\n$> Acquiring Ticker Symbols...");
		TreeSet<String> tickers = acquireTickers.getTickers();
		int count = 0;
		for (String s : tickers)
			if (++count % 8 == 0)
				System.out.println(s);
			else
				System.out.print(s + " \t");

		System.out.println("\n\t<  - - - - - - -  " + count +
				" Valid Symbols  - - - - - - -  >");

		// get and validate user's input symbol
		String coin = "";
		while (!tickers.contains(coin)) {
			if (coin.isEmpty())
				System.out.println("\n Enter your choice: ");
			else
				System.out.println(coin + " is NOT a Valid Ticker!");

			coin = scanner.next().toUpperCase();
			scanner.nextLine();
		}
		System.out.println("\n Validated Symbol: " + coin);

		// delineate order parameters
		double lastPrice = Double.parseDouble(
				cnt.get24HrPriceStatistics(coin + "BTC").getLastPrice());
	}
}
