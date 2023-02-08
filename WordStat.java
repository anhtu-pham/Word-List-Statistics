import java.util.*;
// WordStat is the class that is used to compute the statistics of the words.
public class WordStat{
  
  // wordHashTable stores hash table with hash entries, each of which stores the word as key and this word's count as value
  private HashTable wordHashTable = new HashTable();
  
  // pairHashTable stores hash table with hash entries, each of which stores the word pair as key and this word pair's count as value
  private HashTable pairHashTable = new HashTable();
  
  // wordRankHashTable stores hash table with hash entries, each of which stores the word as key and this word's rank as value
  private HashTable wordRankHashTable = new HashTable();
  
  // pairRankHashTable stores hash table with hash entries, each of which stores the word pair as key and this word pair's rank as value
  private HashTable pairRankHashTable = new HashTable();
  
  // sortedWordEntries stores array of clone entries of hash table's entries sorted in decreasing order of entries' word count and in alphabetical order with clone entries's words having same counts
  private HashEntry[] sortedWordEntries;
  
  // leastWordEntries stores array of clone entries of hash table's entries sorted in decreasing order of entries' word count and in reverse order of alphabetical order with clone entries's words having same counts
  private HashEntry[] leastWordEntries;
  
  // sortedPairEntries stores array of clone entries of hash table's entries sorted in decreasing order of entries' word pair count and in alphabetical order with clone entries's word pairs having same counts
  private HashEntry[] sortedPairEntries;
  
