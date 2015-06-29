DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
echo $DIR
cd $DIR
cd TwitterSentimentAnalysis
cd tokenizer
python parser.py
cd ark-twokenize-py/
python twokenize.py ../tweet-messages
cd ../../preprocessor/
python pos.py
python preprocess.py
cd ../libsvm-3.20/
python unique_unigram_svm_classifier.py
