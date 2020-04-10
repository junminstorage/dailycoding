import java.util.*;

public class Problem22 {

    /**
     * This problem was asked by Microsoft.
     *
     * Given a dictionary of words and a string made up of those words (no spaces),
     * return the original sentence in a list. If there is more than one possible reconstruction,
     * return any of them. If there is no possible reconstruction, then return null.
     *
     * For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox",
     * you should return ['the', 'quick', 'brown', 'fox'].
     *
     * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond",
     * return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].

     */

    public String[] wordLadder(String str, List<String> words) {
        Set<String> set = new HashSet<>();
        for(String w : words)
            set.add(w);

        int len = str.length();
        int[] parents = new int[len+1];
        for(int i= 0; i<=len; i++)
            parents[i] = -1;

        parents[0] = 0;

        for(int i = 0; parents[i]>=0 && i<len; i++){
            for(int j = i+1; j<=len; j++) {
                if(set.contains(str.substring(i, j))) {
                    parents[j] = i;
                }
            }
            if(parents[len] >=0)
                break;
        }

        return getStringsFromParents(str, len, parents);
    }


    public List<String> dfsRec(String str, int pos, Set<String> dict, Map<Integer, List<String>> store) {
        int len = str.length();
        if(pos == 0)
            return new ArrayList<>();
        if(store.containsKey(pos))
            return store.get(pos);

        List<String> result = null;
        for(int next = pos+1; next <=len; next++) {
            String w = str.substring(pos, next);
            if(dict.contains(w)){
                result = dfsRec(str, next, dict, store);
                if(result != null){
                    result.add(w);
                    break;
                }
            }
        }
        store.put(pos, result);
        return result;
    }

    public List<String> bfs(String str, Set<String> dict) {
        Map<Integer, Integer> parents = new HashMap<>();
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        parents.put(0, -1);
        int len = str.length();
        while(!q.isEmpty()) {
            int pos = q.poll();

            for(int next = pos+1; next<=len; next++) {
                if(!parents.containsKey(next) && dict.contains(str.substring(pos, next))) {
                    parents.put(next, pos);
                    q.offer(next);
                }
            }

            if(parents.containsKey(len))
                break;
        }

        return getListFromParentsMap(str, len, parents);
    }

    public List<String> dfsIter(String str, Set<String> dict) {
        int len = str.length();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parents = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        parents.put(0, -1);
        while(!stack.isEmpty()) {
            int pos = stack.pop();
            if(visited.contains(pos))
                continue;
            visited.add(pos);
            for(int next = pos+1; next<=len; next++) {
                if (dict.contains(str.substring(pos, next))) {
                    parents.put(next, pos);
                    stack.push(next);
                }
            }
        }

       return getListFromParentsMap(str, len, parents);
    }

    private String[] getStringsFromParents(String str, int len, int[] parents) {
        List<String> list = new ArrayList<>();
        int p = len;
        while(p>0){
            list.add(str.substring(parents[p], p));
            p = parents[p];
        }

        Collections.reverse(list);
        return list.toArray(new String[0]);
    }

    private List<String> getListFromParentsMap(String str, int start, Map<Integer, Integer> parents) {
        List<String> list = new ArrayList<>();
        int p = start;
        while(p>0){
            list.add(str.substring(parents.get(p), p));
            p = parents.get(p);
        }
        Collections.reverse(list);
        return list;
    }
}
