package com.company.BinaryTree;


import java.util.Stack;

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
    public static class Pair{
        Node node;
        int state;
        Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
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
        Integer[] arr = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};
        Stack<Pair> st = new Stack<>();
        Node rtn = new Node(arr[0] , null , null);
        st.push(new Pair(rtn , 1));
        int idx = 0;
        while (st.size() > 0){
            Pair top = st.peek();
            if(top.state == 1){
                idx++;
                if(arr[idx] != null){
                    Node newNode = new Node(arr[idx] , null , null);
                    top.node.left = newNode;
                    st.push(new Pair(newNode , 1));
                }
                top.state++;

            }
            else if(top.state == 2){
                idx++;
                if(arr[idx] != null){
                    Node newNode = new Node(arr[idx] , null , null);
                    top.node.right = newNode;
                    st.push(new Pair(newNode , 1));
                }
                top.state++;
            }
            else {
                st.pop();
            }
        }
        display(rtn);
    }
}
