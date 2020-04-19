package LeetCode;

//https://leetcode.com/contest/weekly-contest-185/problems/minimum-number-of-frogs-croaking/
class Contest185 {

  static Map<Character, Character> store = new HashMap<>();
  static { 
      store.put('r', 'c');
      store.put('o', 'r');
      store.put('a', 'o');
      store.put('k', 'a');
  }
  
  public int minNumberOfFrogs(String croak) {
      
      Map<Character, Integer> map = new HashMap<>();
      
      int j = 0;
      int len = croak.length();
      
      int min = 0;
      int count = 0;
      while(j<len){
  
              char c = croak.charAt(j);
              map.put(c, map.getOrDefault(c, 0)+1);
          
              if(store.containsKey(c) && map.getOrDefault(store.get(c), 0) < map.get(c) ){
                  return -1;
              }
          
              if(c == 'c')
                  count++;
              if(c == 'k')
                  count--;
          
              min = Math.max(min, count);
              
         j++; 
          
      }
      
      int n = map.get('c');
      for(Character k : map.keySet()){
          if(map.get(k) != n)
             return -1;
      }
      
      return min;
      
  }
}