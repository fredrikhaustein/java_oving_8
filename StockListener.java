package patterns.observable;

public interface StockListener {

	public void stockPriceChange(Stock stock, double oldValute, double newValue);
	
}
