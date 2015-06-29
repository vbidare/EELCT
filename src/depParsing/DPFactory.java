package depParsing;

public class DPFactory {
	public DPInterface getParser(String ParsetType) {
		if(ParsetType == null){
            return null;
        } else if(ParsetType.equalsIgnoreCase("tweebo")){
        	return new TweeboParser();
        }
		return null;
	}
}
