package entityExtraction;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import main.Data;
import main.Entity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by veerendra on 11/6/15.
 * Adding stub for testing.
 */
public class AlchemyNER implements NERInterface {
    private String apiKey = "f4f90d86336a4b53d803df6288c62e83c7a22539";
    private String endpoint = "http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities";

    public String formUrl(String text) throws UnsupportedEncodingException {
        String encodedText = URLEncoder.encode(text, "UTF-8");
        String url = this.endpoint+"?apikey="+ this.apiKey +"&text="+ encodedText +"&outputMode=json";
        return url;
    }

    @Override
    public ArrayList<Entity> getEntities(Data dataObject){
        String line, res = "";

        try {
            System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
            System.setProperty("http.proxyPort", "8080");
            InputStream is = new URL( formUrl(dataObject.text) ).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            while ((line = br.readLine()) != null) {
                res += line;
            }
            br.close();
//            System.out.println(res);

            dataObject.hashtags = new ArrayList<String>();
            JSONObject obj = new JSONObject(res);
            if(obj.getString("status").equals("OK")){
                if(obj.getString("language").equals("english")){
                    dataObject.language = "english";
                    JSONArray entities = obj.getJSONArray("entities");
                    for (int i = 0; i < entities.length(); i++){
                        JSONObject entityJSON = entities.getJSONObject(i);
                        Entity entity = new Entity(entityJSON.getString("text"));
                        entity.type = entityJSON.getString("type");
                        entity.relevance = Double.parseDouble(entityJSON.getString("relevance"));
                        entity.count = Integer.parseInt(entityJSON.getString("count"));
                        dataObject.entitiesList.add(entity);
                        if (entity.type != null && entity.type.equalsIgnoreCase("Hashtag")) {
                        	dataObject.hashtags.add(entityJSON.getString("text"));
                        }
                    }
                }else{
                    throw new Exception("Please provide english text!");
                }
            }else{
                throw new Exception("Status not OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Entity extraction failed. Quitting...");
            System.exit(1);
        }
        return dataObject.entitiesList;
    }
}