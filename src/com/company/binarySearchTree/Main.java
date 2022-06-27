
public class Main {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static Node constructor(int[] arr, int lo, int hi){
        if(lo>hi){
            return null;
        }
        int mid = lo + (hi-lo)/2;
        int midNode = arr[mid];
        Node lc = constructor(arr,lo,mid-1); //left child
        Node rc = constructor(arr,mid+1,hi); //right child
        Node node = new Node(midNode , lc,rc);  //connect left child and right child with node
        return node;
    }
    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = " <- " + node.data + " -> ";

        String left = (node.left == null) ? "." :  "" + node.left.data;
        String right = (node.right == null) ? "." : "" + node.right.data;

        str = left + str + right;

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        int[] arr = {12,25,37,50,62,75,87};
        Node root = constructor(arr,0,arr.length-1);
        display(root);
    }
}
