import org.junit.*;
import static org.junit.Assert.*;
public class HashEntryTester{
  
  /**
   * Tests the getKey() method of HashEntry.
   */
  @Test
  public void testGetKey(){
    HashEntry entry = new HashEntry("hello", 12);
    // Check after initializing entry (key cannot be changed after initializing)
    assertEquals("hello", entry.getKey());
  }
  
  /**
   * Tests the getValue() method of HashEntry.
   */
  @Test
  public void testGetValue(){
    HashEntry entry = new HashEntry("hi", 20);
    // Check after initializing entry
    assertEquals(20, entry.getValue());
    entry.setValue(80);
    // Check after using setValue(int value)to set value for entry
    assertEquals(80, entry.getValue());
  }
  
  /**
   * Tests the setValue(int value) method of HashEntry.
   */
  @Test
  public void testSetValue(){
    HashEntry entry = new HashEntry("nice", 25);
    // Check before using setValue(int value) to set value for entry
    assertEquals(25, entry.getValue());
    entry.setValue(50);
    // Check after using setValue(int value) to set value for entry
    assertEquals(50, entry.getValue());
  }
}