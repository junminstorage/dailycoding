package CrackingTheCodingInterview;

import java.util.*;

public class LinkedListQ {
    static class LinkedList {
        int val;
        LinkedList next;
    }

    /* 2.1
    Remove Dups: Write code to remove duplicates from an unsorted linked list.
     */
    static void removeDup(LinkedList head) {
        Set<Integer> visited = new HashSet<>();

        LinkedList curr = head;
        LinkedList pre = null;

        while(curr!=null){
            if(visited.contains(curr.val)){
                pre.next = curr.next;
            }

            visited.add(curr.val);
            curr = curr.next;
            pre = curr;
        }
    }

    /* 2.1
        FOLLOW UP
        How would you solve this problem if a temporary buffer is not allowed?
     */
    static void removeDupWithoutSpace(LinkedList head) {
        LinkedList curr = head;
        while(curr!=null){
            removeDupFrom(curr);
            curr = curr.next;
        }
    }

    static private void removeDupFrom(LinkedList node) {
        if(node == null)
            return;

        LinkedList curr = node.next;
        LinkedList pre = node;

        while(curr!=null) {
            if(curr.val == pre.val) {
                pre.next = curr.next;
            }else {
                pre = curr;
            }
            curr = curr.next;
        }
    }

    /* 2.2
    find k-th from linkedlist
     */
    static LinkedList findKthNode(LinkedList head, int k) {
        LinkedList p1 = head;
        while(k-->1 && p1!=null){
            p1 = p1.next;
        }
        LinkedList result = head;
        while(p1!=null && p1.next!=null) {
            p1 = p1.next;
            result = result.next;
        }
        return result;
    }
}
