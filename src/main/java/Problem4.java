/*
2020/3/22
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */

public class Problem4 {

    //https://leetcode.com/problems/first-missing-positive/
    /**
     * the key of this solution is to use the number at index to store the positive number
     * number[index] = -Math.abs(number[index]) where index = num
     *
     * how about the non-positive numbers? We can set them to len+2 so they won't play in any roles
     * in the algorithm since the first missing positive will less or equal to len+1
     */
    public int firstMissingPositive(int[] numbers) {
        int len = numbers.length;
        if(len == 0)
            return 1;
        int max = numbers[0];
        for(int i=0; i<len; i++){
            max = Math.max(max, numbers[i]);
            if(numbers[i] <=0)//make non-positive not play not role in line 13-14
                numbers[i] = len+2;
        }

        for(int i=0; i<len; i++){
            int original = Math.abs(numbers[i]);
            if(original<len){
                numbers[original] = -Math.abs(numbers[original]);
            }
        }

        for(int i=1; i<len; i++){
            if(numbers[i]>0)
                return i;
        }

        if(max == len)
            return len+1;
        else
            return len;

    }


}
