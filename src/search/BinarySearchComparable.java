package search;

import java.util.List;

// Works with sorted arrays only!
public class BinarySearchComparable<A extends Comparable<A>> {

    public BinarySearchComparable() {
    }

    public int findIndex(List<A> list, A objectToFind) {
        if (list == null || objectToFind == null || list.isEmpty()) {
            return -1;
        }

        int start = 0;
        int end = list.size() - 1;
        int middle = (start + end) / 2;

        while (start <= end) {
            if (list.get(middle).compareTo(objectToFind) == 0) {
                return middle;
            } else if (list.get(middle).compareTo(objectToFind) < 0) {
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
