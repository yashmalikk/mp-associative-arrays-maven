package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;
import java.util.Arrays;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author Yash Malik
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    this.pairs = (KVPair<K, V>[]) newInstance(KVPair.class, DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   *
   * @return a new copy of the array
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> newArray = new AssociativeArray<>();
    newArray.pairs = Arrays.copyOf(this.pairs, this.size);
    newArray.size = this.size;
    return newArray;
  } // clone()

  /**
   * Convert the array to a string.
   *
   * @return a string of the form "{Key0:Value0, Key1:Value1, ... KeyN:ValueN}"
   */
  public String toString() {
    StringBuilder returnVal = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      if (i > 0) {
        returnVal.append(", ");
      } // create , between key-value pair
      returnVal.append(pairs[i].key).append(":").append(pairs[i].value);
    } // print "key:value".
    return "{" + returnVal + "}";
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   *
   * @param key
   *   The key whose value we are setting.
   * @param value
   *   The value of that key.
   *
   * @throws NullKeyException
   *   If the client provides a null key.
   */
  public void set(K key, V value) throws NullKeyException {
    if (key == null) {
      throw new NullKeyException("Key cannot be null");
    } //NullKeyException.
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i].value = value; // Update existing key
        return;
      } // update all keys.
    } // loop for entire dictionary.
    if (size >= pairs.length) {
      expand(); // Expand array if necessary
    } // check if we need to expand.
    pairs[size++] = new KVPair<>(key, value); // Add new key/value pair
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @param key
   *   A key
   *
   * @throws KeyNotFoundException
   *   when the key is null or does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    if (key == null) {
      throw new KeyNotFoundException("Key cannot be null");
    }
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        return pairs[i].value;
      }
    }
    throw new KeyNotFoundException("Key not found: " + key);
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    if (key == null) {
      return false;
    }
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        return true;
      }
    }
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    if (key == null) {
      return; // Do nothing for null key
    }
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        // Shift elements left to remove this entry
        System.arraycopy(pairs, i + 1, pairs, i, size - i - 1);
        pairs[--size] = null; // Clear last entry
        return;
      }
    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   *
   * @param key
   *   The key of the entry.
   *
   * @throws KeyNotFoundException
   *   If the key does not appear in the associative array.
   */
  int find(K key) throws KeyNotFoundException {
    if (key == null) {
      throw new KeyNotFoundException("Key cannot be null");
    }
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException("Key not found: " + key);
  } // find(K)

  // +--------------+
  // | Inner Class  |
  // +--------------+
  
  private static class KVPair<K, V> {
    K key;
    V value;

    KVPair(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
} // class AssociativeArray
