package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of my Sorter.
 */
public class TestManzaSebSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new ManzaSebSorter<String>((x,y) -> x.compareTo(y));
    intSorter = new ManzaSebSorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestSelectionSorter
