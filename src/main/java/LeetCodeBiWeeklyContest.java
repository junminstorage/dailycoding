import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCodeBiWeeklyContest {

    public static void main(String[] args) {
        System.out.println(findTheDistanceValue(new int[]{4,5,8}, new int[]{10,9,1,8}, 2));
        System.out.println(findTheDistanceValue(new int[]{1,4,2,3}, new int[]{-4,-3,6,10,20,30}, 3));
        System.out.println(findTheDistanceValue(new int[]{2,1,100,3}, new int[]{-5,-2,10,-3,7}, 6));
    }

    /*
 https://leetcode.com/contest/biweekly-contest-22/problems/find-the-distance-value-between-two-arrays/
  */
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int shift = 1_100;
        for(int i = 0; i<arr1.length; i++)
            arr1[i] +=shift;
        for(int i = 0; i<arr2.length; i++)
            arr2[i] +=shift;

        Map<Integer, Integer> min = new HashMap<>();
        Map<Integer, Integer> max = new HashMap<>();
        Set<Integer> store = new HashSet<>();

        for(int n : arr2) {
            int key = n/d;
            store.add(key);
            min.put(key, Math.min(min.getOrDefault(key, Integer.MAX_VALUE), n));
            max.put(key, Math.max(max.getOrDefault(key, Integer.MIN_VALUE), n));

            key = n/d-1;
            store.add(key);
            min.put(key, Math.min(min.getOrDefault(key, Integer.MAX_VALUE), n));
            max.put(key, Math.max(max.getOrDefault(key, Integer.MIN_VALUE), n));

            key = n/d + 1;
            store.add(key);
            min.put(key, Math.min(min.getOrDefault(key, Integer.MAX_VALUE), n));
            max.put(key, Math.max(max.getOrDefault(key, Integer.MIN_VALUE), n));

        }

        int counter = 0;
        for(int n : arr1) {
            int key = n/d;
            if(store.contains(key) && (Math.abs(n - min.get(key))<=d || Math.abs(n - max.get(key))<= d ) ) {
                continue;
            }else if (store.contains(key-1) && (Math.abs(n - min.get(key-1))<=d || Math.abs(n - max.get(key-1))<= d ) ) {
                continue;
            }else if (store.contains(key+1) && (Math.abs(n - min.get(key+1))<=d || Math.abs(n - max.get(key+1))<= d ) ) {
                continue;
            }
            counter++;
        }
        return counter;

    }

}
