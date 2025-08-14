package data_structure.heap.array_heap;

import java.util.Arrays;

/*
 * 배열을 이용한 최대 힙 구현
 */
public class Heap {
    static int[] heap;  // 정수형만 담을 수 있게, 문제별로 크기 세팅
    static int heapSize;

    public static void main(String[] args) {
        heap = new int[10];
        heapSize = 0;
        
        System.out.println(Arrays.toString(heap));
        
        heapPush(10);
        heapPush(20);
        heapPush(15);
        heapPush(30);
        
        System.out.println(Arrays.toString(heap));

        System.out.println(heapPop());
        System.out.println(heapPop());
        System.out.println(heapPop());
        System.out.println(heapPop());
        System.out.println(heapPop());

    }

    // for max heap
    public static void heapPush(int item) {
        // 마지막 자리에 아이템 추가
        heap[++heapSize] = item;

        // 힙 정렬
        int child = heapSize;
        int parent = child / 2;

        while(parent > 0 && heap[parent] < heap[child]) {
            int tmp = heap[parent];
            heap[parent] = heap[child];
            heap[child] = tmp;

            child = parent;
            parent = child / 2;
        }
    }

    // for max heap
    public static Integer heapPop() {
        // isEmpty?
        if(heapSize == 0) {
            return null;
        }

        int item = heap[1];
        heap[1] = heap[heapSize--];

        int parent = 1;
        int child = parent * 2; // left child
        // check right child is bigger
        if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
            child += 1;
        }

        while (child <= heapSize && heap[parent] < heap[child]) {
            int tmp = heap[parent];
            heap[parent] = heap[child];
            heap[child] = tmp;

            parent = child;
        }

        return item;
    }
}