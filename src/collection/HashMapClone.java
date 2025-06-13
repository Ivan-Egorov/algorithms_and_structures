package collection;

import java.util.Arrays;

/**
 * The HashMapClone Class is intended for storing key-value pairs.
 * Its main methods are similar with HashMap methods.
 * However, HashMapClone doesn't implement Map interface.
 */
public class HashMapClone<K, V> {

    private int capacity;
    private Node<K, V>[] table;
    private float loadFactor;
    private int size;

    public HashMapClone() {
        capacity = 16;
        loadFactor = 0.75f;
        table = new Node[capacity];
        size = 0;
    }

    public HashMapClone(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        loadFactor = 0.75f;
        table = new Node[this.capacity];
        size = 0;
    }

//    public HashMapClone(int capacity, float loadFactor) {
//        if (capacity < 1 || loadFactor <= 0) {
//            throw new IllegalArgumentException();
//        }
//        this.capacity = capacity;
//        this.loadFactor = loadFactor;
//        table = new Node[this.capacity];
//        size = 0;
//    }

    public int size() {
        return this.size;
    }

    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int index = hashFunc(node.hash);
        Node<K, V> current = table[index];

        if (current == null) {
            table[index] = node;
            size++;
            return;
        }

        Node<K, V> previous = null;
        while (current != null) {
            if (current.hash == node.hash && current.key.equals(key)) {
                current.value = value;
                return;
            }
            previous = current;
            current = current.next;
        }

        previous.next = node;
        size++;

        if (size > capacity * loadFactor) {
            System.out.println("changing capacity!");
            HashMapClone<K, V> newMap = new HashMapClone<>(capacity * 2);

            Node<K, V> currentNode;
            for (int i = 0; i < capacity; i++) {
                currentNode = table[i];
                while (currentNode != null) {
                    newMap.put(currentNode.key, currentNode.value);
                    currentNode = currentNode.next;
                }
            }

            this.capacity = newMap.capacity;
            this.table = newMap.table;
            this.size = newMap.size;
        }
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V remove(K key) {
        int hash = key.hashCode();
        int index = hashFunc(hash);
        Node<K, V> current = table[index];

        if (current == null) {
            return null;
        }

        Node<K, V> previous = null;
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hashFunc(hash);
        Node<K, V> current = table[index];

        if (current == null) {
            return null;
        }

        Node<K, V> previous = null;
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                return current.value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int index = hashFunc(hash);
        Node<K, V> current = table[index];

        if (current == null) {
            return false;
        }

        Node<K, V> previous = null;
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    private int hashFunc(int hash) {
        return Math.abs(hash) % capacity;
    }

    private class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            hash = key.hashCode();
            next = null;
        }
    }
}
