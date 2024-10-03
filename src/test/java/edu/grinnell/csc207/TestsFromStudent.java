package edu.grinnell.csc207;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.NullKeyException;
import edu.grinnell.csc207.util.KeyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A place for you to put your own tests (beyond the shared repo).
 *
 * @author Yash Malik
 */
public class TestsFromStudent {
/**
 * A test of basic set and get functionality.
 *
 * @throws NullKeyException
 */
@Test
public void testSetAndGet() throws NullKeyException {
  AssociativeArray<String, Integer> arr = new AssociativeArray<>();
  arr.set("One", 1);
  arr.set("Two", 2);

  try {
    assertEquals(Integer.valueOf(1), arr.get("One"));
    assertEquals(Integer.valueOf(2), arr.get("Two"));
  } catch (Exception e) {
    fail("Failed to retrieve values for keys set in the associative array");
  }
}

  /**
   * A test for handling null keys and key not found scenarios.
   *
   * @throws NullKeyException
   */
  @Test
  public void testNullKeyAndKeyNotFound() {
    AssociativeArray<String, String> arr = new AssociativeArray<>();

    // Test setting a null key
    try {
      arr.set(null, "ShouldFail");
      fail("Setting a null key did not throw NullKeyException");
    } catch (NullKeyException e) {
      // Expected exception
    }

    // Test retrieving a key that doesn't exist
    try {
      arr.get("NonExistentKey");
      fail("Retrieving a non-existent key did not throw KeyNotFoundException");
    } catch (KeyNotFoundException e) {
      // Expected exception
    }
  }

  /**
   * A test for the remove functionality.
   *
   * @throws NullKeyException
   */
  @Test
  public void testRemoveFunctionality() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<>();
    arr.set("Key1", "Value1");
    arr.set("Key2", "Value2");

    // Ensure values are present before removal
    try {
      assertEquals("Value1", arr.get("Key1"));
      assertEquals("Value2", arr.get("Key2"));
    } catch (Exception e) {
      fail("Values should be retrievable before removal");
    }

    // Remove a key and check its value
    arr.remove("Key1");
    try {
      assertEquals("Value2", arr.get("Key2")); // Ensure Key2 is still there
      arr.get("Key1"); // This should throw an exception
      fail("Removing Key1 did not throw KeyNotFoundException");
    } catch (KeyNotFoundException e) {
      // Expected exception for Key1
    } catch (Exception e) {
      fail("An unexpected exception occurred when retrieving Key1 after removal");
    }
  }
}
