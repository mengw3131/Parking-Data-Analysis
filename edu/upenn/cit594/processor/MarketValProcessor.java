package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

public class MarketValProcessor extends TotalValProcessor{

	@Override
	public double getTotalVal(Property p, double totalVal) {
		totalVal += p.getMarketValue();
		return totalVal;
	}

}
