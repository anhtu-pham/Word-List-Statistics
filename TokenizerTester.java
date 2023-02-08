import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class TokenizerTester{
  
  /**
   * Tests the wordList() method of Tokenizer.
   */
  @Test
  public void testWordList(){
    String[] words;
    Tokenizer tokenizer;
    ArrayList<String> wordList;
    
    
    // Check when words is empty
    words = new String[]{};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{}, wordList.toArray());
    
    
    // Check when words has only ""
    words = new String[]{""};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{}, wordList.toArray());
    
    
    // Check when words has only 1 word
    words = new String[]{"nice"};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{"nice"}, wordList.toArray());
    
    
    // Check when words has only 1 word that has upper case characters with punctuations and leading and trailing spaces
    words = new String[]{" HELLO%%&@@ "};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{"hello"}, wordList.toArray());
    
    
    // Check when words has many words that have upper case characters with punctuations and leading and trailing spaces
    words = new String[]{" HELLO.", "     Welcome(^^)  ", "!! ", "   Can ", "my ", "friend", "CO-OPERATE ", "  With", "You??"};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{"hello", "welcome", "can", "my", "friend", "cooperate", "with", "you"}, wordList.toArray());
    words = new String[]{" HOW  ", "Are", "YOU", "todaY??@@", "!&%@!", "  &,%! ", "  Will", "  you", "  have", "     LUNCH  ", "at", "  noon", "(12pm)%%??"};
    tokenizer = new Tokenizer(words);
    wordList = tokenizer.wordList();
    assertArrayEquals(new String[]{"how", "are", "you", "today", "will", "you", "have", "lunch", "at", "noon", "12pm"}, wordList.toArray());
  }
}