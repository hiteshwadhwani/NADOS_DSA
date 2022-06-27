package com.company.binarySearchTree;

import java.util.Stack;

public class BSTIterator {
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

      Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        TreeNode pointer = root;
        while (pointer != null){   //push all the left nodes into the stack
            st.push(pointer);
            pointer = pointer.left;
        }
    }

    public int next() {
        TreeNode node = st.pop();
        TreeNode pointer = node;
        if(pointer.right != null){
            pointer = pointer.right;
            while (pointer != null){
                st.push(pointer);
                pointer = pointer.left;
            }
        }
        return node.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }
}
