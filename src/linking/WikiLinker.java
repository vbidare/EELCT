package linking;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import main.Data;
import main.Entity;
import main.Main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * Concrete class for linking using Wikipedia
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class WikiLinker implements LinkerInterface {
	private static String API_BASEURL = "https://en.wikipedia.org/w/api.php?action=query&list=search&inprop=url&format=json&srsearch=";
	private static String KB_BASEURL = "https://en.wikipedia.org/wiki/";

	@Override
	public void link(Data tweetData) {
		for (Entity e : tweetData.entitiesList) {
			e.knowledgeBaseLink = this.getWikiURL(e.entityName);
		}
		
	}

	/**
	 * Get the wiki URL of the first result
	 * @param entityName
	 * @return
	 */
	private String getWikiURL(String entityName) {
		String apiResponse = this.hitQuery(entityName);
		if (apiResponse.equals("")) {
			return "";
		}

		try {
			JsonElement jelement = new JsonParser().parse(apiResponse);
			JsonObject jobj = jelement.getAsJsonObject();
			JsonObject pages = jobj.getAsJsonObject("query");		
	        JsonArray results = pages.getAsJsonArray("search");

	        if (results.size() == 0) {
	        	return "";
	        }
	        else {
        		JsonObject targetItem = results.get(0).getAsJsonObject();
        		return getURLFromPageTitle(targetItem.get("title").toString().replace("\"", "").trim());
        	}
		}
        catch (Exception e) {
    		System.out.println("Could not fetch results from Wikipedia!!!");
    		//e.printStackTrace();
    	}
    	return "";
	}

	/**
	 * Hit the API to with the entity name
	 * @param entityName
	 */
	private String hitQuery(String entityName) {
		StringBuffer response = new StringBuffer();
		try {

			String query = URLEncoder.encode(entityName, "UTF-8");
			
			URL uobj = new URL(WikiLinker.API_BASEURL + query);
			
			URLConnection con = null;
			
			if (Main.useProxy) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.iiit.ac.in", 8080));
				con = uobj.openConnection(proxy);
			}
			else {
				con = uobj.openConnection();
				
			}
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("host", "en.wikipedia.org");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0");

			con.connect();
			BufferedReader isReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
		 
			while ((inputLine = isReader.readLine()) != null) {
				response.append(inputLine);
			}
			isReader.close();
		 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return response.toString();
	}

	/**
	 * Prepare the page url using title
	 * @param string
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	private String getURLFromPageTitle(String string) throws UnsupportedEncodingException {
		return WikiLinker.KB_BASEURL + URLEncoder.encode(string.replace(" ", "_"), "UTF-8");
	}

}
