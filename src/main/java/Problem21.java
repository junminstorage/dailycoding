import java.util.Arrays;
import java.util.Comparator;

public class Problem21 {

    /**
     * This problem was asked by Snapchat.
     *
     * Given an array of time intervals (start, end) for classroom lectures
     * (possibly overlapping), find the minimum number of rooms required.
     *
     * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
     */

    static class Point {
        int v;
        boolean isStart;
        Point(int v, boolean flag) {
            this.v = v;
            this.isStart = flag;
        }

        int getV() {
            return v;
        }
    }
    public int roomRequire(int[][] intervals) {
        int counter = 0;

        Point[] points = new Point[intervals.length*2];
        int index = 0;
        for(int[] inter : intervals) {
            points[index++] = new Point(inter[0], true);
            points[index++] = new Point(inter[1], false);
        }

        Arrays.sort(points, Comparator.comparing(Point::getV));

        int max = 0;
        for(Point p : points) {
            if(p.isStart)
                counter++;
            else
                counter--;
            max = Math.max(max, counter);
        }
        return max;
    }
}
