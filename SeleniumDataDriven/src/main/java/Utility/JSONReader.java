package Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	
	static JSONParser parser = new JSONParser(); 
	
	public static String ReadJSONFile (String nameParameter, String filePath) throws InvalidFormatException, IOException, FileNotFoundException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		
		JSONObject jsonObject = (JSONObject) obj;
		String value = (String) jsonObject.get(nameParameter);
		
		return value;
	}

}
