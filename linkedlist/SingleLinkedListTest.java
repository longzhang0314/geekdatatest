package com.zl.geekdata.linkedlist;

public class SingleLinkedListTest {

    public static void main(String[] args) {

    }
}


class SingleList {
    private Node head;


    //无头节点链表反转
    public Node reverseLinkedList(Node p) {
        Node pre = null;
        Node r = head;
        Node next = null;
        while (r != p) {
            next = r.next;

        }
        return null;
    }


    //判断两个链表是否一致
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        while (l != null && r != null) {
            if (l.data != r.data) {
                l = l.next;
                r = r.next;
            } else {
                break;
            }
        }
        if (l == null && r == null) {
            return true;
        }
        return false;
    }

    //删除指定节点
    public void deleteByNode(Node p) {
        if (head == null || p == null)
            return;

        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null)
            return;
        q.next = q.next.next;
    }

    //删除指定值的节点
    public void deleteByValue(int value) {
        if (head == null)
            return;
        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null)
            return;
        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }


    //value插入p节点前
    public void insertBefore(Node p, int value) {
        Node newNode = createNode(value);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null)
            return;
        if (p == head) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null)
            return;
        newNode.next = p;
        q.next = p;
    }

    //value插入p节点后
    public void insertAfter(Node p, int value) {
        Node newNode = createNode(value);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null)
            return;
        newNode.next = p.next;
        p.next = newNode;
    }

    //无头节点  尾插
    public void insertToTail(int value) {
        Node newNode = createNode(value);
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }

    //无头节点  头插
    public void insertToHead(int value) {
        Node newNode = createNode(value);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    //打印链表
    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    //根据索引查询
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            ++pos;
            p = p.next;
        }
        return p;
    }

    //根据值查询
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    //创建新节点
    public Node createNode(int value) {
        return new Node(value, null);
    }

}


class Node {
    int data;
    Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
