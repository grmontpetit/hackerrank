package hackerrank.morelinkedlist;

public class Solution {

    public static Node removeDuplicates(Node head) {
        Node current = head;
        while (current != null) {
            if (current.next == null) {
                return head;
            }
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static  Node insert(Node head,int data) {
        Node p=new Node(data);
        if(head==null)
            head=p;
        else if(head.next==null)
            head.next=p;
        else
        {
            Node start=head;
            while(start.next!=null)
                start=start.next;
            start.next=p;

        }
        return head;
    }

    public static void display(Node head) {
        Node start=head;
        while(start!=null)
        {
            System.out.print(start.data+" ");
            start=start.next;
        }
    }

    public static void main(String args[]) {
        Node head = new Node(1);
        int[] nodes = {2, 2, 3, 3, 4};
        for (int ele: nodes) {
            head=insert(head,ele);
        }
        head = removeDuplicates(head);
        display(head);
    }
}
