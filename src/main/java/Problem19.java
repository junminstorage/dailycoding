
public class Problem19 {

  /**
   * This problem was asked by Facebook.

A builder is looking to build a row of N houses that can be of K different colors. He has a goal of minimizing cost 
while ensuring that no two neighboring houses are of the same color.

Given an N by K matrix where the nth row and kth column represents the cost to build the nth house with kth color, 
return the minimum cost which achieves this goal.
   */

   public int min(int[][] matrix) {
      int rows = matrix.length, cols = matrix[0].length;
      int[] dp = new int[cols]; 
      
      for(int h = 0; h < rows; h++){
        int[] next = new int[cols];
        for(int c = 0; c < cols; c++) {
          if(h == 0) {
            next[c] = matrix[0][c];
          }else{
            next[c] = matrix[h][c];

            int min = Integer.MAX_VALUE;
            for(int other = 0; other<cols; other++) {
              if(other!=c)
                 min = Math.min(min, dp[other]);
            }
            next[c] += min;
          }
        }
        dp = next;
      }

      int cost = dp[0];
      for(int d : dp){
        cost = Math.min(cost, d);
      }
      return cost;
   }

}