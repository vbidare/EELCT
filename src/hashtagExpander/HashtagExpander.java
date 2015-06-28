package hashtagExpander;

public class HashtagExpander implements HashtagExpanderInterface {
	public String expand(String hashtag) {
		return hashtag
				.substring(1)
				.replaceAll("([A-Z][a-z]+)", " $1 ")
				.replaceAll("\\s+", " ")
				.trim();
	}
}
