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

    static int numberOfUnival;

    public static int numberOfSubtree(Node root) {
        numberOfUnival = 0;
        isUnival(root);
        return numberOfUnival;
    }

    public static boolean isUnival(Node root) {
        if(root == null)
            return true;
        boolean left = isUnival(root.left);
        boolean right = isUnival(root.right);

        boolean hasSameValue = (root.left==null || root.left.v==root.v) && (root.right==null || root.right.v == root.v);

        boolean isUnival = left && right && hasSameValue;
        if(isUnival)
            numberOfUnival++;

        return isUnival;
    }
}
