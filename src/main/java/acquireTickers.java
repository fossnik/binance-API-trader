import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

public class acquireTickers {

	public static TreeSet<String> getTickers() throws IOException {

		TreeSet<String> tickers = new TreeSet<String>();

		String url = "https://api.binance.com/api/v1/ticker/allPrices";

		String json = Jsoup.connect(url).ignoreContentType(true).execute().body();

		String symbol;
		for (String s : json.split("},"))
			if (!(symbol = s.substring(s.indexOf(":\"")+2, s.indexOf("\","))
				.replace("BTC", "")
					.replace("USDT", "")
						.replace("ETH", "")
							.replace("BNB", "")).isEmpty())
				tickers.add(symbol);

		// add primary symbols back
		tickers.addAll(Arrays.asList("BTC", "ETH", "BNB"));

		// remove fake test symbol
		tickers.remove("123456");

		return tickers;
	}
}
