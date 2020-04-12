import java.util.ArrayDeque;
import java.util.Deque;

public class Problem23 {

    /**
     * You are given an M by N matrix consisting of booleans that represents a board.
     * Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
     *
     * Given this matrix, a start coordinate, and an end coordinate, return the minimum number
     * of steps required to reach the end coordinate from the start. If there is no possible path, then return null.
     * You can move up, left, down, and right. You cannot move through walls.
     * You cannot wrap around the edges of the board.
     */

    public int minSteps(boolean[][] board, int[] start, int[] end) {
        int step = 0;
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(start);
        visited[start[0]][start[1]]= true;
        boolean found = false;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            if(curr[0] == end[0] && curr[1] == end[1]){
                found = true;
                return step;
            }
            step++;
            move(curr[0]-1, curr[1],  board, visited, q);
            move(curr[0]+1, curr[1], board, visited, q);
            move(curr[0], curr[1]-1, board, visited, q);
            move(curr[0], curr[1]+1, board, visited, q);
        }

        return found? step : -1;
    }

    private void move(int r, int c, boolean[][] board, boolean[][] visited, Deque<int[]> q) {
        if(r<0 || c<0 || r ==  board.length || c == board[0].length)
            return;

        if(!visited[r][c]) {
            q.add(new int[]{r, c});
            visited[r][c] = true;
        }
    }

}
