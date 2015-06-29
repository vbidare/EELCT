package chunkAndEntityExtraction;

import main.Data;

/**
 * @author abhi
 * An interface for Named Entity Recognition. All NERs will implement this interface.
 */
public interface ChunkerAndERInterface 
{
	/**
	 * Extracts the entities from the tweet data
	 * @param dataObject
	 * @return
	 */
	public void extractChunksAndEntity (Data data);
}
