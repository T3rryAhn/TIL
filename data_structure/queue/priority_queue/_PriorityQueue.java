package data_structure.queue.priority_queue;

public class _PriorityQueue {
    public static void main(String[] args) {
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // pq.add(10);
        // pq.add(20);
        // pq.add(15);
        // pq.add(30);
        // pq.add(25);

        // System.out.println(pq);
        // System.out.println(pq.poll());
        // System.out.println(pq);

        int a = 1;
        // int b = 0x80000000 ^ a;
        int b = 0xFFFFFFFF | a;
        System.out.printf("%32s%n", Integer.toBinaryString(b));
        System.out.printf("%d%n", b);
    }
}
