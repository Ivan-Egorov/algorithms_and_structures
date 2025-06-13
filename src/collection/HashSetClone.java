package collection;

/**
 * The HashSetClone Class is intended for storing unique keys.
 * Its main methods are similar with HashSet methods.
 * Realization is very simple and based on HashMapClone instance.
 */
public class HashSetClone<K> {

    private HashMapClone<K, Object> map;

    public HashSetClone() {
        map = new HashMapClone<>();
    }

    public HashSetClone(int capacity) {
        map = new HashMapClone<>(capacity);
    }

    public int size() {
        return map.size();
    }

    public void add(K key) {
        map.put(key, null);
    }

    public void clear() {
        map.clear();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void remove(K key) {
        map.remove(key);
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }
}
