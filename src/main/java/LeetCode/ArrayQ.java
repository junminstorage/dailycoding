package LeetCode;

class ArrayQ {
  /**
   * Problem 845. Wekly contest 87
   * https://leetcode.com/contest/weekly-contest-87/problems/longest-mountain-in-array/
   * 
   * Let's call any (contiguous) subarray B (of A) a mountain if the following
   * properties hold:
   * 
   * B.length >= 3 There exists some 0 < i < B.length - 1 such that B[0] < B[1] <
   * ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1] (Note that B could be any
   * subarray of A, including the entire array A.)
   * 
   * Given an array A of integers, return the length of the longest mountain.
   * 
   * Return 0 if there is no mountain.
   * 
   * Example 1:
   * 
   * Input: [2,1,4,7,3,2,5] Output: 5 Explanation: The largest mountain is
   * [1,4,7,3,2] which has length 5. Example 2:
   * 
   * Input: [2,2,2] Output: 0 Explanation: There is no mountain. Note:
   * 
   * 0 <= A.length <= 10000 0 <= A[i] <= 10000 Follow up:
   * 
   * Can you solve it using only one pass? Can you solve it in O(1) space?
   */

  public static int longestMountain(int[] A) {
    // initial state
    int number = 1;
    boolean flag = false; //false: going up, true: going down

    int max = 0;
    for (int i = 1; i < A.length; i++) {
      if (A[i] > A[i - 1]) {//going up
        if (flag) {// was going down now start to go up a mountain
          flag = false;
          number = 2;
        } else {// continue going up a mountain
          number++;
        }
      } else if (A[i] < A[i - 1]) {//going down
        if (flag) {
          if (number > 2)// continue going down a mountain
            number++;
          else {
            // no mountain, doing nothing, just going down
          }
        } else {
          if (number > 1) {//going up at lease twice since, so this is a mountain, and start to go down a mountain
            number++;
          } else {// previous was initial state, no mountain, just going down
            number = 0;
          }
          flag = true;

        }
      } else {// going flat, reset to initial state
        flag = false;
        number = 1;
      }

      if (flag)
        max = Math.max(max, number);
    }

    return max;
  }

}