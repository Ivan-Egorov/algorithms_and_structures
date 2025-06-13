package search;

import java.util.*;

public class KnapsackProblem {
    public static void main(String[] args) {
        Item guitar = new Item("guitar", 1, 1500);
        Item jbl = new Item("JBL", 4, 3000);
        Item laptop = new Item("laptop", 3, 2000);
        Item iPhone = new Item("iPhone", 1, 1500);
        Item diamond = new Item("diamond", 2, 10000);
        List<Item> items = new ArrayList<>();
        items.add(guitar);
        items.add(jbl);
        items.add(laptop);
        items.add(iPhone);
        items.add(diamond);
        int dynamicBagResult = dynamicAlgorithm(items, 5, 1);
        System.out.println("Dynamic algorithm: " + dynamicBagResult);
        System.out.println();
        List<Item> greedyBag = greedyAlgorithm(items, 5);
        int greedyBagResult = greedyBag.stream().mapToInt(Item::getValue).sum();
        greedyBag.forEach(System.out::println);
        System.out.println("Greedy algorithm: " + greedyBagResult);

    }

    static List<Item> greedyAlgorithm(List<Item> items, int maxCapacity) {
        List<Item> bag = new LinkedList<>();
        int occupied = 0;
        int smallestItemSize = items.stream().mapToInt(Item::getSize).min().orElse(Integer.MAX_VALUE);
        Item mostExpensive;
        while (!items.isEmpty() && smallestItemSize <= (maxCapacity - occupied)) {
            mostExpensive = items.stream().max(Comparator.comparing(Item::getValue)).get();
            if (mostExpensive.getSize() <= maxCapacity - occupied) {
                bag.add(mostExpensive);
                occupied += mostExpensive.getSize();
            }
            items.remove(mostExpensive);
        }
        return bag;
    }

    static int dynamicAlgorithm(List<Item> items, int maxCapacity, int threshold) {
        int columns = maxCapacity / threshold;
        int rows = items.size() + 1;
        int[][] table = new int[rows][columns];
        int previousRow;
        Item currentItem;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0) {
                    table[i][j] = 0;
                    continue;
                }

                currentItem = items.get(i - 1);
                previousRow = table[i - 1][j];

                if (currentItem.getSize() > j + 1) {
                    table[i][j] = previousRow;
                } else if (currentItem.getSize() == j + 1) {
                    table[i][j] = Math.max(previousRow, currentItem.getValue());
                } else {
                    int smallerBag = table[i - 1][j - currentItem.getSize()];
                    table[i][j] = Math.max(previousRow, currentItem.getValue() + smallerBag);
                }
            }
        }
        System.out.println(Arrays.deepToString(table));
        return table[rows - 1][columns - 1];
    }
}

class Item {
    private String name;
    private int size;
    private int value;

    public Item(String name, int size, int value) {
        this.name = name;
        this.size = Math.max(size, 0);
        this.value = Math.max(value, 0);
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
