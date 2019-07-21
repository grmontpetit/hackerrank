package datastructureandalgorithms.binarysearchtree;

public class Main {

    public static void main(String[] args) {

        int[] nodes = {39, 12, 19, 9, 23, 55, 31, 60, 35, 41, 70, 90};
        Node root = new Node(25);
        for (int i: nodes) {
            root.insert(i);
        }

        root.levelOrder(root);

        System.out.println();
        Node newRoot = root.removeNode(root, 9);
        root.levelOrder(newRoot);
    }

}
