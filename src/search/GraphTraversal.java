package search;

import java.util.*;

public class GraphTraversal {
    public static void main(String[] args) {
        GraphVertex root = new GraphVertex("root");
        GraphVertex bob = new GraphVertex("Bob");
        GraphVertex claire = new GraphVertex("Claire");
        GraphVertex alice = new GraphVertex("Alice");
        GraphVertex peggy = new GraphVertex("Peggy");
        GraphVertex ann = new GraphVertex("Ann");
        GraphVertex tom = new GraphVertex("Tom");
        GraphVertex jonny = new GraphVertex("Jonny");
        root.addNeighbour(bob);
        root.addNeighbour(claire);
        root.addNeighbour(alice);
        bob.addNeighbour(ann);
        bob.addNeighbour(peggy);
        alice.addNeighbour(peggy);
        claire.addNeighbour(tom);
        claire.addNeighbour(jonny);
        GraphTraversal traversal = new GraphTraversal();
        System.out.println("Breadth search");
        Set<GraphVertex> breadth = traversal.breadthFirst(root);
        breadth.forEach(System.out::println);
        System.out.println();
        System.out.println("Depth search");
        Set<GraphVertex> depth = traversal.depthFirst(root);
        depth.forEach(System.out::println);
        System.out.println();
    }

    public Set<GraphVertex> breadthFirst(GraphVertex root) {
        Set<GraphVertex> explored = new LinkedHashSet<GraphVertex>();
        LinkedList<GraphVertex> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            GraphVertex vertex = queue.peek();
            if (!explored.contains(vertex)) {
                queue.addAll(vertex.getNeighbours());
                explored.add(vertex);
            }
            queue.pop();
        }

        return explored;
    }

    public Set<GraphVertex> depthFirst(GraphVertex root) {
        Set<GraphVertex> explored = new LinkedHashSet<GraphVertex>();
        depthFirstUtil(root, explored);
        return explored;
    }

    private void depthFirstUtil(GraphVertex vertex, Set<GraphVertex> explored) {
        if (!explored.contains(vertex)) {
            explored.add(vertex);
            vertex.getNeighbours().forEach(v -> depthFirstUtil(v, explored));
        }
    }


}

class GraphVertex {
    private String name;
    private List<search.GraphVertex> neighbours;

    public GraphVertex(String name) {
        this.name = name;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour (search.GraphVertex neighbour) {
        neighbours.add(neighbour);
    }

    public List<search.GraphVertex> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return "GraphVertex{" +
                "name='" + name + '\'' +
                '}';
    }
}



