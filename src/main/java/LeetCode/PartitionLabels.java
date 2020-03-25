package LeetCode;

/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int len = S.length();

        Map<Character, Integer> map = new HashMap<>(); //map letter to its last position in the string

        for(int i = 0; i<len; i++) {
            map.put(S.charAt(i), i);
        }

        List<Integer> result = new ArrayList<>();
        int p1 = 0;

        while(p1<len) {
            //max index p2 that p can move to
            int p2=map.get(S.charAt(p1));
            int p = p1;
            while(p<=p2) {
                //update the max index p2 that p can move to
                p2 = Math.max(p2, map.get(S.charAt(p)));
                p++;
            }
            //p1 - p2 is the partition
            result.add(p2-p1+1);
            p1 = p;
        }

        return result;

    }
}
