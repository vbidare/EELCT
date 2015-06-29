package helper;

import java.io.ByteArrayOutputStream; 
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URL;  

import org.apache.tika.detect.DefaultDetector;  
import org.apache.tika.detect.Detector;  
import org.apache.tika.io.TikaInputStream;  
import org.apache.tika.metadata.Metadata;  
import org.apache.tika.parser.AutoDetectParser;  
import org.apache.tika.parser.ParseContext;  
import org.apache.tika.parser.Parser;  
import org.apache.tika.sax.BodyContentHandler;  
   
import org.xml.sax.ContentHandler;  

import main.Data;
   
public class UrlExtractor {  
  private OutputStream outputstream;  
  private ParseContext context;  
  private Detector detector;  
  private Parser parser;  
  private Metadata metadata;  
  private String extractedText; 
   
  public UrlExtractor() {  
    context = new ParseContext();  
    detector = new DefaultDetector();  
    parser = new AutoDetectParser(detector);  
    context.set(Parser.class, parser);  
    outputstream = new ByteArrayOutputStream();  
    metadata = new Metadata();  
    System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
    System.getProperties().put("http.proxyPort", "8080");  
  }  
   
  public void getContents(Data data, String url) throws Exception {  
    InputStream input = TikaInputStream.get(new URL(url), metadata);  
    ContentHandler handler = new BodyContentHandler(outputstream);  
    parser.parse(input, handler, metadata, context);  
    input.close();  
    
    //Update the data object
    extractedText = outputstream.toString().replaceAll("\\s+", " ").trim();
    for ( int i = 0; i < extractedText.length(); ++i ) {
    	data.urlContents += extractedText.charAt(i);
    	if ( i%150 == 0 && i != 0 ) {
    		data.urlContents += '\n';
    	}
    }
  }  
   
}
