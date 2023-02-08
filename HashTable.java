import java.util.*;
// Class HashTable is the class for the hash table storing pairs of keys and values; indexes of pairs of keys and values in the hash table are generated from the hash function
public class HashTable{
  
  // table stores the table of HashTable instance
  private HashEntry[] table;
  
  // sizeOfTable stores the size (capacity) of the table
  private int sizeOfTable;
  
  // numberOfHashEntries stores number of entries in the table
  private int numberOfHashEntries = 0;
  
  // restrictingValueForLoadFactor stores the value that the load factor of the table should be kept lower than it
  private final double restrictingValueForLoadFactor = 0.5;
  
  /**
   * Initialize hash table with default size of 31.
   */
  public HashTable(){
    sizeOfTable = 31;
    table = new HashEntry[sizeOfTable];
  }
  
  /**
   * Initialize hash table with size specified in input or throw exception if input size is negative.
   * Time complexity: O(1)
   */
  public HashTable(int size) throws IllegalArgumentException{
    if(size < 0){
      throw new IllegalArgumentException("Size cannot be negative");
    }
    sizeOfTable = size;
    table = new HashEntry[sizeOfTable];
  }
  
  /**
   * Find the next prime of the input number.
   */
  private int nextPrime(int number){
    // If input number is smaller than 2, return 2
    if(number < 2){
      return 2;
    }
    // If input number is not smaller than 2, increase number by 1, then continue increasing number by 1 if number is divisible by a number in range from 2 to (number / 2)
    number = number + 1;
    for(int i = 2; i <= (number / 2); i = i + 1){
      if(number % i == 0){
        number = number + 1;
        i = 2;
      }
    }
    return number;
  }
  
  /**
   * Rehash the hash table with size being the next prime of twice the old table's size.
   * Time complexity: O(n)
   */
  private void rehash(){
    // oldSize stores the size of the old table
    int oldSize = sizeOfTable;
    // oldTable stores the old table
    HashEntry[] oldTable = table;
    // Change the size of table to the next prime of twice the old table's size
    sizeOfTable = nextPrime(oldSize * 2);
    // Change the table to new table with new size
    table = new HashEntry[sizeOfTable];
    // Put all hash entries from old table into the table
    for(int i = 0; i < oldSize; i = i + 1){
      if(oldTable[i] != null){
        put(oldTable[i].getKey(), oldTable[i].getValue());
      }
    }
  }
  
  /**
   * Put entry having input key and input value into the hash table by using linear probing for open addressing to deal with collisions.
   * Time complexity: approaching O(1), worst case: O(n) when having to rehash but this is rare
   */
  public void put(String key, int value){
    // If table's size is 0, expand table to have size of 31
    if(sizeOfTable == 0){
      sizeOfTable = 31;
      table = new HashEntry[sizeOfTable];
    }
    // i stores the key's hash value (the remainder of division of absolute value of key's hash code found by Java by table's size)
    int i = Math.abs(key.hashCode()) % sizeOfTable;
    // Start from entry with index i and continue with next slot until reaching empty slot and use this slot for the entry with input key and input value, increase table's size by 1
    while(table[i] != null){
      i = (i + 1) % sizeOfTable;
    }
    table[i] = new HashEntry(key, value);
    numberOfHashEntries = numberOfHashEntries + 1;
    // If ratio of number of hash entries after putting over table's size is higher than or equal to restrictingValueForLoadFactor, rehash the table
    if((numberOfHashEntries / sizeOfTable) >= restrictingValueForLoadFactor){
      rehash();
    }
  }
  
  /**
   * Put entry having input key, input value with hash code specified in input into the hash table by using linear probing for open addressing to deal with collisions.
   * Time complexity: approaching O(1), worst case: O(n) when having to rehash but this is rare
   */
  public void put(String key, int value, int hashCode){
    // If table's size is 0, expand table to have size of 31
    if(sizeOfTable == 0){
      sizeOfTable = 31;
      table = new HashEntry[sizeOfTable];
    }
    // i stores the key's hash value (the remainder of division of absolute value of key's hash code specified in input by table's size)
    int i = Math.abs(hashCode) % sizeOfTable;
    // Start from entry with index i and continue with next slot until reaching empty slot and use this slot for the entry with input key and input value, increase table's size by 1
    while(table[i] != null){
      i = (i + 1) % sizeOfTable;
    }
    table[i] = new HashEntry(key, value);
    numberOfHashEntries = numberOfHashEntries + 1;
    // If ratio of number of hash entries after putting over table's size is higher than or equal to restrictingValueForLoadFactor, rehash the table
    if((numberOfHashEntries / sizeOfTable) >= restrictingValueForLoadFactor){
      rehash();
    }
  }
  
