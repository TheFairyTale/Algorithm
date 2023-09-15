package asia.dreamdropsakura.Algorithm.entity;

import java.io.Serializable;

/**
 * Definition for singly-linked list.
 */
public class ListNode implements Serializable {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
