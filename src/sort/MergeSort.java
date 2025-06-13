package sort;

/**
 * O(n log n)
 */
public class MergeSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) return;

        int start = 0;
        int end = array.length - 1;
        sortUtil(array, start, end);
    }

    private static void sortUtil (int[] array, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            sortUtil(array, start, middle);
            sortUtil(array, middle + 1, end);
            merge(array, start, middle, end);
        }
    }

    private static void merge(int[] array, int start, int middle, int end) {
        int index = start;
        int[] arrayA = new int[middle - start + 1];
        int indexA = 0;
        System.arraycopy(array, start, arrayA, 0, arrayA.length);
        int[] arrayB = new int[end - middle];
        int indexB = 0;
        System.arraycopy(array, middle + 1, arrayB, 0, arrayB.length);

        while(indexA < arrayA.length && indexB < arrayB.length) {
            if(arrayA[indexA] < arrayB[indexB]) {
                array[index] = arrayA[indexA];
                indexA++;
                index++;
            } else {
                array[index] = arrayB[indexB];
                indexB++;
                index++;
            }
        }
        while(indexA < arrayA.length) {
            array[index] = arrayA[indexA];
            indexA++;
            index++;
        }
        while(indexB < arrayB.length) {
            array[index] = arrayB[indexB];
            indexB++;
            index++;
        }
    }
}
