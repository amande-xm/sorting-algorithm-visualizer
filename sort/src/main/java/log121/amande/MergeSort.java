package log121.amande;

public class MergeSort extends SortAlgorithm {

    public MergeSort(int[] integers) {
        super(integers);
    }

    // method to determine the pivot of the list
    public int divide(int[] array, int low, int high) {
        return low + (high - low) / 2;
    }

    @Override
    protected int getLeftSubarrayEnd(int splitIndex) {
        return splitIndex;
    }

    @Override
    protected int getRightSubarrayStart(int splitIndex) {
        return splitIndex + 1;
    }

    public void merge(int[] array, int low, int middle, int high) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - low + 1;
        int n2 = high - middle;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = array[low + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[middle + 1 + j];

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = low;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            recordSnapshot(array);
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            recordSnapshot(array);
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            recordSnapshot(array);
        }

        return;
    }

}
