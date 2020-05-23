package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

public class LivableAreaProcessor extends TotalValProcessor{

	@Override
	public double getTotalVal(Property p, double totalVal) {
		totalVal += p.getTotalLivableArea();
		return totalVal;
	}

	
}
