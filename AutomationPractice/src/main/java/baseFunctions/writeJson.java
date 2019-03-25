package baseFunctions;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class writeJson {

	public static void main(String[] args) throws IOException
	{
		JSONObject jObject = new JSONObject ();
		jObject.put("Title", "Mrs");
		jObject.put("FirstName", "Luda");
		
		JSONArray jArray = new JSONArray();
		jArray.add("First");
		jArray.add("Second");
		jArray.add("Third");
		
		jObject.put("TestId", jArray);
		
		
		FileWriter file = new FileWriter("myjson.json");
		file.write(jObject.toString());
		file.flush();
		
		System.out.println(jObject);
		
		
	}

}
