package collection;

import java.util.LinkedList;
import java.util.List;

/**
 * The TreeMapClone Class is intended for storing key-value pairs.
 * It's based on the tree structure, and its main methods are similar with TreeMap methods.
 * However, TreeMapClone doesn't implement Map interface.
 */
public class TreeMapClone<K extends Comparable<K>, V> {

    Node<K, V> root;
    int size;

    public K getRoot() {
        return root.key;
    }

    public TreeMapClone() {
        root = null;
        size = 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (root == null) {
            root = node;
            size++;
            return;
        }
        Node<K, V> currentNode = root;
        Node<K, V> previous = null;
        while (true) {
            if (key.compareTo(currentNode.key) == 0) {
                currentNode.value = value;
                return;
            } else if (key.compareTo(currentNode.key) < 0 && currentNode.left == null) {
                currentNode.left = node;
                break;
            } else if (key.compareTo(currentNode.key) > 0 && currentNode.right == null) {
                currentNode.right = node;
                break;
            } else if (key.compareTo(currentNode.key) < 0) {
                previous = currentNode;
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                previous = currentNode;
                currentNode = currentNode.right;
            }
        }
        node.parent = previous;
        size++;
    }

    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        Node<K, V> currentNode = root;
        while (currentNode != null) {
            if (key.compareTo(currentNode.key) == 0) {
                return true;
            } else if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    public V get(K key) {
        if (root == null) {
            return null;
        }
        Node<K, V> currentNode = root;
        while (currentNode != null) {
            if (key.compareTo(currentNode.key) == 0) {
                return currentNode.value;
            } else if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    public V remove (K key) {
        Node<K, V> currentNode = root;
        Node<K, V> parentNode = root;
        Side side = null;

        while (currentNode.key != key) {
            parentNode = currentNode;
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
                side = Side.LEFT;
            } else {
                currentNode = currentNode.right;
                side = Side.RIGHT;
            }
            if (currentNode == null) {
                return null; // node not found
            }
        }

        if (currentNode.left == null && currentNode.right == null) { // node has no heir
            if (currentNode == root) { // if node == root, simply clear the tree
                root = null;
            } else if (side == Side.LEFT) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (currentNode.right == null) { // node doesn't have the right branch, replace it by the left subtree
            if (currentNode == root) {
                root = currentNode.left;
            } else if (side == Side.LEFT) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }
        } else if (currentNode.left == null) { // node doesn't have the left branch, replace it by the right subtree
            if (currentNode == root) {
                root = currentNode.right;
            } else if (side == Side.LEFT) {
                parentNode.left = currentNode.right;
            } else {
                parentNode.right = currentNode.right;
            }
        } else { // node has both branches, replace it by the heir from the right branch
            Node<K, V> nextNode = findNextNode(currentNode);
            if (currentNode == root) {
                root = nextNode;
            } else if (side == Side.LEFT) {
                parentNode.left = nextNode;
            } else {
                parentNode.right = nextNode;
            }
        }
        size--;
        return currentNode.value;
    }

    private Node<K, V> findNextNode(Node<K, V> node) {
        Node<K, V> parentNode = node;
        Node<K, V> currentNode = node.right;
        while (currentNode.left != null) {
            parentNode = currentNode;
            currentNode = currentNode.left;
        }
        if (currentNode == node.right) {
            currentNode.left = node.left;
        } else {
            parentNode.left = currentNode.right;
            currentNode.left = node.left;
            currentNode.right = node.right;
        }
        return currentNode;
    }

    // Depth first recursion method:
    public boolean containsValue(V value) {
        return containsValueUtil(root, value);
    }

    private boolean containsValueUtil(Node<K, V> node, V value) {
        if (node == null) {
            return false;
        } else if (value.equals(node.value)) {
            return true;
        }
        return (containsValueUtil(node.left, value) || containsValueUtil(node.right, value));
    }

    // Breadth first non-recursion method:
//    public boolean containsValue(V value) {
//        List<Node<K, V>> list = new LinkedList<>();
//        list.add(root);
//        while (!list.isEmpty()) {
//            Node node = list.getFirst();
//            if (value.equals(node.value)) {
//                return true;
//            }
//            if (node.left != null) {
//                list.add(node.left);
//            }
//            if (node.right != null) {
//                list.add(node.right);
//            }
//            list.removeFirst();
//        }
//        return false;
//    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> currentNode = root;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.key;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> currentNode = root;
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode.key;
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        // private NodeColour colour;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private enum Side {
        RIGHT,
        LEFT
    }
}

