package classification;

public class ClassifyFactory {
	public ClassifyInterface getClassifier(String apiType){
	      if(apiType == null){
	         return null;
	      }	
	      
	      if ( apiType.equalsIgnoreCase("Alchemy") ) {
	    	  return new AlchemyClassifier();
	      }
	      
	      return null;
	}
}
