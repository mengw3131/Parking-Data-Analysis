package edu.upenn.cit594.data;

public class Property{
	double marketValue;
	double totalLivableArea;
	String zipCode;
	
	public Property(double mv, double la, String zc) {
		this.marketValue = mv;
		this.totalLivableArea = la;
		this.zipCode = zc;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getTotalLivableArea() {
		return totalLivableArea;
	}

	public String getZipCode() {
		return zipCode;
	}

	
}
