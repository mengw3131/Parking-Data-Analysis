package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.CSVParkingReader;
import edu.upenn.cit594.datamanagement.CSVPropertyReader;
import edu.upenn.cit594.datamanagement.JSONReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.TXTReader;

public class ReaderFactory {

	public Reader getReader(String fileFormat, String fileName) {
		if (fileFormat.equals("csv")) {
			return new CSVParkingReader(fileName);
		} else {
			return new JSONReader(fileName);
		}
	}
	
	public Reader getPropertyReader(String fileName) {
		return new CSVPropertyReader(fileName);
	}
	
	public Reader getPopReader(String fileName) {
		return new TXTReader(fileName);
	}
}
