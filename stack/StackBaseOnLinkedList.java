package com.zl.geekdata.stack;

//链表实现栈
public class StackBaseOnLinkedList {

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    Node top = null;

    //入栈  入在链表头
    public void push(int value) {
        Node newNode = new Node(value, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    //出栈(出头节点)
    public int pop() {
        if (top == null)
            return -1;
        int value = top.data;
        top = top.next;
        return value;
    }

    //打印
    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
}
