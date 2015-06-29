package entityExtraction;
import java.util.*;

// Not in use

public class HashtagExtractor {
	public static ArrayList<String> getHashtags(String tweetText) {
		ArrayList<String> list = new ArrayList<String>();
		
		String[] tokens = tweetText.split(" ");
		for(String token: tokens) {
			if (token.charAt(0) == '#') {
				list.add(token);
			}
		}
		
		return list;
	}
}
