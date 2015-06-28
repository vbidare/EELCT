package main;
import java.util.*;

/**
 * Created by veerendra on 11/6/15.
 * Intentionally keeping all attributes public, as scope is not very clear.
 * Will make it more secure in next iterations.
 */
public class Data {
    public String text;                    //Tweet text to be processed
    public String language;
    public ArrayList<Entity> entitiesList; //List of objects of class Entity
    public ArrayList<String> category;     //Category could be single, keeping array list to make it extensible in future
    public ArrayList<String> tags;         //Tags associated with the tweet
    public ArrayList<String> hashtags;     //Hashtags mentioned in the tweet
    public ArrayList<Map.Entry<String, Integer>> tree;	//Dependency tree for the tweet
    public ArrayList<Map.Entry<String, String>> hashtagExpanded;	//Dependency tree for the tweet

    public Data(String tweet){
        this.text = tweet;
        entitiesList = new ArrayList<Entity>();
        category = new ArrayList<String>();
        tags = new ArrayList<String>();
    }

    public String toString(){
        String text = "";
        for (Entity entity: entitiesList){
            text += "Name: " + entity.entityName + " Type: " + entity.type + " Rel: " + entity.relevance + " Count: " + entity.count + " KBLink: "+entity.knowledgeBaseLink+"\n";
        }

        text += "\nClassification :\n";
        for (String s: category){
            text += s + "\n";
        }

        text += "\nTags :\n";
        for (String s: tags){
            text += s + "\n";
        }
        
        text += "\nHashtags :\n";
        text += hashtags.toString();
        text += "\n";
        
        text += "\nExpanded Hashtags :\n";
        text += hashtagExpanded.toString();
        text += "\n";
        
        text += "\nDependency tree :\n";
        text += tree.toString();
        text += "\n";
        
        return text;
    }
}
