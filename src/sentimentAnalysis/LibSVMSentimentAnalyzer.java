package sentimentAnalysis;

import main.Data;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by veerendra on 29/6/15.
 */
public class LibSVMSentimentAnalyzer implements SentimentAnalyzerInterface {
    public ArrayList<String> getSentiment(Data data){
        System.out.println("Fetching the sentiment of tweet. This may take a minute....");
        try {
            File file = new File("TwitterSentimentAnalysis/dataset/final_test.tsv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("1\tT1\tneutral\t"+data.text);
            bw.close();
        }catch (Exception e){
            System.out.println("Exception while writing the tweet for sentiment analysis!!");
        }

        try {
            Process p2 = Runtime.getRuntime().exec("pwd");
            p2.waitFor();

            Process p = Runtime.getRuntime().exec("bash executer.sh");
            p.waitFor();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        }catch (Exception e){
            System.out.println("Exception while performing Sentiment Analysis");
        }

        try{
            BufferedReader br;
            String sCurrentLine;
            ArrayList<String> s = new ArrayList<String>();
            br = new BufferedReader(new FileReader("TwitterSentimentAnalysis/libsvm-3.20/results.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                s.add(sCurrentLine);
            }
            data.sentimentOfTweets = s;
            br.close();
            return s;
        }catch (Exception e){
            System.out.println("Exception while reading Sentiment of the tweet");
        }
        return new ArrayList<String>();
    }
}
