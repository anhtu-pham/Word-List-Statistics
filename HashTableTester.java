import org.junit.*;
import static org.junit.Assert.*;
public class HashTableTester {
  
  /**
   * Tests the put(String key, int value) method of HashTable.
   */
  @Test
  public void testPut1(){
    
    
    // Check put(String key, int value) with the hash table that has size of 0 initially
    HashTable hashTable1 = new HashTable(0);
    hashTable1.put("hi", 56);
    hashTable1.put("nnnnn", 18);
    assertEquals(18, hashTable1.get("nnnnn"));
    assertEquals(56, hashTable1.get("hi"));
    
    
    // Check put(String key, int value) with the hash table that will be rehashed after putting an entry
    HashTable hashTable2 = new HashTable(2);
    hashTable2.put("hello", 28);
    hashTable2.put("yes", 11);
    hashTable2.put("answer", 920);
    assertEquals(11, hashTable2.get("yes"));
    assertEquals(28, hashTable2.get("hello"));
    assertEquals(920, hashTable2.get("answer"));
    
    
    // Check put(String key, int value) with the hash table that is initialized by constructor with no inputs and will be rehashed after putting an entry
    HashTable hashTable3 = new HashTable();
    for(int i = 0; i < 31; i = i + 1){
      hashTable3.put(i + " ", (2 * i));
    }
    for(int i = 0; i < 31; i = i + 1){
      assertEquals((2 * i), hashTable3.get(i + " "));
    }
  }
  
