package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.logging.Logger;

public abstract class Reader {
	Logger logger = Logger.getLogger();

	public abstract Object read();
	
	public boolean isNumber(String s) {
		boolean numeric = true;
		if (s.length() < 1) {
			return false;
		}
		try {
			Double d = Double.valueOf(s);
		} catch (Exception e) {
			numeric = false;
		}
	    return numeric; 
	}
}
