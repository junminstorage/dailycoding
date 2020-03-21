import java.util.*;

public class StringQ {

    public static void main(String[] args) {
        System.out.println(convertToBase10("2147483647", 10));
        System.out.println(convertToBase10("-2147483648", 10));
    }

    /*
     Parses the string argument as a signed integer in the radix
     * specified by the second argument. The characters in the string
     * must all be digits of the specified radix (as determined by
     * whether {@link java.lang.Character#digit(char, int)} returns a
     * nonnegative value), except that the first character may be an
     * ASCII minus sign {@code '-'} ({@code '\u005Cu002D'}) to
     * indicate a negative value or an ASCII plus sign {@code '+'}
     * ({@code '\u005Cu002B'}) to indicate a positive value. The
     * resulting integer value is returned.
     */
    public static int convertToBase10(String number, int base) {
        int i=0, result = 0, len =number.length();
        boolean positive = true;

        char first = number.charAt(0);
        if(first == '+' || first == '-'){
            i++;
            positive = first == '+'? true : false;

            if(len == 1)
                throw new NumberFormatException("For input string: \"" + number + "\"");
        }

        while(i<len) {
            char c = number.charAt(i);
            int value = -1;
            if(c>='0' && c<='9')
                value =  c - '0';
            else if(c>='A' && c<='Z')
                value = c - 'A';
            else if(c>='a' && c<= 'z')
                value = c - 'a';
            else
                throw new NumberFormatException("For input string: \"" + number + "\"");

            if(value >= base)
                throw new NumberFormatException("For input string: \"" + number + "\"");

            if(positive && result > (Integer.MAX_VALUE - value)/base )
                throw new NumberFormatException("For input string: \"" + number + "\"");

            if(!positive && result > value/base - Integer.MIN_VALUE/base)
                throw new NumberFormatException("For input string: \"" + number + "\"");

            result = result * base + value;
            i++;
        }
        return result;
    }


}
