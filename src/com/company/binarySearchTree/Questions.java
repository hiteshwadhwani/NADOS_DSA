package com.company.binarySearchTree;

import java.util.ArrayList;

public class Questions {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static int size(Node node) {
        if(node == null){
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null){
            return 0;
        }
        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if(node.right == null){
            return node.data;
        }
        else {
            return max(node.right);
        }
    }

    public static int min(Node node) {
        if(node.left == null){
            return node.data;
        }
        else {
            return min(node.left);
        }
    }

    public static boolean find(Node node, int data){
        if (node == null){
            return false;
        }
        else if(data < node.data){
            return find(node.left , data);
        }
        else if(data > node.data){
            return find(node.right , data);
        }
        else {
            return true;
        }
    }
    //https://nados.io/question/add-node-to-bst
    public static Node add(Node node, int data) {
        if(node == null){
            Node newNode = new Node(data , null, null);
            return newNode;
        }
        else if(data > node.data){
            node.right = add(node.right , data);
            return node;
        }
        else if(data< node.data){
            node.left = add(node.left , data);
            return node;
        }
        return node;
    }
    //https://nados.io/question/remove-node-from-bst
    public static Node remove(Node node, int data) {
        if(data > node.data){
            node.right = remove(node.right ,data);
            return node;
        }
        else if(data < node.data){
            node.left = remove(node.left , data);
            return node;
        }
        else {
            //when node have 0 child
            if(node.left == null && node.right == null){
                return null;
            }
            //when node have 1 child
            else if(node.left == null){
                return node.right;
            }
            //when node have 2 child
            else if(node.right == null){
                return node.left;
            }
            //when node which we have to remove have two children
            //1)find the max node from left subtree
            //2)swap that max node with node which we have to remove
            //3)then remove the max node from left subtree
            else{
                int max = max(node.left);    //swap the maximum from left subtree to maintain BST
                node.data = max;
                node.left = remove(node.left,max);  //after swapping remove the maximum node from left subtree
                return node;
            }
        }

    }

    static int sum = 0;
    public static void rwsol(Node node){
        if(node == null){
            return;
        }
        rwsol(node.right);
        int val = node.data;
        node.data = sum;
        sum+= val;
        rwsol(node.left);
    }

    //https://nados.io/question/lca-of-bst
    public static int lca(Node node, int d1, int d2) {
        if(node == null){
            return 0;
        }
        if(d1 < node.data && d2 < node.data){
            return lca(node.left , d1 , d2);
        }
        else if(d1 > node.data && d2 > node.data){
            return lca(node.right , d1 , d2);
        }
        else {
            return node.data;
        }
    }

    //https://nados.io/question/print-in-range
    public static void pir(Node node, int d1, int d2) {
        if(node == null){
            return;
        }
        pir(node.left , d1 , d2);
        if(node.data >= d1 && node.data <= d2){
            System.out.println(node.data);
        }
        pir(node.right, d1,d2);
    }

    //https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public static boolean find2(TreeNode node, int data){
        if (node == null){
            return false;
        }
        else if(data < node.val){
            return find2(node.left , data);
        }
        else if(data > node.val){
            return find2(node.right , data);
        }
        else {
            return true;
        }
    }
    public boolean findTarget(TreeNode root, int k) {
        if(root == null){
            return false;
        }
        boolean leftAns = findTarget(root.left , k);
        boolean rightAns = findTarget(root.right , k);
        int rem = k - root.val;
        if(rem > root.val){
            return leftAns || rightAns || find2(root ,k - root.val) ;
        }
        else {
            return false;
        }

    }

    //https://nados.io/question/target-sum-pair-in-bst
    public static void targetSumPair(Node root,Node node,int k){
        if(node == null){
            return;
        }
        targetSumPair(root,node.left , k);
        int rem = k - node.data;
        if(rem > node.data){
            if(find(root , rem)){
                System.out.print(node.data + " " + rem);
            }
        }
        targetSumPair(root,node.right , k);
    }


}
