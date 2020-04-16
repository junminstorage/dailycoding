import java.util.ArrayList;
import java.util.List;

public class BinrayLifting {
    int[] levelDown;
    int[] levelUp;
    int N, log, timer;
    List<Integer>[] adj;
    int[][] up;

    void preprocess(int N, int root) {
        levelDown = new int[N];
        levelUp = new int[N];
        adj = new ArrayList[N];
        //omit the code to prepopulat the adjacent list for each node 0 ... N-1
        log = (int) Math.ceil(Math.log10(2)/Math.log10(N));
        timer = 0;
        up = new int[N][log + 1];
        dfs(root, root);

    }

    //either dfs or bfs can work
    private void dfs(int current, int parent) {
        levelDown[current] = ++timer;
        up[current][0] = parent;
        for(int i=1; i<=up[0].length; i++) {
            //the key of binary lifting
            // up[current][0] -> the direct parent of current
            // up[current[1] -> the parent which is 2 level up
            // up[current][2] -> the parent which is 4 level up
            // up[current][2] = up[up[current][1]][1]
            up[current][i] = up[up[current][i-1]][i-1];
        }

        for(int child : adj[current]){
            if( child != parent)
                dfs(child, current);
        }

        levelUp[current] = ++ timer;
    }

    boolean isAncestor(int u, int v) {
        return levelDown[u] <= levelDown[v] && levelUp[u] >= levelUp[v];
    }

    int lca(int u, int v) {
        //* this take care of the situation if u/v are direct parent of each other
        if(isAncestor(u, v))
            return u;
        if(isAncestor(v, u))
            return v;
        // this take care of the case there is LCA between u and v
        for(int i = log; i>=0; i-- ) {
            // here we make sure u can only move to its parent if up[u][i]
            // is not parent of v
            //and u can finally reach LCA but one level down
            if(!isAncestor(up[u][i], v))
                u = up[u][i];
        }
        return up[u][0];
    }

}
