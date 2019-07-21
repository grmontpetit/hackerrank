package hackerrank.bstlevelordertraversal;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static void levelOrder(Node root){
        //Write your code here
        Queue<Node> q = new LinkedList<>();
        if (root == null) return;
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.remove();
            System.out.print(n.data + " ");
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    public static Node insert(Node root,int data){
        if(root == null) {
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            }
            else {
                cur = insert(root.right, data);
                root.right=cur;
            }
            return root;
        }
    }
    public static void main(String args[]){
//        Scanner sc=new Scanner(System.in);
//        int T=sc.nextInt();
//        Node root=null;
//        while(T-->0){
//            int data=sc.nextInt();
//            root=insert(root,data);
//        }
        Node root1 = new Node(3);
        int[] nodes1 = {5, 4, 7, 2, 1};
        for (int n: nodes1) {
            root1 = insert(root1, n);
        }
        //levelOrder(root1);

        Node root2 = new Node(25);
        int[] nodes2 = {39, 12, 19, 9, 23, 55, 31, 60, 35, 41, 70, 90};
        for (int n: nodes2) {
            root2 = insert(root2, n);
        }
        levelOrder(root2);


    }
}
