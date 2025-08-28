package data_structure.tree.binary_tree;

public class Test {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        System.out.println("add root twice : 1, 'a'");
        bt.addRoot(1);
        bt.addRoot('a');

        System.out.println("getRoot().item : " + bt.getRoot().item);
        System.out.println("findByItem(1).item : " + bt.findByItem(1).item);
        System.out.println("findByItem('a') : " + bt.findByItem('a'));
        System.out.println();

        System.out.println("add()");

        // 채울 레벨 수
        int levels = 2; // 예: 루트 다음 두 레벨
        int item = 2;

        for (int level = 0; level < levels; level++) {
            int start = 1 << level; // 2^level
            int end = (1 << (level + 1)) - 1; // 2^(level+1)-1
            for (int parent = start; parent <= end; parent++) {
                var p = bt.findByItem(parent);
                if (p == null)
                    continue; // 부모 없으면 스킵
                bt.add(p, item++, 0); // left
                bt.add(p, item++, 1); // right
            }
        }

        System.out.println();
        System.out.println("items added");

        System.out.println("preOrder()");
        bt.traversePreOrder();

    }
}
