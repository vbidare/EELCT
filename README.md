## Setup
- Add `TweeboParser` to `./lib` and conpile it. (Can be downloaded from https://sourceforge.net/projects/tweeboparser/)

- Install the following jar files
	i. gson: http://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1
	ii. org.json: https://code.google.com/p/org-json-java/downloads/list
	iii. Apache tika: https://tika.apache.org/download.html
	
- After that everything should work, hopefully!


## Setup for Entity and Chunk Extractor
- The following package must be installed - 
    i. twitter_nlp : https://github.com/aritter/twitter_nlp
- Make the environmet variable 'TWITTER_NLP' point to the installation directory
- While using the above package, make sure that the static String 'BASE_DIR' in WashingtonChunkerAndER.java contain the
  fully qualified path name of the installation directory/twitter_nlp/
  eg - /home/abhi/twitter_nlp/
- The entity recognizer takes some time to load the model files into memory, so please be patient!!!