  /**
   * Tests the put(String key, int value, int hashCode) method of HashTable.
   */
  @Test
  public void testPut2(){
    
    
    // Check put(String key, int value, int hashCode) with many overlapping hashCode values
    HashTable hashTable1 = new HashTable(20);
    hashTable1.put("nice", 19, -2);
    hashTable1.put("dolphin", 10, 9);
    hashTable1.put("river", 25, 4);
    hashTable1.put("ocean", 42, 4);
    hashTable1.put("side", 11, 6);
    hashTable1.put("ruler", 23, 0);
    hashTable1.put("goal", 65, 6);
    hashTable1.put("table", 251, 6);
    assertEquals(10, hashTable1.get("dolphin", 9));
    assertEquals(19, hashTable1.get("nice", -2));
    assertEquals(42, hashTable1.get("ocean", 4));
    assertEquals(11, hashTable1.get("side", 6));
    assertEquals(23, hashTable1.get("ruler", 0));
    assertEquals(251, hashTable1.get("table", 6));
    assertEquals(25, hashTable1.get("river", 4));
    assertEquals(65, hashTable1.get("goal", 6));
    assertEquals(-1, hashTable1.get("hi", 12));
    
    
    // Check put(String key, int value, int hashCode) with many overlapping hashCode values and with hash table that has size of 0 initially and is expanded to size of 31 when putting an entry
    HashTable hashTable2 = new HashTable(0);
    String[] keysForHashTable2 = new String[]{"straw", "rainbow", "bike", "cat", "tree", "umbrella", "paper", "box", "square", "rectangle", "calculus"};
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      hashTable2.put(keysForHashTable2[i], 5 * i, i / 3);
    }
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      assertEquals(5 * i, hashTable2.get(keysForHashTable2[i], i / 3));
    }
    
    
    // Check put(String key, int value, int hashCode) with many overlapping hashCode values and with hash table is initialized by constructor with no inputs
    HashTable hashTable3 = new HashTable();
    String[] keysForHashTable3 = new String[]{"conductor", "electron", "inductor", "book", "battery", "chair", "instruction", "lamp", "furniture", "light", "glasses", "trousers"};
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      hashTable3.put("the " + keysForHashTable3[i], (10 * i), (i / 5));
    }
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      assertEquals((10 * i), hashTable3.get("the " + keysForHashTable3[i], (i / 5)));
    }
    
    
    // Check put(String key, int value, int hashCode) using hash code values found by Java
    HashTable hashTable4 = new HashTable(50);
    String[] keysForHashTable4 = new String[]{"restaurant", "shoes", "roof", "order", "poster", "picture", "photographer", "people", "finance", "clean", "wildlife", "animal", "shrimp", "lobster", "crab"};
    for(int i = 0; i < keysForHashTable4.length; i = i + 1){
      hashTable4.put(keysForHashTable4[i], keysForHashTable4[i].length(), keysForHashTable4[i].hashCode());
    }
    for(int i = 0; i < keysForHashTable4.length; i = i + 1){
      assertEquals(keysForHashTable4[i].length(), hashTable4.get(keysForHashTable4[i], keysForHashTable4[i].hashCode()));
    }
  }
  
  /**
   * Tests the update(String key, int value) method of HashTable.
   */
  @Test
  public void testUpdate(){
    
    
    // Check when key has not been in the hash table and key has been in the hash table while updating
    HashTable hashTable1 = new HashTable(0);
    hashTable1.update("universe", 1600);
    hashTable1.update("sun", 200);
    hashTable1.update("lighten", 512);
    hashTable1.update("sun", 250);
    hashTable1.update("universe", 5700);
    assertEquals(5700, hashTable1.get("universe"));
    assertEquals(250, hashTable1.get("sun"));
    assertEquals(512, hashTable1.get("lighten"));
    
    
    // Check when some entries are updated again after all entries are updated
    HashTable hashTable2 = new HashTable();
    String[] keys = new String[]{"king", "queen", "castle", "bishop", "knight", "pawn", "chess", "board", "clock"};
    for(int i = 0; i < keys.length; i = i + 1){
      hashTable2.update(keys[i], i * 10);
      if(i > 3 && i < 8){
        hashTable2.update(keys[i], i * 25);
      }
    }
    for(int i = 0; i < keys.length; i = i + 1){
      if(i > 3 && i < 8){
        assertEquals(i * 25, hashTable2.get(keys[i]));
      }
      else{
        assertEquals(i * 10, hashTable2.get(keys[i]));
      }
    }
  }
  
  /**
   * Tests the get(String key) method of HashTable.
   */
  @Test
  public void testGet1(){
    
    
    // Check get(String key) with the hash table that is rehased after putting some entries into the hash table
    HashTable hashTable1 = new HashTable(8);
    hashTable1.put("green", 86);
    hashTable1.put("elephant", 100);
    hashTable1.update("lake", 25);
    hashTable1.put("blue", 42);
    hashTable1.update("small", 1);
    hashTable1.update("green", 26);
    hashTable1.put("brown", 24);
    hashTable1.update("lake", 40);
    assertEquals(100, hashTable1.get("elephant"));
    assertEquals(26, hashTable1.get("green"));
    assertEquals(42, hashTable1.get("blue"));
    assertEquals(1, hashTable1.get("small"));
    assertEquals(24, hashTable1.get("brown"));
    assertEquals(40, hashTable1.get("lake"));
    assertEquals(-1, hashTable1.get("sad"));
    
    
    // Check get(String key) with the hash table that is initialized as having size of 0 and after many entries are put in, some of which are updated
    HashTable hashTable2 = new HashTable(0);
    String[] keysForHashTable2 = new String[]{"cyan", "black", "pink", "aqua", "white", "violet", "bronze", "silver", "emerald"};
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      hashTable2.put(keysForHashTable2[i], 8 * i);
      if(i > 1 && i < 6){
        hashTable2.update(keysForHashTable2[i], 12 * i);
      }
    }
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      if(i > 1 && i < 6){
        hashTable2.update(keysForHashTable2[i], 12 * i);
      }
      else{
        assertEquals(8 * i, hashTable2.get(keysForHashTable2[i]));
      }
    }
    
    
    // Check get(String key) with the hash table that is initialized by constructor with no inputs
    HashTable hashTable3 = new HashTable();
    String[] keysForHashTable3 = new String[]{"building", "museum", "restaurant", "tower", "statue", "bridge", "guide", "photograph", "aquarium", "cinema"};
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      hashTable3.put(keysForHashTable3[i], (28 * i));
    }
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      assertEquals((28 * i), hashTable3.get(keysForHashTable3[i]));
    }
  }
  
  /**
   * Tests the get(String key, int hashCode) method of HashTable.
   */
  @Test
  public void testGet2(){
    
    
    // Check get(String key, int hashCode) after entries with many overlapping hashCode values are put into the hash table
    HashTable hashTable1 = new HashTable(20);
    hashTable1.put("period", 18, -6);
    hashTable1.put("media", 14, 8);
    hashTable1.put("article", 28, 9);
    hashTable1.put("store", 215, 5);
    hashTable1.put("electronic", 11, 5);
    hashTable1.put("bookstore", 152, 22);
    hashTable1.put("magazine", 45, 1);
    hashTable1.put("etymology", 251, 0);
    assertEquals(14, hashTable1.get("media", 8));
    assertEquals(18, hashTable1.get("period", -6));
    assertEquals(215, hashTable1.get("store", 5));
    assertEquals(11, hashTable1.get("electronic", 5));
    assertEquals(152, hashTable1.get("bookstore", 22));
    assertEquals(251, hashTable1.get("etymology", 0));
    assertEquals(28, hashTable1.get("article", 9));
    assertEquals(45, hashTable1.get("magazine", 1));
    assertEquals(-1, hashTable1.get("basis", 12));
    
    
    // Check get(String key, int hashCode) with many overlapping hashCode values and with hash table that has size of 0 initially and is expanded to size of 31 when putting an entry
    HashTable hashTable2 = new HashTable(0);
    String[] keysForHashTable2 = new String[]{"library", "motor", "cycle", "velocity", "accelerate", "speed", "conservation", "mechanics", "electricity", "magnetism"};
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      hashTable2.put(keysForHashTable2[i], 11 * i, i / 4);
    }
    for(int i = 0; i < keysForHashTable2.length; i = i + 1){
      assertEquals(11 * i, hashTable2.get(keysForHashTable2[i], i / 4));
    }
    
    
    // Check get(String key, int hashCode) with many overlapping hashCode values and with hash table is initialized by constructor with no inputs
    HashTable hashTable3 = new HashTable();
    String[] keysForHashTable3 = new String[]{"free", "energy", "thermodynamics", "material", "dielectric", "field", "force", "magnetic", "fundamental", "electric", "capacitor", "voltage"};
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      hashTable3.put(keysForHashTable3[i], (21 * i), (i / 2));
    }
    for(int i = 0; i < keysForHashTable3.length; i = i + 1){
      assertEquals((21 * i), hashTable3.get(keysForHashTable3[i], (i / 2)));
    }
    
    
    // Check get(String key, int hashCode) using hash code values found by Java
    HashTable hashTable4 = new HashTable(40);
    String[] keysForHashTable4 = new String[]{"coconut", "banana", "apple", "orange", "mango", "lemon", "lime", "guava", "pear", "pineapple", "cherry", "durian", "jackfruit", "raspberry"};
    for(int i = 0; i < keysForHashTable4.length; i = i + 1){
      hashTable4.put(keysForHashTable4[i], keysForHashTable4[i].length() * 31, keysForHashTable4[i].hashCode());
    }
    for(int i = 0; i < keysForHashTable4.length; i = i + 1){
      assertEquals(keysForHashTable4[i].length() * 31, hashTable4.get(keysForHashTable4[i], keysForHashTable4[i].hashCode()));
    }
  }
  
  /**
   * Tests the sortCloneEntriesAlphabetically() method of HashTable.
   */
  @Test
  public void testSortCloneEntriesAlphabetically(){
    HashTable hashTable = new HashTable();
    hashTable.put("tomato", 5);
    hashTable.put("onion", 6);
    hashTable.put("pumpkin", 2);
    hashTable.put("pineapple", 5);
    hashTable.put("lemon", 4);
    hashTable.put("banana", 2);
    HashEntry[] result = hashTable.sortCloneEntriesAlphabetically();
    assertEquals("onion", result[0].getKey());
    assertEquals(6, result[0].getValue());
    assertEquals("pineapple", result[1].getKey());
    assertEquals(5, result[1].getValue());
    assertEquals("tomato", result[2].getKey());
    assertEquals(5, result[2].getValue());
    assertEquals("lemon", result[3].getKey());
    assertEquals(4, result[3].getValue());
    assertEquals("banana", result[4].getKey());
    assertEquals(2, result[4].getValue());
    assertEquals("pumpkin", result[5].getKey());
    assertEquals(2, result[5].getValue());
  }
  
  /**
   * Tests the sortCloneEntriesAlphabeticallyReversed() method of HashTable.
   */
  @Test
  public void testSortCloneEntriesAlphabeticallyReversed(){
    HashTable hashTable = new HashTable();
    hashTable.put("potato", 4);
    hashTable.put("butter", 8);
    hashTable.put("vegetable", 1);
    hashTable.put("strawberry", 5);
    hashTable.put("raspberry", 4);
    hashTable.put("lime", 1);
    HashEntry[] result = hashTable.sortCloneEntriesAlphabeticallyReversed();
    assertEquals("butter", result[0].getKey());
    assertEquals(8, result[0].getValue());
    assertEquals("strawberry", result[1].getKey());
    assertEquals(5, result[1].getValue());
    assertEquals("raspberry", result[2].getKey());
    assertEquals(4, result[2].getValue());
    assertEquals("potato", result[3].getKey());
    assertEquals(4, result[3].getValue());
    assertEquals("vegetable", result[4].getKey());
    assertEquals(1, result[4].getValue());
    assertEquals("lime", result[5].getKey());
    assertEquals(1, result[5].getValue());
  }
}