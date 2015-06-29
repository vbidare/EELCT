The downloaded tweets are in dataset folder(both training and testing)
Use parser.py under tokenizer folder to separate tweets and their polarities.
Then use twokenize.py under ark-twokenize.py on the tweet files above to get space separated tokens of tweets
Apply pos.py under preprocessor on tokenized tweet files to get POS tagging
Apply preprocess.py under preprocessor on tokenized tweet files to get preprocessed tweet files.
(**Imp : You have to apply the above actions on both training and testing files separately by changing the filenames/args for inside each python script. Generate separate output files for train and test for all the above actions**)
You need preprocessed, pos tagged and polarity(obtained by applying parser.py) files further.
change filenames in unique_unigram_svm_classifier and use it inside libsvm package to obtain output and accuracy. (Keep all the other folders also inside libsvm so that it becomes easy for access)

Execution :
Write all the tweets whose sentiment is to be detected into dataset/final_test.tsv in the tab separated format: <ID> T<ID> neutral <Tweet text>
Here 3rd field neutral serves no purpose. Its used for verification. But we don't know the sentiment yet.
Eg:
1   T1  neutral Rafa is King of Clay!!!
Execute executer.sh shell script
Output can be obtained in 'libsvm-3.20/results'
