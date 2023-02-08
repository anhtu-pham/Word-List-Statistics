import org.junit.*;
import static org.junit.Assert.*;
public class WordStatTester{
  
  /**
   * Tests the wordCount(String word) method of WordStat.
   */
  @Test
  public void testWordCount(){
    WordStat stat;
    
    
    /* Check when array is empty */
    stat = new WordStat(new String[]{});
    assertEquals(0, stat.wordCount("cycling"));
    
    
    /* Check when array has only 1 words */
    stat = new WordStat(new String[]{"baseball"});
    assertEquals(1, stat.wordCount("baseball"));
    assertEquals(0, stat.wordCount("bowling"));
    
    
    /* Check when array has many words */
    // Whan words have unique counts
    stat = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    assertEquals(7, stat.wordCount("football"));
    assertEquals(1, stat.wordCount("cricket"));
    assertEquals(3, stat.wordCount("tennis"));
    assertEquals(0, stat.wordCount("swimming"));
    // When some words have same counts
    stat = new WordStat(new String[]{" Football!!!!", "@TENNIS&", "BASEBALL!!%%", "&baseBall%@", "  @footBALL; ", "BASEball@%", " ;tennis? ", "Tennis;&", " .Baseball; ", " TENNIS!!%%@@", "baseBALL!!??", "tennis@"});
    assertEquals(2, stat.wordCount("football"));
    assertEquals(5, stat.wordCount("tennis"));
    assertEquals(5, stat.wordCount("baseball"));
    assertEquals(0, stat.wordCount("gymnastics"));
  }
  
  /**
   * Tests the wordPairCount(String w1, String w2) method of WordStat.
   */
  @Test
  public void testWordPairCount(){
    WordStat stat;
    
    
    /* Check when array is empty */
    stat = new WordStat(new String[]{});
    assertEquals(0, stat.wordPairCount("cycling", "jogging"));
    
    
    /* Check when array has only 1 pair */
    stat = new WordStat(new String[]{"raspberry", "blueberry"});
    assertEquals(1, stat.wordPairCount("raspberry", "blueberry"));
    assertEquals(0, stat.wordPairCount("banana", "swimming"));
    
    
    /* Check when array has many pairs */
    // Whan distinct pairs have unique counts
    stat = new WordStat(new String[]{"LEMON!!", "  Lemon!!??", "&&&&lemon@@", " LEMON. ", "strawberry!!@@", "lemon@@&&", "  Strawberry??!! ", "lemon. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Pineapple!!??"});
    assertEquals(3, stat.wordPairCount("lemon", "strawberry"));
    assertEquals(4, stat.wordPairCount("lemon", "lemon"));
    assertEquals(1, stat.wordPairCount("strawberry", "pineapple"));
    assertEquals(2, stat.wordPairCount("strawberry", "lemon"));
    assertEquals(0, stat.wordPairCount("cherry", "jogging"));
    assertEquals(0, stat.wordPairCount("lemon", "surfing"));
    // When some distinct pairs have same counts
    stat = new WordStat(new String[]{"LEMON!!", "  Lemon!!??", "&&&&strawBERRY@@", " LEMON. ", "@@Coconut!!  ", "BANANA@@&&  ", "  Strawberry??!! ", "lemon. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Mango!!??"});
    assertEquals(1, stat.wordPairCount("strawberry", "mango"));
    assertEquals(1, stat.wordPairCount("lemon", "coconut"));
    assertEquals(1, stat.wordPairCount("coconut", "banana"));
    assertEquals(1, stat.wordPairCount("banana", "strawberry"));
    assertEquals(2, stat.wordPairCount("strawberry", "lemon"));
    assertEquals(2, stat.wordPairCount("lemon", "strawberry"));
    assertEquals(2, stat.wordPairCount("lemon", "lemon"));
    assertEquals(0, stat.wordPairCount("banana", "basketball"));
  }
  
