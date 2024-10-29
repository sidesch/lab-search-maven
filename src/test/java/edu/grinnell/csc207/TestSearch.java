package edu.grinnell.csc207;

import java.util.Arrays;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static  org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Tests of our search methods.
 *
 * @author Samuel A. Rebelsky
 */
public class TestSearch {
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * A string version of a call to binary search.
   *
   * @param values The array.
   * @param val The value we're searching for.
   *
   * @return The string "binarySearch(values, val)"
   */
  String bsCall(int[] values, int val) {
    return String.format("binarySearch(%s, %d)", Arrays.toString(values), val);
  } // bsCall

  /**
   * Assert that a search for a particular value finds the value at that index.
   *
   * @param expected The expected index.
   * @param values The array we're searching.
   * @param val The value we're searching for.
   */
  void assertBinarySearchFinds(int expected, int[] values, int val) throws Exception {
    assertEquals(expected, SearchUtils.binarySearch(values, val), () -> bsCall(values, val));
  } // assertBinarySearchFinds(int, int[], int)

  /**
   * Assert that a search for a particular value fails (hopefully, because the value * is not in the
   * array).
   *
   * @param values The array we're searching.
   * @param val The value we're searching for.
   */
  void assertBinarySearchFails(int[] values, int val) throws Exception {
    assertThrows(Exception.class, () -> SearchUtils.binarySearch(values, val),
        () -> bsCall(values, val));
  } // assertBinarySearchFails()

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Searching the empty array should fail.
   */
  @Test
  void testBinarySearchEmpty() throws Exception {
    assertBinarySearchFails(new int[] {}, 0);
  } // testSearchEmpty()

  /**
   * Searching in a one-element array.
   */
  @Test
  void testBinarySearchOne() throws Exception {
    assertBinarySearchFinds(0, new int[] {5}, 5);
    assertBinarySearchFails(new int[] {5}, 0);
    assertBinarySearchFails(new int[] {5}, 10);
  } // testBinarySearchOne()

  /**
   * Searching in a two-element array.
   */
  @Test
  void testBinarySearchTwo() throws Exception {
    assertBinarySearchFinds(0, new int[] {7, 11}, 7);
    assertBinarySearchFinds(1, new int[] {7, 11}, 11);
    assertBinarySearchFails(new int[] {7, 11}, 0);
    assertBinarySearchFails(new int[] {7, 11}, 10);
    assertBinarySearchFails(new int[] {7, 11}, 20);
  } // testBinarySearchTwo()

  @Test
  void testBinarySearchThree() throws Exception {
    assertBinarySearchFinds(0, new int[] {1, 2, 3}, 1);
    assertBinarySearchFinds(1, new int[] {1, 2, 3}, 2);
    assertBinarySearchFinds(2, new int[] {1, 2, 3}, 3);
  } // testBinarySearchThree()

  @Test
  void testBinarySearchMultiple() throws Exception {
    int[] ints = new int[] {1, 1, 1, 2};
    assertEquals(1, ints[SearchUtils.binarySearch(ints, 1)]);
  } // testBinarySearchUnordered()


  @Test
  void comprehensiveBinarySearchTest() throws Exception{
    for (int i = 1; i<= 32; i++){
      int[] currentArray = new int[i];
      for (int n = 0; n<i;  n++){
        currentArray[n] = 2*n;
      }

      for (int n = 0; n<i; n++){
        assert(SearchUtils.binarySearch(currentArray, 2*i) == i);
        try{
          SearchUtils.binarySearch(currentArray, 2*i +1);
          fail("Odd number found");
        }catch (Exception e){
        }
      }
    }

  }
} // class TestSearch
