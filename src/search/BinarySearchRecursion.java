package search;

// Works with sorted arrays only!
public class BinarySearchRecursion {

    public BinarySearchRecursion() {
    }

    public int findIndex(int[] array, int numberToFind) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;
        return findIndexUtil(array, numberToFind, start, end);
    }

    private int findIndexUtil(int[] array, int numberToFind, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;

        if (array[middle] == numberToFind) {
            return middle;
        } else if (array[middle] < numberToFind) {
            start = middle + 1;
        } else {
            end = middle - 1;
        }
        return findIndexUtil(array, numberToFind, start, end);
    }
}
