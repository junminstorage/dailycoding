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
    public boolean hasValidPath(int[][] grid) {
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
