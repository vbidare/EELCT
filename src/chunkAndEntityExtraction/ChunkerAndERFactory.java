package chunkAndEntityExtraction;

import chunkAndEntityExtraction.ChunkerAndERInterface;
import chunkAndEntityExtraction.WashingtonChunkerAndER;

/**
 * @author abhi
 * Factory which gets instance of required class.
 */
public class ChunkerAndERFactory {

	/**
	 * Gets the required NER package instance as specified by NERType
	 * @param NERType
	 * @return
	 */
	public ChunkerAndERInterface getCERInstance (String NERType)
	{
		if (NERType == null) return null;
		else if (NERType.equalsIgnoreCase("WASHINGTON"))
		{
			return new WashingtonChunkerAndER ();		
		}
		
		return null;
	}

}
