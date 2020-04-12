
import java.util.*;

public class Problem17 {

    /**
     * This problem was asked by Google.
     * Suppose we represent our file system by a string in the following manner:
     * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext\n\tsubdir3\n\t\tsubdir3\n\t\t\tfile4.ext" represents:
     * <p>
     * dir
     * subdir1
     * subdir2
     * file.ext
     * subdir3
     * file.ext
     * <p>
     * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
     * For example, in the second example above,
     * the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
     * <p>
     * Given a string representing the file system in the above format,
     * return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
     * <p>
     * Note:
     * <p>
     * The name of a file contains at least a period and an extension.
     * The name of a directory or sub-directory will not contain a period.
     */

    public static void main(String[] args) {
        System.out.println(new Problem17().longestPathStr("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(new Problem17().longestPathStr("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext\n\tsubdir3\n\t\tsubdir4\n\t\t\tfile4.ext"));
    }

    class Node {
        String dir;
        boolean isFile;
        int level;
        List<Node> children;

        public Node(String name, int l, boolean flag) {
            this.dir = name;
            this.level = l;
            this.isFile = flag;
            children = new ArrayList<>();
        }
    }

    private int pos; //pointer position when recursive going through the string

    public String longestPathStr(String str) {
        //reset pos
        pos = 0;
        //get root dir name
        String dir = getDirName(str, 0);
        //assume top level is always a directory
        Node root = new Node(dir, 0, false);
        //construct the graph of dir structure
        buildGraph(str, root);
        return dfs(root);
    }

    //get dirname at level  and move the pos as well
    private String getDirName(String str, int tab) {
        //eating \t
        while (tab-- > 0 && str.charAt(pos) == '\t') {
            pos++;
        }
        //get dir name until \n
        StringBuilder sb = new StringBuilder();
        while (pos < str.length() && str.charAt(pos) != '\n') {
            sb.append(str.charAt(pos));
            pos++;
        }
        //eating \n
        pos++;
        return sb.toString();
    }

    private void buildGraph(String str, Node parent) {
        int tabs = parent.level + 1;

        while (true) {
            if (!hasDirWithTabs(str, tabs))
                break;
            //find dir with #tab tabs
            String dir = getDirName(str, tabs);

            //make node
            Node node = new Node(dir, tabs, dir.contains("."));
            parent.children.add(node);
            //go deeper
            buildGraph(str, node);
        }
    }

    private String dfs(Node root) {
        if (root.isFile) {
            return "/" + root.dir;
        }

        if (root.children.size() == 0)
            return "";

        boolean flag = false; //contains files inside curr directory
        String curr = "/" + root.dir; // "/{dirname}"
        String max = "";
        for (Node child : root.children) {
            String next = dfs(child);
            if (next.length() > 0) {
                flag = true;
                if (max.length() < next.length())
                    max = next;
            }
        }
        return flag ? curr + max : "";
    }

    private boolean hasDirWithTabs(String str, int tab) {
        int p = pos;
        while (tab-- > 0 && p < str.length() && str.charAt(p) == '\t') {
            p++;
        }
        return tab == -1 && p < str.length() && str.charAt(p) != '\t';
    }

}