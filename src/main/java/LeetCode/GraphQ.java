package LeetCode;
import java.util.*;
public class GraphQ {
    Map<Integer, List<Integer>> graph;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        graph = new HashMap<>();
        for(int[] pair : richer) {
            if(!graph.containsKey(pair[1]))
                graph.put(pair[1], new ArrayList<>());
            graph.get(pair[1]).add(pair[0]);
        }
        int len = quiet.length;
        int[] result = new int[len];
        for(int i=0; i<len; i++)
            result[i] = -1;
        for(int i = 0; i<len; i++) {
            if(result[i] == -1)
                dfs(i, result, quiet);
        }
        return result;
    }
    private int dfs(int node, int[] result, int[] quiet) {
        if(result[node] > -1)
            return result[node];
        
        if(!graph.containsKey(node)){
            result[node] = node;
            return result[node];
        }
        
        int min = node;
        for(int child : graph.get(node)) {
            int next = dfs(child, result, quiet);
            if(quiet[next] < quiet[min])
                min = next;
        }
        result[node] = min;
        return min;
    }
}