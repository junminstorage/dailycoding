package LeetCode;

class Contest185 {
  class Point {
      int index;
      boolean start;
      Point(int i, boolean s){
          index = i;
          start = s;
      }
  }
  
  static Map<Character, Character> store = new HashMap<>();
  static { 
      store.put('r', 'c');
      store.put('o', 'r');
      store.put('a', 'o');
      store.put('k', 'a');
  }
  
  public int minNumberOfFrogs(String croak) {

      List<Integer> c_pos = new ArrayList<>();
      List<Integer> k_pos = new ArrayList<>();
      
      Map<Character, Integer> map = new HashMap<>();
      
      int j = 0;
      int len = croak.length();
      while(j<len){
  
              char c = croak.charAt(j);
              map.put(c, map.getOrDefault(c, 0)+1);
          
              if(store.containsKey(c) && map.getOrDefault(store.get(c), 0) < map.get(c) ){
                  return -1;
              }
          
              if(c == 'c')
                  c_pos.add(j);
              if(c == 'k')
                  k_pos.add(j);
              
         j++; 
          
      }
      
      int n = map.get('c');
      for(Character k : map.keySet()){
          if(map.get(k) != n)
             return -1;
      }
      
      Point[] points = new Point[c_pos.size()*2];
      int i = 0;
      for(int c : c_pos){
          points[i++] = new Point(c, true);
      }
      for(int c: k_pos){
          points[i++] = new Point(c, false);
      }
      
      Arrays.sort(points, Comparator.comparing(p->p.index));
      
      int min = 0;
      int count = 0;
      for(Point p : points) {
          if(p.start)
              count++;
          else
              count--;
          min = Math.max(min, count);
      }
      
      return min;
      
  }
}