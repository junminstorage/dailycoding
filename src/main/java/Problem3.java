import java.util.*;
/*
This problem was asked by Google.

Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s),
which deserializes the string back into the tree.

For example, given the following Node class

class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
The following test should pass:

node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left'
 */
public class Problem3 {
    static class Node {
        String value;
        Node left, right;
        Node(String v) {
            this.value = v;
        }
        @Override
        public String toString() {
            return this.value + this.left.toString() + this.right.toString();
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        List<String> list = new ArrayList<>();
        List<Node> q = new ArrayList<>();
        if(root!=null)
            q.add(root);
        while(!q.isEmpty()) {
            List<Node> level = new ArrayList<>();
            for(Node curr : q) {
                if(curr==null){
                    list.add("null");
                }
                else{
                    list.add(""+curr.value);
                    level.add(curr.left);
                    level.add(curr.right);
                }
            }
            q = level;
        }
        return String.join(",", list);
    }

    // Decodes your encoded data to tree.
    public static Node deserialize(String data) {
        if(data == null || data.length() == 0)
            return null;

        String[] nodes = data.split(",");
        Node root = null;
        List<Node> current = new ArrayList<>();
        if(!nodes[0].equals("null")) {
            root = new Node(nodes[0]);
            current.add(root);
        }
        int index = 1;
        while(!current.isEmpty()) {
            List<Node> next  = new ArrayList<>();
            for(Node node : current) {
                if(!nodes[index].equals("null")){
                    Node left = new Node(nodes[index]);
                    node.left = left;
                    next.add(left);
                }

                index++;
                if(!nodes[index].equals("null")){
                    Node right = new Node(nodes[index]);
                    node.right = right;
                    next.add(right);
                }
                index++;
            }
            current = next;
        }

        return root;
    }


}
