package sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) return;

        int start = 0;
        int end = array.length - 1;
        sortUtil(array, start, end);
    }

    private static void sortUtil(int[] array, int start, int end) {
        if (start < end) {
            int pivot = sortByPivot(array, start, end);
            sortUtil(array, start, pivot - 1);
            sortUtil(array, pivot + 1, end);
        }
    }

    private static int sortByPivot(int[] array, int start, int end) {
        int pivot = start + (int)(Math.random() * (end - start));
        int pivotCount = 1;
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        int index = start;
        int indexCount = end;

        while(index <= indexCount) {
            if(index == pivot) {
                index++;
                continue;
            }

            if(arrayCopy[index] < arrayCopy[pivot]) {
                array[start] = arrayCopy[index];
                start++;
            } else if(arrayCopy[index] > arrayCopy[pivot]) {
                array[end] = arrayCopy[index];
                end--;
            } else {
                pivotCount++;
            }
            index++;
        }

        while(pivotCount > 0) {
            array[start] = arrayCopy[pivot];
            pivotCount--;
            start++;
        }

        return (start - 1);
    }
}
