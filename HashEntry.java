// Class HashEntry is used for the hash entry of the hash table
public class HashEntry{
  
  // key is used to store key of the hash entry
  private String key;
  
  // value is used to store value of the hash entry
  private int value;
  
  /**
   * Initialize the hash entry with both key and value specified in input.
   * Time complexity: O(1)
   */
  public HashEntry(String key, int value){
    this.key = key;
    this.value = value;
  }
  
  /**
   * Return the key of the hash entry.
   * Time complexity: O(1)
   */
  public String getKey(){
    return key;
  }
  
  /**
   * Return the value of the hash entry.
   * Time complexity: O(1)
   */
  public int getValue(){
    return value;
  }
  
  /**
   * Set the hash entry's value as the value from input.
   * Time complexity: O(1)
   */
  public void setValue(int value){
    this.value = value;
  }
}