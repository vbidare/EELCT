package sentimentAnalysis;
import java.util.ArrayList;

import main.Data;
/*
 *  Created by veerendra on 23/6/15.
 *  An interface for Sentiment Analysis. All Sentiment Analyzers will be implemented on this interface.
 */
public interface SentimentAnalyzerInterface {
    ArrayList<String> getSentiment(Data dataObject);
}