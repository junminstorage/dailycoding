public class Problem9 {

    /*
    Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
    Numbers can be 0 or negative.

    For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10,
    since we pick 5 and 5.

    Follow-up: Can you do this in O(N) time and constant space?
    */
     public static int sum(int[] numbers) {
         if(numbers.length == 0)
             return 0;

         int p0 = 0, p1 = numbers[0], len = numbers.length, max = Math.max(p0, p1);

         for(int i=1; i<len; i++) {
            int temp0 = Math.max(p1, p0);//with number at i
            int temp1 = p0 + numbers[i];//with number at i
            max = Math.max(temp0, temp1);
            p0 = temp0;
            p1 = temp1;
         }
         return max;
     }
}
