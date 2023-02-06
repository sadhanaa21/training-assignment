import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
//we will have to set class path 
// set classpath=%classpath%;D:\Programs\jsoup-1.15.3.jar;
class CountFre // a class to store count of words and words
{

  String word;
  int freq;

  CountFre(String word, int freq) // constructor to initialize 
  {
    this.word = word;
    this.freq = freq;
  }

  public int hashCode() //overriding hashCode
  {
    return word.hashCode();
  }

  //if hashCode is same then equals method will be called and here we will update frequency for final print

  public boolean equals(Object o) //overriding equals method
  {
    CountFre count = (CountFre) o;
    count.freq = freq + count.freq; //if word was same then we will update older and newer frequency
    return true;
  }

  public String toString() //overriding toString method
  {
    return "{Word =" + word + ", frequency =" + freq + "}\n";
  }

}
class webScrap // a class to get web page and count certain words
{

  String load(String url) throws Exception // method to load data from web file
  {
    Document doc = Jsoup.connect(url).get();
    String text = doc.body().text();
    text = text.replaceAll("[^a-zA-Z\\w]", " ");
    return text;
  }

  int count(String word, String text) throws Exception //method to count frequency of a word
  {
    int count = 0;
    String arr[] = text.split(" ");
    for (String str: arr) {
      if (str.equalsIgnoreCase(word)) count++;
    }
    return count;
  }

}
class Demo {
  public static void main(String ar[]) throws Exception {
    HashSet < CountFre > set = new HashSet < > ();
    webScrap ws = new webScrap();

    //storing all urls into a ArrayList
    BufferedReader br = new BufferedReader(new FileReader("url.txt"));
    String stringURL = br.readLine();
    ArrayList < String > url = new ArrayList < > ();
    while (stringURL != null) {
      url.add(stringURL);
      stringURL = br.readLine();
    }

    //storing all words to an arraylist
    BufferedReader br1 = new BufferedReader(new FileReader("word.txt"));
    String stringWORD = br1.readLine();
    ArrayList < String > words = new ArrayList < > ();
    while (stringWORD != null) {
      words.add(stringWORD);
      stringWORD = br1.readLine();
    }

    int url_num = 1; //variable to give url number

    for (String str: url) {
      ArrayList < CountFre > alist = new ArrayList < > ();
      System.out.println("\nURL : " + url_num);
      url_num++;

      for (String str1: words) {
        int count1 = ws.count(str1, ws.load(str));
        alist.add(new CountFre(str1, count1)); //adding to arraylist
        set.add(new CountFre(str1, count1)); // adding to hashSet
      }

      alist.sort((object1, object2) -> object2.freq - object1.freq); //sorting the arraylist

      for (int index = 0; index < 3; index++) //printing the first 3 
        System.out.print(alist.get(index));
    }

    //storing the hashSet to TreeSet to sort it descending
    System.out.println("\nComplete .......");

    TreeSet <CountFre> treeSet = new TreeSet <CountFre>((object1, object2) -> object2.freq - object1.freq);
    for (CountFre countfreq: set)
      treeSet.add(countfreq);

    System.out.println(treeSet);
  }
}