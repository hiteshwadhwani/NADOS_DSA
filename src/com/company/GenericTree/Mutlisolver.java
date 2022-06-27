package com.company.GenericTree;
import java.io.*;
import java.util.*;

public class Mutlisolver {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static void display(Node node) {
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);

        for (Node child : node.children) {
            display(child);
        }
    }

    public static Node construct(int[] arr) {
        Node root = null;

        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    root = t;
                }

                st.push(t);
            }
        }

        return root;
    }
    static int size;
    static int min;
    static int max;
    static int height;

    public static void multiSolver(Node node , int depth){
        size++;
        min = Math.min(min , node.data);
        max = Math.max(max , node.data);
        height = Math.max(height , depth);
        for (Node child:node.children){
            multiSolver(child , depth+1);
        }
    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        size =0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        height =0;
        Node root = construct(arr);
        multiSolver(root , 0);
        System.out.println("size:" + size);
        System.out.println("min:" + min);
        System.out.println("max:" + max);
        System.out.println("height:" + height);

    }
}
