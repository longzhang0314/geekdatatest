package com.zl.geekdata.linkedlist;

/**
 * 单链表
 */
public class LinkedListTest {

    /**
     * 节点定义
     */
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    //打印
    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //尾
    public static void insertTailByValue(Node list, int value) {
        Node newNode = createNode(value);
        insertTailByNode(list, newNode);
//        if (list == null) {
//            list = createNode(value);
//        } else {
//            Node q = list;
//            while (q.next != null) {
//                q = q.next;
//            }
//            newNode.next = q.next;
//            q.next = newNode;
//        }
    }

    //尾
    public static void insertTailByNode(Node list, Node p) {
        if (list == null) {
            list = p;
        } else {
            Node q = list;
            while (q.next != null) {
                q = q.next;
            }
            p.next = list.next;
            list.next = p;
        }
    }

    //头
    public static void insertHeadByValue(Node list, int value) {
        Node newNode = createNode(value);
        insertHeadByNode(list, newNode);
    }

    //头
    public static void insertHeadByNode(Node list, Node p) {
        if (list == null) {
            list = p;
        } else {
            p.next = list;
            list = p;
        }
    }

    //根据value查Node
    public static Node findByValue(Node list, int value) {
        Node p = list;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    //根据index查Node
    public static Node findByIndex(Node list, int index) {
        Node p = list;
        int pos = 0;
        while (p != null && pos != index) {
            ++pos;
            p = p.next;
        }
        return p;
    }

    //根据value删除节点
    public static void deleteByValue(Node list, int value) {
        if (list == null)
            return;
        Node p = list;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null)
            return;
        if (q == null) {
            list = list.next;
        } else {
            q.next = q.next.next;
        }
    }

    //根据Node删除节点
    public static void deleteByNode(Node list, Node p) {
        if (list == null || p == null)
            return;
        if (list == p) {
            list = list.next;
            return;
        }

        Node q = list;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null)
            return;
        q.next = q.next.next;
    }

    //判断两个链表是否一致
    public static boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
            } else {
                break;
            }
        }
        if (l == null && r == null)
            return true;
        return false;
    }


    //单链表反转
    public static Node reverse(Node list) {
        Node cur = list, pre = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    //检测链表是否成环
    public static boolean checkCircle(Node list) {
        if (list == null) {
            return false;
        }
        Node fast = list.next;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;

    }

    //有序链表合并 leetCode21
    public Node mergeTwoLists(Node l1, Node l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Node head;
        Node p = l1;
        Node q = l2;
        if (l1.data < l2.data) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node r = head;

        while (p != null && q != null) {
            if (p.data < q.data) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
        }
        if (p != null) {
            r.next = p;
        } else {
            r.next = q;
        }
        return head;
    }

    //有序链表合并 leetCode21
    public Node mergeTwoLists2(Node l1, Node l2) {
        Node soldier = new Node(0);
        Node p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 == null) {
            p.next = l2;
        } else {
            p.next = l1;
        }
        return soldier.next;
    }

    //求中间节点
    public static Node findMiddleNode(Node list) {
        if (list == null)
            return null;
        Node slow = list;
        Node fast = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //删除倒数第K个节点，并返回删除后的链表
    public static Node deletaLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        //fast指针到达正数第K
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }
        //list为null或k大于链表长度返回list
        if (fast == null) {
            return list;
        }

        Node slow = list;//待删除节点
        Node prev = null;
        //fast指针到最后，slow指针到n-k，即倒数第k
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }


        if (prev == null) {//删除头节点
            list = list.next;
        } else {//删除其他节点
            prev.next = prev.next.next;
        }
        return list;
    }


}
