package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.data.ParkingViolation;

public class JSONReader extends Reader {

	protected String parkingFileName;

	public JSONReader(String fileName) {
		this.parkingFileName = fileName;
	}

	@Override
	public List<ParkingViolation> read() {
		List<ParkingViolation> violations = new LinkedList<>();
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(parkingFileName));
			logger.logString(parkingFileName);
			JSONArray joArray = new JSONArray();
			joArray.add(obj);
			JSONArray array = (JSONArray) joArray.get(0);
			for (int i = 0; i < array.size(); i++) {
				JSONObject jo = (JSONObject) array.get(i);
				String state = (String) jo.get("state");
				long fineNumber = (Long) jo.get("fine");
				int fine = (int) fineNumber;
				String zipCode = (String) jo.get("zip_code");
				if (zipCode.length() < 5 || !isNumber(zipCode.substring(0, 5))) {
					continue;
				}
				violations.add(new ParkingViolation(fine, state, zipCode.substring(0, 5)));
			}
		} catch (Exception e) {
			throw new IllegalStateException("File Not Found Or Cannot Read");
		}
		return violations;
	}
}
