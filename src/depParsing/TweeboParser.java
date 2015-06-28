package depParsing;
import java.io.*;
import java.util.*;

public class TweeboParser implements DPInterface {
	
	private String tweetText;
	ArrayList<Map.Entry<String, Integer>> tree;
	ArrayList<Map.Entry<String, String>> pos;

	private String execParser() {
		try {
			Process p = Runtime.getRuntime().exec(new String[] {"./TweeboParse.sh", tweetText});
			
			int exitStatus = p.waitFor();
			
			if (exitStatus != 0) {
				return null;
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String filename = in.readLine();
			return filename;
		} catch (Exception e) {
			return null;
		}
	}
	
	private void parseOutFile(String filename) {
		File outFile = new File(filename);
		if (!outFile.exists()) {
			return;
		}
		BufferedReader fileData = null;
		try {
			fileData = new BufferedReader(new FileReader(outFile));
		} catch (Exception e) {
			System.err.println(e);
			return;
		}
		
		tree = new ArrayList<Map.Entry<String, Integer>>();
		pos = new ArrayList<Map.Entry<String, String>>();
		String line;
		String[] columns;
		
		try {
			while((line = fileData.readLine()) != null && !line.equals("")) {
				columns = line.split("\t");
				tree.add(new AbstractMap.SimpleEntry<String, Integer>(columns[1], new Integer(columns[6])));
				pos.add(new AbstractMap.SimpleEntry<String, String>(columns[1], columns[4]));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			fileData.close();
			outFile.delete();
		} catch (Exception e) {
			System.err.println(e);
		}
		return;
	}
	
	public ArrayList<Map.Entry<String, Integer>> getDepTree() {
		return tree;
	}

	public ArrayList<Map.Entry<String, String>> getPOSTags() {
		return pos;
	}

	public void parseTweetText(String tweet) {
		tweetText = tweet;
		
		String filename = execParser();
		parseOutFile(filename);
		
		return;
	}
}
