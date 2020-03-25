package CrackingTheCodingInterview;

public class TreeQ {

    static class Node {
        Node left, right;
    }
    //given a binary tree, and each of node has parent pointer as well
    //find a given node's in-order traveral successor
    public static Node inOrderSuccessor(Node root, Node target) {
        if(target.right == null)
            return dfs(root, target, null);
        else{
            Node right = target.right;
            while(right.left!=null)
                right = right.left;
            return right;
        }
    }

    //find parent node for target node
    public static Node dfs(Node curr, Node target, Node parent) {
        if(curr == target)
            return parent;
        else{
            Node found = dfs(curr.left, target, curr);
            if(found == null)
                found = dfs(curr.right, target, curr);
            return found;
        }
    }


}
