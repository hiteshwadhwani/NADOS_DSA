package com.leetcode.GenericTree;

import com.sun.source.tree.Tree;

public class Questions {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //https://leetcode.com/problems/diameter-of-binary-tree/
    public int height(TreeNode node){
        if(node == null){
            return -1;
        }
        int h = Math.max(height(node.left),height(node.right)) + 1;
        return h;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return -1;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        int dia1 = diameterOfBinaryTree(root.left);
        int dia2 = diameterOfBinaryTree(root.right);
        return Math.max((lh+rh+2), Math.max(dia1,dia2));
    }


}
