package com.company.GenericTree;

import java.util.*;

public class Questions {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }
    //https://nados.io/question/levelorder-linewise-zig-zag
    public static void levelOrderLinewiseZZ(Node node){
        Queue<Node> mainQ = new ArrayDeque<>();
        Stack<Node> childS = new Stack<>();
        mainQ.add(node);
        int level =0;
        while (mainQ.size() > 0){
            node = mainQ.remove();
            System.out.print(node.data+ " ");
            if(level % 2 !=0){
                for (int i=node.children.size()-1;i>=0;i--){
                    childS.add(node.children.get(i));
                }
            }
            else {
                for (Node children:node.children){
                    childS.add(children);
                }
            }

            if(mainQ.size() == 0){
                while (childS.size()>0){
                    mainQ.add(childS.pop());
                }
                level++;
                System.out.println();
            }

        }
    }

    //https://nados.io/question/mirror-a-generic-tree
    public static void mirror(Node node){
        if(node.children.isEmpty()){
            return;
        }
        ArrayList<Node> newList = new ArrayList<>();
        for (int i=node.children.size()-1;i>=0;i--){
            newList.add(node.children.get(i));
        }
        node.children = newList;
        for (Node child:node.children){
            mirror(child);
        }
    }
    //https://nados.io/question/remove-leaves-in-generic-tree                        (seen)
    public static void removeLeaves(Node node) {
        //check for the size of the children of child nodes
        for (int i=node.children.size()-1;i>=0;i--){
            Node child = node.children.get(i);
            if(child.children.isEmpty()){
                node.children.remove(child);
            }
        }
        for (Node child:node.children){
            removeLeaves(child);
        }
    }
    //https://nados.io/question/linearize-a-generic-tree                              (seen)
    public static Node getTail(Node node){
        while (node.children.size() == 1){
            node = node.children.get(0);
        }
        return node;
    }
    public static void linearize(Node node){
        //we have faith that children of node know how to linearize themselves
        for (Node child:node.children){
            linearize(child);
        }
        while (node.children.size() > 1){
            Node ln = node.children.remove(node.children.size() -1); //remove last child node
            Node sln = node.children.get(node.children.size() -1); //second last child node
            Node tailNode = getTail(sln); //tail of second last child node
            tailNode.children.add(ln); //connect tail node with the last child node
        }
    }
    //https://nados.io/article/find-in-generic-tree
    public static boolean find(Node node, int data) {
       if(node.data == data){
           return true;
       }
       for (Node child: node.children){
           boolean ans = find(child , data);
           if(ans){
               return true;
           }
       }
       return false;
    }
    //https://nados.io/question/node-to-root-path-in-generic-tree
    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node.data ==  data){
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(node.data);
            return ans;
        }
        for (Node child:node.children){
            ArrayList<Integer> ans = nodeToRootPath(child , data);
            if(ans.size() > 0){
                ans.add(node.data);
            }
            return ans;
        }
        return new ArrayList<>();
    }
    //https://nados.io/question/lowest-common-ancestor-generic-tree
    public static int lca(Node node, int d1, int d2) {
        ArrayList<Integer> path1 = nodeToRootPath(node , d1);
        ArrayList<Integer> path2 = nodeToRootPath(node , d2);
        int i=path1.size()-1;
        int j=path2.size()-1;
        while (i>=0 && j>=0 && path1.get(i) == path2.get(j)){
            i--;
            j--;
        }
        return path1.get(i+1);
    }
    //https://nados.io/question/distance-between-two-nodes-in-a-generic-tree
    public static int distanceBetweenNodes(Node node, int d1, int d2){
        ArrayList<Integer> path1 = nodeToRootPath(node , d1);
        ArrayList<Integer> path2 = nodeToRootPath(node , d2);
        int i=path1.size()-1;
        int j=path2.size()-1;
        while (i>=0 && j>=0 && path1.get(i) == path2.get(j)){
            i--;
            j--;
        }
        return (i+1)+(j+1);
    }

    //https://nados.io/question/are-trees-similar-in-shape                       (seen)
    public static boolean areSimilar(Node n1, Node n2) {
       //check if nodes have same number of child nodes
        if(n1.children.size() != n2.children.size()){
            return false;
        }
        //children know how to check wether they are similar or not
        for (int i=0;i<n1.children.size();i++){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);
            if(!areSimilar(c1, c2)){
                return false;
            }
        }
        return true;
    }
    //https://nados.io/question/are-trees-mirror-in-shape
    public static boolean areMirror(Node n1, Node n2) {
        //make mirror of node n2 and then check wether they are similar or not
        mirror(n2);
        return areSimilar(n1,n2);
    }
    //areMirror with another approach
    public static boolean areMirror2(Node n1, Node n2) {
        if(n1.children.size() != n2.children.size()){
            return false;
        }
        //children know how to check wether they are similar or not
        for (int i=0;i<n1.children.size();i++){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(n2.children.size() -1-i);
            if(!areMirror(c1, c2)){
                return false;
            }
        }
        return true;
    }
    //https://nados.io/question/is-generic-tree-symmetric                     (seen)
    public static boolean IsSymmetric(Node node) {
        return areMirror(node,node);
    }

    //https://nados.io/article/predecessor-and-successor-of-an-element
    //code in NADOS editor only


    //https://nados.io/question/ceil-and-floor-in-generic-tree
    static int ceil;
    static int floor;
    public static void ceilAndFloor(Node node, int data) {
        if(node.data < data){
            floor = Math.max(floor , node.data);
        }
        else if(node.data > data){
            ceil = Math.min(ceil , node.data);
        }
        for(Node child:node.children){
            ceilAndFloor(child , data);
        }
    }

    //https://nados.io/question/kth-largest-element-in-tree                         (seen)
    public static int kthlarge(Node node, int k)
    {
        int data = Integer.MAX_VALUE;
        floor = Integer.MIN_VALUE;
        for (int i=0;i<k;i++){
            ceilAndFloor(node , data);
            data = floor;
            floor = Integer.MIN_VALUE;
        }
        return data;
    }

    //https://nados.io/article/node-with-maximum-subtree-sum
    static int mSum=Integer.MIN_VALUE;
    static int mSumNode=0;

    public static int subSumTree(Node node)
    {
        int sum =node.data;
        for (Node child:node.children){
            sum += subSumTree(child);
        }
        if(sum > mSum){
            mSum = sum;
            mSumNode = node.data;
        }
        return sum;


    }
    //https://nados.io/question/diameter-of-generic-tree         (seen)
    static int dia = 0;
    public static int diameter(Node root){
        int dch = -1;  //deepest child height
        int sdch = -1;  //second deepest child height
        for (Node child: root.children){
            int ch = diameter(child); //current height of child
            if(ch > dch){
                sdch = dch;
                dch = ch;
            }
            else if(ch > sdch){
                sdch = ch;
            }
        }
        if(dch + sdch + 2 > dia){          //update diameter if diameter(dch + sdch + 2) from node is greater than global diameter
            dia = dch + sdch + 2;
        }
        return dch+1;      //return height of node
    }


    //https://nados.io/question/iterative-preorder-and-postorder-of-generic-tree

    static class Pair{
        Node node;
        int state;
        Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativePreandPostOrder(Node node) {
        Stack<Pair> st = new Stack<>();
        String pre = "";
        String post = "";
        Pair p = new Pair(node , -1);
        st.push(p);
        while (st.size() > 0){
            Pair top = st.peek();
            if(top.state == -1){
                pre += top.node.data + " ";
                top.state++;
            }
            else if(top.state == top.node.children.size()){
                post += top.node.data + " ";
                st.pop();
            }
            else{
                Pair pair = new Pair(top.node.children.get(top.state) , -1);
                st.push(pair);
                top.state++;
            }
        }
        System.out.println(pre);
        System.out.println(post);
    }





}
