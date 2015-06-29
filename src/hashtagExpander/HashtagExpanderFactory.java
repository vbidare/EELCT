package hashtagExpander;

public class HashtagExpanderFactory {
	public HashtagExpanderInterface getExpander() {
        return new HashtagExpander();
	}
}
