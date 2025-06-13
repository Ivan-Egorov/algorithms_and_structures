package sort;

public class InsertionSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) return;

        int temp;
        int j;
        for (int i = 0; i < array.length - 1; i++) {
            j = i;
            while (j != -1 && array[j] > array[j + 1]) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                j--;
            }
        }
    }
}
