package linked_list;

public class LinkedList<T> {
    class Node {
        T item;
        Node next = null;

        Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    Node head = null;
    int size = 0;

    public boolean isEmpty() {
        return head == null;
    }

    private void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            addFirst(item);
            return;
        }
        Node newNode = new Node(item);
        Node curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = newNode;
        size++;
    }

    public void add(int idx, T item) {
        if (isEmpty()) {
            System.out.println("empty...");
            return;
        }
        int i = 0;
        Node curNode = head;
        while (i < idx && curNode.next != null) {
            curNode = curNode.next;
            i++;
        }
        if (i < idx) {
            System.out.println("idx not exist...");
            return;
        }
        Node newNode = new Node(item);
        newNode.next = curNode.next;
        curNode.next = newNode;
        size++;
    }

    public void remove(int idx) {
        if (isEmpty()) {
            System.out.println("empty...");
            return;
        }

        int i = 0;
        Node curNode = head;
        while (i < idx && curNode.next != null) {
            curNode = curNode.next;
            i++;
        }
        if (i < idx) {
            System.out.println("idx not exist...");
            return;
        }
        if (size - 1 == idx) {
            curNode.next.next = null;
            size--;
            return;
        }

        curNode.next = curNode.next.next;
        size--;
    }

    public T get(int idx) {
        if (idx >= size) {
            System.out.println("idx not exist...");
            return null;
        }

        int i = 0;
        Node curNode = head;
        while (i < idx && curNode.next != null) {
            curNode = curNode.next;
            i++;
        }

        return curNode.next;

    }
}
