import java.security.KeyException;
import java.util.*;

public class Problem67 {
  
  /**
   * This problem was asked by Google.

Implement an LFU (Least Frequently Used) cache. It should be able to be initialized with a cache size n, and contain the following methods:

set(key, value): sets key to value. If there are already n items in the cache and we are adding a new item, then it should also remove the least frequently used item. If there is a tie, then the least recently used key should be removed.
get(key): gets the value at key. If no such key exists, return null.
Each operation should run in O(1) time.
   * 
   */

  class Node {
    int key;
    int value;
    int freq;
    Node next;
    Node pre;

    Node(int k, int v, int f) {
      this.key = k;
      this.value = v;
      this.freq = f;
    }

    Node(int f) {
      this.freq = f;
    }
  }

  class LFU {
    //all nodes at the same frequecy form a double linked list with a dummy node whose next -> head and pre -> tail
    private final Map<Integer, Node> freqMap; // for each freq or key, its value is the dummy node, 
    private final Map<Integer, Node> map; //k -> Node
    private final int size;
    private int counter;

    LFU (int n) {
      size = n;
      freqMap = new LinkedHashMap<>();
      map = new HashMap<>();
      counter = 0;
    }

    public int get(int k) throws KeyException {
      if(map.containsKey(k)) {
        Node node = map.get(k);
        node.freq++;
        removeFromLinkedList(node);
        addToFreqMap(node);
        return node.value;
      }else
        throw new KeyException("not found");
    }

    public void put(int k, int v) {
      if(size == counter) {
        removeLFUEntry();
      }

      if(map.containsKey(k)) {
        Node node = map.get(k);
        node.value = v;
        node.freq++;
        //remove from current linkedlist
        removeFromLinkedList(node);
        //add to the tail of doubly link list with key of k = node.freq
        addToFreqMap(node);

      }else {
        Node node = new Node(k, v, 1);
        map.put(k, node);
        addToFreqMap(node);
      }

      counter++;

    }

    private void removeLFUEntry() {
      //remove the first non-empty key
      //this op is amortized O(1)
      //since for worse case we call get() SIZE times, before call put()
      for(int key : freqMap.keySet()) {
        if(freqMap.get(key).next != null) {
          removeFromLinkedList(freqMap.get(key).next);
        }
      }
    }

    private void removeFromLinkedList(Node node) {
      Node dummy = freqMap.get(node.key);
      if(node.pre == dummy) {
        dummy.next = dummy.pre = null;
      } else {
        node.pre.next = node.next;
        node.next.pre = node.pre;
      }
      node.pre = node.next = null;
    }

    private void addToFreqMap(Node node) {

      if(!freqMap.containsKey(node.freq)) {
        freqMap.put(node.freq, new Node(node.freq));
      }

      Node dummy = freqMap.get(node.freq);
      if(dummy.pre == null) {
        dummy.next = node;
        dummy.pre = node;
        node.next = dummy;
        node.pre = dummy;
      }else{
        dummy.pre.next = node;
        node.pre = dummy.pre;
        dummy.pre = node;
        node.next = dummy;
      }

    }
  }


}