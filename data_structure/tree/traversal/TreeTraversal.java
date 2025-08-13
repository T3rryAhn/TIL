package tree.traversal;

public class TreeTraversal {
    // \u0000 == null;
    // 2^n index가 n - 1 레벨의 맨 왼쪽 노드
    static char[] tree = { '\u0000', 'A', 'B', 'C', 'D', 'E', 'F', 'G', '\u0000', '\u0000', 'H', 'I' };
    static int N = tree.length;

    public static void main(String[] args) {
        System.out.println("전위순회");
        preOrder(1);
        System.out.println();
        System.out.println("중위순회");
        inOrder(1);
        System.out.println();
        System.out.println("후위순회");
        postOrder(1);
        System.out.println();

    }

    // 전위 순회 VLR
    public static void preOrder(int v) {
        if (v < N && tree[v] != '\u0000') {
            System.out.print(tree[v] + " "); // 방문처리 출력
            // left child
            preOrder(v * 2);
            // right child
            preOrder(v * 2 + 1);
        }
    }

    // 중위 순회 LVR
    public static void inOrder(int v) {
        if (v < N && tree[v] != '\u0000') {
            // left child
            inOrder(v * 2);
            System.out.print(tree[v] + " "); // 방문처리 출력
            // right child
            inOrder(v * 2 + 1);
        }
    }

    // 후위 순회 LRV
    public static void postOrder(int v) {
        if (v < N && tree[v] != '\u0000') {
            // left child
            postOrder(v * 2);
            // right child
            postOrder(v * 2 + 1);
            System.out.print(tree[v] + " "); // 방문처리 출력
        }
    }


}
