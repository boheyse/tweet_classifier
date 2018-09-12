import java.util.*;
import java.io.*;

/**
  A class that is used to classify a tweet's sentiment as good, bad, or neutral
*/

public class test {

  String score = "";
  int wrong = 0;
  List<String> target = new ArrayList<String>();
  List<String> tweets = new ArrayList<String>();
  String neg_path;
  String pos_path;
  String tweets_path;


  // public test(String neg_path, String pos_path, String tweets_path){
  //   // Loads the negative words into an array named neg_words
  //   Scanner neg = new Scanner(new File("n.txt"));
  //   Set<String> neg_set = new HashSet<>();
  //   while (neg.hasNextLine()) {
  //     neg_set.add(neg.nextLine());
  //   }
  //
  //   // Loads the positive words into an array named pos_words
  //   Scanner pos = new Scanner(new File("p.txt"));
  //   Set<String> pos_set = new HashSet<>();
  //   while (pos.hasNextLine()) {
  //     pos_set.add(pos.nextLine());
  //   }
  //
  //
  //
  //   // Loads each line of the csv file into one long string variable called score
  //   // The string is then split by only commas outside of quotes to give us the 6 columns
  //   // index 0 is the tweets score, index 6 is the tweet itself from the csv file
  //   // The score for each tweet is loaded into a list called target, and the tweets is loaded into a list called tweets.
  //   Scanner tweet = new Scanner(new BufferedReader(new FileReader("t.csv")));
  //   int i = 0;
  //   while(tweet.hasNextLine())
  //   {
  //     score = tweet.nextLine();
  //     String[] score_array = score.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
  //
  //     target.add(i, score_array[0]);
  //     tweets.add(i, score_array[5]);
  //     i++;
  //   }
  //   String[] target_array = target.toArray(new String[0]);
  //   String[] tweet_array = tweets.toArray(new String[0]);
  // }

  public static void main(String[] args) throws Exception {

    Scanner scan = new Scanner (System.in);
    System.out.println("Please Specify The File Path To The Negative Words: ");
    neg_path = scan.nextLine();
    System.out.println("Please Specify The File Path To The Positive Words: ");
    pos_path = scan.nextLine();
    System.out.println("Please Specify The File Path To The Tweets: ");
    tweets_path = scan.nextLine();

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // //scans in the csv file the user specified in the opening prompt
    //
    // int length_tweet_array = tweet_array.length;
    //
    //
    //
    //
    //
    // //
    // for(int k = 0; k < length_tweet_array; k++)
    // {
    //   int pos_count = 0;
    //   int neg_count = 0;
    //   String[] sentiment = new String[length_tweet_array]; //create array of strings equal to the length of the array of tweets, 493 in this program
    //   String[] check_tweet_array = tweet_array[k].split("[ .,?!]+");
    //   for(int l = 0; l < check_tweet_array.length; l++)
    //   {
    //     String check_tweet_word = check_tweet_array[l];
    //     check_tweet_word = check_tweet_word.toLowerCase();
    //     if(pos_set.contains(check_tweet_word))
    //     {
    //       pos_count = pos_count + 1;
    //     }
    //     if(neg_set.contains(check_tweet_word))
    //     {
    //       neg_count = neg_count + 1;
    //     }
    //   }
    //
    //   if(pos_count>neg_count)
    //     sentiment[k] = "4";
    //   else if(neg_count>pos_count)
    //     sentiment[k] = "0";
    //   else
    //     sentiment[k] = "2";
    //
    //   if(!Objects.equals(sentiment[k], target_array[k]))
    //     wrong = wrong + 1;
    //
    //
    //   System.out.println("Tweet: " + tweet_array[k]);
    //   System.out.println("Target Sentiment: " + target_array[k]);
    //   System.out.println("Predicted Sentiment: " + sentiment[k] + "\n");
    //
    //   // System.out.println("A");
    // }
    //
    // System.out.println("# of Misclassified Tweets: " + wrong);
    //


  }
}
