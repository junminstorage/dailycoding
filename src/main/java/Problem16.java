
public class Problem16 {
  /**
   * 
   * This problem was asked by Twitter.

You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:

record(order_id): adds the order_id to the log
get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
You should be as efficient with time and space as possible.

   */
  int[] buffer;
  int write;
  public Problem16(int n) {
    buffer = new int[n];
    write = 0;
  }

   public void record(int order_id) {
    buffer[write] = order_id;
    write++; 
    write %= buffer.length;
   }

   //assume i will always be valid, otherwise if 0 is returned that means there is no order_id
   public int get_last(int i) {
     if(write - i >=0) {
       return buffer[write-i];
     }else{
      return buffer[buffer.length + write - i];
     }
   }

}