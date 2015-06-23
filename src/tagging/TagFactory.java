package tagging;

public class TagFactory {
	public TagInterface getTagger(String apiType){
	      if(apiType == null){
	         return null;
	      }	
	      
	      if ( apiType.equalsIgnoreCase("Alchemy") ) {
	    	  return new AlchemyTagger();
	      }
	      
	      return null;
	}
}
