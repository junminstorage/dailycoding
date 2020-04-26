
import java.util.*;

public class Problem38 {
  /**
   * 
   * This problem was asked by Microsoft.

You have an N by N board. Write a function that, given N, 
returns the number of possible arrangements of the board where N queens 
can be placed on the board without threatening each other, i.e. 
no two queens share the same row, column, or diagonal.
   */

   public int queens(int N) {
    return rec(N, new ArrayList<>(), 0);
   }

   private int rec(int N, List<Integer> rows, int row) {
    if(row == N)
      return 1;
    int sum = 0;
    for(int i = 0; i < N; i++) {
      if(canPlace(rows, i)) {
        rows.add(i);
        sum += rec(N, rows, row+1);
        rows.remove(rows.size()-1);
      }
    }
    return sum;
   }

   private boolean canPlace(List<Integer> indices, int pos) {
     int size = indices.size();
     for(int row = 0; row < size; row++) {
       //row, indices.get(row) - size, pos
      if(pos == indices.get(row) ||  ( Math.abs(size - row ) == Math.abs( pos - indices.get(row))  ) )
        return false;
     }
     return true;
   }
}