  /**
   * Tests the wordRank(String word) method of WordStat.
   */
  @Test
  public void testWordRank(){
    WordStat stat;
    
    
    /* Check when array is empty */
    stat = new WordStat(new String[]{});
    assertEquals(0, stat.wordRank("cycling"));
    
    
    /* Check when array has only 1 word */
    stat = new WordStat(new String[]{"baseball"});
    assertEquals(1, stat.wordRank("baseball"));
    assertEquals(0, stat.wordRank("bowling"));
    
    
    /* Check when array has many words */
    // When distinct words' ranks are unique (distinct words have unique counts)
    stat = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    assertEquals(2, stat.wordRank("tennis"));
    assertEquals(3, stat.wordRank("cricket"));
    assertEquals(1, stat.wordRank("football"));
    assertEquals(0, stat.wordRank("swimming"));
    // When some distinct words' ranks are similar (these words have same counts)
    stat = new WordStat(new String[]{" BaseBALL!!@@", ", footBALL; ", "  tennis? ", "Skiing. ", "Tennis!&  ", " Football ", " BASKETball ", "  TENNIS!!%%@@", "  Cricket!!??", "SKIING@!"});
    assertEquals(1, stat.wordRank("tennis"));
    assertEquals(2, stat.wordRank("football"));
    assertEquals(2, stat.wordRank("skiing"));
    assertEquals(4, stat.wordRank("cricket"));
    assertEquals(4, stat.wordRank("baseball"));
    assertEquals(0, stat.wordRank("jogging"));
  }
  
  /**
   * Tests the wordPairRank(String w1, String w2) method of WordStat.
   */
  @Test
  public void testWordPairRank(){
    WordStat stat;
    
    
    /* Check when array is empty */
    stat = new WordStat(new String[]{});
    assertEquals(0, stat.wordPairRank("cycling", "jogging"));
    
    
    /* Check when array has only 1 pair */
    stat = new WordStat(new String[]{"raspberry", "blueberry"});
    assertEquals(1, stat.wordPairRank("raspberry", "blueberry"));
    assertEquals(0, stat.wordPairRank("banana", "swimming"));
    
    
    /* Check when array has many pairs */
    // When distinct pairs' ranks are unique (distinct pairs have unique counts)
    stat = new WordStat(new String[]{"LEMON!!", "  Lemon!!??", "&&&&lemon@@", " LEMON. ", "strawberry!!@@", "lemon@@&&", "  Strawberry??!! ", "lemon. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Pineapple!!??"});
    assertEquals(2, stat.wordPairRank("lemon", "strawberry"));
    assertEquals(4, stat.wordPairRank("strawberry", "pineapple"));
    assertEquals(1, stat.wordPairRank("lemon", "lemon"));
    assertEquals(3, stat.wordPairRank("strawberry", "lemon"));
    assertEquals(0, stat.wordPairRank("lemon", "tennis"));
    assertEquals(0, stat.wordPairRank("cherry", "volleyball"));
    // When some distinct pairs' ranks are similar (these pairs have same counts)
    stat = new WordStat(new String[]{"  Lemon!!??", "&&Cherry@@", "@Mango!", " LEMON. ", "StrawBERRY!!@@", "lemon@@&&", "  Strawberry??!! ", "mango. ", ";LEMON, ", "  (STRAWBERRY)@&", "  MANGO!!??"});
    assertEquals(2, stat.wordPairRank("mango", "lemon"));
    assertEquals(2, stat.wordPairRank("strawberry", "mango"));
    assertEquals(1, stat.wordPairRank("lemon", "strawberry"));
    assertEquals(4, stat.wordPairRank("lemon", "cherry"));
    assertEquals(4, stat.wordPairRank("cherry", "mango"));
    assertEquals(4, stat.wordPairRank("strawberry", "lemon"));
    assertEquals(0, stat.wordPairRank("coconut", "swimming"));
  }
  
