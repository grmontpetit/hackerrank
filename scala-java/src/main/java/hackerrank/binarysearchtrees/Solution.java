package hackerrank.binarysearchtrees;

public class Solution {

    public static int recurse(Node root) {
        if (root == null) {
            return 0;
        }
        int left = recurse(root.left);
        int right = recurse(root.right);
        return Math.max(left, right) + 1;
    }

    public static int getHeight(Node root){
        //Write your code here
        return recurse(root) - 1;
    }

    public static Node insert(Node root, int data){
        if(root == null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left=cur;
            }
            else{
                cur=insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
    public static void main(String args[]) {
        Node root1 = new Node(3);
        int[] nodes1 = {5, 2, 1, 4, 6, 7};
        for (int n: nodes1) {
            root1 = insert(root1, n);
        }

        Node root2 = new Node(20);
        int[] nodes2 = {50, 35, 44, 9, 15, 62, 11, 13};
        for (int n: nodes2) {
            root2 = insert(root2, n);
        }

        try {
            int value1 = getHeight(root1);
            assertEquals(value1, 3, "got " + value1 + " want " + 3);
            int value2 = getHeight(root2);
            assertEquals(value2, 4, "got " + value2 + " want " + 4);
        } catch (Exception e) {
            System.err.print(e);
        }

//        Scanner sc=new Scanner(System.in);
//        int T=sc.nextInt();
//        Node root=null;
//        while(T-->0){
//            int data=sc.nextInt();
//            root=insert(root,data);
//        }
//        int height=getHeight(root);
//        System.out.println(height);
    }

    private static void assertEquals(int value, int shouldEqual, String message) throws Exception {
        if (value != shouldEqual) throw new Exception(message);
    }
}
