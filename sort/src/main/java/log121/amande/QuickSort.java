package log121.amande;

public class QuickSort extends SortAlgorithm {

    public QuickSort(int[] integers) {
        super(integers);
    }

    // method to determine the pivot of the list
    public int divide(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                super.swap(array, i, j);
            }
        }

        super.swap(array, i + 1, high);
        return i + 1;
    }

    @Override
    protected int getLeftSubarrayEnd(int splitIndex) {
        return splitIndex - 1;
    }

    @Override
    protected int getRightSubarrayStart(int splitIndex) {
        return splitIndex + 1;
    }

    // method declaration necessary for the Method Template implementation but
    // serves no purpose
    public void merge(int[] array, int low, int middle, int high) {
        return;
    }

}
