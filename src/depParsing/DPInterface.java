package depParsing;
import java.util.*;

public interface DPInterface {
	void parseTweetText(String tweet);
	ArrayList<Map.Entry<String, Integer>> getDepTree();
	ArrayList<Map.Entry<String, String>> getPOSTags();
}
