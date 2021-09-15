package patterns.observable;

import java.util.ArrayList;
import java.util.Collection;

public class Stock {

	String ticker;
	double exchangeRate;
	Collection<StockListener> stockListeners = new ArrayList<StockListener>();
	
	Stock(String ticker, double exchangeRate){
		this.ticker = ticker;
		setPrice(exchangeRate);
	}
	
	public void addStockListener(StockListener stocklistener) {
		stockListeners.add(stocklistener);
	}
	
	public void removeStockListener(StockListener stockListener) {
		stockListeners.remove(stockListener);
	}
	
	public void setPrice(double newExchangeRate) {
		if (newExchangeRate <= 0) {
			throw new IllegalArgumentException("Kan ikke være null eller mindre enn 0");
		}
		else {
		for (StockListener stockListener : stockListeners) {
			stockListener.stockPriceChange(this, getPrice(), newExchangeRate);
		}
		this.exchangeRate = newExchangeRate;
		}
	}
	
	public String getTicker() {
		return ticker;
	}
	public double getPrice() {
		return exchangeRate;
	}
}
