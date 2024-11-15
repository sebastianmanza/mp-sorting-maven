package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

import edu.grinnell.csc207.main.SortTools;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class Quicksorter<T> implements Sorter<T> {
    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The way in which elements are ordered.
     */
    Comparator<? super T> order;

    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+
    /**
     * Create a sorter using a particular comparator.
     *
     * @param comparator The order in which elements in the array should be
     * ordered after sorting.
     */
    public Quicksorter(Comparator<? super T> comparator) {
        this.order = comparator;
    } // Quicksorter(Comparator)

    // +---------+-----------------------------------------------------
    // | Methods |
    // +---------+
    /**
     * Partitions an array into items equal to a random median, greater than it,
     * or less than it.
     *
     * @param values An array of values.
     * @param low The low index of the array.
     * @param high The end index of the array.
     * @return A length 2 array with the index of the beginning of medians at
     * one index and end of medians at the other.
     */
    private int[] partition(T[] values, int low, int high) {

        /* Create a random pivot */
        Random rand = new Random();
        int random = low + rand.nextInt(high - low);
        T pivot = values[random];
        /* Set the proper DNF values (lower = red, i = white, higher = blue) */
        int lower = low;
        int higher = high;
        int i = low;

        /* Swap stuff around to sort around the pivot */
        while (i <= higher) {
            int cmp = order.compare(values[i], pivot);
            if (cmp < 0) {
                /* If the element is less than the pivot, swap the pivot and the lower value and increment. */
                SortTools.swap(values, i, lower);
                lower++;
                i++;
            } else if (cmp > 0) {
                /* if the element is greater than the pivot, swap and decrement the higher index */
                SortTools.swap(values, i, higher);
                higher--;
            } else {
                /* otherwise just increment the unsorted part. */
                i++;
            } //if/else
        } //while
        return new int[]{lower, higher};
    } //partition(T[], int, int)

    /**
     * Sort an array in place using Quicksort.
     *
     * @param values an array to sort.
     *
     * @post The array has been sorted according to some order (often one given
     * to the constructor).
     * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
     * values[i]) &lt;= 0
     */
    public void sort(T[] values) {
        sortHelper(values, 0, values.length - 1);
    } //sort(T[])

    /**
     * A helper method to implement recursive quicksort.
     *
     * @param values The list of values.
     * @param low The low index of the part to be sorted.
     * @param high The high index of the part to be sorted.
     */
    public void sortHelper(T[] values, int low, int high) {
        if (low < high) {
            int[] partitionIndex = partition(values, low, high);
            sortHelper(values, low, partitionIndex[0] - 1);
            sortHelper(values, partitionIndex[1] + 1, high);
        } //if
    } // sort(T[])
} // class Quicksorter
