public class Problem12 {

    /**
     * This problem was asked by Amazon.
     *
     * There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. 
     * Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
     *
     * For example, if N is 4, then there are 5 unique ways:
     *
     * 1, 1, 1, 1
     * 2, 1, 1
     * 1, 2, 1
     * 1, 1, 2
     * 2, 2
     * What if, instead of being able to climb 1 or 2 steps at a time, you could climb any 
     * number from a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
     */

    public static int steps(int n) {
        //dp[i] = dp[i-1] + dp[i-2]
        int p1 = 1, p2= 2;
        if(n==1)
            return p1;
        int p = 2;
        for(int i=3; i<=n; i++){
            p = p1 + p2;
            p1 = p2;
            p2 = p;
        }
        return p;
    }

    public static int steps(int n, int[] steps) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i<=n; i++) {
            dp[i] = 0;
            for(int step : steps) {
                if(i-step>=0)
                    dp[i] += dp[i-step];
            }
        }
        return dp[n];
    }
}
