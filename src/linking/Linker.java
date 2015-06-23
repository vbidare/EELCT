package linking;
import main.Data;

/**
 * A class to delegate the linking work 
 * @author atul
 *
 */

public class Linker {

	/**
	 * Links each of the entities and update the Data object so as to pass it on to the next stage
	 * 
	 * @param tweetData - Data as passed on by the previous stage
	 * @param source - Source name which is used by the LinkerFactory to create an object of the intended type 
	 */
	public void link(Data tweetData, String source) {

		// Get an object of the desired linker
		LinkerFactory factory = new LinkerFactory();
		LinkerInterface li = factory.getLinker(source);
		
		// perform the linking operation
		li.link(tweetData);

	}

}
