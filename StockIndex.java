package patterns.observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StockIndex implements StockListener{

	String name;
	double index;
	Collection<Stock> stockIndex = new ArrayList<>();
	private Map<StockListener,Double> stockListenerMin = new HashMap<StockListener,Double>();
	private Map<StockListener,Double> stockListenerMax = new HashMap<StockListener,Double>();
	
	StockIndex(String name,Stock...stocks){
		this.name = name;
		for (Stock stock : stocks) {
			addStock(stock);
		}
	}

	void addStock(Stock stock) {
		if (!this.stockIndex.contains(stock)) {
			stockIndex.add(stock);
		}
	}
	
	void removeStock(Stock stock) {
		stockIndex.remove(stock);
	}
	
	double getIndex() {
		index = 0.0;
		for (Stock stock : stockIndex) {
			index += stock.getPrice();
		}
		return this.index;
	}
	
	void addStockListener(StockListener stockListener, double min, double max) {
		stockListenerMin.put(stockListener, min);
		stockListenerMax.put(stockListener, max);
	}
	
	@Override
	public void stockPriceChange(Stock stock, double oldValue, double newValue) {
		if (oldValue == newValue) {
			throw new IllegalArgumentException("Lik pris");
		}
		else {
			stock.setPrice(newValue);
		}
//		Object[] keysMin = stockListenerMin.keySet().toArray();
//		Object[] keysMax = stockListenerMax.keySet().toArray();
//		for (int i = 0;i<stockListenerMin.size();i++){
//			if (stockListenerMin.get(keysMin[i]) > newValue) {
//				StockListener stocklistener = (StockListener) keysMin[i];
//				stocklistener.stockPriceChange(stock, oldValue, newValue);
//			}
//			if(stockListenerMax.get(keysMax[i]) > newValue) {
//				StockListener stocklistener = (StockListener) keysMax[i];
//				stocklistener.stockPriceChange(stock, oldValue, newValue);
//			}
//			
//		}
	}
	
	public static void main(String[] args) {
		Stock facebook = new Stock("FB",60);
		Stock apple = new Stock("Apple",80);
		StockIndex stockindex = new StockIndex("Hei",facebook,apple);
		System.out.println(stockindex.getIndex());
		stockindex.removeStock(apple);
		System.out.println(stockindex.getIndex());
	}
	
}
