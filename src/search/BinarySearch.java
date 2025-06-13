package search;

// Works with sorted arrays only!
public class BinarySearch {

    public BinarySearch() {
    }

    public int findIndex(int[] array, int numberToFind) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;
        int middle = (start + end) / 2;

        while (start <= end) {
            if (array[middle] == numberToFind) {
                return middle;
            } else if (array[middle] < numberToFind) {
                start = middle + 1;
                middle = (start + end) / 2;
            } else {
                end = middle - 1;
                middle = (start + end) / 2;
            }
        }
        return -1;
    }
}
