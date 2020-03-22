public class LeetCodeWeeklyContest {

    /*
    Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.
     */
    //https://leetcode.com/contest/weekly-contest-181/problems/check-if-there-is-a-valid-path-in-a-grid/

    Map<Integer, List<String>> dirMap = new HashMap<>();
    {dirMap.put(1, Arrays.asList("L", "R"));
        dirMap.put(2, Arrays.asList("U", "D"));
        dirMap.put(3, Arrays.asList("L", "D"));
        dirMap.put(4, Arrays.asList("D", "R"));
        dirMap.put(5, Arrays.asList("L", "U"));
        dirMap.put(6, Arrays.asList("R", "U"));}

    Map<String, List<Integer>> streetMap = new HashMap<>();
    {streetMap.put("U", Arrays.asList(2, 3, 4));
        streetMap.put("D", Arrays.asList(2, 5, 6));
        streetMap.put("L", Arrays.asList(1, 4, 6));
        streetMap.put("R", Arrays.asList(1, 3, 5));}

    private List<Integer> next(int current, String dir) {
        if(dirMap.get(current).contains(dir)){
            return streetMap.get(dir);
        }
        return new ArrayList<>();
    }

    public boolean hasValidPathBFS(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});
        while(!queue.isEmpty()) {
            int[] current = queue.remove();
            int r = current[0], c = current[1];
            if(r == rows-1 && c == cols-1)
                return true;
            visited[r][c] = true;
            //up
            if(r-1>=0 && !visited[r-1][c] && next(grid[r][c], "U").contains(grid[r-1][c]))
                queue.add(new int[]{r-1, c});
            //down
            if(r+1<rows && !visited[r+1][c] && next(grid[r][c], "D").contains(grid[r+1][c]))
                queue.add(new int[]{r+1, c});
            //L
            if(c-1>=0 && !visited[r][c-1] && next(grid[r][c], "L").contains(grid[r][c-1]))
                queue.add(new int[]{r, c-1});
            //R
            if(c+1<cols && !visited[r][c+1] && next(grid[r][c], "R").contains(grid[r][c+1]))
                queue.add(new int[]{r, c+1});
        }

        return false;

    }



    public boolean hasValidPathDFS(int[][] grid) {
        int m = grid.length, n=grid[0].length;
        int[][] roads = new int[m*3][n*3];
        for(int r = 0; r<m; r++) {
            for(int c=0; c<n; c++) {
                if(grid[r][c] == 1){ //-
                    roads[3*r+1][3*c] = 1;
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+1][3*c+2] = 1;
                }else if(grid[r][c] == 2){// |
                    roads[3*r][3*c+1] = 1;
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+2][3*c+1] = 1;
                }
                else if(grid[r][c] == 3){// |
                    roads[3*r+1][3*c] = 1;
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+2][3*c+1] = 1;
                }
                else if(grid[r][c] == 4){// |
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+1][3*c+2] = 1;
                    roads[3*r+2][3*c+1] = 1;
                }
                else if(grid[r][c] == 5){// |
                    roads[3*r][3*c+1] = 1;
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+1][3*c] = 1;
                }
                else if(grid[r][c] == 6){// |
                    roads[3*r][3*c+1] = 1;
                    roads[3*r+1][3*c+1] = 1;
                    roads[3*r+1][3*c+2] = 1;
                }
            }
        }

        boolean[][] visited = new boolean[m*3][n*3];
        for(int r = 0; r<3; r++) {
            for(int c = 0; c<3; c++) {
                dfs(roads, r, c, visited);
            }
        }

        for(int r = 3*m-1; r>= 3*m-3; r--){
            for(int c=3*n-1; c>=3*n-3; c--){
                if(visited[r][c])
                    return true;
            }
        }
        return false;

    }

    private void dfs(int[][] roads, int r, int c, boolean[][] visited) {
        if(r<0 || c<0 || r>=roads.length || c>=roads[0].length)
            return;
        if(roads[r][c]==0)
            return;
        if(visited[r][c])
            return;
        visited[r][c] = true;
        dfs(roads, r-1, c, visited);
        dfs(roads, r+1, c, visited);
        dfs(roads, r, c-1, visited);
        dfs(roads, r, c+1, visited);
    }
}
