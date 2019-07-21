package datastructureandalgorithms.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

public class Node {

    private int data;
    private Node left;
    private Node right;

    public Node() {}

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "" + data;
    }

    public void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.print(cur.data + " ");
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

//    public Node find(int data) {
//
//    }

    public Node removeNode(Node root, int data) {
        if (root == null)  return null;

        if (data < root.data)
            root.left = removeNode(root.left, data);
        else if (data > root.data)
            root.right = removeNode(root.right, data);

        else {
            if (root.left == null) {
                return root.right;
            }

            else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = removeNode(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public void insert(int data) {
        Node node = new Node(data);
        if (this.data <= data) {
            if (right == null) {
                right = node;
            } else {
                right.insert(data);
            }
        } else {
            if (left == null) {
                left = node;
            } else {
                left.insert(data);
            }
        }
    }

}
