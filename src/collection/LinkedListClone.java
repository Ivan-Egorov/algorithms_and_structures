package collection;

/**
 * The LinkedListClone Class is a collection for storing objects.
 * Its main methods are similar with LinkedList methods.
 * However, LinkedListClone doesn't implement Iterable, Collection or List interfaces.
 */
public class LinkedListClone<A> {
    private int size;
    private Node first;
    private Node last;

    public LinkedListClone() {
        size = 0;
        first = null;
        last = null;
    }

    public void add(A a) {
        if (first == null) {
            first = new Node(a, null, null);
            last = first;
        } else {
            Node node = new Node(a, last, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(int index, A a) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index  == 0) {
            addFirst(a);
            return;
        }else if (index == size) {
            addLast(a);
            return;
        }

        Node previousNode = first;
        int count = 1;
        while (count != index) {
            previousNode = previousNode.next;
            count++;
        }
        Node nextNode = previousNode.next;
        Node node = new Node(a, previousNode, nextNode);
        previousNode.next = node;
        nextNode.previous = node;
        size++;
    }

    public void addFirst(A a) {
        if (first == null) {
            first = new Node(a, null, null);
            last = first;
        } else {
            Node node = new Node(a, null, first);
            first.previous = node;
            first = node;
        }
        size++;
    }

    public void addLast(A a) {
        add(a);
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(A a) {
        if (first == null) {
            return false;
        } else if (first.value == a) {
            return true;
        }
        Node node = first;
        while (node.next != null) {
            if (node.next.value == a) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public A get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        int count = 0;
        while (count < size) {
            if (count == index) {
                return node.value;
            }
            node = node.next;
            count++;
        }
        return null;
    }

    public int indexOf(A a) {
        if (first == null) {
            return -1;
        } else if (first.value == a) {
            return 0;
        }
        Node node = first;
        int index = 1;
        while (node.next != null) {
            if (node.next.value == a) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public boolean remove(A a) {
        if (first == null) {
            return false;
        }
        if (first.value == a) {
            if (first.next == null) {
                first = null;
                last = null;
            } else {
                Node nextNode = first.next;
                nextNode.previous = null;
                first = nextNode;
            }
            size--;
            return true;
        }

        Node node = first;
        while (node.next != null) {
            Node nextNode = node.next;
            if (nextNode.value == a) {
                Node nextNextNode = nextNode.next;
                if (nextNextNode != null) {
                    node.next = nextNextNode;
                    nextNextNode.previous = node;
                } else {
                    node.next = null;
                    last = node;
                }
                size--;
                return true;
            }
            node = nextNode;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }
        return array;
    }

    public A getFirst() {
        return first.value;
    }

    public A getLast() {
        return last.value;
    }

    public int getSize() {
        return size;
    }

    // inner node class
    private class Node {
        A value;
        Node next;
        Node previous;

        public Node(A value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
