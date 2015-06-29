package main;
import java.util.*;

import main.Entity;

/**
 * Created by veerendra on 11/6/15.
 * Intentionally keeping all attributes public, as scope is not very clear.
 * Will make it more secure in next iterations.
 */
public class Data {
    public String text;                    //Tweet text to be processed
    public String language;
    public ArrayList<Entity> entitiesList; //List of objects of class Entity
    public ArrayList<String> chunkList;  // list of chunks in the tweet
    public ArrayList<String> category;     //Category could be single, keeping array list to make it extensible in future
    public ArrayList<String> tags;         //Tags associated with the tweet
    public ArrayList<String> hashtags;     //Hashtags mentioned in the tweet
    public ArrayList<Map.Entry<String, String>> pos;	//POS tags for the tweet
    public ArrayList<Map.Entry<String, Integer>> tree;	//Dependency tree for the tweet
    public ArrayList<Map.Entry<String, String>> hashtagExpanded;	//Dependency tree for the tweet
    public ArrayList<String> sentimentOfTweets;
    public String urlContents;             //Data retrived by crawling url

    public Data(String tweet){
        this.text = tweet;
        entitiesList = new ArrayList<Entity>();
        category = new ArrayList<String>();
        tags = new ArrayList<String>();
        chunkList = new ArrayList<String> ();
    }
    
    /**
	 * initializes a data object from a tweet
	 * @param tweet
	 * @author abhinaba
	 */
	public void setEnititiesAndChunks (ArrayList<Entity> entityList, ArrayList<String> chunksList) 
	{
		this.entitiesList = entityList;
		this.chunkList = chunksList;
	}

    public String toString(){
    	//naba
        String text = "\n Entities: \n";
        for (Entity entity: entitiesList){
            text += "Name: " + entity.entityName + " Type: " + entity.type + " Rel: " + entity.relevance + " Count: " + entity.count + " KBLink: "+entity.knowledgeBaseLink+"\n";
        }
        
        // naba
        text += "\nChunks \n";
        if(chunkList.isEmpty()) System.out.println("Chunks list is empty");
        else
            for (String chunk : chunkList)
            {
                text += chunk + "\n";
            }

        text += "\nClassification :\n";
        for (String s: category){
            text += s + "\n";
        }

        text += "\nTags :\n";
        for (String s: tags){
            text += s + "\n";
        }

        text += "\nSentiment :\n";
        if(sentimentOfTweets == null || sentimentOfTweets.isEmpty())
            text += "Sentiment is Empty \n";
        else
        	for (String s: sentimentOfTweets){
        		text += s + "\n";
        	}
        
        text += "\nHashtags :\n";
        text += (hashtags == null || hashtags.isEmpty()) ? "" : hashtags.toString();
        text += "\n";
        
        text += "\nExpanded Hashtags :\n";
        text += ( hashtagExpanded == null || hashtagExpanded.isEmpty()) ? "" : hashtagExpanded.toString();
        text += "\n";
        
        text += "\nPOS tags :\n";
        text += (pos == null || pos.isEmpty()) ? "" : pos.toString();
        text += "\n";

        text += "\nDependency tree :\n";
        text += (tree == null || tree.isEmpty()) ? "" : tree.toString();

        text += "\nUrl Extraction: \n";
        text += (urlContents == null || urlContents.equals("")) ? "" : urlContents;
        text += "\n";
        
        return text;
    }
}
