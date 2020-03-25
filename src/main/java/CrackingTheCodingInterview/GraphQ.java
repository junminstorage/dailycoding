package CrackingTheCodingInterview;

import java.util.*;

public class GraphQ {
    static class Node {
        int val;
        Node[] neighbors;
        Node left, right;
        public Node(int v) {
            this.val = v;
        }
    }

    //4.1 route between nodes of directed graph
    /*
    Route Between Nodes: Given a directed graph, design an algorithm to find out whether
    there is a route between two nodes.
     */
    public boolean hasRoute(Node start, Node end) {
        Deque<Node> q = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        q.add(start);
        visited.add(start);

        while(!q.isEmpty()) {
            Node curr = q.remove();
            if(curr == end){
                return true;
            }

            for(Node neighbor : curr.neighbors){
                if(!visited.contains(neighbor)){
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return false;
    }

    //4.2
    /*
    Given a sorted (increasing order) array with unique integer elements,
    write an algorithm to create a binary search tree with minimal height.
     */
    public static Node minimalHeightBST(int[] numbers) {
        int len = numbers.length;
        return minimalHeightBSTRec(numbers, 0, len-1);
    }

    private static Node minimalHeightBSTRec(int[] numbers, int start, int end) {
        if(start > end)
            return null;

        if(start == end){
            return new Node(numbers[start]);
        }
        int mid = end - (end - start)/2;
        Node curr = new Node(numbers[mid]);
        curr.left = minimalHeightBSTRec(numbers, start, mid-1);
        curr.right = minimalHeightBSTRec(numbers, mid+1, end);
        return curr;
    }

    //4.3
    /*
    List of Depths: Given a binary tree, design an algorithm which creates a
    linked list of all the nodes at each depth (e.g., if you have a tree with depth 0,
    you'll have 0 linked lists).
     */
    public List<List<Node>> listOfDepaths(Node root) {
        List<List<Node>> list = new ArrayList<>();
        List<Node> curr = new ArrayList<>();

        if(root!=null) {
            curr.add(root);
            list.add(curr);
        }

        while(!curr.isEmpty()) {
            List<Node> next = new ArrayList<>();
            for(Node node : curr) {
                if(node.left!=null)
                    next.add(node.left);
                if(node.right!=null)
                    next.add(node.right);
            }
            if(!next.isEmpty())
                list.add(next);

            curr = next;
        }

        return list;
    }

}
