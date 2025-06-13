package search;

import java.util.*;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        Node book = new Node("book");
        Node vinyl = new Node("vinyl");
        Node poster = new Node("poster");
        Node drum = new Node("drum");
        Node bassGuitar = new Node("bass guitar");
        Node piano = new Node("piano");
        Node nowhere = new Node("nowhere");
        book.neighbours.put(vinyl, 5);
        book.neighbours.put(poster, 0);
        vinyl.neighbours.put(bassGuitar, 15);
        vinyl.neighbours.put(drum, 20);
        poster.neighbours.put(bassGuitar, 30);
        poster.neighbours.put(drum, 35);
        bassGuitar.neighbours.put(piano, 20);
        drum.neighbours.put(piano, 10);

        List<Node> pathToPiano = findShortestPath(book, piano);
        if (pathToPiano != null) {
            pathToPiano.forEach(x -> System.out.println(x.name));
        } else {
            System.out.println("path not found");
        }
        System.out.println();

        List<Node> pathToDrum = findShortestPath(book, drum);
        if (pathToDrum != null) {
            pathToDrum.forEach(x -> System.out.println(x.name));
        } else {
            System.out.println("path not found");
        }
        System.out.println();

        List<Node> pathToNowhere = findShortestPath(book, nowhere);
        if (pathToNowhere != null) {
            pathToNowhere.forEach(x -> System.out.println(x.name));
        } else {
            System.out.println("path not found");
        }
    }

    public static List<Node> findShortestPath(Node root, Node toFind) {
        Set<Node> explored = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> shortestPath = new LinkedList<>();
        root.cost = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node element = queue.peek();
            if (explored.contains(element)) {
                queue.pop();
                continue;
            }
            for (Map.Entry<Node, Integer> entry : element.neighbours.entrySet()) {
                Node neighbour = entry.getKey();
                queue.add(neighbour);

                if (!explored.contains(neighbour)) {
                    neighbour.cost = Integer.MAX_VALUE;
                }

                if ((element.cost + entry.getValue()) < neighbour.cost) {
                    neighbour.cost = element.cost + entry.getValue();
                    neighbour.parent = element;
                }
            }
            explored.add(element);
            queue.pop();
        }

        if (toFind.parent != null) {
            shortestPath.add(toFind);
            Node currentNode = toFind;

            while (currentNode != root) {
                currentNode = currentNode.parent;
                shortestPath.add(currentNode);
            }
            return shortestPath.reversed();
        }
        return null;
    }
}

class Node {
    Map<Node, Integer> neighbours;
    String name;
    int cost;
    Node parent;

    public Node(String name) {
        neighbours = new HashMap<Node, Integer>();
        this.name = name;
        cost = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}