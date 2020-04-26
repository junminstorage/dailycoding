package LeetCode;

import java.util.*;

public class Contest186 {

  /**
   * https://leetcode.com/contest/weekly-contest-186/problems/constrained-subset-sum/
   * 
   * Given an integer array nums and an integer k, return the maximum sum of 
   * a non-empty subset of that array such that for every two consecutive integers in the subset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

    A subset of an array is obtained by deleting some number of elements (can be zero) 
    from the array, leaving the remaining elements in their original order.

    Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subset is [10, -2, -5, 20].

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subset is [10, 2, 5, 20].
 
   */
  class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = dp[0] = nums[0];
        
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(max);
        
        for(int i = 1; i<len; i++) {
            if( i-k-1 >=0 && q.peekFirst() == dp[i-k-1] )
                q.removeFirst();
            
            dp[i] = nums[i];
            dp[i] = Math.max(dp[i], q.peekFirst() + nums[i]);
            
            while(q.size() > 0  && q.peekLast() < dp[i]){
                q.removeLast();
            }
            
            q.addLast(dp[i]);
         
            max = Math.max(max, dp[i]);
        }
        
        return max;
        
    }
}

}