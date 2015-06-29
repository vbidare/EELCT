## Setup
- Add `TweeboParser` to `./lib` and conpile it (with `install.sh`). (Can be downloaded from https://sourceforge.net/projects/tweeboparser/)

- Install the following jar files
  - gson: http://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1
  - org.json: https://code.google.com/p/org-json-java/downloads/list
  - Apache tika: https://tika.apache.org/download.html (tika-app)
	
- After that everything should work, hopefully!


## Setup for Entity and Chunk Extractor
- The following package must be installed - 
  - twitter_nlp : https://github.com/aritter/twitter_nlp
- Make the environmet variable 'TWITTER_NLP' point to the installation directory. Make it usable system-wide and not user-specific.
    eg - If the project directory is - /home/veerendra/summer-project/EELCT2, then TWITTER_NLP should be set to '/home/veerendra/summer-project/EELCT2/twitter_nlp-master'
- While using the above package, make sure that the static String 'BASE_DIR' in WashingtonChunkerAndER.java contain the
  fully qualified path name of the installation directory/twitter_nlp/
  eg - /home/abhi/twitter_nlp/
- The entity recognizer takes some time to load the model files into memory, so please be patient!!!
- The tweets to be processed should be kept in a file named 'tweetTmp.txt'(one tweet per line) in the directory where you are running the project from. If you are running the project from the command line this file should be present in the same folder as your Main.class file. Else if you are ruuning the project from Eclipse IDE, this file should be present in the root directory of your project. 

##Setup for Sentiment Analysis
- The following python modules must be installed -
  - nltk
  - numpy
  - scipy
  - sklearn
