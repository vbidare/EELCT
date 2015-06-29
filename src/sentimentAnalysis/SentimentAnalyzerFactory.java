package sentimentAnalysis;

/**
 * Created by veerendra on 29/6/15.
 */
public class SentimentAnalyzerFactory {
        public SentimentAnalyzerInterface getSentimentAnalyzer(String SAtype){
            if(SAtype == null){
                return null;
            }else if(SAtype.equalsIgnoreCase("LIBSVM")){
                return new LibSVMSentimentAnalyzer();
            }
            return null;
        }
}
