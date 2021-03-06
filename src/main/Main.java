package main;
import linking.Linker;
import tagging.TagFactory;
import tagging.TagInterface;
import classification.ClassifyFactory;
import classification.ClassifyInterface;
import entityExtraction.NERInterface;
import entityExtraction.NERfactory;

public class Main {
    // Note: set it to false when you don't want to use IIIT proxy
    public static boolean useProxy = true;
    public static void main(String[] args) {
        //NER
	    NERfactory neRfactory = new NERfactory();
        NERInterface NERIdentifier = neRfactory.getNER("ALCHEMY");
        Data data = new Data("Nadal is the King of Clay");
//        Data data = new Data("Sachin hit a century");
//        Data data = new Data("Missing Dornier flight navigator's wife seeks PM");
        NERIdentifier.getEntities(data);

        //Linking
        new Linker().link(data, "wiki");

        //Classification
        ClassifyFactory classifyfactory = new ClassifyFactory();
        ClassifyInterface classify = classifyfactory.getClassifier("ALCHEMY");
        classify.generateClass(data);

        //Tagging
        TagFactory tagfactory = new TagFactory();
        TagInterface tag = tagfactory.getTagger("ALCHEMY");
        tag.generateTags(data);
        System.out.print(data);


    }
}
