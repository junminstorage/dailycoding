
import java.util.*;

public class Problem17 {

  /**
   * This problem was asked by Google.
  Suppose we represent our file system by a string in the following manner:
  The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext

        We are interested in finding the longest (number of characters) absolute path to a file within our file system. 
        For example, in the second example above, 
        the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

  Given a string representing the file system in the above format, 
  return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.

Note:

The name of a file contains at least a period and an extension.
The name of a directory or sub-directory will not contain a period.
   */

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

   private int pos;

   public int longestPathStr(String str) {

    Arrays.hashCode(new int[]{1, 2});

     //reset pos
     pos = 0;
     //get root dir name
     String dir = getDirName(str, 0);
     //assume top level is always a directory
     Node root = new Node(dir, 0, false);
     //construct the graph of dir structure
     rec(str, root);
     return dfs(root);
   }

   private int dfs(Node root) {
     if(root.isFile) {
       return root.dir.length()+1;
     }

     if(root.children.size() == 0)
      return 0;

    boolean flag = false;
    int curr = root.dir.length() + 1; // /dir
    int max = 0;
     for(Node child : root.children) {
       int next = dfs(child);
       if(next > 0){
         flag = true;
         max = Math.max(max, next);
       }
     }
     return flag?curr + max:0;
   }

   private boolean hasDirWithTabs(String str, int tab) {
    int p = pos;

    while(tab-->0 && p<str.length() && str.charAt(p) == '\t'){
      p++;
    }

    return tab == 0 && p<str.length() && str.charAt(p) != 't';

   }

   private String getDirName(String str, int tab) {
    //eating tabs 
    while(tab-->0 && str.charAt(pos) == '\t') {
       pos++;
    }

    //get dir name until \n
    StringBuilder sb = new StringBuilder();
    while(str.charAt(pos) != '\n') {
      sb.append(str.charAt(pos));
      pos++;
    }

    pos++;

    return sb.toString();

   }

   private void rec(String str, Node parent) {
     int tabs = parent.level+1; 
     
     while(true) {
      if(!hasDirWithTabs(str, tabs))
        break;
      //find dir with #tab tabs
      String dir = getDirName(str, tabs);
      //make node
      Node node = new Node(dir, tabs, dir.contains("."));
      parent.children.add(node);
      //find its children
      rec(str, node);
    }
   }

}