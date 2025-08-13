package queue.array_queue;

import java.util.Arrays;

public class QueueTest {

	public static void main(String[] args) {
		Queue q = new Queue();

		for (int i = 0; i < 10; i++) {
			q.add(i);
		}
		
		System.out.println(Arrays.toString(q.queue));

		for (int i = 0; i < 10; i++) {
			q.add(i + 10);
		}
		
		System.out.println(Arrays.toString(q.queue));
		System.out.println("isFull() : " + q.isFull());
		System.out.println("q.peek() : " + q.peek());
		
		for (int i = 0; i < 5; i++ ) {
			System.out.println("q.poll() : " + q.poll());
		}
		
		System.out.println("head : " + q.head);
		System.out.println("tail : " + q.tail);
		System.out.println("isFull() : " + q.isFull());
		for (int i = 0; i < 10; i++) {
			q.add(i);
			System.out.println("q.add(" + i + ")");
		}
		System.out.println(Arrays.toString(q.queue));

	}

}
