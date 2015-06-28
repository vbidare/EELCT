package depParsing;
import java.io.*;
import java.util.*;

public class TweeboParser implements DPInterface {
	
	private String tweetText;

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
	
	private ArrayList<Map.Entry<String, Integer>> parseOutFile(String filename) {
		File outFile = new File(filename);
		if (!outFile.exists()) {
			return null;
		}
		BufferedReader fileData = null;
		try {
			fileData = new BufferedReader(new FileReader(outFile));
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
		
		ArrayList<Map.Entry<String, Integer>> tree = new ArrayList<Map.Entry<String, Integer>>();
		String line;
		String[] columns;
		
		try {
			while((line = fileData.readLine()) != null && !line.equals("")) {
				columns = line.split("\t");
				tree.add(new AbstractMap.SimpleEntry<String, Integer>(columns[1], new Integer(columns[6])));
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
		return tree;
	}
	
	public ArrayList<Map.Entry<String, Integer>> getDepTree(String tweet) {
		
		tweetText = tweet;
		
		String filename = execParser();
		
		ArrayList<Map.Entry<String, Integer>> tree = parseOutFile(filename);
		
		return tree;
	}
}
