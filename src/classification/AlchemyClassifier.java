package classification;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import main.Data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AlchemyClassifier implements ClassifyInterface {
	
	public String formUrl(String text) throws UnsupportedEncodingException {
		String apiKey = "f4f90d86336a4b53d803df6288c62e83c7a22539";
		String encodedText = URLEncoder.encode(text, "UTF-8");
		String url = "http://access.alchemyapi.com/calls/text/TextGetRankedTaxonomy?apikey="+ apiKey +"&text="+ encodedText +"&outputMode=json";
		return url;
	}
	
	public void generateClass(Data data) {
		
		String line, res = "";
	    try {
	    	System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
			System.setProperty("http.proxyPort", "8080");
			InputStream is = new URL( formUrl(data.text) ).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        
	        while ((line = br.readLine()) != null) {
	        	res += line;
	        }
	        br.close();

			JsonElement jelement = new JsonParser().parse(res);
			JsonObject  response = jelement.getAsJsonObject();
			if ( response.get("status").toString().replace("\"", "").equalsIgnoreCase("OK")) {
				JsonArray   taxonomy = response.getAsJsonArray("taxonomy");
				if ( taxonomy.size() == 0 ) {
					data.category.add("Unable to classify..!!!");
				}
				for(int i = 0; i < taxonomy.size(); i++) {
					JsonObject obj = taxonomy.get(i).getAsJsonObject();
					data.category.add(obj.get("label").toString().replace("\"", ""));
//					System.out.println("Label: "+);
//					System.out.println("Score: "+obj.get("score").toString().replace("\"", "")+"\n");
				}
			} else {
				data.category.add(response.get("statusInfo").toString().replace("\"", ""));
			}

	    } catch (Exception e) {
	         e.printStackTrace();
	    }
	}
	
}
