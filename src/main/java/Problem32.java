
import java.util.*;

public class Problem32 {

  /**
   * Good morning! Here's your coding interview problem for today.

This problem was asked by Jane Street.

Suppose you are given a table of currency exchange rates, represented as a 2D array. 
Determine whether there is a possible arbitrage: that is, whether there is some sequence of trades you can make, 
starting with some amount A of any currency, so that you can end up with some amount greater than A of that currency.

There are no transaction costs and you can trade fractional quantities.
   */

   /**
    * 
    Solution, the goal here is to find a path from source --> ... --> source, such that
     r1 * r2 * r3 * r4 > 1 =>
     logR1 + logR2 + logR3 + logR4 > 0 =>
     -logR1 - logR2 - logR3 - logR4 < 0 =>
     a negative cycle => bellman-ford algorithm to detect negative cycle
    */

    public boolean hasArbitrage(double[][] rates, int source) {
      int size = rates.length;
      for(int i = 0; i< size; i++){
        for(int j = 0; j< size; j++ ){
          rates[i][j] = -Math.log(rates[i][j]);
        }
      }

      double[] dist = new double[size];
      Arrays.fill(dist, Double.MAX_VALUE);
      dist[source] = 0;

      int[] pre = new int[size];

      for(int times = 0; times < size; times++) { // go through each edges N times
        for(int r = 0; r< size; r++){
          for(int c = 0; c< size; c++) {
            if(r==c)
              continue;
            
            if(dist[c] > dist[r] + rates[r][c]){
              dist[c] = dist[r] + rates[r][c];
              pre[c] = r;
            }
            
          }
        }
    }

    for(int r = 0; r< size ; r++) {
      for(int c = 0; c<size; c++) {
        if(dist[c] > dist[r] + rates[r][c]){
          //find r->c is part of negative cycle
          return true;
        }
      }
    }

    return false;

    }

}