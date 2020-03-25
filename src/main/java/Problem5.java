import java.util.*;

public class Problem5 {

    /**
     * An XOR linked list is a more memory efficient doubly linked list.
     * Instead of each node holding next and prev fields, it holds a field named both,
     * which is an XOR of the next node and the previous node. Implement an XOR linked list;
     * it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.
     *
     * If using a language that has no pointers (such as Python), you can assume you have
     * access to get_pointer and dereference_pointer functions that converts between nodes and memory addresses.
     */
    static class XORList {
        //in java there is no way to do so,
        //here we use Node to simplify the a XOR pointer

        static class Node {
            int val;
            long xor;
            Node(int v){
                this.val = v;
            }

        }

        //placeholder func
        static Node getNode(long address) {
            if(address == 0)
                return null;
            return new Node(1);
        }

        //placeholder func
        static long addAddress(Node n) {
            if(n==null)
                return 0;
            return (long)n.hashCode();
        }

        Node head, tail;

        public void add(int val) {
            Node node = new Node(val);
            if(tail==null){
                head = tail = node;
            }else{
                if(tail.xor == 0) {
                    tail.xor = addAddress(node);
                }else{
                    tail.xor = tail.xor ^ addAddress(node);
                }
                node.xor = addAddress(tail);
                tail = node;
            }
        }

        public Node get(int index) {
            Node curr = head;
            long preAddress = 0;
            while(index-->-1 && curr!=null){
                //move curr
                Node next = getNode(curr.xor ^ preAddress);
                preAddress = addAddress(curr);
                curr = next;
            }
            return curr;
        }
    }




}
