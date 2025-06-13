package sort;

/**
 * O(n^2)
 */
public class BubbleSort {

    public static void sort(int[] array) {
        final int LENGTH = array.length;
        int temp;
        boolean isSorted;

        for (int i = 1; i < LENGTH; i++) {
            isSorted = true;
            for (int j = 0; j < LENGTH - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }
}