  /**
   * Build hash tables for wordHashTable, pairHashTable, wordRankHashTable, pairRankHashTable and build arrays for sortedWordEntries, leastWordEntries, sortedPairEntries with source from input array;
   * compute the statistics for the words from input array after normalizing these words.
   */
  public WordStat(String[] words){
    // wordList stores normalized words from words in input array
    ArrayList<String> wordList = new Tokenizer(words).wordList();
    // With each word that will be added to the wordHashTable table, if the word has not been in the table, set its count (value) as 1; if the word has been in the table, increase its count(value) by 1
    for(int i = 0; i < wordList.size(); i = i + 1){
      int value = wordHashTable.get(wordList.get(i));
      if(value != -1){
        wordHashTable.update(wordList.get(i), value + 1);
      }
      else{
        wordHashTable.put(wordList.get(i), 1);
      }
    }
    // With each pair that will be added to the pairHashTable table, if the pair has not been in the table, set its count (value) as 1; if the pair has been in the table, increase its count (value) by 1
    for(int i = 0; i < (wordList.size() - 1); i = i + 1){
      StringBuilder pairBuilder = new StringBuilder();
      String pair = pairBuilder.append(wordList.get(i)).append(" ").append(wordList.get(i + 1)).toString();
      int pairValue = pairHashTable.get(pair);
      if(pairValue != -1){
        pairHashTable.update(pair, pairValue + 1);
      }
      else{
        pairHashTable.put(pair, 1);
      }
    }
    // sortedWordEntries stores clone entries of hash table's entries sorted in decreasing order of entries' word count and in alphabetical order with clone entries's words having same counts
    sortedWordEntries = wordHashTable.sortCloneEntriesAlphabetically();
    // wordRank is used to store the rank of the word that will be added to the wordRankHashTable table
    int wordRank;
    // For each word, if the word is not at beginning of the table and has same count as that of its previous word, set word's rank as that of previous word; otherwise, set word's rank as (its index + 1)
    for(int i = 0; i < sortedWordEntries.length; i = i + 1){
      if(i > 0 && sortedWordEntries[i].getValue() == sortedWordEntries[i - 1].getValue()){
        wordRank = wordRankHashTable.get(sortedWordEntries[i - 1].getKey());
      }
      else{
        wordRank = i + 1;
      }
      wordRankHashTable.put(sortedWordEntries[i].getKey(), wordRank);
    }
    // sortedPairEntries stores clone entries of hash table's entries sorted in decreasing order of entries' word pair count and in alphabetical order with clone entries's word pairs having same counts
    sortedPairEntries = pairHashTable.sortCloneEntriesAlphabetically();
    // pairRank is used to store the rank of the pair that will be added to the pairRankHashTable table
    int pairRank;
    // For each pair, if the pair is not at beginning of the table and has same count as that of its previous pair, set word's pair as that of previous pair; otherwise, set pair's rank as (its index + 1)
    for(int i = 0; i < sortedPairEntries.length; i = i + 1){
      if(i > 0 && sortedPairEntries[i].getValue() == sortedPairEntries[i - 1].getValue()){
        pairRank = pairRankHashTable.get(sortedPairEntries[i - 1].getKey());
      }
      else{
        pairRank = i + 1;
      }
      pairRankHashTable.put(sortedPairEntries[i].getKey(), pairRank);
    }
    // leastWordEntries stores clone entries of hash table's entries sorted in decreasing order of entries' word count and in reverse order of alphabetical order with clone entries's words having same counts
    leastWordEntries = wordHashTable.sortCloneEntriesAlphabeticallyReversed();
    // Computing statistics of the words from the source after normalizing these words
    System.out.println("COMPUTE STATISTICS OF THE WORDS FROM THE SOURCE AFTER NORMALIZING THESE WORDS");
    System.out.println("NOTICE: In constructor, for the methods from mostCommonWords(int k), I use the sample inputs instead of requiring users to type in the input data they want as requiring so in constructor obstructs testing");
    System.out.println("        In demonstration of WordStat in the main method of this class, for the methods from mostCommonWords(int k), users can be required to type in the input data they want as this does not affect testing");
    System.out.println("COMPUTATION FOR wordCount(String word)");
    System.out.print("Counts of words:    ");
    for(int i = 0; i < sortedWordEntries.length; i = i + 1){
      System.out.print(sortedWordEntries[i].getKey() + ": " + wordCount(sortedWordEntries[i].getKey()) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordPairCount(String w1, String w2)");
    System.out.print("Counts of word pairs:    ");
    for(int i = 0; i < sortedPairEntries.length; i = i + 1){
      String[] pair = sortedPairEntries[i].getKey().split(" ");
      System.out.print(sortedPairEntries[i].getKey() + ": " + wordPairCount(pair[0], pair[1]) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordRank(String word)");
    System.out.print("Ranks of words:    ");
    for(int i = 0; i < sortedWordEntries.length; i = i + 1){
      System.out.print(sortedWordEntries[i].getKey() + ": " + wordRank(sortedWordEntries[i].getKey()) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordPairRank(String w1, String w2)");
    System.out.print("Ranks of word pairs:    ");
    for(int i = 0; i < sortedPairEntries.length; i = i + 1){
      String[] pair = sortedPairEntries[i].getKey().split(" ");
      System.out.print(sortedPairEntries[i].getKey() + ": " + wordPairRank(pair[0], pair[1]) + "    ");
    }
    System.out.println("");
    int k;
    String baseWord;
    String startWord;
    int i;
    System.out.println("COMPUTATION FOR mostCommonWords(int k)");
    System.out.print("Take value of k as 2 as sample");
    k = 2;
    System.out.print("k most common words: ");
    System.out.println(Arrays.toString(mostCommonWords(k)));
    System.out.println("COMPUTATION FOR leastCommonWords(int k)");
    System.out.print("Take value of k as 3 as sample");
    k = 3;
    System.out.print("k least common words: ");
    System.out.println(Arrays.toString(leastCommonWords(k)));
    System.out.println("COMPUTATION FOR mostCommonWordPairs(int k)");
    System.out.print("Take value of k as 2 as sample");
    k = 2;
    System.out.print("k most common word pairs: ");
    System.out.println(Arrays.toString(mostCommonWordPairs(k)));
    System.out.println("COMPUTATION FOR mostCommonCollocs(int k, String baseWord, int i)");
    System.out.println("Take value of k as 2 as sample");
    k = 2;
    if(sortedWordEntries.length > 0){
      baseWord = sortedWordEntries[(int) Math.random() * sortedWordEntries.length].getKey();
      System.out.println("If source has at least 1 word, take value of baseWord as random word from source as sample: " + baseWord);
    }
    else{
      baseWord = "hello";
      System.out.println("If source has no words, take value of baseWord as hello as sample");
    }
    System.out.println("Take value of i as 1 as sample");
    i = 1;
    System.out.print("k most common collocs at relative position i: ");
    System.out.println(Arrays.toString(mostCommonCollocs(k, baseWord, i)));
    System.out.println("COMPUTATION FOR mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions)");
    System.out.print("Take value of k as 3 as sample");
    k = 3;
    if(sortedWordEntries.length > 0){
      baseWord = sortedWordEntries[(int) Math.random() * sortedWordEntries.length].getKey();
      System.out.println("If source has at least 1 word, take value of baseWord as random word from source as sample: " + baseWord);
    }
    else{
      baseWord = "hello";
      System.out.println("If source has no words, take value of baseWord as hello as sample");
    }
    System.out.println("Take value of i as -1 as sample");
    i = -1;
    String[] exclusions;
    if(sortedWordEntries.length > 0){
      exclusions = new String[10];
      System.out.println("If source has at least 1 word, take exclusions as array of 10 random words as sample");
      for(int j = 0; j < 10; j = j + 1){
        exclusions[j] = sortedWordEntries[(int) Math.random() * sortedWordEntries.length].getKey();
      }
    }
    else{
      exclusions = new String[]{"hello"};
      System.out.println("If source has no words, take array exclusions as array having only hello as sample");
    }
    System.out.print("k most common collocs at relative position i excluding words in exclusions: ");
    System.out.println(Arrays.toString(mostCommonCollocsExc(k, baseWord, i, exclusions)));
    System.out.println("COMPUTATION FOR generateWordString(int k, String startWord)");
    System.out.print("Take value of k as 4 as sample");
    k = 4;
    if(sortedWordEntries.length > 0){
      startWord = sortedWordEntries[(int) Math.random() * sortedWordEntries.length].getKey();
      System.out.println("If source has at least 1 word, take value of startWord as random word from source as sample: " + startWord);
    }
    else{
      startWord = "hello";
      System.out.println("If source has no words, take value of startWord as hello as sample");
    }
    System.out.print("The string of k words, each of which is the most common word following its previous word, starting from startWord: ");
    System.out.println(generateWordString(k, startWord));
  }
  
  /**
   * Return the count of word specified in input or return 0 if that word is not in the table.
   * Time complexity: approaching O(1)
   */
  public int wordCount(String word){
    // count stores the count of input word by getting value from wordHashTable
    int count = wordHashTable.get(word);
    // If count is not -1, return count
    if(count != -1){
      return count;
    }
    // If count is -1, it means that the word is not in the hash table, and 0 is returned
    else{
      return 0;
    }
  }
  
  /**
   * Return the count of word pair w1 w2 specified in input or return 0 if that word pair is not in the table.
   * Time complexity: approaching O(1)
   */
  public int wordPairCount(String w1, String w2){
    // pairBuilder is used to store the pair of w1 and w2
    StringBuilder pairBuilder = new StringBuilder();
    // pair stores the String value of pairBuilder
    String pair = pairBuilder.append(w1).append(" ").append(w2).toString();
    // pairCount stores count of pair by getting value from pairHashTable
    int pairCount = pairHashTable.get(pair);
    // If pairCount is not -1, return pairCount
    if(pairCount != -1){
      return pairCount;
    }
    // If pairCount is -1, it means that the pair is not in the hash table, and 0 is returned
    else{
      return 0;
    }
  }
  
  /**
   * Return the rank of word specified in input or return 0 if that word is not in the table.
   * Time complexity: approaching O(1)
   */
  public int wordRank(String word){
    // wordRank stores the rank of input word by getting value from wordRankHashTable
    int wordRank = wordRankHashTable.get(word);
    // If wordRank is not -1, return wordRank
    if(wordRank != -1){
      return wordRank;
    }
    // If wordRank is -1, it means that the word is not in the hash table, and 0 is returned
    else{
      return 0;
    }
  }
  
  /**
   * Return the rank of word pair w1 w2 specified in input or return 0 if that word pair is not in the table.
   * Time complexity: approaching O(1)
   */
  public int wordPairRank(String w1, String w2){
    // pairBuilder is used to store the pair of w1 and w2
    StringBuilder pairBuilder = new StringBuilder();
    // pair stores the String value of pairBuilder
    String pair = pairBuilder.append(w1).append(" ").append(w2).toString();
    // pairRank stores rank of pair by getting value from pairRankHashTable
    int pairRank = pairRankHashTable.get(pair);
    // If pairRank is not -1, return pairRank
    if(pairRank != -1){
      return pairRank;
    }
    // If pairRank is -1, it means that the pair is not in the hash table, and 0 is returned
    else{
      return 0;
    }
  }
  
  /**
   * Return String[] array of the k most common words (or array of existing items if there are fewer than k distinct words to return or empty array if there are none)
   * in decreasing order of these words' counts and in alphabetical order with words having same counts; throw exception if k is negative.
   * Time complexity: O(k)
   */
  public String[] mostCommonWords(int k) throws IllegalArgumentException{
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // Change value of k to number of words in the array that is returned in this method (minimum value of 2 values: number of distinct words and k)
    k = Math.min(sortedWordEntries.length, k);
    // mostCommonWords is used to store the array of the k most common words or array of existing items if there are fewer than k distinct words to return or is just empty array if there are none
    String[] mostCommonWords = new String[k];
    // Add key of each clone entry from sortedWordEntries to mostCommonWords in same order
    for(int i = 0; i < k; i = i + 1){
      mostCommonWords[i] = sortedWordEntries[i].getKey();
    }
    // return the mostCommonWords array
    return mostCommonWords;
  }
  
  /**
   * Return String[] array of the k least common words (or array of existing items if there are fewer than k distinct words to return or empty array if there are none)
   * in increasing order of these words' counts and in alphabetical order with words having same counts; throw exception if k is negative.
   * Time complexity: O(k)
   */
  public String[] leastCommonWords(int k) throws IllegalArgumentException{
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // Change value of k to number of words in the array that is returned in this method (minimum value of 2 values: number of distinct words and k)
    k = Math.min(leastWordEntries.length, k);
    // leastCommonWords is used to store the array of the k least common words or array of existing items if there are fewer than k distinct words to return or is just empty array if there are none
    String[] leastCommonWords = new String[k];
    // Add key of each clone entry from leastWordEntries to leastCommonWords in reverse order
    /**
     * The reason for reverse order is that leastWordEntries is sorted in decreasing order of entries' word count and in reverse order of alphabetical order with clone entries's words having same counts;
     * reverse order of leastWordEntries means that elements in leastCommonWords is sorted in increasing order of entries' word count and in alphabetical order with clone entries's words having same counts
     */
    for(int i = 0; i < k; i = i + 1){
      leastCommonWords[i] = leastWordEntries[sortedWordEntries.length - 1 - i].getKey();
    }
    // return the leastCommonWords array
    return leastCommonWords;
  }
  
  /**
   * Return String[] array of the k most common word pairs (or array of existing items if there are fewer than k distinct word pairs to return or empty array if there are none)
   * in decreasing order of these word pairs' counts and in alphabetical order with word oairs having same counts; throw exception if k is negative.
   * Time complexity: O(k)
   */
  public String[] mostCommonWordPairs(int k) throws IllegalArgumentException{
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // Change value of k to number of pairs in the array that is returned in this method (minimum value of 2 values: number of distinct pairs and k)
    k = Math.min(sortedPairEntries.length, k);
    // mostCommonWordPairs is used to store array of the k most common word pairs or array of existing items if there are fewer than k distinct word pairs to return or is just empty array if there are none
    String[] mostCommonWordPairs = new String[k];
    // Add key of each clone entry from sortedPairEntries to mostCommonWordPairs in same order
    for(int i = 0; i < k; i = i + 1){
      mostCommonWordPairs[i] = sortedPairEntries[i].getKey();
    }
    // return the mostCommonWordPairs array
    return mostCommonWordPairs;
  }
  
  /**
   * Return String[] array of the k most common words at specified relative position i (1 or -1) to baseWord (or array of existing items if there are fewer than k elements to return or empty array if there are none)
   * in decreasing order of these words' counts and in alphabetical order with words having same counts; throw exception if either i is neither 1 or -1 or k is negative.
   * Time complexity: O(n)
   */
  public String[] mostCommonCollocs(int k, String baseWord, int i) throws IllegalArgumentException{
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // collocs is used to store the words at specified relative position i to baseWord
    ArrayList<String> collocs = new ArrayList<String>(sortedPairEntries.length);
    // If i is -1, split each pair from sortedPairEntries into 2 half; if the second half is equal to baseWord, add first half to collocs
    if(i == -1){
      for(int j = 0; j < sortedPairEntries.length; j = j + 1){
        String[] pair = sortedPairEntries[j].getKey().split(" ");
        if(pair[1].equals(baseWord)){
          collocs.add(pair[0]);
        }
      }
    }
    // If i is 1, split each pair from sortedPairEntries into 2 half; if the first half is equal to baseWord, add second half to collocs
    else if(i == 1){
      for(int j = 0; j < sortedPairEntries.length; j = j + 1){
        String[] pair = sortedPairEntries[j].getKey().split(" ");
        if(pair[0].equals(baseWord)){
          collocs.add(pair[1]);
        }
      }
    }
    // If i is neither 1 nor -1, throw exception
    else{
      throw new IllegalArgumentException("i should only be 1 or -1");
    }
    // Change value of k to number of words in the array that is returned in this method (minimum value of 2 values: size of collocs and k)
    k = Math.min(collocs.size(), k);
    // mostCommonCollocs is used to store array that complies to this method's requirement
    String[] mostCommonCollocs = new String[k];
    // Add each element with index j in collocs to space with index j in mostCommonCollocs
    for(int j = 0; j < k; j = j + 1){
      mostCommonCollocs[j] = collocs.get(j);
    }
    // return the mostCommonCollocs array
    return mostCommonCollocs;
  }
  
  /**
   * Return String[] array of the k most common words at specified relative position i (1 or -1) to baseWord (or array of existing items if there are fewer than k elements to return or empty array if there are none)
   * in decreasing order of these words' counts and in alphabetical order with words having same counts, but words that are from exclusions are excluded from consideration;
   * throw exception if either i is neither 1 or -1 or k is negative.
   * Time complexity: O(mn) (m is number of elements in exclusions)
   */
  public String[] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) throws IllegalArgumentException{
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // collocs is used to store the words at specified relative position i to baseWord, excluding words that are from exclusions
    ArrayList<String> collocs = new ArrayList<String>(sortedPairEntries.length);
    // isExcluded is used to check if the examined word needs to be excluded
    boolean isExcluded = false;
    // If i is -1, split each pair from sortedPairEntries into 2 half; if the second half is equal to baseWord and first half is not in exclusions, add first half to collocs
    if(i == -1){
      for(int j = 0; j < sortedPairEntries.length; j = j + 1){
        String[] pair = sortedPairEntries[j].getKey().split(" ");
        if(pair[1].equals(baseWord)){
          for(int p = 0; p < exclusions.length; p = p + 1){
            if(pair[0].equals(exclusions[p])){
              isExcluded = true;
            }
          }
          if(!isExcluded){
            collocs.add(pair[0]);
          }
          isExcluded = false;
        }
      }
    }
    // If i is 1, split each pair from sortedPairEntries into 2 half; if the first half is equal to baseWord and second half is not in exclusions, add second half to collocs
    else if(i == 1){
      for(int j = 0; j < sortedPairEntries.length; j = j + 1){
        String[] pair = sortedPairEntries[j].getKey().split(" ");
        if(pair[0].equals(baseWord)){
          for(int p = 0; p < exclusions.length; p = p + 1){
            if(pair[1].equals(exclusions[p])){
              isExcluded = true;
            }
          }
          if(!isExcluded){
            collocs.add(pair[1]);
          }
          isExcluded = false;
        }
      }
    }
    // If i is neither 1 nor -1, throw exception
    else{
      throw new IllegalArgumentException("i should only be 1 or -1");
    }
    // Change value of k to number of words in the array that is returned in this method (minimum value of 2 values: size of collocs and k)
    k = Math.min(collocs.size(), k);
    // mostCommonCollocsExc is used to store array that complies to this method's requirement
    String[] mostCommonCollocsExc = new String[k];
    // Add each element with index j in collocs to space with index j in mostCommonCollocsExc
    for(int j = 0; j < k; j = j + 1){
      mostCommonCollocsExc[j] = collocs.get(j);
    }
    // Return the mostCommonCollocsExc
    return mostCommonCollocsExc;
  }
  
  /**
   * Return string of k words, each of which is the most common word following its previous word, starting from startWord, or as many words complying this rule as possible if fewer than k words comply this rule;
   * each word in this string is separated by single space; throw exception if k is negative.
   * Time complexity: O(k)
   */
  public String generateWordString(int k, String startWord){
    // If k is negative, throw exception
    if(k < 0){
      throw new IllegalArgumentException("k cannot be negative");
    }
    // wordStringBuilder is used to store the string builder for the word string required by this method
    StringBuilder wordStringBuilder = new StringBuilder();
    // If k is not higher than 0 or count of startWord is not higher than 0 (which means startWord is not from the source and is not in hash table), just return String representation of wordStringBuilder when it has nothing inside (return empty string)
    /**
     * If k is higher than 0 and count of startWord (which means startWord is from the source and is in hash table),
     * use baseWord to store the current last word in the wordStringBuilder,
     * use mostCommonCollocs to return array of only 1 element that is the most common colloc that has relative position 1 to the baseWord, append whitespace and that element to wordStringBuilder
     */
    if(k > 0 && wordCount(startWord) > 0){
      wordStringBuilder.append(startWord);
      String baseWord = startWord;
      for(int i = 2; i <= k; i = i + 1){
        String[] mostCommonCollocs = mostCommonCollocs(1, baseWord, 1);
        if(mostCommonCollocs.length == 0){
          break;
        }
        wordStringBuilder.append(" ");
        wordStringBuilder.append(mostCommonCollocs[0]);
        baseWord = mostCommonCollocs[0];
      }
    }
    // return the String representation of the wordStringBuilder
    return wordStringBuilder.toString();
  }
  
  /**
   * Show demonstration of the code.
   */
  public static void main(String[] args){
    WordStat wordStat = new WordStat(new String[]{"LEMON!!  ", "raspberry!", "  DURIAN!!??", "&&lemon%%", " LEMON@. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "COCONUT%. ", ";Lemon, ", "(STRAWBERRY)@&", "Durian!!??  "});
    System.out.println("");
    System.out.println("DEMONSTRATION FOR WordStat");
      
    System.out.println("COMPUTATION FOR wordCount(String word)");
    System.out.print("Counts of words:    ");
    for(int i = 0; i < wordStat.sortedWordEntries.length; i = i + 1){
      System.out.print(wordStat.sortedWordEntries[i].getKey() + ": " + wordStat.wordCount(wordStat.sortedWordEntries[i].getKey()) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordPairCount(String w1, String w2)");
    System.out.print("Counts of word pairs:    ");
    for(int i = 0; i < wordStat.sortedPairEntries.length; i = i + 1){
      String[] pair = wordStat.sortedPairEntries[i].getKey().split(" ");
      System.out.print(wordStat.sortedPairEntries[i].getKey() + ": " + wordStat.wordPairCount(pair[0], pair[1]) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordRank(String word)");
    System.out.print("Ranks of words:    ");
    for(int i = 0; i < wordStat.sortedWordEntries.length; i = i + 1){
      System.out.print(wordStat.sortedWordEntries[i].getKey() + ": " + wordStat.wordRank(wordStat.sortedWordEntries[i].getKey()) + "    ");
    }
    System.out.println("");
    System.out.println("COMPUTATION FOR wordPairRank(String w1, String w2)");
    System.out.print("Ranks of word pairs:    ");
    for(int i = 0; i < wordStat.sortedPairEntries.length; i = i + 1){
      String[] pair = wordStat.sortedPairEntries[i].getKey().split(" ");
      System.out.print(wordStat.sortedPairEntries[i].getKey() + ": " + wordStat.wordPairRank(pair[0], pair[1]) + "    ");
    }
    System.out.println("");
    Scanner scanner  = new Scanner(System.in);
    int k;
    String baseWord;
    String startWord;
    int i;
    ArrayList<String> exclusionsList = new ArrayList<String>();
    String answer;
    System.out.println("COMPUTATION FOR mostCommonWords(int k)");
    System.out.println("Note: if there are fewer than k distinct words, print array of existing items; if there are none, print empty array; if k is negative, throw exception");
    System.out.print("Please type in value of k for mostCommonWords(int k) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("k most common words: ");
    System.out.println(Arrays.toString(wordStat.mostCommonWords(k)));
    System.out.println("COMPUTATION FOR leastCommonWords(int k)");
    System.out.println("Note: if there are fewer than k distinct words, print array of existing items; if there are none, print empty array; if k is negative, throw exception");
    System.out.print("Please type in value of k for leastCommonWords(int k) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("k least common words: ");
    System.out.println(Arrays.toString(wordStat.leastCommonWords(k)));
    System.out.println("COMPUTATION FOR mostCommonWordPairs(int k)");
    System.out.println("Note: if there are fewer than k distinct word pairs, print array of existing items; if there are none, print empty array; if k is negative, throw exception");
    System.out.print("Please type in value of k for mostCommonWordPairs(int k) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("k most common word pairs: ");
    System.out.println(Arrays.toString(wordStat.mostCommonWordPairs(k)));
    System.out.println("COMPUTATION FOR mostCommonCollocs(int k, String baseWord, int i)");
    System.out.println("Note: if there are fewer than k distinct word pairs, print array of existing items; if there are none, print empty array; if k is negative or i is neither 1 nor -1, throw exception");
    System.out.print("Please type in value of k for mostCommonCollocs(int k, String baseWord, int i) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("Please type in value of baseWord for mostCommonCollocs(int k, String baseWord, int i) here: ");
    baseWord = scanner.next();
    System.out.print("Please type in value of i for mostCommonCollocs(int k, String baseWord, int i) here: ");
    i = Integer.parseInt(scanner.next());
    System.out.print("k most common collocs at relative position i: ");
    System.out.println(Arrays.toString(wordStat.mostCommonCollocs(k, baseWord, i)));
    System.out.println("COMPUTATION FOR mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions)");
    System.out.println("Note: if there are fewer than k distinct word pairs having relative position i to baseWord, print array of existing items; if there are none, print empty array; if k is negative or i is neither 1 nor -1, throw exception");
    System.out.print("Please type in value of k for mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("Please type in value of baseWord for mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) here: ");
    baseWord = scanner.next();
    System.out.print("Please type in value of i for mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) here: ");
    i = Integer.parseInt(scanner.next());
    while(true){
      System.out.print("Do you have word that needs to be added to exclusions for mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions)? Please type in yes or no here: ");
      answer = scanner.next();
      if(answer.equals("yes")){
        System.out.print("Please type in that word here: ");
        exclusionsList.add(scanner.next());
      }
      else if(answer.equals("no")){
        break;
      }
      else{
        System.out.println("You should type in yes or no");
      }
    }
    String[] exclusions = new String[exclusionsList.size()];
    for(int j = 0; j < exclusionsList.size(); j = j + 1){
      exclusions[j] = exclusionsList.get(j);
    }
    System.out.print("k most common collocs at relative position i excluding words in exclusions: ");
    System.out.println(Arrays.toString(wordStat.mostCommonCollocsExc(k, baseWord, i, exclusions)));
    System.out.println("COMPUTATION FOR generateWordString(int k, String startWord)");
    System.out.print("Please type in value of k for generateWordString(int k, String startWord) here: ");
    k = Integer.parseInt(scanner.next());
    System.out.print("Please type in startWord for generateWordString(int k, String startWord) here: ");
    startWord = scanner.next();
    System.out.print("The string of k words, each of which is the most common word following its previous word, starting from startWord: ");
    System.out.println(wordStat.generateWordString(k, startWord));
    System.out.println("DEMONSTRATION FOR Tokenizer");
    System.out.println("Create tokenizer with array input as source");
    Tokenizer tokenizer = new Tokenizer(new String[]{"LEMON!!  ", "raspberry!", "  DURIAN!!??", "&&lemon%%", " LEMON@. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "COCONUT%. ", ";Lemon, ", "(STRAWBERRY)@&", "Durian!!??  "});
    ArrayList<String> wordList2 = tokenizer.wordList();
    System.out.print("Normalized words from words in this source: ");
    for(int m = 0; m < wordList2.size(); m = m + 1){
      System.out.print("  " + wordList2.get(m));
    }
    System.out.println("");
    System.out.println("DEMONSTRATION FOR HashEntry");
    System.out.println("Create hash entry named entry with key being hello and value being 10");
    HashEntry entry = new HashEntry("hello", 10);
    System.out.println("key in entry is that " + entry.getKey());
    System.out.println("value in entry is that " + entry.getValue());
    System.out.println("Set value in entry to 20");
    entry.setValue(20);
    System.out.println("Now value in entry is that " + entry.getValue());
    System.out.println("DEMONSTRATION FOR HashTable");
    System.out.println("Create hash table named table1 with default size of 31");
    HashTable table1 = new HashTable();
    System.out.println("Creating hash table named table2 with negative input for size should cause the exception to be thrown");
    try{
      HashTable table2 = new HashTable(-5);
    }
    catch(IllegalArgumentException exception){
      System.out.println("The exception is thrown correctly");
    }
    catch(Exception exception){
      System.out.println("Wrong exception is thrown");
    }
    System.out.println("Create hash table named table3 with positive size of 4");
    HashTable table3 = new HashTable(4);
    System.out.println("Put the key-value pairs into table3: hi-4, rectangle-8, hexagon-1, box-10, regular-8, nice-10");
    table3.put("hi", 4);
    table3.put("rectangle", 8);
    table3.put("hexagon", 1);
    table3.put("box", 10);
    table3.put("regular", 8);
    table3.put("nice", 10);
    System.out.println("Get values associated with some keys:");
    System.out.println("Value associated with hi is that " + table3.get("hi"));
    System.out.println("Value associated with regular is that " + table3.get("regular"));
    System.out.println("Value associated with hexagon is that " + table3.get("hexagon"));
    System.out.println("Using update method for table3 with this pair of key-value in input: hi-10, hexagon-6, solution-18 to show that value in key-value pair can be updated and key-value pair can be added to table3 if key has not existed in table3");
    table3.update("hi", 10);
    table3.update("hexagon", 6);
    table3.update("solution", 18);
    System.out.println("Get values associated with some keys (to show correctness of get and update):");
    System.out.println("Value associated with solution is that " + table3.get("solution"));
    System.out.println("Value associated with hi is that " + table3.get("hi"));
    System.out.println("Value associated with hexagon is that " + table3.get("solution"));
  }
}