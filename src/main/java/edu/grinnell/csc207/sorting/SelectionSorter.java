package edu.grinnell.csc207.sorting;

import java.util.Comparator;

import edu.grinnell.csc207.main.SortTools;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class SelectionSorter<T> implements Sorter<T> {
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
    public SelectionSorter(Comparator<? super T> comparator) {
        this.order = comparator;
    } // SelectionSorter(Comparator)

    // +---------+-----------------------------------------------------
    // | Methods |
    // +---------+
    /**
     * Selects the smallest value in the array.
     *
     * @param values The array being searched.
     * @return The index of the arrays smallest value.
     */
    private int select(T[] values, int unsorted) {
        /* Initialize a minimum value. */
        int minIndex = unsorted;
        for (int i = unsorted + 1; i < values.length; i++) {
            /* Find if there is a new minimum value and set the minimum index to that. */
            if (order.compare(values[minIndex], values[i]) > 0) {
                minIndex = i;
            } //if
        } //for
        return minIndex;
    } //select(T[] values)

    /**
     * Sort an array in place using selection sort.
     *
     * @param values an array to sort.
     *
     * @post The array has been sorted according to some order (often one given
     * to the constructor).
     * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
     * values[i]) &lt;= 0
     */
    @Override
    public void sort(T[] values) {
        for (int i = 0; i < values.length; i++) {
            SortTools.swap(values, i, select(values, i));
        } //for
    } // sort(T[])
} // class SelectionSorter
