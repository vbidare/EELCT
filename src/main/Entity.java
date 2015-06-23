package main;
/**
 * Created by veerendra on 11/6/15.
 */
public class Entity {
	public String entityName;              //Name of the entity. Eg. Rafa
	public String type;                    //Type of entity. Eg. Person, Organization, Place etc
	public double relevance;
	public int count;
	public String knowledgeBaseLink;       //Wiki link as wiki has been decided as primary KB
	public String socialTags;              //Social tags, nicknames associated with entity etc. May or may not be used.

    public Entity(String entityName){
        this.entityName = entityName;
    }
}
