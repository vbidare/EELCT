package chunkAndEntityExtraction;

import main.Data;
import main.Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Washington Edu implementation of tweet NER and Chunker
 * https://github.com/aritter/twitter_nlp
 * WARNING : the static BASE_DIR must be set before running
 * @author abhi
 *
 */
public class WashingtonChunkerAndER implements ChunkerAndERInterface
{
	private static String INPUT_FILE_PLACEHOLDER = "inputFile";
	private static String LAST_LINE = "Average time per tweet";
	private static String BASE_DIR = "twitter_nlp-master/";
	private static String[] COMMAND = 
	{
		"/bin/sh",
		"-c",
		"cat inputFile | python " + BASE_DIR + "python/ner/extractEntities2.py --pos --chunk --classify"
	};
	private static String INPUT_FILE = "tweetTmp.txt";
	/**
	 * Extract the chunks from the tweets present in the input file. The python script which we run to extract the chunks
	 * requires each tweet to be on a separate line.
	 * 
	 * @param file
	 * @return String
	 */
	@Override
	public void extractChunksAndEntity (Data data) 
	{
		try
		{
			File file = new File(INPUT_FILE);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data.text);
            bw.newLine();
            bw.close();
		}
		catch (Exception e)
		{
            System.out.println("Exception while getting Entities!!");
        }
		
		try 
		{
			// Execute the python script and get the stream handle
			WashingtonChunkerAndER.COMMAND[2] = WashingtonChunkerAndER.COMMAND[2].replace(WashingtonChunkerAndER.INPUT_FILE_PLACEHOLDER, INPUT_FILE);
			Process pyScript = Runtime.getRuntime().exec(WashingtonChunkerAndER.COMMAND);
			BufferedReader in = new BufferedReader(new InputStreamReader(pyScript.getInputStream()));
			
			// Process the python script output.
			
			String line;
			while ((line = in.readLine()) != null) 
			{
				if (line.contains(WashingtonChunkerAndER.LAST_LINE)) 
				{
					break;
				}
				
				// Indices in the array - 0 is token, 1 is entity or not, 2 is POS,
				// 3 is position of the word in chunk if it is a part of the current chunk
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				String NEType = new String();
				String tokens[] = line.split(" ");
				ArrayList<String> chunkList = new ArrayList<String>();
				ArrayList<Entity> entityList = new ArrayList<Entity> ();
				
				for (String token : tokens) 
				{
					String tokenInfo[] = token.split("/");
					
					// extracts chunks
					if (tokenInfo[3].contains("B-")) 
					{
						if (sb.length() > 0) 
						{
							chunkList.add(sb.toString());
							sb.setLength(0);
						}
						sb.append(tokenInfo[3]).append(": ").append(tokenInfo[0]);
					}
					else if (tokenInfo[3].equals("O")) 
					{
						if (sb.length() > 0) 
						{
							chunkList.add(sb.toString());
							sb.setLength(0);
						}
					}
					else 
					{
						sb.append(" ").append(tokenInfo[0]);
					}
					
					// extracts entities
					if (tokenInfo[1].contains("B-"))
					{
						if (sb1.length() > 0)
						{
							entityList.add(new Entity(sb1.toString(), NEType));
							sb1.setLength(0);
						}
						sb1.append(tokenInfo[0]);
						NEType = tokenInfo[1].split("-")[1];
					}
					else if (tokenInfo[1].equals("O"))
					{
						if (sb1.length() > 0)
						{
							entityList.add(new Entity(sb1.toString(), NEType));
							sb1.setLength(0);
						}
					}
					else
					{
						sb1.append(" ").append(tokenInfo[0]);
					}
				}
				data.setEnititiesAndChunks(entityList, chunkList);
			}
			
			File fileIn = new File(INPUT_FILE);
			fileIn.delete();
		} 
		catch (IOException e) 
		{
			System.out.println("Chunking error - Problem occurred while extracting the chunks.");
		}		
	}
}
