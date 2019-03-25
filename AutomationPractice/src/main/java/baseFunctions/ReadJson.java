package baseFunctions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
		JSONParser  jparser = new JSONParser();
		Object obj = jparser.parse(new FileReader("myjson.json"));
		JSONObject jobj = (JSONObject) obj;
		JSONObject jobjPageName = (JSONObject) jobj.get("CreateAccount");
		JSONObject jobjTestSet = (JSONObject) jobjPageName.get("TestSet1");
		String name = (String) jobjTestSet.get("Title");
		System.out.println(name);
		
		/*Object [][] arr = new String[jarr.size()][2]; 
	
		for (int i = 0; i < jarr.size(); i++) 
		{ 
			JSONObject obj1 = (JSONObject)jarr.get(i);
			System.out.println(arr[i][0] = String.valueOf(obj1.get("FirstName")));
			System.out.println(arr[i][1] = String.valueOf(obj1.get("Title")));		
		}*/
		
		
		
		
		
		

	}

}
