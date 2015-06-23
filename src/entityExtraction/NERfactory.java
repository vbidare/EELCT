package entityExtraction;
/**
 * Created by veerendra on 11/6/15.
 * Factory which gets instance of required class.
 */
public class NERfactory {
    public NERInterface getNER(String NERtype){
        if(NERtype == null){
            return null;
        }else if(NERtype.equalsIgnoreCase("ALCHEMY")){
            return new AlchemyNER();
        }else if(NERtype.equalsIgnoreCase("STANFORD")){
            return new StanfordNER();
        }
        return null;
    }
}
