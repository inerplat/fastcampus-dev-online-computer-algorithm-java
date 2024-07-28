package practice28;

import java.util.*;

public class Main {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point baseP;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] points = new Point[n];
        int base = 0;
        for (int i = 0; i < n; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
            if (points[i].y < points[base].y || (points[i].y == points[base].y && points[i].x < points[base].x)) {
                base = i;
            }
        }
        Point temp = points[0];
        points[0] = points[base];
        points[base] = temp;
        baseP = points[0];

        Arrays.sort(points, 1, n, (a, b) -> compare(a, b));
        List<Point> hull = convexHull(points);
        System.out.println(n - hull.size());
    }

    static int compare(Point a, Point b) {
        int ccwValue = ccw(baseP, a, b);
        if (ccwValue == 0) {
            int distanceA = Math.abs(baseP.x - a.x) + Math.abs(baseP.y - a.y);
            int distanceB = Math.abs(baseP.x - b.x) + Math.abs(baseP.y - b.y);
            return distanceA - distanceB;
        }
        return -ccwValue;
    }

    public static List<Point> convexHull(Point[] points) {
        List<Point> lower = new ArrayList<>();
        for (Point p : points) {
            while (lower.size() >= 2 && ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }
        return lower;
    }

    private static int ccw(Point o, Point a, Point b) {
        return (o.x * a.y + a.x * b.y + b.x * o.y) - (o.y * a.x + a.y * b.x + b.y * o.x);
    }
}
