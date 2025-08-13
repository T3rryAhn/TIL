package stack.array_stack;

import java.util.Arrays;

public class Stack {
	// let's make a stack
	
	/*
	 * we need
	 * 1. select data structure
	 * 		i will use Array. for making stack growth
	 * 
	 * 2. make fields
	 * 		int[] stack : only for int elements lol;
	 * 		top : index, pointing top of stack. if this value is '-1' it means empty. 
	 * 		capacity : array size. for stack growth
	 * 		elementCount : hmm i'm not sure.
	 * 	
	 * 3. make methods
	 * 		push()
	 * 		growth() : if stack is full. this method will copy and return bigger size array for stack.
	 * 		peek()
	 * 		pop()
	 * 		isEmpty();
	 * 		
	 * 4. test it
	 * 		
	 * 		
	 */
	
	// field
	static int top = -1;
	static int capacity = 10;
	static int[] stack = new int[capacity];
	
	// methods
	public static void growth() {
		capacity *= 2;// double size it; 
		stack = Arrays.copyOf(stack, capacity);
	}
	
	public static void push(int n) {
		if (top + 1 >= capacity) {
			growth();
		}
		stack[++top] = n;
	}
	
	public static boolean isEmpty() {
		if(top < 0) {
			return true;
		}
		return false;
	}
	
	public static Integer peek() {
		if(isEmpty()) {
			System.out.println("stack is empty!!!");
			return null;	//@todo << think better return
		}
		return stack[top];
	}
	
	public static Integer pop() {
		if(isEmpty()) {
			System.out.println("stack is empty!!! nothing for pop");
			return null;	//@todo << think better return
		}
		return stack[top--];
	}
	
	public static void main(String[] args) {
		System.out.println("push test");
		for(int i = 0; i < 15; i++) {
			push(i);
		}
		System.out.println(Arrays.toString(stack));
		
		System.out.println("\npeek() : " + peek());
		
		System.out.println("isEmpty() : " + isEmpty());
		
		System.out.println("\npop it all");
		while(!isEmpty() ) {
			System.out.printf("%3d", pop());
		}
		System.out.println();
		System.out.println();
		
		System.out.println("pop() when stack is empty");
		System.out.println(pop());
	}
	
	
}
