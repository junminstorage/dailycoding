package CrackingTheCodingInterview;

public class TreeQ {

    static class Node {
        Node left, right, parent;
    }

    public static Node postOrderSuccessor(Node root, Node target) {
        Node parent = target.parent;
        if(parent !=null) {
            if(parent.left == target) {
                if(parent.right == null)
                    return parent;
                else{
                    //find "left-most" or first leaf if we look at the subtree rooted at parent.right
                    return findNext(parent.right);
                }
            }else{
                return parent;
            }
        }else{
            //target is root
            return null;
        }
    }

    public static Node findNext(Node curr) {
        if(curr.left!=null)
            return findNext(curr.left);
        else if(curr.right!=null)
            return findNext(curr.right);
        else
            return curr;
    }

    //given a binary tree, and each of node has parent pointer as well
    //find a given node's in-order traveral successor
    public static Node inOrderSuccessor(Node root, Node target) {
        if(target.right == null) {
            Node found =  dfs(root, target);
            if(found == target)
                return null;
            else
                return found;
        }
        else{
            /*
              target
                 \
                 right
                 /
                left
                /
               left_most
             */
            Node right = target.right;
            while(right.left!=null)
                right = right.left;
            return right;
        }
    }

    /*
    //find parent node for target node which is
    //either left child of the parent
    //or right child of the left node of the parent
      p
     /
    target

      P
     /
    node
     \
     target
    */
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
                //update the returned value to parent
                if(found == target)
                    return curr;
                else
                    return found;
            }
        }
    }


}
