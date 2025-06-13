package sort;

/**
 * O(n^2)
 */
public class SelectionSort {

    public static void sort(int[] array) {
        final int LENGTH = array.length;
        int temp;
        int minIndex;

        for (int i = 0; i < LENGTH - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < LENGTH; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
}
