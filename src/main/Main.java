package main;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import linking.Linker;
import helper.*;
import sentimentAnalysis.SentimentAnalyzerFactory;
import sentimentAnalysis.SentimentAnalyzerInterface;
import tagging.TagFactory;
import tagging.TagInterface;
import chunkAndEntityExtraction.ChunkerAndERFactory;
import chunkAndEntityExtraction.ChunkerAndERInterface;
import classification.ClassifyFactory;
import classification.ClassifyInterface;
import entityExtraction.NERInterface;
import entityExtraction.NERfactory;
import hashtagExpander.HashtagExpanderFactory;
import hashtagExpander.HashtagExpanderInterface;
import depParsing.DPFactory;
import depParsing.DPInterface;

public class Main {
    // Note: set it to false when you don't want to use IIIT proxy
    public static boolean useProxy = true;
    public static void main(String[] args) {
        //NER
	    NERfactory neRfactory = new NERfactory();
        NERInterface NERIdentifier = neRfactory.getNER("ALCHEMY");
//        Data data = new Data("Nadal is the King of Clay");
//        Data data = new Data("Sachin hit a century. That was Awesome !!!");
        Data data = new Data("Paul Walker's death came to me as a terrible shock");
        NERIdentifier.getEntities(data);


        //Classification
        ClassifyFactory classifyfactory = new ClassifyFactory();
        ClassifyInterface classify = classifyfactory.getClassifier("ALCHEMY");
        classify.generateClass(data);

        //Tagging
        TagFactory tagfactory = new TagFactory();
        TagInterface tag = tagfactory.getTagger("ALCHEMY");
        tag.generateTags(data);
        
        // Hashtag expansion
        HashtagExpanderFactory HEfactory = new HashtagExpanderFactory();
        HashtagExpanderInterface HExpander = HEfactory.getExpander();
        data.hashtagExpanded = new ArrayList<Map.Entry<String, String>>();
        for (String hashtag: data.hashtags) {
            data.hashtagExpanded.add(
        		new AbstractMap.SimpleEntry<String, String>(hashtag, HExpander.expand(hashtag))
        	);
        }

        // Dependency parsing
        DPFactory depFactory = new DPFactory();
        DPInterface DParser = depFactory.getParser("Tweebo");
        DParser.parseTweetText(data.text);
        data.tree = DParser.getDepTree();
        data.pos = DParser.getPOSTags();

        //Sentiment Analysis
        SentimentAnalyzerFactory sentimentAnalyzerFactory= new SentimentAnalyzerFactory();
        SentimentAnalyzerInterface sai = sentimentAnalyzerFactory.getSentimentAnalyzer("LIBSVM");
        sai.getSentiment(data);

        ChunkerAndERFactory cerFactory = new ChunkerAndERFactory();
        ChunkerAndERInterface cer = cerFactory.getCERInstance("WASHINGTON");
        cer.extractChunksAndEntity(data);

        //Linking
        new Linker().link(data, "wiki");

        //Url Extractor
        UrlExtractor urlextractor = new UrlExtractor();
        try {
            urlextractor.getContents(data,"http://t.co/BNQ0Jk5x1O");
        } catch (Exception e) {
            System.out.println("Connection timed out");
        }
        
        System.out.print(data);
   }
}
