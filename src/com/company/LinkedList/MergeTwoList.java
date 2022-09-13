package com.company.LinkedList;

import java.util.List;

public class MergeTwoList {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = null;

            if (size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }

            size++;
        }

        public int size() {
            return size;
        }

        public void display() {
            for (Node temp = head; temp != null; temp = temp.next) {
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                head = head.next;
                size--;
            }
        }

        public int getFirst() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else {
                return head.data;
            }
        }

        public int getLast() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else {
                return tail.data;
            }
        }

        public int getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
                return -1;
            } else {
                Node temp = head;
                for (int i = 0; i < idx; i++) {
                    temp = temp.next;
                }
                return temp.data;
            }
        }

        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;

            if (size == 0) {
                tail = temp;
            }

            size++;
        }

        public void addAt(int idx, int val) {
            if (idx < 0 || idx > size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                addFirst(val);
            } else if (idx == size) {
                addLast(val);
            } else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }
                node.next = temp.next;

                temp.next = node;
                size++;
            }
        }

        public void removeLast() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                Node temp = head;
                for (int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }

                tail = temp;
                tail.next = null;
                size--;
            }
        }

        public void removeAt(int idx) {
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                removeFirst();
            } else if (idx == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }

                temp.next = temp.next.next;
                size--;
            }
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            for (int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public void reverseDI() {
            int li = 0;
            int ri = size - 1;
            while (li < ri) {
                Node left = getNodeAt(li);
                Node right = getNodeAt(ri);

                int temp = left.data;
                left.data = right.data;
                right.data = temp;

                li++;
                ri--;
            }
        }

        public void reversePI() {
            if (size <= 1) {
                return;
            }

            Node prev = null;
            Node curr = head;
            while (curr != null) {
                Node next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            Node temp = head;
            head = tail;
            tail = temp;
        }

        public int kthFromLast(int k) {
            Node slow = head;
            Node fast = head;
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow.data;
        }

        public int mid() {
            Node f = head;
            Node s = head;

            while (f.next != null && f.next.next != null) {
                f = f.next.next;
                s = s.next;
            }

            return s.data;
        }

        public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
//            Node temp = new Node();
//            Node ptemp = temp;
//            Node p1 = l1.head;
//            Node p2 = l2.head;
//            while(p1 != null && p2 != null){
//                if(p1.data < p1.data){
//                    Node n = new Node();
//                    n.data = p1.data;
//                    ptemp.next = n;
//                    ptemp = n;
//                    p1 = p1.next;
//                }
//                else {
//                    Node n = new Node();
//                    n.data = p2.data;
//                    ptemp.next = n;
//                    ptemp = n;
//                    p2 = p2.next;
//                }
//            }
//            while(p1 != null){
//                Node n = new Node();
//                n.data = p1.data;
//                ptemp.next = n;
//                ptemp = n;
//                p1 = p1.next;
//            }
//            while(p2 != null){
//                Node n = new Node();
//                n.data = p2.data;
//                ptemp.next = n;
//                ptemp = n;
//                p2 = p2.next;
//            }

            LinkedList list = new LinkedList();
            Node p1 = l1.head;
            Node p2 = l2.head;
            while (p1 != null && p2 != null){
                if(p1.data < p2.data){
                    list.addLast(p1.data);
                    p1 = p1.next;
                }
                else {
                    list.addLast(p2.data);
                    p2 = p2.next;
                }
            }
            while (p1 != null){
                list.addLast(p1.data);
                p1 = p1.next;
            }
            while (p2 != null){
                list.addLast(p2.data);
                p2 = p2.next;
            }
            return list;


        }
        public static Node midNode(Node head,Node tail){
            Node s = head;
            Node f = head;
            while(f != tail && f.next != tail){
                s = s.next;
                f = f.next.next;
            }
            return s;
        }
        public static LinkedList mergeSort(Node head, Node tail){
            if(head == tail){
                LinkedList list = new LinkedList();
                list.addLast(head.data);
                return list;
            }
            Node mid = midNode(head,tail);
            LinkedList list1 = mergeSort(head , mid);
            LinkedList list2 = mergeSort(mid.next,tail);
            return mergeTwoSortedLists(list1 , list2);
        }
    }
}
