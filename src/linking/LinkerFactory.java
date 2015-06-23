package linking;
/**
 * As the name suggests it's a factory that gives an object of the desired linker. 
 * @author atul
 *
 */

public class LinkerFactory {

	/**
	 * Get the linker object of the type asked for.
	 * 
	 * @param source
	 * @return
	 */
	public LinkerInterface getLinker(String source) {

		if(source.equals("alchemyapi")){
			return new AlchemyAPILinker();
		}else{
			return new WikiLinker();
		}

	}

}
