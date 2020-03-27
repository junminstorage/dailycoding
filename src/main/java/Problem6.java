import java.util.*;

public class Problem6 {

    /*
    This problem was asked by Facebook.

    Given the mapping a = 1, b = 2, ... z = 26, and an encoded message,
    count the number of ways it can be decoded.
    For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

    You can assume that the messages are decodable. For example, '001' is not allowed.
     */
    public int countWaysDP(String number) {
        int len = number.length();
        if(len == 1)
            return 1;

        int p1 = 1, p2 = 1;
        if(len>=2){
            int num = Integer.parseInt(number.substring(0, 2));
            if(num<=26)
                p2 += 1;
        }

        int p = p2;
        for(int i=2; i<len; i++) {
            p = p2;
            int num = Integer.parseInt(number.substring(i-1, i+1));
            if(num<=26)
                p += p1;

            p1 = p2;
            p2 = p;
        }
        return p;
    }

    Map<Integer, Set<String>> store;
    public int countDecodeWays(String number) {
        store = new HashMap<>();
        return rec(number, 0).size();
    }

    private Set<String> rec(String number, int pos){
        int len = number.length();
        if(pos == len) {
            return new HashSet<>();
        }

        if(store.containsKey(pos))
            return store.get(pos);

        Set<String> result = new HashSet<>();

        for(String str : rec(number, pos+1)) {
            result.add( (char)(number.charAt(pos) -'1' + 'a') + str);
        }

        if(pos+2<=len) {
            int num = Integer.parseInt(number.substring(pos, pos + 2));
            if(num <= 26) {
                for(String str : rec(number, pos+2)) {
                    result.add( (char)(num-1 + 'a') + str);
                }
            }
        }
        store.put(pos, result);
        return result;
    }

}
