package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
public class Logger {

	private static PrintWriter out;
	private static String logFileName;
	private static Logger instance = null;
	
	private Logger(String fileName) {
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		} catch (Exception e){
			throw new IllegalStateException(e);
		}
	}
	
	public static void setFileName(String name) {
		logFileName = name;
	}

	public static Logger getLogger() {
		if (instance == null) {
			instance = new Logger(logFileName);
		}
		return instance;
	}

	public void log(String msg) {
		out.println(msg);
		out.flush();		
	}

	public void logString(String[] args) {
		String line = "";
		line += System.currentTimeMillis();
		for (String each : args) {
			line += (" " + each);
		}
		log(line);
	}

	public void logString(String string) {
		String line = "";
		line += System.currentTimeMillis();
		line += (" " + string);
		log(line);
	}
	
	public void logString(int number) {
		String line = "";
		line += System.currentTimeMillis();
		line += (" " + Integer.toString(number));
		log(line);
	}
}