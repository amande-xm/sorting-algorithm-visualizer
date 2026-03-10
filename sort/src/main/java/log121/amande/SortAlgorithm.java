package log121.amande;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SortAlgorithm {

    // list of the integers chosen by the user is directly stored inside the parent
    // class
    protected int[] integers;

    // list of the different steps of the list when being sorted
    private List<int[]> history = new ArrayList<>();

    public SortAlgorithm(int[] integers) {
        this.integers = integers;
        recordSnapshot(this.integers);
    }

    public List<int[]> getHistory() {
        return history;
    }

    // method to record the current state of the list inside the history
    protected void recordSnapshot(int[] array) {
        history.add(Arrays.copyOf(array, array.length));
    }

    // method that serves as an entry point for the sort method, that calls the
    // second one with the right parameters
    public void sort() {
        sort(this.integers, 0, this.integers.length - 1);
    }

    // sort method whuch is recursive and calls abstract methods redefined inside
    // the children classes
    protected int[] sort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = divide(array, low, high);

            sort(array, low, getLeftSubarrayEnd(pivot));
            sort(array, getRightSubarrayStart(pivot), high);

            merge(array, low, pivot, high);
        }
        return array;
    }

    // helper method
    protected void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
        recordSnapshot(array);
    }

    // declaration of abstract methods redefined inside the children classes
    // each represents a step of the sort algorithm

    protected abstract int divide(int[] arr, int low, int high);

    protected abstract void merge(int[] arr, int low, int middle, int high);

    protected abstract int getLeftSubarrayEnd(int splitIndex);

    protected abstract int getRightSubarrayStart(int splitIndex);

}