  /**
   * Find index of the input key in the hash table by probing linearly given the key's hash value specified in input.
   * Time complexity: approaching O(1)
   */
  private int findKeyIndex(String key, int hashValue){
    // If table's size is 0, input key does not exist in the table, so return -1
    if(sizeOfTable == 0){
      return -1;
    }
    // i stores the key's hash value (the remainder of division of absolute value of key's input hashValue by table's size)
    int i = Math.abs(hashValue) % sizeOfTable;
    // iterationNumber is used to control number of iterations and return -1 if number of iterations is higher than table's size
    int iterationNumber = 0;
    // Start from entry with index i and contine with next slot, return i if key of entry with index i is equal to input key or return -1 if entry with index i is null
    while(table[i] != null){
      if(table[i].getKey().equals(key)){
        return i;
      }
      i = (i + 1) % sizeOfTable;
      iterationNumber = iterationNumber + 1;
      if(iterationNumber > sizeOfTable){
        return -1;
      }
    }
    return -1;
  }
  
  /**
   * If input key exists, update the value of the entry having input key in the hash table to input value; if input key does not exist, add entry with input key and input value to the hash table.
   * Time complexity: approaching O(1), worst case: O(n) when having to rehash but this is rare
   */
  public void update(String key, int value){
    // index stores value returned by findKeyIndex(String key, int hashValue) with hashValue being key's hash code found by Java
    int index = findKeyIndex(key, key.hashCode());
    // If index is -1, put entry with input key and input value into the table
    if(index == -1){
      put(key, value);
    }
    // Otherwise, set value of table's entry which has that index as input value
    else{
      table[index].setValue(value);
    }
  }
  
  /**
   * If input key exists, returns the value of the entry having input key in the hash table; if input key does not exist, return -1
   * Time complexity: approaching O(1)
   */
  public int get(String key){
    // index stores value returned by findKeyIndex(String key, int hashValue) with hashValue being key's hash code found by Java
    int index = findKeyIndex(key, key.hashCode());
    // If index is -1, return -1
    if(index == -1){
      return -1;
    }
    // Otherwise, return value of table's entry with that index
    else{
      return table[index].getValue();
    }
  }
  
  /**
   * By using hash code specified in input, returns the value of the entry having input key in the hash table if input key exists or return -1 if input key does not exist
   * Time complexity: approaching O(1)
   */
  public int get(String key, int hashCode){
    // index stores value returned by findKeyIndex(String key, int hashValue) with hashValue being input hashCode
    int index = findKeyIndex(key, hashCode);
    // If index is -1, return -1
    if(index == -1){
      return -1;
    }
    // Otherwise, return value of table's entry with that index
    else{
      return table[index].getValue();
    }
  }
  
  /**
   * Sort clone entries of entries in the hash table in decreasing order of entries' values and in alphabetical order with clone entries having same values
   * Time complexity: approaching O(nlogn)
   */
  public HashEntry[] sortCloneEntriesAlphabetically(){
    // HashEntry[] sortedCloneEntries is used to store only clones of existing table's hash entries in decreasing order of hash entries' values and in alphabetical order with clone entries having same values
    HashEntry[] sortedCloneEntries = new HashEntry[numberOfHashEntries];
    // Using index, add each clone entries to the next empty slot of sortedCloneEntries
    int index = 0;
    for(int i = 0; i < sizeOfTable; i = i + 1){
      if(table[i] != null){
        sortedCloneEntries[index] = new HashEntry(new String(table[i].getKey()), table[i].getValue());
        index = index + 1;
      }
    }
    // Sort all clones in sortedCloneEntries in decreasing order of hash entries' values and in alphabetical order with clone entries having same values, and then return sortedCloneEntries
    Arrays.sort(sortedCloneEntries, (entry1, entry2) -> {
      return (entry2.getValue() == entry1.getValue()) ? (entry1.getKey().compareTo(entry2.getKey())) : (entry2.getValue() - entry1.getValue());
    });
    return sortedCloneEntries;
  }
  
  /**
   * Sort clone entries of entries in the hash table in decreasing order of entries' values and in reverse order of alphabetical order with clone entries having same values
   * Time complexity: approaching O(nlogn)
   */
  public HashEntry[] sortCloneEntriesAlphabeticallyReversed(){
    /**
     * HashEntry[] sortedCloneEntriesAlphabeticallyReversed is used to store only clones of existing table's hash entries in decreasing order of hash entries' values 
     * and in reverse order of alphabetical order with clone entries having same values
     */
    HashEntry[] sortedCloneEntriesAlphabeticallyReversed = new HashEntry[numberOfHashEntries];
    // Using index, add each clone entries to the next empty slot of sortedCloneEntriesAlphabeticallyReversed
    int index = 0;
    for(int i = 0; i < sizeOfTable; i = i + 1){
      if(table[i] != null){
        sortedCloneEntriesAlphabeticallyReversed[index] = new HashEntry(new String(table[i].getKey()), table[i].getValue());
        index = index + 1;
      }
    }
    /**
     * Sort all clones in sortedCloneEntriesAlphabeticallyReversed in decreasing order of hash entries' values
     * and in reverse order of alphabetical order with clone entries having same values, and then return sortedCloneEntriesAlphabeticallyReversed
     */
    Arrays.sort(sortedCloneEntriesAlphabeticallyReversed, (entry1, entry2) -> {
      return (entry2.getValue() == entry1.getValue()) ? (entry2.getKey().compareTo(entry1.getKey())) : (entry2.getValue() - entry1.getValue());
    });
    return sortedCloneEntriesAlphabeticallyReversed;
  }
}