package data_structure.tree.binary_tree;

/*
 * 필요한것
 * # 필드
 * - root
 * - size ??
 * 
 * # 생성자
 * 
 * # 메서드
 * getRoot() : 루트 노드 조회
 * add(parent, child, position) : 부모 노드의 왼쪽/오른쪽 자식으로 삽입
 * traversePreOrder() : 전위 순회 출력
 * traverseInOrder() : 중위 순회 출력
 * traversePostOrder() : 후위 순회 출력
 * find(value) : 값으로 노드 탐색
 * 
 */
public class BinaryTree {

    // 이진 트리용 노드 클래스 선언
    class Node<T> {
        T item;
        Node parent;
        Node left;
        Node right;

        Node(T item) {
            this.item = item;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    private Node root = null;
    private int size = 0;

    public BinaryTree() {

    }

    public Node getRoot() {
        return root;
    }

    public <T> Node findByItem(T target) {
        if (target == null)
            return null;
        return findByItemRecursive(target, root);
    }

    private <T> Node findByItemRecursive(T target, Node curNode) {
        if (curNode == null)
            return null;
        if (target.equals(curNode.item))
            return curNode;

        Node found = findByItemRecursive(target, curNode.left);
        if (found != null)
            return found;
        return findByItemRecursive(target, curNode.right);
    }

    /*
     * add root node
     */
    public <T> boolean addRoot(T item) {
        if (root != null) {
            System.out.println("# BinaryTree add(): root exist");
            return false;
        }
        root = new Node(item);
        return true;

    }

    /*
     * position : 0 : left, 1 : right
     */
    public <T> boolean add(Node parent, T item, int position) {
        if (position == 0) {
            if (parent.left != null) {
                System.out.println("# BinaryTree add(): child exist");
                return false;
            }
            parent.left = new Node(item);
            return true;
        } else {
            if (parent.right != null) {
                System.out.println("# BinaryTree add(): child exist");
                return false;
            }
            parent.right = new Node(item);
            return true;
        }
    }

    public void traversePreOrder() {
        if (root == null) {
            System.out.println("BinaryTree traverse(): root is null");
        }
        System.out.print("preOrder : ");
        traversePreOrderRecursive(root);
    }

    private void traversePreOrderRecursive(Node node) {
        if (node == null) return;
        System.out.print(node.item + " ");
        traversePreOrderRecursive(node.left);
        traversePreOrderRecursive(node.right);
    }

    // @todo 
    // public <T> boolean remove(T target) {
    //     if (target == null) return false;

    //     Node found = findByItem(target);
    //     if (found == null) {
    //         System.out.println("# BinaryTree remove(): " + target + " not found");
    //         return false;
    //     }

    //     Node parent = found.parent;
    //     if(parent.left == found) {

    //     }
    // }
}
