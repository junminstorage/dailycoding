import java.util.*;

public class Problem13 {

    /**
     * his problem was asked by Amazon.
     *
     * Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
     *
     * For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
     */

    public static String longestSubStringWithDistinctChars(String s, int k) {
        if(s == null || s.length() == 0 || k<1)
            return s;

        int i = 0, j= 0, len=s.length();
        int counter = 0;
        Map<Character, Integer> map = new HashMap<>();

        String maxStr = "";

        while(i<len && j<len) {

            while(j<len && counter<=k){
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0)+1);
                if(map.get(c)==1)
                    counter++;
                j++;
            }

            if(maxStr.length() < j-i) {
                maxStr = s.substring(i, j);
            }

            while(i<len && counter>k) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0)-1);
                if(map.get(c) == 0)
                    counter--;
                i++;
            }
        }

        return maxStr;
    }
}
