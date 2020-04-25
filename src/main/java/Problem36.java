
import java.util.*;

public class Problem36 {

  /**
   * This problem was asked by Dropbox.

Given the root to a 
binary search tree, find the second largest node in the tree.
   */

   class Node {
     int v;
     Node left, right;
   }

   //O(lgn)
   public static Node secondLargestIteratively(Node root) {
     if(root==null)
      return null;

     Node right = root;
     Node parent = null;

     while(right.right!=null){
       parent = right;
       right = right.right;
     }

     //find the right most node which is the largest node
     if(right.left!=null) {
      //find the right most node of its left subtree
      Node result = right.left;
      while(result.right!=null)
        result = result.right;
      return result;
     }else {
       return parent;
     }

   }

      //O(n)
      public static Node secondLargestUsingStack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Node pre = null;
        Node result = null;
        while(!stack.isEmpty() || curr!=null) {
          if(curr!=null){
            stack.push(curr);
            curr = curr.left;
          }else{
            Node p = stack.pop();
            if(pre !=null)
              result = pre;
            pre = p;
            p = p.right;
          }
        }
        return result;
       }

}