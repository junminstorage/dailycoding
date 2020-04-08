import java.util.*;

public class Problem18 {
  /**
   * https://leetcode.com/problems/sliding-window-maximum/
   * 
   * Given an array nums, there is a sliding window of size k which is moving from the very left of the array
   * to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
   * by one position. Return the max sliding window.
   *
   * Follow up:
   * Could you solve it in linear time?
   *
   * Example:
   *
   * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
   * Output: [3,3,5,5,6,7]
   * @param nums
   * @param k
   * @return
   */

  public int[] maxSlidingWindow(int[] nums, int k) {
    int len=nums.length;
    int[] max = new int[len-k+1];
    Deque<Integer> q = new ArrayDeque<>();

    for(int i=0; i<len; i++){
      //remove the i-k from the head of q
      if(i-k>=0 && nums[i-k] == q.peekFirst())
        q.poll();

      //add i to the back of q
      while(!q.isEmpty() && q.peekLast() < nums[i])
        q.removeLast();
      q.offer(nums[i]);

      if(i-k+1>=0)
        max[i-k+1] = q.peekFirst();
    }

    return max;

  }

}