package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class CampusBike {

    /**
     * 1057. Campus Bikes
     * On a campus represented as a 2D grid, there are Nworkers and Mbikes, with N <= M. Each worker and bike is
     * a 2D coordinate on this grid.
     * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike)
     * pair with the shortest Manhattan distance between each other, and assign the bike to that worker.
     * (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with
     * the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index).
     * We repeat this process until there are no available workers.
     * The Manhattan distance between two points p1and p2is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
     * Return a vector ansof length N, where ans[i]is the index (0-indexed) of the bike that the i-th worker is assigned to.
     *
     * Note:
     * 0 <= workers[i][j], bikes[i][j] < 1000
     * All worker and bike locations are distinct.
     * 1 <= workers.length <= bikes.length <= 1000
     */

    class Pair {
        int bike;
        int worker;
        public Pair(int b, int w) {
            this.bike = b;
            this.worker = w;
        }
    }

    class Bucket {
        Deque<Pair> queue;
        Bucket() {
            this.queue = new ArrayDeque<>();
        }
    }

    public int[] solutionBucketSort(int[][] bikes, int[][] workers) {
        int lenB = bikes.length, lenW = workers.length;
        assert lenB >= lenW;

        Bucket[] buckets = new Bucket[2001]; //generic array is not allowed: ArrayDeque<Pair>[] buckets = new ArrayDeque<>[2001]

        for(int w = 0; w<lenW; w++) { //start with order by worker index
            for(int b = 0; b<lenB; b++) { //then order by bike index
                int dist = dist(bikes[b], workers[w]);
                if(buckets[dist] == null)
                    buckets[dist] = new Bucket();
                buckets[dist].queue.offer(new Pair(b, w));
            }
        }

        boolean[] assigned = new boolean[lenW];
        int[] result = new int[lenW];
        for(int d = 0; d<2001; d++) {
            if(buckets[d]!=null){
                for(Pair pair : buckets[d].queue) {
                    if(!assigned[pair.worker])
                        result[pair.worker] = pair.bike;
                }
            }
        }
        return result;
    }

    public int[] solutionPQ(int[][] bikes, int[][] workers) {
        assert bikes.length >= workers.length;
        int lenB = bikes.length, lenW = workers.length;

        //O(B*W) space
        PriorityQueue<Pair> minQ = new PriorityQueue<>(lenB*lenW,
                Comparator.comparing((Pair p) -> dist(bikes[p.bike], workers[p.worker])).thenComparing(p -> p.worker).thenComparing(p -> p.bike));

        boolean[] assigned = new boolean[lenW];
        int[] assignments = new int[lenW];

        //O(B*W) Amortized time
        for(int b = 0; b<lenB; b++) {
            for(int w = 0; w<lenW; w++){
                minQ.offer(new Pair(b, w));
            }
        }

        //O(B*W(log(B*W)) worse case
        int total = 0;
        while(total < lenW) { //B*W
            Pair p = minQ.poll(); //log(B*W)
            if(!assigned[p.worker]){
                assignments[p.worker] = p.bike;
                total++;
            }
        }
        return assignments;
    }

    private int dist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
