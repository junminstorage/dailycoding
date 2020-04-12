package LeetCode;

public class Contest184 {

    class Problem4 {
        
        private int[] row(int number) {
            int[] row = new int[3];
            int index = 0;
            while(number>0){
                row[index] = number%3;
                number /= 3;
                index++;
            }
            return row;
        }

        private boolean canPaint(int number) {
            int[] rows = row(number);
            return rows[0]!=rows[1] && rows[1]!=rows[2];
        }

        private boolean canPaint(int number1, int number2) {
            int[] row1 = row(number1), row2 = row(number2);
            return (row1[0]!=row2[0]) && (row1[1]!=row2[1]) && (row1[2]!=row2[2]);
        }

        public int numOfWays(int n) {
            int MOD = 1_000_000_007;
            int[][] dp = new int[n][27];

            for(int row = 0; row<n; row++) {
                for(int paint = 0; paint<27; paint++) {
                    if(!canPaint(paint)){
                        dp[row][paint] = 0;
                        continue;
                    }

                    if(row==0){
                        dp[row][paint] = 1;
                    }else {

                        for(int p=0; p<27; p++){
                            if(canPaint(p) && canPaint(paint, p)){
                                dp[row][paint] += dp[row-1][p];
                                dp[row][paint] %= MOD;
                            }
                        }

                    }
                }
            }

            int count = 0;
            for(int paint = 0; paint<27; paint++){
                count += dp[n-1][paint];
                count %= MOD;
            }
            return count;
        }
    }

}
