import java.util.*;
import java.io.*;

/**
  A class for classifying tweets as Good, Bad, or neutral
*/
public class ClassifyTweets {
  String negPath; // holds user defined path to negative word set
  String posPath; // holds user defined path to positive word set
  String tweetsPath;  // holds user defined path to tweets data
  String[] scoreArray; // holds the columns of the tweet data we want
  String[] targetArray; // holds the target sentiment of each tweet (column 0)
  String[] tweetArray;  // holds the tweet data (column 5)
  List<String> target = new ArrayList<String>();  //
  List<String> tweets = new ArrayList<String>();  //
  Set<String> negSet = new HashSet<>(); // hashset of all negative words
  Set<String> posSet = new HashSet<>(); // hashset of all positive words


  /**
    Constructs a ClassifyTweetsobjects that asks the user for the file locations of
    the negative words, positive words, tweet data. It then saves the users input
    locations to variables negPath, posPath, and tweetsPath
  */
  public ClassifyTweets(){
    Scanner scan = new Scanner (System.in);
    System.out.println("Please Specify The File Path To The Negative Words: ");
    negPath = scan.nextLine();
    System.out.println("Please Specify The File Path To The Positive Words: ");
    posPath = scan.nextLine();
    System.out.println("Please Specify The File Path To The Tweets: ");
    tweetsPath = scan.nextLine();
  }



  /**
    The setNegativeWords method uses the negPath variable to scan the
    file and put its contents into a HashSet named negSet.
    @throws Exception if the negPath is not initialized.
  */

  public void setNegativeWords() throws Exception {
    try
    {
      Scanner neg = new Scanner(new File(negPath));
      while (neg.hasNextLine()) {
        negSet.add(neg.nextLine());
      }
    }
    catch(Exception e)
    {
      System.out.print("negPath was initialized as null, enter a file path \n");
    }
  }



  /**
    The setPositiveWords method uses the posPath variable to scan the
    file assigned to that path and put its contents into a HashSet named negSet
    @throws Exception if the posPath is not initialized.
  */

  public void setPositiveWords() throws Exception {
    // Loads the positive words into an array named pos_words
    try
    {
      Scanner pos = new Scanner(new File(posPath));
      while (pos.hasNextLine()) {
        posSet.add(pos.nextLine());
      }
    }
    catch(Exception e)
    {
      System.out.print("posPath was initialized as null, enter a file path\n");
    }
  }



  /**
    Loads each line of the csv file into one long string variable called score
    The string is then split by only commas outside of quotes to give us the 6 columns
    index 0 is the tweets score, index 6 is the tweet itself from the csv file
    The score for each tweet is loaded into a list called target, and the tweets is loaded into a list called tweets.
    @throws Exception if the posPath is not initialized.
  */

  public void loadTweets() throws Exception {

      try
      {
        Scanner tweet = new Scanner(new BufferedReader(new FileReader(tweetsPath)));
        int i = 0;
        String score;
        while(tweet.hasNextLine())
        {
          score = tweet.nextLine();
          scoreArray = score.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

          target.add(i, scoreArray[0]);
          tweets.add(i, scoreArray[5]);
          i++;
        }
        targetArray = target.toArray(new String[0]);
        tweetArray = tweets.toArray(new String[0]);
      }
      catch(Exception e)
      {
        System.out.print("tweetsPath was initialized as null, enter a file path\n");
      }
    }



  /**
    The classify method takes each tweet from tweetArray and creates a temporary
    array that splits the tweet into individual words. Each word in the tweet
    occupies it's own index value in the new temporary array and is compared
    to all words in the neg_words and pos_words HashSet using the .contains()
    function. The number of pos_words recognized is compared to the number of
    neg_words recognized and subsequently scored based on word count,
    "4" for positive, "2" for neutral, "0" for negative. The classifier score is
    compared to the target score provided in the tweet data. If the scores differ
    the wrong increases by 1. The total number of Misclassified tweets is printed
    at the end
  */

  public void classify(){
    int lengthTweetArray = tweetArray.length;  // gets the length of the tweet array
    int wrong = 0;   // counts number of incorrect tweet classifications

    for(int k = 0; k < lengthTweetArray; k++)
    {
      int posCount = 0;  // counts positive tweets
      int negCount = 0;  // counts negative tweets
      String[] sentiment = new String[lengthTweetArray]; //create array of strings equal to the length of the array of tweets, 493 in this program
      String[] checkTweetArray = tweetArray[k].split("[ .,?!]+");
      for(int l = 0; l < checkTweetArray.length; l++)
      {
        String check_tweet_word = checkTweetArray[l];
        check_tweet_word = check_tweet_word.toLowerCase();
        if(posSet.contains(check_tweet_word))
        {
          posCount = posCount + 1;
        }
        if(negSet.contains(check_tweet_word))
        {
          negCount = negCount + 1;
        }
      }

      if(posCount>negCount)
        sentiment[k] = "4";
      else if(negCount>posCount)
        sentiment[k] = "0";
      else if((negCount>0 && posCount>0) && (negCount==posCount))
        sentiment[k] = "0";
      else
        sentiment[k] = "2";

      if(!Objects.equals(sentiment[k], targetArray[k]))
        wrong = wrong + 1;

      System.out.println("Tweet: " + tweetArray[k]);
      System.out.println("Target Sentiment: " + targetArray[k]);
      System.out.println("Predicted Sentiment: " + sentiment[k] + "\n");

    }

    System.out.println("# of Misclassified Tweets: " + wrong);

  }


  public static void main(String[] args) throws Exception {
    try{
      ClassifyTweets t1 = new ClassifyTweets();
      t1.setNegativeWords();
      t1.setPositiveWords();
      t1.loadTweets();
      t1.classify();
    }
    catch(Exception e){
      System.out.println("A critical path was initialized to null");
    }
  }
}
