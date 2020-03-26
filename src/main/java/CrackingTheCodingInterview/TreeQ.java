package CrackingTheCodingInterview;

public class TreeQ {

    static class Node {
        Node left, right;
    }
    //given a binary tree, and each of node has parent pointer as well
    //find a given node's in-order traveral successor
    public static Node inOrderSuccessor(Node root, Node target) {
        if(target.right == null)
            return dfs(root, target);
        else{
            Node right = target.right;
            while(right.left!=null)
                right = right.left;
            return right;
        }
    }

    //find parent node for target node
    //and target node is left child of the parent
    public static Node dfs(Node curr, Node target) {
        if(curr == null)
            return null;
        if(curr == target)
            return curr;
        else{
            Node found = dfs(curr.left, target);
            if(found == null)
                return dfs(curr.right, target);
            else{
                if(found == target)
                    return curr;
                else
                    return found;
            }
        }
    }


}
