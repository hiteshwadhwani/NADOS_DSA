package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Questions {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static int size(Node node) {
        int size =1;
        if(node.left != null){
            size += size(node.left);
        }
        if(node.right != null){
            size += size(node.right);
        }


        return size;
    }

    public static int sum(Node node) {
        int sum = node.data;
        if(node.left != null){
            sum += sum(node.left);
        }
        if(node.right != null){
            sum += sum(node.right);
        }
        return sum;
    }

    public static int max(Node node) {
        int max = Integer.MIN_VALUE;
        if(node.left != null){
            max = Integer.max(max , max(node.left));
        }
        if(node.right != null){
            max = Integer.max(max , max(node.right));
        }
        return Integer.max(max , node.data);
    }

    public static int height(Node node) {
        int h = -1;
        if(node.left != null){
            h = Integer.max(h , height(node.left));
        }
        if(node.right != null){
            h = Integer.max(h , height(node.right));
        }
        return h+1;
    }
    //https://nados.io/question/levelorder-traversal-of-binary-tree
    public static void levelOrder(Node node) {
        Queue<Node> main = new ArrayDeque<>();
        Queue<Node> child = new ArrayDeque<>();
        main.add(node);
        while (main.size() > 0){
            Node top = main.remove();
            System.out.println(top.data + " ");
            if(top.left != null){
                child.add(top.left);
            }
            if(top.right != null){
                child.add(top.right);
            }
            if(main.size() == 0){
                main = child;
                child = new ArrayDeque<>();
                System.out.println();
            }
        }
    }
    //approach 2
    public static void levelOrder2(Node node) {
        Queue<Node> main = new ArrayDeque<>();
        main.add(node);
        while (main.size() > 0){
            int size = main.size();
            for (int i=0;i<size;i++){
                Node top = main.remove();
                System.out.print(top.data+" ");
                if(top.left != null){
                    main.add(top.left);
                }
                if(top.right != null){
                    main.add(top.right);
                }
            }
            System.out.println();
        }
    }
    static class Pair{
        Node node;
        int state;
        Pair(Node node , int state){
            this.node = node;
            this.state = state;
        }
    }
    //https://nados.io/question/iterative-pre-post-and-inorder-traversals-of-binary-tree
    public static void iterativePrePostInTraversal(Node node) {
        Stack<Pair> st = new Stack<>();
        st.add(new Pair(node , 1));
        // state - 1 - preorder
        //         2 - in order
        //         3 - post order
        String pre = "";
        String in = "";
        String post = "";
        while (st.size() > 0){
            Pair top = st.peek();
            if (top.state == 1){
                pre += top.node.data + " ";
                if(top.node.left != null){
                    st.add(new Pair(top.node.left , 1));
                }
                top.state++;
            }
            else if(top.state ==2){
                in += top.node.data + " ";
                if(top.node.right != null){
                    st.add(new Pair(top.node.right , 1));
                }
                top.state++;
            }
            else {
                post += top.node.data + " ";
                st.pop();
            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);

    }
    //https://nados.io/question/find-and-nodetorootpath-in-binary-tree
    public static boolean find(Node node, int data){
        if(node == null){
            return false;
        }
        if(node.data ==  data){
            return true;
        }
        if(find(node.right , data) || find(node.left  , data)){
            return true;
        }
        return false;

    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node.data == data){
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(node.data);
            return ans;
        }
        if(node.left != null){
            ArrayList<Integer> left = nodeToRootPath(node.left ,data);
            if(left.size() > 0){
                left.add(node.data);
                return left;
            }
        }
        if(node.right != null){
            ArrayList<Integer> right = nodeToRootPath(node.right , data);
            if(right.size() > 0){
                right.add(node.data);
                return right;
            }
        }
        return new ArrayList<>();
    }
    //https://nados.io/question/print-k-levels-down
    public static void printKLevelsDown(Node node, int k){
        if(node == null || k < 0){
            return;
        }
        if(k ==0){
            System.out.println(node.data);
            return;
        }
        printKLevelsDown(node.left , k-1);
        printKLevelsDown(node.right , k-1);
    }

    //https://nados.io/question/print-nodes-k-distance-away                    (seen)(IMP)(Amazon)

    //same nodeToRootPath but return type is ArrayList<Node> not ArrayList<Integer>
    public static ArrayList<Node> nodeToRootPath2(Node node, int data){
        if(node.data == data){
            ArrayList<Node> ans = new ArrayList<>();
            ans.add(node);
            return ans;
        }
        if(node.left != null){
            ArrayList<Node> left = nodeToRootPath2(node.left ,data);
            if(left.size() > 0){
                left.add(node);
                return left;
            }
        }
        if(node.right != null){
            ArrayList<Node> right = nodeToRootPath2(node.right , data);
            if(right.size() > 0){
                right.add(node);
                return right;
            }
        }
        return new ArrayList<>();
    }
    //same printKLevelDown but with a blocker node in a path functionality added
    public static void printKLevelsDownWithBlocker(Node node, int k,Node blocker){
        if(node == null || k < 0 || node == blocker){
            return;
        }
        if(k ==0){
            System.out.println(node.data);
            return;
        }
        printKLevelsDownWithBlocker(node.left , k-1 , blocker);
        printKLevelsDownWithBlocker(node.right , k-1 , blocker);
    }
    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = nodeToRootPath2(node , data);
        for (int i=0;i<path.size();i++){
            printKLevelsDownWithBlocker(path.get(i) , k-i , i == 0 ? null : path.get(i-1));
        }

    }
    //https://nados.io/question/path-to-leaf-from-root-in-range
    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
        if(node == null){
            return;
        }
        if(node.right == null && node.left == null){
            sum += node.data;
            if(sum >= lo && sum <= hi){
                System.out.println(path + node.data);
            }
        }
        pathToLeafFromRoot(node.left , path + node.data , sum + node.data , lo , hi);
        pathToLeafFromRoot(node.right , path + node.data , sum + node.data , lo , hi);
    }
    //https://nados.io/question/transform-to-left-cloned-tree
    public static Node createLeftCloneTree(Node node){
        if(node == null){
            return null;
        }

        Node leftClonedNode =     createLeftCloneTree(node.left);


        Node rightCloneNode =     createLeftCloneTree(node.right);



        Node newNode = new Node(node.data , leftClonedNode , null);
        node.left = newNode;
        node.right = rightCloneNode;
        return node;
    }
    //https://nados.io/question/transform-to-normal-from-left-cloned-tree
    public static Node transBackFromLeftClonedTree(Node node){
        if(node == null) return null;
        Node leftTree = transBackFromLeftClonedTree(node.left.left);
        Node rightTree = transBackFromLeftClonedTree(node.right);
        node.left = leftTree;
        node.right = rightTree;
        return node;

    }
    //https://nados.io/question/print-single-child-nodes
    public static void printSingleChildNodes(Node node, Node parent){
        if (node == null){
            if(parent.right != null){
                System.out.println(parent.right.data);
                return;
            }
            else if(parent.left != null){
                System.out.println(parent.left.data);
                return;
            }
            else {
                return;
            }
        }
        printSingleChildNodes(node.left , node);
        printSingleChildNodes(node.right , node);

    }
    //https://nados.io/question/remove-leaves-in-binary-tree

    //my approach
    public static Node removeLeaves(Node node){
        if(node == null){
            return null;
        }
        if(node.left.left == null && node.left.right == null){
            node.left = null;
        }
        if(node.right.left == null && node.right.right == null){
            node.right = null;
        }
        removeLeaves(node.left);
        removeLeaves(node.right);
        return node;

    }

    //NADOS approach
    public static Node removeLeaves2(Node node){
        if(node == null){
            return null;
        }
        //when we get on leaf node we will return null so that NULL will be attached to its parent
        if(node.left == null && node.right == null){
            return null;
        }
        node.left = removeLeaves2(node.left);
        node.right = removeLeaves2(node.right);
        return node;
    }


    //https://nados.io/question/diameter-of-a-binary-tree                (IMP)

    //my approach
    static int dia =0;
    public static int diameter1(Node node) {
        int dch = -1;
        int sdch = -1;
        if(node.left != null){
            int h = diameter1(node.left);
            if(h > dch){
                sdch = dch;
                dch = h;
            }
            else if(h > sdch){
                sdch = h;
            }
        }
        if(node.right != null){
            int h = diameter1(node.right);
            if(h > dch){
                sdch = dch;
                dch = h;
            }
            else if(h > sdch){
                sdch = h;
            }
        }
        if(dch + sdch + 2 > dia){
            dia = dch + sdch + 2;
        }
        return dch + 1;

    }

    //NADOS approachm
    static int dia2 =0;
    public static int diameter2(Node node) {
        if(node == null){
            return -1;
        }
        int lh = diameter2(node.left);
        int rh = diameter2(node.right);
        int h = Math.max(lh,rh) + 1;
        if(lh + rh + 2 > dia2){
            dia2 = lh + rh + 2;
        }
        return h;
    }

    //NADOS ineffeciant approach     O(n^2)
    public static int diameter3(Node node) {
        if(node == null){
            return 0;
        }
        int ld = diameter3(node.left);
        int rd = diameter3(node.right);
        int f = height(node.left) + height(node.right) + 2;
        return Math.max(f , Math.max(ld,rd));
    }

    //approach using class
    //why this is efficient than diameter3 approach ?
    //because we are not calling height in postOrder of recursion because we are calculating height with recursion only
    static class DiaPair{
        int ht;       //height
        int dia;      //diameter
    }
    public static DiaPair diameter4(Node node) {
        if(node == null){
            DiaPair dp = new DiaPair();
            dp.ht = -1;
            dp.dia =0;
            return dp;
        }
        DiaPair lp = diameter4(node.left);
        DiaPair rp = diameter4(node.right);
        DiaPair ans = new DiaPair();
        ans.ht = Math.max(lp.ht,rp.ht) + 1;
        int dia = lp.ht + rp.ht + 2;
        ans.dia = Math.max(dia , Math.max(lp.dia,rp.dia));
        return ans;
    }
    //https://nados.io/question/tilt-of-binary-tree
    static int tilt = 0;
    public static int tilt(Node node){
        if(node == null){
            return 0;
        }
        int leftSum = tilt(node.left);
        int rightSum = tilt(node.right);
        tilt += Math.abs(leftSum - rightSum);
        return node.data + leftSum + rightSum;
    }


    //https://nados.io/question/is-a-binary-search-tree
    static class BSTpair{
        int min;
        int max;
        boolean isBST;
    }
    public static BSTpair isBST(Node node){
        if(node == null){
            BSTpair pair = new BSTpair();
            pair.min = Integer.MAX_VALUE;
            pair.max = Integer.MIN_VALUE;
            pair.isBST = true;
            return pair;
        }
        BSTpair pairLeft = isBST(node.left);
        BSTpair pairRight = isBST(node.right);
        BSTpair pair = new BSTpair();
        pair.min = Math.min(node.data , Math.min(pairLeft.min, pairRight.min));
        pair.max = Math.max(node.data , Math.max(pairLeft.max, pairRight.max));
        pair.isBST = pairLeft.isBST && pairRight.isBST && node.data >= pairLeft.max && node.data <= pairRight.min;
        return pair;
    }

    //https://nados.io/question/largest-bst-subtree
    static class BSTPair2{
        int min;
        int max;
        boolean isBST;
        Node LBST; //largest BST
        int SBST; //size of BST
    }
    public static BSTPair2 largestBst(Node node){
        if(node == null){
            BSTPair2 pair = new BSTPair2();
            pair.min = Integer.MAX_VALUE;
            pair.max = Integer.MIN_VALUE;
            pair.isBST = true;
            pair.LBST = null;
            pair.SBST = 0;
            return pair;
        }
        BSTPair2 Lp = largestBst(node.left);
        BSTPair2 Rp = largestBst(node.right);
        BSTPair2 pair = new BSTPair2();
        pair.min = Math.min(node.data , Math.min(Lp.min, Rp.min));
        pair.max = Math.max(node.data , Math.max(Lp.max, Rp.max));
        pair.isBST = Lp.isBST && Rp.isBST && node.data >= Lp.max && node.data <= Rp.min;

        if(pair.isBST){
            pair.LBST = node;
            pair.SBST = Lp.SBST + Rp.SBST + 1;
        }
        else if(Lp.SBST > Rp.SBST){
            pair.LBST = Lp.LBST;
            pair.SBST = Lp.SBST;
        }
        else {
            pair.LBST = Rp.LBST;
            pair.SBST = Rp.SBST;
        }
        return pair;
    }
    //https://nados.io/question/is-balanced-tree
    static class BalancePair{
        boolean isBalanced;
        int height;
    }
    public static BalancePair checkBalancedTree(Node node){
        if(node == null){
            BalancePair pair = new BalancePair();
            pair.isBalanced = true;
            pair.height = -1;
            return pair;
        }
        BalancePair Lp = checkBalancedTree(node.left);
        BalancePair Rp = checkBalancedTree(node.right);
        BalancePair ans = new BalancePair();
        ans.isBalanced = Lp.isBalanced && Rp.isBalanced && Math.abs(Lp.height - Rp.height) <=1;
        ans.height = Math.max(Lp.height, Rp.height) + 1;
        return ans;
    }










}
