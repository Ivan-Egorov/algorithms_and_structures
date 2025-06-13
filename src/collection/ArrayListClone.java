package collection;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The ArrayListClone Class is a collection for storing objects.
 * Its main methods are similar with ArrayList methods.
 * However, ArrayListClone doesn't implement Iterable, Collection or List interfaces.
 */
public class ArrayListClone<A> {
    private A[] list;
    private int count;

    public ArrayListClone() {
        list = (A[]) new Object[10];
        count = 0;
    }

    public ArrayListClone(int initialSize) {
        list = (A[]) new Object[initialSize];
        count = 0;
    }

    public ArrayListClone(A[] aArray) {
        list = Arrays.copyOf(aArray, (aArray.length * 3 / 2 + 1));
        count = aArray.length;
    }

    public int size() {
        return count;
    }

    public void add(A aToAdd) {
        if (list.length < count + 1) {
            list = Arrays.copyOf(list, (list.length * 3 / 2 + 1));
        }
        list[count] = aToAdd;
        count++;
    }

    public void add(A aToAdd, int index) {
        if (index > count) {
            System.out.println("Error: index must not exceed array length.");
            return;
        }
        if (list.length < count + 1) {
            list = Arrays.copyOf(list, (list.length * 3 / 2 + 1));
        }
        A[] listCopy = Arrays.copyOf(list, count);
        Arrays.fill(list, null);
        System.arraycopy(listCopy, 0, list, 0, index);
        list[index] = aToAdd;
        System.arraycopy(listCopy, index, list, index + 1, count - index);
        count++;
        listCopy = null;
    }

    public void addArray(A[] arrayToAdd) {
        if (list.length < (count + 1 + arrayToAdd.length)) {
            list = Arrays.copyOf(list, ((list.length + arrayToAdd.length) * 3 / 2 + 1));
        }
        System.arraycopy(arrayToAdd, 0, list, count, arrayToAdd.length);
        count += arrayToAdd.length;
    }

    public void remove(A aToRemove) {
        for (int i = 0; i < count; i++) {
            if (list[i] == aToRemove) {
                A[] listCopy = Arrays.copyOf(list, count);
                Arrays.fill(list, null);
                System.arraycopy(listCopy, 0, list, 0, i);
                if (i != count - 1) {
                    System.arraycopy(listCopy, i + 1, list, i, count - i - 1);
                }
                count--;
                listCopy = null;
                break;
            }
        }
    }

    public void remove(int index) {
        if (index >= count) {
            System.out.println("Error: index must not exceed array length.");
            return;
        }
        A[] listCopy = Arrays.copyOf(list, count);
        Arrays.fill(list, null);
        System.arraycopy(listCopy, 0, list, 0, index);
        if (index != count - 1) {
            System.arraycopy(listCopy, index + 1, list, index, count - index - 1);
        }
        count--;
        listCopy = null;
    }

    public void clear() {
        Arrays.fill(list, null);
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void trimToSize() {
        list = Arrays.copyOf(list, count);
    }

    public Stream<A> stream() {
        this.trimToSize();
        return Arrays.stream(list);
    }

    public A[] toArray() {
        this.trimToSize();
        return list;
    }

    public boolean contains(A aObject) {
        for (int i = 0; i < count; i++) {
            if (list[i] == aObject) {
                return true;
            }
        }
        return false;
    }

    public A get(int index) {
        if (index >= count) {
            System.out.println("Error: index must not exceed array length.");
            return null;
        }
        return list[index];
    }
}
