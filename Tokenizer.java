import java.io.*;
import java.util.*;
// Class Tokenizer is the class that has feature of reading lines from text file, extracting, normalizing words from the text file or from array of String elements, and storing them in the word list
public class Tokenizer{
  
  // wordList is used to store the word list created in the constructors
  private ArrayList<String> wordList;
  
  /**
   * Normalize the words from the input words, and add to the word list.
   * Time complexity: O(n)
   */
  public Tokenizer(String[] words){
    // Create ArrayList<String> instance for wordList with length equal to that of input words so that wordList does not need to be expanded later
    wordList = new ArrayList<String>(words.length);
    // For each word from input words, remove all punctuations and trim; for each word that are not "", change its characters to lower case and add the word to the wordList
    for(int i = 0; i < words.length; i = i + 1){
        words[i] = words[i].replaceAll("\\p{Punct}", "").trim();
        if(!words[i].equals("")){
          words[i] = words[i].toLowerCase();
          wordList.add(words[i]);
      }
    }
  }
  
  /**
   * Return the word list that is created in the constructors.
   * Time complexity: O(1)
   */
  public ArrayList<String> wordList(){
    return wordList;
  }
}