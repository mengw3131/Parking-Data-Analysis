package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.*;

import edu.upenn.cit594.data.Property;

public class CSVPropertyReader extends Reader {

	protected String propertyFileName;

	public CSVPropertyReader(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	@Override
	public List<Property> read() {
		List<Property> values = new ArrayList<>();
		Scanner in = null;
		try {
			File file = new File(propertyFileName);
			logger.logString(propertyFileName);
			if (!file.exists() || !file.canRead()) {
				throw new Exception("File Not Found Or Cannot Read");
			}
			in = new Scanner(file);
			int marketValInd = 0;
			int livableAreaInd = 0;
			int zipCodeInd = 0;
			if (in.hasNextLine()) {
				String headLine = in.nextLine();
				String[] fields = headLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].equals("market_value")) {
						marketValInd = i;
					} else if (fields[i].equals("total_livable_area")) {
						livableAreaInd = i;
					} else if (fields[i].equals("zip_code")) {
						zipCodeInd = i;
					}
				}
			}
			while (in.hasNext()) {
				String valueInfo = in.nextLine();
				String[] value = valueInfo.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				String marketValue = value[marketValInd];
				String totalLivableArea = value[livableAreaInd];
				String zipCode = value[zipCodeInd];
				if (!isNumber(marketValue) || !isNumber(totalLivableArea) || zipCode.length() < 5
						|| !isNumber(zipCode.substring(0, 5))) {
					continue;
				}
				values.add(new Property(Double.parseDouble(marketValue), Double.parseDouble(totalLivableArea),
						zipCode.substring(0, 5)));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			in.close();
		}
		return values;
	}
}
