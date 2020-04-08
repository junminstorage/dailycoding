public class Problem20 {

    /**
     * This problem was asked by Google.
     *
     * Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.
     *
     * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.
     *
     * In this example, assume nodes with the same value are the exact same node objects.
     *
     * Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.
     */
    static class LinkedList {
        int val;
        LinkedList next;
    }
    public LinkedList intersect(LinkedList list1, LinkedList list2) {
        int len1 = len(list1), len2 = len(list2);
        int diff = Math.abs(len1-len2);
        LinkedList p1 = list1, p2 = list2;
        if(len2 > len1) {
            p2 = list1;
            p1 = list1;
        }

        while(diff-->0)
            p1 = p1.next;

        while(p1.val != p2.val){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    private int len(LinkedList list) {
        int len = 0;
        while(list!=null) {
            list = list.next;
            len++;
        }
        return len;
    }
}
