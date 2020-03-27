public class Problem8 {

    /**
     * This problem was asked by Google.
     *
     * A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
     *
     * Given the root to a binary tree, count the number of unival subtrees.
     *
     * For example, the following tree has 5 unival subtrees:
     *
     *    0
     *   / \
     *  1   0
     *     / \
     *    1   0
     *   / \
     *  1   1
     */

    static class Node {
        int v;
        Node left, right;
    }

    public static int numberOfSubtree(Node root) {
        if(root == null)
            return 0;
        int left = numberOfSubtree(root.left);
        int right = numberOfSubtree(root.right);
        if((root.left==null || root.left.v==root.v) && (root.right==null || root.right.v == root.v)){
            return left + right + 1;
        }else
            return left + right;
    }
}
