package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class MergeSorter<T> implements Sorter<T> {
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
    public MergeSorter(Comparator<? super T> comparator) {
        this.order = comparator;
    } // MergeSorter(Comparator)

    // +---------+-----------------------------------------------------
    // | Methods |
    // +---------+
    /**
     * Merges two sorted arrays together
     *
     * @param vals An array with both halves sorted
     * @param tmp A temporary array
     * @param low A lowpoint
     * @param mid A midpoint
     * @param high A highpoint
     */
    public void merge(T[] vals, T[] tmp, int low, int mid, int high) {
        int i = low;
        int k = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (order.compare(vals[i], vals[j]) >= 0) {
                tmp[k++] = vals[j++];
            } else {
                tmp[k++] = vals[i++];
            } //if/else
        } //while
        while (j <= high) {
            tmp[k++] = vals[j++];
        } //while
        while (i <= mid) {
            tmp[k++] = vals[i++];
        } //while

        /* Copy the array over to the original array */
        for (int l = low; l <= high; l++) {
            vals[l] = tmp[l];
        } //for
    } //merge

    public void sortHelper(T[] values, T[] tmp, int low, int high) {
        if (low < high) {
            /* Find the midpoint */
            int mid = (low + high) / 2;

            /* Sort the two halves. */
            sortHelper(values, tmp, low, mid);
            sortHelper(values, tmp, mid + 1, high);

            /* Merge em together */
            merge(values, tmp, low, mid, high);
        }
    }

    /**
     * Sort an array in place using merge sort.
     *
     * @param values an array to sort.
     *
     * @post The array has been sorted according to some order (often one given
     * to the constructor).
     * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
     * values[i]) &lt;= 0
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort(T[] values) {
        T[] arr = (T[]) new Object[values.length];
        sortHelper(values, arr, 0, values.length - 1);
    } // sort(T[])
} // class MergeSorter
