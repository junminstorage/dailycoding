
import java.util.*;

/**
 * This problem was asked by Quora.

Given a string, find the palindrome that can be made by inserting the fewest number of characters as possible anywhere in the word. 
If there is more than one palindrome of minimum length that can be made, return the lexicographically earliest one (the first one alphabetically).

For example, given the string "race", you should return "ecarace", since we can add three letters to it (which is the smallest
 amount to make a palindrome). There are seven other palindromes that can be made from "race" by adding three letters, 
 but "ecarace" comes first alphabetically.

As another example, given the string "google", you should return "elgoogle".
 * 
 * 
 */

public class Problem35 {

  static Map<List<Integer>, String> map = new HashMap<>();
  public static void main(final String[] args) {
    map = new HashMap<>();
    System.out.println(rec("google", 0, "google".length()-1));
    map = new HashMap<>();
    System.out.println(rec("race", 0, "race".length()-1));
    map = new HashMap<>();
    System.out.println(rec("facebook", 0, "facebook".length()-1));
  }

  
  public static String rec( final String str, final int left, final int right) {
    if (left > right)
      return "";
    if (left == right) {
      return str.substring(left, right + 1);
    }

    List<Integer> key = Arrays.asList(left, right);
    if (map.containsKey(key))
      return map.get(key);
    String result;
    final char l = str.charAt(left), r = str.charAt(right);
    if (l == r) {
      final String mid = rec(str, left + 1, right - 1);
      result = l + mid + r;
    } else {
      final String lResult = rec(str, left + 1, right);
      final String rResult = rec(str, left, right - 1);

      if (lResult.length() == rResult.length()) {
        if (l > r) {
          result = r + rResult + r;
        } else {
          result = l + lResult + l;
        }
      } else if (lResult.length() < rResult.length()) {
        result = l + lResult + l;
      } else {
        result = r + rResult + r;
      }

    }

    map.put(key, result);
    return result;

  }

}