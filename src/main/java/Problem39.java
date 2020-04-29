
import java.util.*;

public class Problem39 {

  /**
   * This problem was asked by Dropbox.
   * 
   * Conway's Game of Life takes place on an infinite two-dimensional board of
   * square cells. Each cell is either dead or alive, and at each tick, the
   * following rules apply:
   * 
   * Any live cell with less than two live neighbours dies. Any live cell with two
   * or three live neighbours remains living. Any live cell with more than three
   * live neighbours dies. Any dead cell with exactly three live neighbours
   * becomes a live cell. A cell neighbours another cell if it is horizontally,
   * vertically, or diagonally adjacent.
   * 
   * Implement Conway's Game of Life. It should be able to be initialized with a
   * starting list of live cell coordinates and the number of steps it should run
   * for. Once initialized, it should print out the board state at each step.
   * Since it's an infinite board, print out only the relevant coordinates, i.e.
   * from the top-leftmost live cell to bottom-rightmost live cell.
   * 
   * You can represent a live cell with an asterisk (*) and a dead cell with a dot
   * (.).
   * 
   * 
   */

  public void gameOfLifeLimited(int[][] board) {
    int rows = board.length, cols = board[0].length;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int count = count(board, r, c);
        if (board[r][c] == 0) {
          if (count == 3) // case 1
            board[r][c] = -1;
        } else {
          if (count == 0) { // case 2
            board[r][c] = 9;
          } else
            board[r][c] = count;
        }
      }
    }

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        board[r][c] = liveOrDie(board[r][c]);
      }
    }
  }

  private int count(int[][] board, int r, int c) {
    int rows = board.length, cols = board[0].length;
    int sum = 0;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (r + i >= 0 && r + i < rows && c + j >= 0 && c + j < cols && !(i == 0 && j == 0)) {
          if (board[r + i][c + j] > 0)
            sum++;
        }
      }
    }
    return sum;
  }

  private int liveOrDie(int j) {
    if (j == -1) // case 1 0 with 3 1s
      return 1;
    else if (j == 9) // case 2
      return 0;
    else if (j < 2) {
      return 0;
    } else if (j == 2 || j == 3) {
      return 1;
    } else if (j > 3)
      return 0;
    return 0;
  }

  // if board is unlimited
  // we store all live cell in a set
  public Set<List<Integer>> gameOfLifeUnlimited(Set<List<Integer>> lives) {
    // store counters in each cell
    Map<List<Integer>, Integer> visited = new HashMap<>();

    // store next list of cells needs to be updated
    Set<List<Integer>> current = new HashSet<>(lives);

    while (current.size() > 0) {
      Set<List<Integer>> candidates = new HashSet<>();
      for (List<Integer> cell : current) {
        int count = count(lives, cell.get(0), cell.get(1));
        visited.put(cell, count);

        if (lives.contains(cell)) {
          // go through live's neighbors
          // add each to the candidates
          for (int i = -1; i <= 1; i++) {
            for (int j = 1; j <= 1; j++) {
              if (i != 0 || j != 0) {
                List<Integer> neighbor = Arrays.asList(cell.get(0) + i, cell.get(1) + j);
                if (!visited.containsKey(neighbor)) {
                  candidates.add(neighbor);
                }
              }
            }
          }
        }
      }
      current = candidates;
    }

    // go through visited and generates new list of lives
    Set<List<Integer>> result = new HashSet<>();
    for (List<Integer> key : visited.keySet()) {
      int count = visited.get(key);
      if( lives.contains(key) && count == 2 || count ==3)
          result.add(key);
    }
    return result;
  }

  private int count(Set<List<Integer>> lives, int r, int c) {
    int sum = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = 1; j <= 1; j++) {
        if (i != 0 || j != 0) {
          List<Integer> neighbor = Arrays.asList(r + i, c + j);
          if (lives.contains(neighbor)) {
            sum++;
          }
        }
      }
    }
    return sum;
  }

}