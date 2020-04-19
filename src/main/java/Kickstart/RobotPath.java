package Kickstart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc8/00000000002d83dc

/**
 * Problem
Your country's space agency has just landed a rover on a new planet, which can be thought of as a grid of squares 
containing 10^9 columns (numbered starting from 1 from west to east) and 109 rows (numbered starting from 1 from north to south). 
Let (w, h) denote the square in the w-th column and the h-th row. The rover begins on the square (1, 1).

The rover can be maneuvered around on the surface of the planet by sending it a program, which is a string of characters 
representing movements in the four cardinal directions. The robot executes each character of the string in order:
N: Move one unit north.
S: Move one unit south.
E: Move one unit east.
W: Move one unit west.
There is also a special instruction X(Y), where X is a number between 2 and 9 inclusive and Y is a non-empty subprogram. 
This denotes that the robot should repeat the subprogram Y a total of X times. For example:
2(NWE) is equivalent to NWENWE.
3(S2(E)) is equivalent to SEESEESEE.
EEEE4(N)2(SS) is equivalent to EEEENNNNSSSS.

Since the planet is a torus, the first and last columns are adjacent, so moving east from column 109 will move the rover to column 
1 and moving south from row 109 will move the rover to row 1. Similarly, moving west from column 1 will move the rover to column 109 and moving north from row 1 will move the rover to row 109. Given a program that the robot will execute, 
determine the final position of the robot after it has finished all its movements.
 */
public class RobotPath {

  //"EEEE4(NW)2(SW)"
  public static int[] find(String input) {
    Deque<String> stack = new ArrayDeque<>();

    //to build the NESW
    StringBuilder sb = new StringBuilder();
    //to build the number
    StringBuilder sb2 = new StringBuilder();

    int len = input.length();
    int i = 0;
    while(i<len) {
      char c = input.charAt(i);
      if(c == 'S' || c == 'N' || c == 'E' || c == 'W'){
        sb.append(c);
      }else if ( c >= '1' && c <= '9'){ //expand the solution to handle any numbers like 11, 111
        if(sb.length() > 0) {
          stack.push(sb.toString());
          sb = new StringBuilder();
        }
        sb2.append(c);
      }else if ( c == '('){
        stack.push(sb2.toString());
        sb2 = new StringBuilder();
      }else if ( c == ')') {
        String temp = sb.toString();
        while(stack.peek().charAt(0) <'1' || stack.peek().charAt(0) >'9'){ //non 1-9
          temp = stack.pop() + temp;
        }
        int times = Integer.valueOf(stack.pop()); 
        while(times-->0)
          stack.push(temp);
        sb = new StringBuilder();
      }
      i++;
    }

    stack.push(sb.toString());

    int S = 0; int E = 0;
    for(String s : stack) {
      for(char c : s.toCharArray()){
        if(c == 'S')
          S++;
        if(c == 'N')
          S--;
        if(c == 'E')
          E++;
        if(c == 'W')
          E--;
      }
    }

    int mod = 1_000_000_000;
    int[] pos = new int[]{1, 1};
    E%=mod; S%=mod;
    if(E>0)
      pos[0] = E%mod + 1;
    else if (E<0) {
      pos[0] = mod + (1 + E);
    }

    if(S>0)
      pos[1] = S%mod + 1;
    else if (S< 0) {
      pos[1] = mod + (1 + S);
    }

    return pos;

  }

  public static void main(String[] args) {

    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      String str = in.next();
      int[] pos = find(str);
      System.out.println("Case #" + i + ": " + pos[0] + " " + pos[1]);
    }
  }

}