import collection.*;
import search.*;
import sort.*;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Sorters
        System.out.println("testing sorters...");
        int[] numbers = {11, -5, 0, 4, -7, 13};
//        SelectionSort.sort(numbers);
//        BubbleSort.sort(numbers);
//        MergeSort.sort(numbers);
//        QuickSort.sort(numbers);
        InsertionSort.sort(numbers);
        Arrays.stream(numbers).forEach(System.out::println);
        System.out.println();

        // Binary search
        System.out.println("testing BinarySearch...");
        BinarySearch search = new BinarySearch();
        System.out.println("index: " + search.findIndex(numbers, 13));
        System.out.println();

        // Binary search recursion
        System.out.println("testing BinarySearch recursion type (upgrade)...");
        BinarySearchRecursion searchRecursionB = new BinarySearchRecursion();
        System.out.println("index: " + searchRecursionB.findIndex(numbers, 13));
        System.out.println();

        // Binary search (Comparable)
        System.out.println("testing BinarySearch (Comparable type)...");
        BinarySearchComparable<String> searchComparable = new BinarySearchComparable<>();
        List<String> strList = Stream.of("lemon", "orange", "apple", "banana").sorted().toList();
        strList.forEach(System.out::println);
        System.out.println("index: " + searchComparable.findIndex(strList, "orange"));
        System.out.println();

        // LinkedListCopy
        System.out.println("testing LinkedListClone...");
        LinkedListClone<String> linkedCopy = new LinkedListClone<>();
        linkedCopy.add("first");
        linkedCopy.addFirst("new first");
        linkedCopy.addLast("new last");
        linkedCopy.add(0, "zero");
        linkedCopy.add(1, "one");
        linkedCopy.add(5, "five");
//        linkedCopy.add(7, "seven");
        System.out.println(linkedCopy.contains("zero"));
        System.out.println(linkedCopy.contains("one"));
        System.out.println(linkedCopy.contains("five"));
        System.out.println(linkedCopy.contains("fake request"));
        System.out.println(linkedCopy.indexOf("zero"));
        System.out.println(linkedCopy.get(0));
        System.out.println(linkedCopy.indexOf("one"));
        System.out.println(linkedCopy.get(1));
        System.out.println(linkedCopy.indexOf("five"));
        System.out.println(linkedCopy.get(5));
        System.out.println(linkedCopy.indexOf("fake request"));
//        System.out.println(linkedCopy.get(-1));
        System.out.println(linkedCopy.getFirst());
        System.out.println(linkedCopy.getLast());
        System.out.println(linkedCopy.getSize());
        System.out.println();
        Object[] sArr = linkedCopy.toArray();
        Arrays.stream(sArr).forEach(System.out::println);
        System.out.println();
        System.out.println(linkedCopy.remove("fake request"));
        System.out.println(linkedCopy.remove("zero"));
        System.out.println(linkedCopy.remove("five"));
        System.out.println(linkedCopy.getFirst());
        System.out.println(linkedCopy.getLast());
        System.out.println(linkedCopy.getSize());
        linkedCopy.clear();
        System.out.println(linkedCopy.getSize());
        System.out.println();

        // HashMapClone
        System.out.println("testing HashMapClone...");
        HashMapClone<String, String> map = new HashMapClone<>(2);
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        map.put("orange", "апельсин");
        map.put("banana", "банан");
        System.out.println(map.get("banana"));
        map.put("banana", "бананас");
        System.out.println(map.get("banana"));
        map.put("apple", "яблоко");
        map.put("kiwi", "киви");
        map.put("pineapple", "ананас");
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(map.get("banana"));
        System.out.println(map.get("bananas"));
        System.out.println(map.get("kiwi"));
        System.out.println(map.get("avocado"));
        System.out.println(map.get("cherry"));
        System.out.println(map.remove("banana"));
        System.out.println(map.remove("avocado"));
        System.out.println(map.remove("orange"));
        System.out.println(map.remove("pineapple"));
//        map.clear();
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println();

        // TreeMapClone
        System.out.println("testing TreeMapClone...");
        TreeMapClone<String, String> dictionary = new TreeMapClone<>();
        System.out.println(dictionary.size());
        dictionary.put("orange", "апельсин");
        dictionary.put("banana", "бананас");
        dictionary.put("banana", "банан");
        dictionary.put("apple", "яблоко");
        dictionary.put("kiwi", "киви");
        dictionary.put("watermelon", "арбуз");
        dictionary.put("pineapple", "ананас");
        dictionary.put("radish", "редис");
        System.out.println(dictionary.size());
        System.out.println(dictionary.remove("pineapple"));
        System.out.println(dictionary.size());
        System.out.println("contains key orange - " + dictionary.containsKey("orange"));
        System.out.println("contains key banana - " + dictionary.containsKey("banana"));
        System.out.println("contains key apple - " + dictionary.containsKey("apple"));
        System.out.println("contains key kiwi - " + dictionary.containsKey("kiwi"));
        System.out.println("contains key watermelon - " + dictionary.containsKey("watermelon"));
        System.out.println("contains key pineapple - " + dictionary.containsKey("pineapple"));
        System.out.println("contains key radish - " + dictionary.containsKey("radish"));
        System.out.println("contains key FALSE! - " + dictionary.containsKey("FALSE!")); // false!
        System.out.println("contains value апельсин - " + dictionary.containsValue("апельсин"));
        System.out.println("contains value банан - " + dictionary.containsValue("банан"));
        System.out.println("contains value яблоко - " + dictionary.containsValue("яблоко"));
        System.out.println("contains value киви - " + dictionary.containsValue("киви"));
        System.out.println("contains value арбуз - " + dictionary.containsValue("арбуз"));
        System.out.println("contains value ананас - " + dictionary.containsValue("ананас"));
        System.out.println("contains value редис - " + dictionary.containsValue("редис"));
        System.out.println("contains value бананас - " + dictionary.containsValue("бананас")); // false!
        System.out.println("first key: " + dictionary.firstKey());
        System.out.println("last key: " + dictionary.lastKey());
        System.out.println("root: " + dictionary.getRoot());
        System.out.println(dictionary.get("orange"));
        System.out.println(dictionary.get("banana"));
        System.out.println(dictionary.get("apple"));
        System.out.println(dictionary.get("kiwi"));
        System.out.println(dictionary.get("watermelon"));
        System.out.println(dictionary.get("pineapple"));
        System.out.println(dictionary.get("radish"));
        System.out.println(dictionary.get("FALSE!")); // null!
        dictionary.clear();
        System.out.println(dictionary.size());
        System.out.println();
    }
}