  /**
   * Tests the mostCommonWords(int k) method of WordStat.
   */
  @Test
  public void testMostCommonWords(){
    
    
    /* Check when array is empty */
    // Check when k is negative
    WordStat stat1 = new WordStat(new String[]{});
    try{
      stat1.mostCommonWords(-5);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0 or positive
    WordStat stat2 = new WordStat(new String[]{});
    assertArrayEquals(new String[]{}, stat2.mostCommonWords(0));
    assertArrayEquals(new String[]{}, stat2.mostCommonWords(1));
    
    
    /* Check when array has only 1 word */
    // Check when k is negative
    WordStat stat3 = new WordStat(new String[]{"baseball@"});
    try{
      stat3.mostCommonWords(-2);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0, equal to, or higher than distinct word number
    WordStat stat4 = new WordStat(new String[]{"baseball."});
    assertArrayEquals(new String[]{}, stat4.mostCommonWords(0));
    assertArrayEquals(new String[]{"baseball"}, stat4.mostCommonWords(1));
    assertArrayEquals(new String[]{"baseball"}, stat4.mostCommonWords(2));
    
    
    /* Check when array has many words */
    // Check when k is negative
    WordStat stat5 = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    try{
      stat5.mostCommonWords(-8);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // (When distinct words have unique counts) check with k being equal to 0, smaller than, equal to, or higher than distinct words number
    WordStat stat6 = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    assertArrayEquals(new String[]{}, stat6.mostCommonWords(0));
    assertArrayEquals(new String[]{"football", "tennis"}, stat6.mostCommonWords(2));
    assertArrayEquals(new String[]{"football", "tennis", "cricket"}, stat6.mostCommonWords(3));
    assertArrayEquals(new String[]{"football", "tennis", "cricket"}, stat6.mostCommonWords(6));
    // (When some distinct words have same counts) check with k being equal to 0, smaller than, equal to, or higher than distinct words number
    WordStat stat7 = new WordStat(new String[]{"Football!!  ", "@TENNIS&", "BASEBALL!!%%", "&baseBall%@", " @footBALL;", "BASEball@%", " ;tennis?", "Tennis;", " .Baseball; ", "TENNIS!%", "baseBALL!?", "tennis@"});
    assertArrayEquals(new String[]{}, stat7.mostCommonWords(0));
    assertArrayEquals(new String[]{"baseball", "tennis"}, stat7.mostCommonWords(2));
    assertArrayEquals(new String[]{"baseball", "tennis", "football"}, stat7.mostCommonWords(3));
    assertArrayEquals(new String[]{"baseball", "tennis", "football"}, stat7.mostCommonWords(6));
  }
  
  /**
   * Tests the leastCommonWords(int k) method of WordStat.
   */
  @Test
  public void testLeastCommonWords(){
    
    
    /* Check when array is empty */
    // Check when k is negative
    WordStat stat1 = new WordStat(new String[]{});
    try{
      stat1.leastCommonWords(-12);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0 or positive
    WordStat stat2 = new WordStat(new String[]{});
    assertArrayEquals(new String[]{}, stat2.leastCommonWords(0));
    assertArrayEquals(new String[]{}, stat2.leastCommonWords(1));
    
    
    /* Check when array has only 1 word */
    // Check when k is negative
    WordStat stat3 = new WordStat(new String[]{"baseball"});
    try{
      stat3.leastCommonWords(-6);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0, equal to, or higher than distinct word number
    WordStat stat4 = new WordStat(new String[]{"baseball@"});
    assertArrayEquals(new String[]{}, stat4.leastCommonWords(0));
    assertArrayEquals(new String[]{"baseball"}, stat4.leastCommonWords(1));
    assertArrayEquals(new String[]{"baseball"}, stat4.leastCommonWords(2));
    
    
    /* Check when array has many words */
    // Check when k is negative
    WordStat stat5 = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    try{
      stat5.leastCommonWords(-52);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // (When distinct words have unique counts) Check when k is equal to 0, smaller than, equal to, or higher than distinct words number
    WordStat stat6 = new WordStat(new String[]{"football", "  Football!!!!", "football", "footBALL; ", "  tennis? ", "footBALL. ", "Tennis;", " Football ", " FootBALL ", "  TENNIS!!%%@@", "  Cricket!!??"});
    assertArrayEquals(new String[]{}, stat6.leastCommonWords(0));
    assertArrayEquals(new String[]{"cricket", "tennis"}, stat6.leastCommonWords(2));
    assertArrayEquals(new String[]{"cricket", "tennis", "football"}, stat6.leastCommonWords(3));
    assertArrayEquals(new String[]{"cricket", "tennis", "football"}, stat6.leastCommonWords(6));
    // (When some distinct words have same counts) Check when k is equal to 0, smaller than, equal to, or higher than distinct words number
    WordStat stat7 = new WordStat(new String[]{"@TENNIS&", "Football!!  ", "BASEBALL!!%%", "&baseBall%@", " @footBALL;", "BASEball@%", " .Baseball; ", "TENNIS!%", ";baseBALL!?"});
    assertArrayEquals(new String[]{}, stat7.leastCommonWords(0));
    assertArrayEquals(new String[]{"football", "tennis"}, stat7.leastCommonWords(2));
    assertArrayEquals(new String[]{"football", "tennis", "baseball"}, stat7.leastCommonWords(3));
    assertArrayEquals(new String[]{"football", "tennis", "baseball"}, stat7.leastCommonWords(6));
  }
  
  /**
   * Tests the mostCommonWordPairs(int k) method of WordStat.
   */
  @Test
  public void testMostCommonWordPairs(){
    
    
    /* Check when array is empty */
    // Check when k is negative
    WordStat stat1 = new WordStat(new String[]{});
    try{
      stat1.mostCommonWordPairs(-15);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0 or positive
    WordStat stat2 = new WordStat(new String[]{});
    assertArrayEquals(new String[]{}, stat2.mostCommonWordPairs(0));
    assertArrayEquals(new String[]{}, stat2.mostCommonWordPairs(2));
    
    
    /* Check when array has only 1 pair */
    // Check when k is negative
    WordStat stat3 = new WordStat(new String[]{"lime", "cherry"});
    try{
      stat3.mostCommonWordPairs(-2);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // Check when k is equal to 0, equal to, or higher than distinct pair number
    WordStat stat4 = new WordStat(new String[]{"lime;", "cherry!"});
    assertArrayEquals(new String[]{}, stat4.mostCommonWordPairs(0));
    assertArrayEquals(new String[]{"lime cherry"}, stat4.mostCommonWordPairs(1));
    assertArrayEquals(new String[]{"lime cherry"}, stat4.mostCommonWordPairs(5));
    
    
    /* Check when array has many pairs */
    // Check when k is negative
    WordStat stat5 = new WordStat(new String[]{"LEMON!!", "  Lemon!!??", "&&&&lemon@@", " LEMON. ", "strawberry!!@@", "lemon@@&&", "  Strawberry??!! ", "lemon. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Pineapple!!??"});
    try{
      stat5.mostCommonWordPairs(-4);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    // (When distinct pairs have unique counts) Check when k is equal to 0, smaller than, equal to, or higher than distinct pairs number
    WordStat stat6 = new WordStat(new String[]{"LEMON!!", "  Lemon!!??", "&&&&lemon@@", " LEMON. ", "strawberry!!@@", "lemon@@&&", "  Strawberry??!! ", "lemon. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Pineapple!!??"});
    assertArrayEquals(new String[]{}, stat6.mostCommonWordPairs(0));
    assertArrayEquals(new String[]{"lemon lemon", "lemon strawberry"}, stat6.mostCommonWordPairs(2));
    assertArrayEquals(new String[]{"lemon lemon", "lemon strawberry", "strawberry lemon", "strawberry pineapple"}, stat6.mostCommonWordPairs(4));
    assertArrayEquals(new String[]{"lemon lemon", "lemon strawberry", "strawberry lemon", "strawberry pineapple"}, stat6.mostCommonWordPairs(8));
    // (When some distinct pairs have same counts) Check when k is equal to 0, smaller than, equal to, or higher than distinct pairs number
    WordStat stat7 = new WordStat(new String[]{"  Lemon!!??", "&&Cherry@!", "@coconut&", " Lemon; ", "StrawBERRY!@  ", "lemon@@&&", "  Strawberry??!! ", "coconut. ", ";LEMON, ", "  (STRAWBERRY)@&", "  Coconut@@!!"});
    assertArrayEquals(new String[]{}, stat7.mostCommonWordPairs(0));
    assertArrayEquals(new String[]{"lemon strawberry", "coconut lemon", "strawberry coconut", "cherry coconut"}, stat7.mostCommonWordPairs(4));
    assertArrayEquals(new String[]{"lemon strawberry", "coconut lemon", "strawberry coconut", "cherry coconut", "lemon cherry", "strawberry lemon"}, stat7.mostCommonWordPairs(6));
    assertArrayEquals(new String[]{"lemon strawberry", "coconut lemon", "strawberry coconut", "cherry coconut", "lemon cherry", "strawberry lemon"}, stat7.mostCommonWordPairs(10));
  }
  
  /**
   * Tests the mostCommonCollocs(int k, String baseWord, int i) method of WordStat.
   */
  @Test
  public void testMostCommonCollocs(){
    
    
    /* Check when k is negative */
    WordStat stat1 = new WordStat(new String[]{"LEMON!!  ", "  Lemon!!??", "&&&&lemon", " LEMON. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Pineapple!!??  "});
    try{
      stat1.mostCommonCollocs(-2, "strawberry", 1);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    
    
    /* Check when i is neither 1 nor -1 */
    WordStat stat2 = new WordStat(new String[]{"LEMON!!  ", "  Lemon!!??", "&&&&lemon", " LEMON. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Pineapple!!??  "});
    try{
      stat2.mostCommonCollocs(2, "lemon", 20);
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    
    
    /* Check when k is not negative and i is either 1 or -1 */
    // Check when array is empty and k is equal to 0 or positive
    WordStat stat3 = new WordStat(new String[]{});
    assertArrayEquals(new String[]{}, stat3.mostCommonCollocs(0, "strawberry", -1));
    assertArrayEquals(new String[]{}, stat3.mostCommonCollocs(5, "strawberry", 1));
    // Check when array has only 1 pair that includes baseWord and k is equal to 0, equal to, or higher than distinct colloc number
    WordStat stat4 = new WordStat(new String[]{"LEMON;  ", "  Strawberry!!  "});
    assertArrayEquals(new String[]{}, stat4.mostCommonCollocs(0, "strawberry", -1));
    assertArrayEquals(new String[]{}, stat4.mostCommonCollocs(0, "lemon", 1));
    assertArrayEquals(new String[]{"lemon"}, stat4.mostCommonCollocs(1, "strawberry", -1));
    assertArrayEquals(new String[]{"strawberry"}, stat4.mostCommonCollocs(1, "lemon", 1));
    assertArrayEquals(new String[]{"lemon"}, stat4.mostCommonCollocs(2, "strawberry", -1));
    assertArrayEquals(new String[]{"strawberry"}, stat4.mostCommonCollocs(3, "lemon", 1));
    // (When distinct pairs have distinct counts) Check when array has many pairs that includes baseWord and k is equal to 0, smaller than, equal to, or higher than distinct collocs number
    WordStat stat5 = new WordStat(new String[]{"LEMON!!  ", "  Lemon!!??", "&&&&lemon", " LEMON. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Pineapple!!??  "});
    assertArrayEquals(new String[]{}, stat5.mostCommonCollocs(0, "strawberry", 1));
    assertArrayEquals(new String[]{"lemon"}, stat5.mostCommonCollocs(1, "strawberry", 1));
    assertArrayEquals(new String[]{"lemon", "pineapple"}, stat5.mostCommonCollocs(2, "strawberry", 1));
    assertArrayEquals(new String[]{"lemon", "pineapple"}, stat5.mostCommonCollocs(3, "strawberry", 1));
    assertArrayEquals(new String[]{}, stat5.mostCommonCollocs(0, "lemon", -1));
    assertArrayEquals(new String[]{"lemon"}, stat5.mostCommonCollocs(1,"lemon", -1));
    assertArrayEquals(new String[]{"lemon", "strawberry"}, stat5.mostCommonCollocs(2,"lemon", -1));
    assertArrayEquals(new String[]{"lemon", "strawberry"}, stat5.mostCommonCollocs(3,"lemon", -1));
    // (When some distinct pairs have same counts) Check when array has many pairs that includes baseWord and k is equal to 0, smaller than, equal to, or higher than distinct collocs number
    WordStat stat6 = new WordStat(new String[]{"Raspberry", "LEMON!!  ", "  Lemon!!??", "&&&&lemon", " LEMON@@&& ", "strawberry!!@@", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Coconut!!??  "});
    assertArrayEquals(new String[]{}, stat6.mostCommonCollocs(0, "strawberry", 1));
    assertArrayEquals(new String[]{"coconut"}, stat6.mostCommonCollocs(1, "strawberry", 1));
    assertArrayEquals(new String[]{"coconut", "lemon", "strawberry"}, stat6.mostCommonCollocs(3, "strawberry", 1));
    assertArrayEquals(new String[]{"coconut", "lemon", "strawberry"}, stat6.mostCommonCollocs(8, "strawberry", 1));
    assertArrayEquals(new String[]{}, stat6.mostCommonCollocs(0, "lemon", -1));
    assertArrayEquals(new String[]{"lemon"}, stat6.mostCommonCollocs(1,"lemon", -1));
    assertArrayEquals(new String[]{"lemon", "raspberry", "strawberry"}, stat6.mostCommonCollocs(3,"lemon", -1));
    assertArrayEquals(new String[]{"lemon", "raspberry", "strawberry"}, stat6.mostCommonCollocs(10, "lemon", -1));
  }
  
  /**
   * Tests the mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) method of WordStat.
   */
  @Test
  public void testMostCommonCollocsExc(){
    
    
    /* Check when k is negative */
    WordStat stat1 = new WordStat(new String[]{"LEMON!!  ", "  Lemon!!??", "&&&&coconut", " LEMON. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Pineapple!!??  "});
    try{
      stat1.mostCommonCollocsExc(-5, "lemon", 1, new String[]{"strawberry", "coconut"});
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    
    
    /* Check when i is neither 1 nor -1 */
    WordStat stat2 = new WordStat(new String[]{"LEMON!!  ", "raspberry!", "  DURIAN!!??", "&&lemon%%", " LEMON@. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "COCONUT%. ", ";Lemon, ", "(STRAWBERRY)@&", "Durian!!??  "});
    try{
      stat2.mostCommonCollocsExc(3, "strawberry", 15, new String[]{"lemon", "raspberry"});
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    
    
    /* Check when k is not negative and i is either 1 or -1 */
    // When array is empty
    WordStat stat3 = new WordStat(new String[]{});
    assertArrayEquals(new String[]{}, stat3.mostCommonCollocsExc(5, "cherry", 1, new String[]{"banana, coconut"}));
    // When array is not empty, check when array exclusions is empty
    WordStat stat5 = new WordStat(new String[]{"STRAWBERRY!!  ", "PEAR@@", "Strawberry", "raspberry!", "  Durian!!??", "&&mango%%", " coconut@. ", "lime!!@@", "lemon@@&&", "Strawberry??!! ", "COCONUT%. ", ";Lemon, ", "(STRAWBERRY)@&", "Durian!!??  "});
    assertArrayEquals(new String[]{"coconut", "durian", "pear"}, stat5.mostCommonCollocsExc(3, "strawberry", 1, new String[]{}));
    // When array is not empty, check when array exclusions is not empty
    WordStat stat6 = new WordStat(new String[]{"STRAWBERRY!!  ", "lemon!", "  Lemon!!??", "&&mango%%", "lemon!", " coconut@. ", "lime!!@@", "lemon@@&&", "Strawberry??!! ", "COCONUT%. ", ";Lemon, ", "(STRAWBERRY)@&", "Durian!!??  ", "  LEMON!!"});
    assertArrayEquals(new String[]{"coconut", "lemon"}, stat6.mostCommonCollocsExc(2, "lemon", -1, new String[]{"durian", "mango"}));
  }
  
  /**
   * Tests the generateWordString(int k, String startWord) method of WordStat.
   */
  @Test
  public void testGenerateWordString(){
    
    
    /* Check when k is negative */
    WordStat stat1 = new WordStat(new String[]{"LEMON!!  ", "  Lemon!!??", "&&&&coconut", " LEMON. ", "strawberry!!@@", "lemon@@&&", "Strawberry??!! ", "LEMON. ", ";Lemon, ", "(STRAWBERRY)@&", "Pineapple!!??  "});
    try{
      stat1.generateWordString(-8, "strawberry");
    }
    catch(IllegalArgumentException exception){
    }
    catch(Exception exception){
      fail("Did not throw IllegalArgumentException exception");
    }
    
    
    /* Check when k is negative */
    // When array is empty, check with k being equal to 0 or positive
    WordStat stat2 = new WordStat(new String[]{});
    assertEquals("", stat2.generateWordString(0, "lemon"));
    assertEquals("", stat2.generateWordString(6, "pear"));
    // When array is not empty, check with k being equal to 0 or positive, with word string generated has 1 word or more than 1 words
    WordStat stat3 = new WordStat(new String[]{" CRICKET!@", "football", "Cricket!!  ", "football;", " tennis? ", "VOLLEYBALL", "volleyBALL!!", "jogging!@", "footBALL. ", "Tennis;& ", "Volleyball!", " Jogging!!%%@@", "  Cricket!!??", "Basketball!%", "@Swimming."});
    assertEquals("basketball", stat3.generateWordString(1, "basketball"));
    assertEquals("cricket football tennis volleyball jogging", stat3.generateWordString(5, "cricket"));
  }
}