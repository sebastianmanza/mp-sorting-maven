package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using a customized sorting algorithm. Uses insertion
 * sort if the array is less than size 50 or if 9/10 randomly selected pairs of
 * index's are already in order (since insertion sort works well for mostly
 * sorted arrays.)
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Sebastian Manza
 */
public class ManzaSebSorter<T> implements Sorter<T> {

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
    public ManzaSebSorter(Comparator<? super T> comparator) {
        this.order = comparator;
    } // SelectionSorter(Comparator)

    // +---------+-----------------------------------------------------
    // | Methods |
    // +---------+
    public void sort(T[] values) {
        if (values == null || values.length <= 1) {
            return; // No need to sort if empty or single-element array
        } //if

        // Use MergeSort or QuickSort for larger arrays
        if (values.length > 50) {
            int orderedPairs = countOrderedPairs(values);
            // If most pairs are ordered, use Insertion Sort
            if (orderedPairs >= 9) {
                insertionSort(values);
            } else {
                // Use MergeSort for large arrays
                mergeSort(values, 0, values.length - 1);
            } //if/else
        } else {
            // Use Insertion Sort for smaller arrays
            insertionSort(values);
        } //if/else
    } //sort

    // Count how many of the 10 random pairs are ordered
    private int countOrderedPairs(T[] values) {
        Random rand = new Random();
        int orderedCount = 0;

        for (int i = 0; i < 10; i++) {
            int idx1 = rand.nextInt(values.length);
            int idx2 = rand.nextInt(values.length);
            while (idx1 == idx2) {  // Avoid same indices
                idx2 = rand.nextInt(values.length);
            } //while

            if (order.compare(values[idx1], values[idx2]) < 0) {
                orderedCount++;
            } //if
        } //for

        return orderedCount;
    } //countOrderedPairs

    // Insertion Sort algorithm for generic arrays
    private void insertionSort(T[] values) {
        for (int i = 1; i < values.length; i++) {
            T key = values[i];
            int j = i - 1;

            // Move elements of values[0..i-1] that are greater than key
            while (j >= 0 && order.compare(values[j], key) > 0) {
                values[j + 1] = values[j];
                j = j - 1;
            } //while
            values[j + 1] = key;
        } //for
    } //insertionSort(T[])

    // Merge Sort algorithm for generic arrays
    private void mergeSort(T[] values, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort the two halves
            mergeSort(values, left, mid);
            mergeSort(values, mid + 1, right);

            // Merge the sorted halves
            merge(values, left, mid, right);
        } //if
    } //mergeSort(T[], int, int)

    // Merge the two sorted halves
    private void merge(T[] values, int left, int mid, int right) {
        // Find the size of the two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        T[] leftArray = (T[]) new Object[n1];
        T[] rightArray = (T[]) new Object[n2];

        // Copy data into temp arrays
        System.arraycopy(values, left, leftArray, 0, n1);
        System.arraycopy(values, mid + 1, rightArray, 0, n2);

        // Merge the temp arrays back into the original array
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (order.compare(leftArray[i], rightArray[j]) <= 0) {
                values[k++] = leftArray[i++];
            } else {
                values[k++] = rightArray[j++];
            } //if/else
        } //while

        // Copy any remaining elements of leftArray[]
        while (i < n1) {
            values[k++] = leftArray[i++];
        } //while

        // Copy any remaining elements of rightArray[]
        while (j < n2) {
            values[k++] = rightArray[j++];
        } //while
    } //merge
}//class ManzaSebSorter
