package data_structure.queue.array_queue;

import java.util.Arrays;

/*
 * let's make a queue
 * 
 * ## field
 * head
 * tail
 * 
 * size : elements count
 * capacity
 * 
 * 
 * ## methods
 * isEmpty()
 * isFull()
 * canExpand() : check tail reached arrays last idx
 * peek()
 * add(int data)
 * poll()
 * 
 * ## my kick is...
 * when add() works, remove empty space
 */

public class Queue {
	int head = -1;
	int tail = -1;
	int capacity = 10;
	int size = 0;

	int[] queue;

	Queue() {
		this.queue = new int[capacity];
	}

	Queue(int capacity) {
		this.queue = new int[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == capacity;
	}

	private boolean canExpand() {
		return tail < capacity - 1;
	}

	public Integer peek() {
		return isEmpty() ? null : queue[head];
	}

	public void add(int data) {
		if (!canExpand()) {
			if (size == capacity) {
				System.out.println("# i'm full...");
				return;
			} else {
				System.out.println("# tail reached end of array. i will remove spaces");
				// Reclaim unused space
				System.arraycopy(queue, head, queue, 0, size);
				System.out.println("result : " + Arrays.toString(queue));
				head = 0;
				tail = size - 1;
			}
		}
		if (isEmpty()) {
			head++;
		}
		queue[++tail] = data;
		size++;
	}

	public Integer poll() {
		if (isEmpty()) {
			System.out.println("# i'm empty sir...");
			return null;
		}
		size--;
		return queue[head++];
	}
	
	public int size() {
		return size;
	}
}
