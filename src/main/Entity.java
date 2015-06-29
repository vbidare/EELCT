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
    
    /**
	 * Initializes a Entity object when name and type both are available
	 * @param namedEntity
	 * @param type
	 */
	public Entity (String namedEntity, String type)
	{
		this.entityName = namedEntity;
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString ()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.type.toUpperCase());
		sb.append(" : ");
		sb.append (this.entityName);
		
		return sb.toString();
	}
}
