package edu.upenn.cit594.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

public class CommandLineUserInterface {
	Processor processor;
	Logger logger;
	
	public CommandLineUserInterface(Processor processor, Logger logger) {
		this.processor = processor;
		this.logger = logger;
	}

	public void start() {
		String actionString;
		try {
			while (true) {
				System.out.println("Please specify the action to be performed");
				Scanner in = new Scanner(System.in);
				if (in.hasNextLine()) {
					actionString = in.nextLine();
					logger.logString(actionString);
					int action = 0;
					try {
						action = Integer.parseInt(actionString);
					} catch (NumberFormatException e) {
						System.out.println("Invalid action");
						System.exit(0);
					}
					if (action < 0 || action > 6) {
						System.out.println("Invalid action");
						System.exit(0);
					} else if (action == 0){
						System.out.println("Exit");
						System.exit(0);
					}
					switch(action) {
						case 1:
							displayTotalpop();
							break;
						case 2:
							displayFinePerCapita();
							break;
						case 3:
							displayAveMarketValue();
							break;
						case 4:
							displayAveLivableArea();
							break;
						case 5:
							displayMarketValuePerCapita();
							break;
						case 6:
							displayFineofMinLivableAreaPerCapitaZipCode();
							break;
					}
				}
			}
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	private void displayTotalpop() {
		System.out.println("Here is the total population: " + processor.getTotalPop());
	}
	
	private void displayFinePerCapita() {
		Map<String, Double> map = this.processor.getFinesPerCapita();
		System.out.println("Here is the fine per capita:");
		DecimalFormat df = new DecimalFormat("0.0000");
		df.setRoundingMode(RoundingMode.DOWN);
		for (Entry<String, Double> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + df.format(entry.getValue()));
		}
	}
	
	private void displayAveMarketValue() {
		String zip = requestZipCode();
		logger.logString(zip);
		System.out.println("Here is the average market value: " + processor.getAverageMarketValue(zip));
		
	}
	
	private void displayAveLivableArea() {
		String zip = requestZipCode();
		logger.logString(zip);
		System.out.println("Here is the average livable area: " + processor.getAverageLivableArea(zip));
	}
	
	private void displayMarketValuePerCapita() {
		String zip = requestZipCode();
        logger.logString(zip);
		System.out.println("Here is the average market value per capita: " + processor.getMarketValPerCapita(zip));
	}
	
	private void displayFineofMinLivableAreaPerCapitaZipCode() {
		System.out.println("Here is the total fine per capita of ZIP Code with min livable area per capita : "+ processor.getFineofMinLivableAreaPerCapitaZipCode());
	}
	
	private String requestZipCode() {
		System.out.println("Please provide a valid zipcode");
		Scanner s = new Scanner(System.in);
		return s.next();
	}
}
