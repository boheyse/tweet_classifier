import java.util.*;
import java.io.*;

/**
  A class for classifying tweets as Good, Bad, or neutral
*/
public class tri {
  String neg_path;
  String pos_path;
  String tweets_path;
  String[] score_array;
  String[] target_array;
  String[] tweet_array;
  List<String> target = new ArrayList<String>();
  List<String> tweets = new ArrayList<String>();
  Set<String> neg_set = new HashSet<>();
  Set<String> pos_set = new HashSet<>();


  /**
    Constructs a ClassifyTweetsobjects that asks the user for the file locations of
    the negative words, positive words, tweet data. It then saves the users input
    locations to variables neg_path, pos_path, and tweets_path
  */
  public tri(){
    Scanner scan = new Scanner (System.in);
    System.out.println("Please Specify The File Path To The Negative Words: ");
    neg_path = scan.nextLine();
    System.out.println("Please Specify The File Path To The Positive Words: ");
    pos_path = scan.nextLine();
    System.out.println("Please Specify The File Path To The Tweets: ");
    tweets_path = scan.nextLine();
  }



  /**
    The set_negative_words method uses the neg_path variable to scan the
    file and put its contents into a HashSet named neg_set.
    @throws Exception if the neg_path is not initialized.
  */

  public void set_negative_words() throws Exception {
    try
    {
      Scanner neg = new Scanner(new File(neg_path));
      while (neg.hasNextLine()) {
        neg_set.add(neg.nextLine());
      }
    }
    catch(Exception e)
    {
      System.out.print("neg_path was initialized as null, enter a file path \n");
    }
  }



  /**
    The set_positive_words method uses the pos_path variable to scan the
    file assigned to that path and put its contents into a HashSet named neg_set
    @throws Exception if the pos_path is not initialized.
  */

  public void set_positive_words() throws Exception {
    // Loads the positive words into an array named pos_words
    try
    {
      Scanner pos = new Scanner(new File(pos_path));
      while (pos.hasNextLine()) {
        pos_set.add(pos.nextLine());
      }
    }
    catch(Exception e)
    {
      System.out.print("pos_path was initialized as null, enter a file path\n");
    }
  }



  /**
    Loads each line of the csv file into one long string variable called score
    The string is then split by only commas outside of quotes to give us the 6 columns
    index 0 is the tweets score, index 6 is the tweet itself from the csv file
    The score for each tweet is loaded into a list called target, and the tweets is loaded into a list called tweets.
    @throws Exception if the pos_path is not initialized.
  */

  public void load_tweets() throws Exception {

      try
      {
        Scanner tweet = new Scanner(new BufferedReader(new FileReader(tweets_path)));
        int i = 0;
        String score;
        while(tweet.hasNextLine())
        {
          score = tweet.nextLine();
          score_array = score.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

          target.add(i, score_array[0]);
          tweets.add(i, score_array[5]);
          i++;
        }
        target_array = target.toArray(new String[0]);
        tweet_array = tweets.toArray(new String[0]);
      }
      catch(Exception e)
      {
        System.out.print("tweets_path was initialized as null, enter a file path\n");
      }
    }



  /**
    The classify method takes each tweet from tweet_array and creates a temporary
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
    int length_tweet_array = tweet_array.length;
    int wrong = 0;

    for(int k = 0; k < length_tweet_array; k++)
    {
      int pos_count = 0;
      int neg_count = 0;
      String[] sentiment = new String[length_tweet_array]; //create array of strings equal to the length of the array of tweets, 493 in this program
      String[] check_tweet_array = tweet_array[k].split("[ .,?!]+");
      for(int l = 0; l < check_tweet_array.length; l++)
      {
        String check_tweet_word = check_tweet_array[l];
        check_tweet_word = check_tweet_word.toLowerCase();
        if(pos_set.contains(check_tweet_word))
        {
          pos_count = pos_count + 1;
        }
        if(neg_set.contains(check_tweet_word))
        {
          neg_count = neg_count + 1;
        }
      }

      if(pos_count>neg_count)
        sentiment[k] = "4";
      else if(neg_count>pos_count)
        sentiment[k] = "0";
      else
        sentiment[k] = "2";

      if(!Objects.equals(sentiment[k], target_array[k]))
        wrong = wrong + 1;

      System.out.println("Tweet: " + tweet_array[k]);
      System.out.println("Target Sentiment: " + target_array[k]);
      System.out.println("Predicted Sentiment: " + sentiment[k] + "\n");

    }

    System.out.println("# of Misclassified Tweets: " + wrong);

  }


  public static void main(String[] args) throws Exception {
    try{
      tri t1 = new tri();
      t1.set_negative_words();
      t1.set_positive_words();
      t1.load_tweets();
      t1.classify();
    }
    catch(Exception e){
      System.out.println("A critical path was initialized to null");
    }
  }
}
