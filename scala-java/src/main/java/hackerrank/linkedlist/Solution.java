package hackerrank.linkedlist;

import java.util.Scanner;

public class Solution {

    public static Node insert(Node head, int data) {
        //Complete this method
        Node node = new Node(data);
        if (head == null) {
            node.next = null;
            head = node;
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        return head;
    }

    public static void display(Node head) {
        Node start = head;
        while(start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        Node head = null;
//        int N = sc.nextInt();
//
//        while(N-- > 0) {
//            int ele = sc.nextInt();
//            head = insert(head,ele);
//        }
//        display(head);
//        sc.close();
        int[] nodes = {2, 3, 4, 1};
        Node head = null;
        for (int ele: nodes) {
            head = insert(head, ele);
        }

        display(head);
    }
}
