package LeetCode;

import java.util.*;

public class WaysOfWearingHats {

  class Solution {
    
    int MOD = 1_000_000_007;
    int[][] dp;
    public int numberWays(List<List<Integer>> hats) {
        List<Set<Integer>> sets = new ArrayList<>();
        for(List<Integer> list: hats) {
            sets.add(new HashSet<>(list));
        }
        
        dp = new int[1<<hats.size()][41];
        for (int[] is : dp) { 
            for (int i = 0; i < is.length; i++) { 
                is[i] = -1; 
            } 
        } 
        
        return rec(0, 1, hats.size(), sets );
    }
    
    //bitmask 1010101, i-th bit at it denotes i-nth person wear hats or not
    private int rec(int mask, int hat, int n, List<Set<Integer>> hats) {
        if(mask == (1<<n) -1)
            return 1;
        
        if(hat == 41)
            return 0;
        
        if(dp[mask][hat]!=-1)
            return dp[mask][hat];
        
        //not wear this hat, skip it
        int count = rec(mask, hat+1, n, hats) % MOD;
        
        //wear it
        for (int p = 0; p<n; p++) {//for all persons n<=10 branching factor
            if ((mask & 1<<p) == 0) { //if p doesn't wear any hat
                if (hats.get(p).contains(hat)) { // and p can wear this hat 
                    count += rec(mask | (1<<p), hat+1, n, hats);
                    count %= MOD;
                } 
            }
        }
        dp[mask][hat] = count;
        return count;
    }
    

//     Map<Set<Integer>, Integer> store;
    
//     public int numberWays(List<List<Integer>> hats) {
//         store = new HashMap<>();
        
//         return rec(new HashSet<>(), new ArrayList<>(), hats, 0);
        
//     }
    
//     private int rec(Set<Integer> set, List<Integer> s, List<List<Integer>> hats, int person) {
//         if(person == hats.size()){
//             return 1;
//         }
        
//         if(store.containsKey(set)){
//             return store.get(set);
//         }
        
//         int count = 0, size = set.size();
        
//         //while(size-->0)
//         //    count *= size;
        
//         for(int hat : hats.get(person)){
//             if(!set.contains(hat)) {
//                 set.add(hat);
//                 //s.add(hat);
//                 count += rec(new HashSet(set), s, hats, person+1);
//                 set.remove(hat);
//                 //s.remove(s.size()-1);
//             }
//         }
        
//         store.put(set, count);
//         return count;
//     }
}

}